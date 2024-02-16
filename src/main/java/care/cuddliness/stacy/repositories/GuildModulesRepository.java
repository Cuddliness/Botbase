package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildModulesRepository extends CrudRepository<StacyGuildModule, Long> {

    StacyGuildModule findById(long guildId);


}
