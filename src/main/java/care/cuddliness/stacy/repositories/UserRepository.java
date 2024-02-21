package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.user.StacyUser;
import care.cuddliness.stacy.entities.guild.StacyGuildId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<StacyUser, UUID> {

    StacyUser findByUserIdAndGuildId(long id, long guildId);

    List<StacyUser> findByGuildId(long guildId);


    @Modifying
    @Query(value = "INSERT IGNORE INTO users (id, user_id, guild_id) VALUES (:id, :userId, :guildId)", nativeQuery = true)
    @Transactional
    void saveUserIfNotExists(@Param("id") UUID uuid, @Param("userId") long id, @Param("guildId") long guildId);


}
