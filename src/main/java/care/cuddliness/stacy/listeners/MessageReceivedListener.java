package care.cuddliness.stacy.listeners;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.repositories.UserRepository;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageReceivedListener extends ListenerAdapter {
    @Autowired
    UserRepository userRepository;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().split(" ").length < 3){
            return;
        }
        StacyUser user = userRepository.findByUserIdAndGuildId(event.getMember().getIdLong(), event.getGuild().getIdLong());
        user.setMessageCount(user.getMessageCount() + 1);
        userRepository.save(user);

    }
}
