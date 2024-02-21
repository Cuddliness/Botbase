package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import care.cuddliness.stacy.repositories.UserRepository;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberJoinGuildListener extends ListenerAdapter {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event){

        StacyUser stacyUser = userRepository.findByUserIdAndGuildId(event.getMember().getIdLong(), event.getGuild().getIdLong());

        // Check if member exists otherwise insert
        if(stacyUser == null){
            userRepository.saveUserIfNotExists(UUID.randomUUID(), event.getMember().getIdLong(), event.getGuild()
                    .getIdLong());
        }


    }
}
