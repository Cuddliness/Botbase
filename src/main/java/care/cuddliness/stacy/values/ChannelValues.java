package care.cuddliness.stacy.values;

import care.cuddliness.stacy.entities.guild.StacyGuildValues;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;

public enum ChannelValues {

    RULES("rules"), WELCOME("welcome"),INTERVIEW_LOGS("interview_logs");

    private String name;

    ChannelValues(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void log(GuildChannelsRepository repo, Guild guild, String message){

    }
    public void logEmbed(GuildChannelsRepository repo, Guild guild, MessageEmbed embed){
        StacyGuildValues channel = repo.findByGuildIdAndName(guild.getIdLong(), getName());
        guild.getTextChannelById(channel.getValueId()).sendMessageEmbeds(embed).queue();
    }
    @NotNull
    public TextChannel getChannel(GuildChannelsRepository repo, Guild guild){
        StacyGuildValues channel = repo.findByGuildIdAndName(guild.getIdLong(), getName());
        return guild.getTextChannelById(channel.getValueId());
    }
}
