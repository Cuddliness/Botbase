package care.cuddliness.stacy.command.commands.setup.rules;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.entities.guild.StacyGuildRule;
import care.cuddliness.stacy.repositories.GuildRulesRepository;
import care.cuddliness.stacy.utils.Emoji;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@StacySubCommandComponent(subCommandId = "addrule",subCommandGroupid = "rules", parent = SetupCommand.class)
@StacyCommandOption(name = "name", t = OptionType.STRING, descrip = "Name of the rule", required = true)
@StacyCommandOption(name = "content", t = OptionType.STRING, descrip = "Content of the string", required = true)
@StacyCommandOption(name = "emoji", t = OptionType.STRING, descrip = "Emoji displayed with rule")

public class RulesAddSubCommand implements StacySubCommandInterface {
    @Autowired
    GuildRulesRepository rulesRepository;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        String nameString = event.getOption("name").getAsString();
        String contentString = event.getOption("content").getAsString();
        String emojiString = event.getOption("emoji").getAsString();

        //Check if rule already exists
        if(!rulesRepository.findByGuildIdAndTitle(event.getGuild().getIdLong(), nameString).isEmpty()){
            event.reply("This rule already exists").setEphemeral(true).queue();
            return;
        }

        StacyGuildRule rule = new StacyGuildRule(event.getGuild().getIdLong(), nameString, contentString);
        if(!emojiString.isEmpty())
            rule.setEmoji(new Emoji(event.getJDA()).emoteToMarkdown(emojiString));
        System.out.println(new Emoji(event.getJDA()).emoteToMarkdown(emojiString));
        rulesRepository.save(rule);
        event.reply("Added new rule with title: " + nameString).setEphemeral(true).queue();




    }
}
