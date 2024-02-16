package care.cuddliness.stacy.interaction.button.buttons;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.interaction.button.annotation.StacyButtonComponent;
import care.cuddliness.stacy.interaction.button.data.ButtonExecutorInterface;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import care.cuddliness.stacy.repositories.UserRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import care.cuddliness.stacy.values.RoleValues;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

@StacyButtonComponent(name = "deny_interview", style = ButtonStyle.DANGER)

public class DenyInterviewButton implements ButtonExecutorInterface {
    @Autowired
    GuildChannelsRepository repository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void onExecute(Member clicker, ButtonInteractionEvent event) {

        MessageEmbed embed = event.getMessage().getEmbeds().get(0);

        //Parse user long id with given description
        long parsedLong = Long.parseLong(Objects.requireNonNull(embed.getDescription()).replace("Interview of ", "").
                replace("<", "").replace("@", "")
                .replace(">", ""));

        EmbedUtil editedEmbed = new EmbedUtil();
        editedEmbed.from(embed);
        editedEmbed.setFooter("Denied by " + event.getUser().getName());
        editedEmbed.setTimeStamp(new Date().toInstant());
        editedEmbed.setColor(EmbedColor.WARNING);

        //TODO: Tempban user here

        event.editMessageEmbeds(editedEmbed.build()).setComponents().queue();

    }
}
