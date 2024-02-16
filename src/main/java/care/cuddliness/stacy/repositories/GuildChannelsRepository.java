package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.guild.StacyGuildValues;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildChannelsRepository extends CrudRepository<StacyGuildValues, Long> {


    List<StacyGuildValues> findByGuildId(long id);
    StacyGuildValues findByGuildIdAndName(long guildId, String name);
}
