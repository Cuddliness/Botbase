package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.guild.StacyGuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildRepositoryInterface extends CrudRepository <StacyGuild, Long> {

    StacyGuild findById(long id);
}
