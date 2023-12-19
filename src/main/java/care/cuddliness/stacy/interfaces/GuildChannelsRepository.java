package care.cuddliness.stacy.interfaces;

import care.cuddliness.stacy.entities.StacyGuild;
import care.cuddliness.stacy.entities.StacyGuildChannel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuildChannelsRepository extends CrudRepository<StacyGuildChannel, Long> {


    List<StacyGuildChannel> findByIdGuildId(long id);
}
