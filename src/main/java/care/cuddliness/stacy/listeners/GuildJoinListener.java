package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.entities.guild.StacyGuild;
import care.cuddliness.stacy.repositories.GuildModulesRepository;
import care.cuddliness.stacy.repositories.GuildRepositoryInterface;
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
    private GuildModulesRepository guildModulesRepository ;

    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(MemberJoinGuildListener.class);
    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        if(guildRepositoryInterface.findById(event.getGuild().getIdLong()) == null) {
            StacyGuild guild = new StacyGuild();
            StacyGuildModule modules = new StacyGuildModule();
            modules.setGuildId(event.getGuild().getIdLong());
            guild.setId(event.getGuild().getIdLong());
            guildRepositoryInterface.save(guild);
            guildModulesRepository.save(modules);
            LOGGER.info("Added new guild: " + event.getGuild().getIdLong());
        }
    }
}
