package care.cuddliness.stacy.command.commands.guild;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.AutoCompletableInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.StacyGuildChannel;
import care.cuddliness.stacy.entities.StacyGuildId;
import care.cuddliness.stacy.interfaces.GuildChannelsRepository;
import care.cuddliness.stacy.interfaces.UserRepositoryInterface;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StacySubCommandComponent(subCommandId = "channel", parent = SetupCommand.class)
@StacyCommandOption(name = "channel_setup", auto = true, t = OptionType.STRING, descrip = "Select which you want to setup")
@StacyCommandOption(name = "channel", auto = false, t = OptionType.CHANNEL, descrip = "Select which you want to setup")
public class SetupChannelSubCommand implements StacySubCommandInterface, AutoCompletableInterface {
    @Autowired
    private GuildChannelsRepository guildChannelsRepository ;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        String channelSetup = event.getOption("channel_setup").getAsString();
        Channel channelId = event.getOption("channel").getAsChannel();

        StacyGuildChannel channel = new StacyGuildChannel();
        StacyGuildId guildId = new StacyGuildId();
        guildId.setGuildId(event.getGuild().getIdLong());
        channel.setChannelId(channelId.getIdLong());
        channel.setName(channelSetup);
        channel.setId(guildId);
        guildChannelsRepository.save(channel);

        event.reply("Linked " + channelSetup + " With channel " + channelId.getAsMention()).setEphemeral(true).queue();

    }


    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        String[] channels = new String[]{"rules"};
        if(event.getFocusedOption().getName().equals("channel_setup")){
            List<Command.Choice> options = Stream.of(channels)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(word -> new Command.Choice(word, word)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
