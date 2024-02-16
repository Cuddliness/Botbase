package care.cuddliness.stacy.interaction.modal.modals;

import care.cuddliness.stacy.interaction.modal.annotation.StacyModalComponent;
import care.cuddliness.stacy.interaction.modal.data.ModalExecutorInterface;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import care.cuddliness.stacy.values.ChannelValues;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@StacyModalComponent(name = "application")
public class ApplicationModal implements ModalExecutorInterface {
    @Autowired
    GuildChannelsRepository repository;

    @Override
    public void onExecute(Member sender, ModalInteractionEvent event) {
        EmbedUtil embed = new EmbedUtil(EmbedColor.PRIMARY);
        embed.setTitle("New application");
        embed.setDescription("Interview of " + event.getUser().getAsMention());
        embed.setThumbnail(Objects.requireNonNull(event.getUser().getAvatarUrl()));
        event.getValues().forEach(modalMapping -> embed.addField(modalMapping.getId(), modalMapping.getAsString(), false));
        TextChannel channel = ChannelValues.INTERVIEW_LOGS.getChannel(repository, Objects.requireNonNull(event.getGuild()));
        channel.sendMessageEmbeds(embed.build()).setActionRow(Button.success("approve_interview",
                net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+1F44D")),
                Button.danger("deny_interview", net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+1F44E"))).queue();

        event.reply(channel.getAsMention()).setEphemeral(true).queue();
    }
}
