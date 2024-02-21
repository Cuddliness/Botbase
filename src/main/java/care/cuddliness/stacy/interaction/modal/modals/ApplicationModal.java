package care.cuddliness.stacy.interaction.modal.modals;

import care.cuddliness.stacy.interaction.modal.annotation.StacyModalComponent;
import care.cuddliness.stacy.interaction.modal.data.ModalExecutorInterface;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import care.cuddliness.stacy.utils.EmbedColor;
import care.cuddliness.stacy.utils.EmbedUtil;
import care.cuddliness.stacy.utils.InterviewRate;
import care.cuddliness.stacy.values.ChannelValues;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
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

        InterviewRate rate = new InterviewRate();
        List<String> words = new ArrayList<>();
        event.getValues().forEach(modalMapping -> words.add(modalMapping.getAsString()));
        TextChannel channel = ChannelValues.INTERVIEW_LOGS.getChannel(repository, Objects.requireNonNull(event.getGuild()));
        if(!rate.rate(words)) {
            channel.sendMessageEmbeds(embed.build()).setActionRow(Button.success("approve_interview",
                            net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+1F44D")),
                    Button.danger("deny_interview", net.dv8tion.jda.api.entities.emoji.Emoji.fromUnicode("U+1F44E"))).queue();
            event.reply("Interview has been sent").queue();
        }else{
            embed.setFooter("Kicked by Stacy reason: Naughty words");
            embed.setColor(EmbedColor.WARNING);
            channel.sendMessageEmbeds(embed.build()).queue();
            event.reply("Interview has been sent").queue();

        }
    }
}
