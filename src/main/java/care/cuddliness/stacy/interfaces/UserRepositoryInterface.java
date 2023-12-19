package care.cuddliness.stacy.interfaces;

import care.cuddliness.stacy.entities.StacyUser;
import care.cuddliness.stacy.entities.StacyGuildId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends CrudRepository<StacyUser, StacyGuildId> {

    StacyUser findByUserIdAndGuildIdGuildId(long id, long guildId);

    List<StacyUser> findByGuildIdGuildId(long guildId);


}
