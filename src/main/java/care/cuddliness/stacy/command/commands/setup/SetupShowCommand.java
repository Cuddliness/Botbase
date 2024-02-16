package care.cuddliness.stacy.command.commands.setup;

import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.guild.StacyGuildValues;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@StacySubCommandComponent(subCommandId = "show", parent = SetupCommand.class)
public class SetupShowCommand implements StacySubCommandInterface {
    @Autowired
    GuildChannelsRepository guildChannelsRepository;
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        List<StacyGuildValues> channels = guildChannelsRepository.findByGuildId(event.getGuild().getIdLong());
        StringBuilder channelsString = new StringBuilder();
        if(channels == null){
            channelsString.append("No channels have been set-up");
        }else{
            for(StacyGuildValues channel : channels){
                channelsString.append(channel.getName() + " | <#"  + channel.getValueId() +  "> | " + channel.getValueId());
            }

            EmbedUtil embed = new EmbedUtil();
            embed.addField("Channels", channelsString.toString(), false);
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
        }


    }
}
