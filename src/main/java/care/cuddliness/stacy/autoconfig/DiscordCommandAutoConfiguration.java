package care.cuddliness.stacy.autoconfig;

import care.cuddliness.stacy.command.StacyCommandHandler;
import care.cuddliness.stacy.ReadyEvents;
import care.cuddliness.stacy.repositories.*;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@RequiredArgsConstructor
public class DiscordCommandAutoConfiguration {
    private final ApplicationContext applicationContext;
    private final UserRepository userRepositoryInterface;
    private final GuildRepositoryInterface guildRepositoryInterface;
    private final GuildModulesRepository guildModulesRepository;
    private final GuildChannelsRepository guildChannelsRepository;
    private final GuildRulesRepository guildRulesRepository;

    private final JDA jda;

    @Bean
    @ConditionalOnMissingBean
    public StacyCommandHandler discordCommandBackend() {
        Guild guild = this.jda.getGuildById(-1L);
        return new StacyCommandHandler(this.applicationContext, this.jda, guild);
    }

    @Bean
    @ConditionalOnMissingBean
    public ReadyEvents discordBotBackend() {
        return new ReadyEvents(jda, userRepositoryInterface, guildRepositoryInterface, guildModulesRepository,
                guildChannelsRepository, guildRulesRepository);
    }

}
