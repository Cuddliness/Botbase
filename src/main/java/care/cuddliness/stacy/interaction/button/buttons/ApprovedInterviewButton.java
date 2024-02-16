package care.cuddliness.stacy.interaction.button.buttons;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.interaction.button.annotation.StacyButtonComponent;
import care.cuddliness.stacy.interaction.button.data.ButtonExecutorInterface;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import care.cuddliness.stacy.repositories.UserRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import care.cuddliness.stacy.values.ChannelValues;
import care.cuddliness.stacy.values.RoleValues;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

@StacyButtonComponent(name = "approve_interview", style = ButtonStyle.SUCCESS)

public class ApprovedInterviewButton implements ButtonExecutorInterface {
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

        StacyUser user = userRepository.findByUserIdAndGuildIdGuildId(parsedLong, event.getGuild().getIdLong());
        user.setApplicationId(event.getMessageIdLong());
        event.getGuild().addRoleToMember(Objects.requireNonNull(event.getGuild().getMemberById(parsedLong)),
                RoleValues.INTERVIEWED.getRole(repository, event.getGuild())).queue();

        EmbedUtil editedEmbed = new EmbedUtil();
        editedEmbed.from(embed);
        editedEmbed.setFooter("Approved by " + event.getUser().getName());
        editedEmbed.setTimeStamp(new Date().toInstant());
        editedEmbed.setColor(EmbedColor.SUCCESS);

        event.editMessageEmbeds(editedEmbed.build()).setComponents().queue();

    }
}
