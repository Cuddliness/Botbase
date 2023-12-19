package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.StacyGuild;
import care.cuddliness.stacy.interfaces.GuildRepositoryInterface;
import care.cuddliness.stacy.interfaces.UserRepositoryInterface;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuildJoinListener extends ListenerAdapter {

    @Autowired
    private GuildRepositoryInterface guildRepositoryInterface ;
    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(MemberJoinGuildListener.class);
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        if(guildRepositoryInterface.findById(event.getGuild().getIdLong()) == null) {
            StacyGuild guild = new StacyGuild();
            guild.setId(event.getGuild().getIdLong());
            guildRepositoryInterface.save(guild);
            LOGGER.info("Added new guild: " + event.getGuild().getIdLong());
        }
    }
}
