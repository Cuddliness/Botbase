package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<StacyUser, StacyGuildId> {

    StacyUser findByUserIdAndGuildIdGuildId(long id, long guildId);

    List<StacyUser> findByGuildIdGuildId(long guildId);


}
