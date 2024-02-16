package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import care.cuddliness.stacy.repositories.UserRepository;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberJoinGuildListener extends ListenerAdapter {
    @Autowired
    private UserRepository userRepository ;

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event){

        StacyUser stacyUser = userRepository.findByUserIdAndGuildIdGuildId(event.getMember().getIdLong(), event.getGuild().getIdLong());


        // Check if member exists
        if(stacyUser == null){
            StacyUser user =  new StacyUser();
            StacyGuildId stacyUserId = new StacyGuildId();

            stacyUserId.setGuildId(event.getGuild().getIdLong());

            user.setUserId(event.getMember().getIdLong());
            user.setGuildId(stacyUserId);

            userRepository.save(user);
        }


    }
}
