package care.cuddliness.stacy.command.commands.user;

import care.cuddliness.stacy.command.annotation.StacyCommandComponent;
import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.data.StacyCommandInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.StacyUser;
import care.cuddliness.stacy.interfaces.UserRepositoryInterface;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@StacyCommandComponent(name = "profile")
public class ProfileCommand implements StacyCommandInterface {
    @Autowired
    UserRepositoryInterface userRepository;
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        StacyUser user = userRepository.findByUserIdAndGuildIdGuildId(event.getMember().getIdLong(), event.getGuild().getIdLong());
        EmbedUtil embed = new EmbedUtil(EmbedColor.PRIMARY)
                .setThumbnail(event.getUser().getEffectiveAvatarUrl())
                .setTitle("Profile card of " + event.getUser().getName() + " |  " + event.getGuild().getName())
                .addField("Top Role", event.getMember().getRoles().get(0).getAsMention(), false)
                .addField("Name", event.getUser().getName(), false)
                .addField("Message count", user.getMessageCount() + " ", false);

        event.replyEmbeds(embed.build()).queue();
    }

    @NotNull
    @Override
    public CommandData createCommandData() {
        return Commands.slash("profile", "Profile command");
    }
}
