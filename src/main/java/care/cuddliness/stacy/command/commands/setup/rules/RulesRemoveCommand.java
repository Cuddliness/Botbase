package care.cuddliness.stacy.command.commands.setup.rules;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.command.data.AutoCompletableInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.repositories.GuildRulesRepository;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StacySubCommandComponent(subCommandId = "removerule",subCommandGroupid = "rules", parent = SetupCommand.class)
@StacyCommandOption(name = "title", t = OptionType.STRING, descrip = "Remove a role by title", auto = true, required = true)

public class RulesRemoveCommand implements StacySubCommandInterface, AutoCompletableInterface {
    @Autowired
    GuildRulesRepository rulesRepository;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        if(rulesRepository.findByGuildId(event.getGuild().getIdLong()).isEmpty()){
            event.reply("There are no rules available for this server").setEphemeral(true).queue();
        }

        String title = event.getOption("title").getAsString();
        rulesRepository.deleteByGuildIdAndTitle(event.getGuild().getIdLong(), title);
        event.reply("Removed rule with title: " + title).setEphemeral(true).queue();
    }

    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        List<String> title = new ArrayList<>();


       rulesRepository.findByGuildId(event.getGuild().getIdLong()).forEach(stacyGuildRule ->
               title.add(stacyGuildRule.getTitle()));

        if(event.getFocusedOption().getName().equals("title")){
            List<Command.Choice> options = Stream.of(title.toArray(new String[title.size()]))
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
                    .map(word -> new Command.Choice(word, word))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }

        }
}
