package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.entities.guild.StacyGuildRule;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildRulesRepository extends CrudRepository<StacyGuildRule, Long> {

    List<StacyGuildRule> findByGuildId(long guildId);
    List<StacyGuildRule> findByGuildIdAndTitle(long guildId, String title);
    @Transactional
    long deleteByGuildIdAndTitle(long guildId,String title);
    List<StacyGuildModule> findById(long id);


}
