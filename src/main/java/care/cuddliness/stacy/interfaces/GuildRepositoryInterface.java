package care.cuddliness.stacy.interfaces;

import care.cuddliness.stacy.entities.StacyGuild;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildRepositoryInterface extends CrudRepository <StacyGuild, Long> {

    List<StacyGuild> findById(long id);
}
