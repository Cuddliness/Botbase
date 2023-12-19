package care.cuddliness.stacy.command.commands.guild;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.StacyGuildChannel;
import care.cuddliness.stacy.interfaces.GuildChannelsRepository;
import care.cuddliness.stacy.utils.EmbedUtil;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
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
        List<StacyGuildChannel> channels = guildChannelsRepository.findByIdGuildId(event.getGuild().getIdLong());
        StringBuilder channelsString = new StringBuilder();
        if(channels == null){
            channelsString.append("No channels have been set-up");
        }else{
            for(StacyGuildChannel channel : channels){
                channelsString.append(channel.getName() + " | <#"  + channel.getChannelId() +  "> | " + channel.getChannelId());
            }

            EmbedUtil embed = new EmbedUtil();
            embed.addField("Channels", channelsString.toString(), false);
            event.replyEmbeds(embed.build()).setEphemeral(true).queue();
        }


    }
}
