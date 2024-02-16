package care.cuddliness.stacy.command.commands.setup.modules;

import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.repositories.GuildModulesRepository;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@StacySubCommandComponent(subCommandId = "preview", subCommandGroupid = "modules", parent = SetupCommand.class)
public class ModulesPreviewCommand implements StacySubCommandInterface {
    @Autowired
    GuildModulesRepository modulesRepository;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        StacyGuildModule stacyGuildModule = modulesRepository.findById(event.getGuild().getIdLong());
        if(stacyGuildModule != null){
            EmbedUtil embedUtil = new EmbedUtil();
            StringBuilder builder = new StringBuilder();
            embedUtil.setTitle("Guild modules");
            builder.append((stacyGuildModule.getRules()) ? ":green_circle: " : ":red_circle: ").append("Rules : ").
                    append("``").append(stacyGuildModule.getRules()).append("``");

            embedUtil.addField("Modules", builder.toString(), true);
            event.replyEmbeds(embedUtil.build()).setEphemeral(true).queue();
        }
    }
}
