package care.cuddliness.stacy.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "guild_options")
public class GuildOptions {

    @EmbeddedId
    private StacyGuildId id;
    private String name;
    private Boolean enabled;


}
