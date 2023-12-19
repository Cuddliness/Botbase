package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.StacyGuildId;
import care.cuddliness.stacy.entities.StacyUser;
import care.cuddliness.stacy.interfaces.UserRepositoryInterface;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.GatewayPingEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BotReadyListener extends ListenerAdapter {
    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    private boolean updated = false;
    @Override
    public void onGatewayPing(GatewayPingEvent event) {
        if(!updated){
            System.out.println("I'm here");
            for(Guild g : event.getJDA().getGuilds()){
                for(Member m : g.getMembers()){
                    if(!m.getUser().isBot() && !m.getUser().isSystem()){
                        StacyUser stacyUser = userRepositoryInterface.findByUserIdAndGuildIdGuildId(m.getUser().getIdLong(), g.getIdLong());
                        if(stacyUser == null) {
                                System.out.println("Found new user: " + m.getEffectiveName());
                                StacyUser user = new StacyUser();
                                user.setUserId(m.getIdLong());
                                user.setId(UUID.randomUUID());
                                StacyGuildId guildId = new StacyGuildId();
                                guildId.setGuildId(g.getIdLong());

                                user.setGuildId(guildId);
                                userRepositoryInterface.save(user);
                            }
                    }
                    this.updated = true;

                }
            }
        }
        }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("I'm here");
        for(Guild g : event.getJDA().getGuilds()){
            for(Member m : g.getMembers()){
                if(userRepositoryInterface.findByGuildIdGuildId(g.getIdLong()).stream().
                        noneMatch(stacyUser -> stacyUser.getUserId() == m.getIdLong())){
                    System.out.println("Found new user: " + m.getEffectiveName());
                    StacyUser user = new StacyUser();
                    StacyGuildId guildId = new StacyGuildId();
                    guildId.setGuildId(g.getIdLong());

                    user.setGuildId(guildId);
                    userRepositoryInterface.save(user);

                }
            }
        }
    }
}
