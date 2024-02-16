package care.cuddliness.stacy.command.commands.setup;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.AutoCompletableInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.guild.StacyGuildValues;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StacySubCommandComponent(subCommandId = "value", parent = SetupCommand.class)
@StacyCommandOption(name = "channel_setup", auto = true, t = OptionType.STRING, descrip = "Select which you want to setup")
@StacyCommandOption(name = "channel", auto = false, t = OptionType.CHANNEL, descrip = "Select which you want to setup")
@StacyCommandOption(name = "role", auto = false, t = OptionType.ROLE, descrip = "Select which role you want to setup")

public class SetupChannelSubCommand implements StacySubCommandInterface, AutoCompletableInterface {
    @Autowired
    private GuildChannelsRepository guildChannelsRepository ;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        String valueSetup = event.getOption("channel_setup").getAsString();

        if(event.getOption("role")!= null){
            Role role = event.getOption("role").getAsRole();

            StacyGuildValues value = new StacyGuildValues();
            StacyGuildId guildId = new StacyGuildId();
            guildId.setGuildId(event.getGuild().getIdLong());
            value.setValueId(role.getIdLong());
            value.setGuildId(event.getGuild().getIdLong());
            value.setName(valueSetup);

            guildChannelsRepository.save(value);
            event.reply("Linked " + valueSetup + " With role " + role.getAsMention()).setEphemeral(true).queue();

        }
        if(event.getOption("channel") != null) {
            Channel channel = event.getOption("channel").getAsChannel();
            StacyGuildValues value = new StacyGuildValues();
            StacyGuildId guildId = new StacyGuildId();
            guildId.setGuildId(event.getGuild().getIdLong());
            value.setValueId(channel.getIdLong());
            value.setName(valueSetup);
            value.setGuildId(event.getGuild().getIdLong());
            guildChannelsRepository.save(value);

            event.reply("Linked " + valueSetup + " With channel " + channel.getAsMention()).setEphemeral(true).queue();
        }
    }


    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        String[] channels = new String[]{"rules", "welcome", "interview_logs", "interviewed_role"};
        if(event.getFocusedOption().getName().equals("channel_setup")){
            List<Command.Choice> options = Stream.of(channels)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue())) // only display words that start with the user's current input
                    .map(word -> new Command.Choice(word, word)) // map the words to choices
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
