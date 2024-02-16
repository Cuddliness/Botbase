package care.cuddliness.stacy.command.commands.user;

import care.cuddliness.stacy.command.annotation.StacyCommandComponent;
import care.cuddliness.stacy.command.data.StacyCommandInterface;
import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.repositories.UserRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@StacyCommandComponent(name = "profile")
public class UserProfileCommand implements StacyCommandInterface {
    @Autowired
    UserRepository userRepository;

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
}
