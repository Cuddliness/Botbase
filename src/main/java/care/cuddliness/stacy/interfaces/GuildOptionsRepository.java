package care.cuddliness.stacy.interfaces;

import care.cuddliness.stacy.entities.GuildOptions;
import care.cuddliness.stacy.entities.StacyGuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildOptionsRepository extends CrudRepository<GuildOptions, Long> {

    List<GuildOptions> findById(long id);


}
