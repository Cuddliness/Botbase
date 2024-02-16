package care.cuddliness.stacy.command.commands.setup.rules;

import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.entities.guild.StacyGuildRule;
import care.cuddliness.stacy.repositories.GuildRulesRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@StacySubCommandComponent(subCommandId = "previewrules",subCommandGroupid = "rules", parent = SetupCommand.class)

public class RulesShowCommand implements StacySubCommandInterface {
    @Autowired
    GuildRulesRepository rulesRepository;
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        if(rulesRepository.findByGuildId(event.getGuild().getIdLong()).isEmpty()){
            event.reply("There are no rules available for this server").setEphemeral(true).queue();
            return;
        }

        EmbedUtil embedUtil = new EmbedUtil(EmbedColor.PRIMARY);
        for(StacyGuildRule rule : rulesRepository.findByGuildId(event.getGuild().getIdLong())){
            embedUtil.addField(rule.getTitle(), rule.getContent(), false);
        }
        embedUtil.setThumbnail(event.getGuild().getIconUrl());
        event.replyEmbeds(embedUtil.build()).setEphemeral(true).queue();

    }
}
