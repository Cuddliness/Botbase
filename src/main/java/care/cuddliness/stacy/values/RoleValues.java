package care.cuddliness.stacy.values;

import care.cuddliness.stacy.entities.guild.StacyGuildValues;
import care.cuddliness.stacy.repositories.GuildChannelsRepository;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.jetbrains.annotations.NotNull;

public enum RoleValues {

    INTERVIEWED("interviewed_role");

    private String name;

    RoleValues(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @NotNull
    public Role getRole(GuildChannelsRepository repo, Guild guild){
        StacyGuildValues value = repo.findByGuildIdAndName(guild.getIdLong(), getName());
        return guild.getRoleById(value.getValueId());
    }
}
