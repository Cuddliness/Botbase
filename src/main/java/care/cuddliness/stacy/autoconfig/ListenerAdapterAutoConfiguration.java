package care.cuddliness.stacy.autoconfig;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

@AutoConfiguration
@AutoConfigureAfter(JdaAutoConfiguration.class)
public class ListenerAdapterAutoConfiguration {

    public ListenerAdapterAutoConfiguration(@NotNull ListenerAdapter[] listenerAdapters, JDA jda) {

        for (ListenerAdapter listenerAdapter : listenerAdapters) {
            System.out.println(listenerAdapter.getClass());
            jda.addEventListener(listenerAdapter);
        }
    }
}
