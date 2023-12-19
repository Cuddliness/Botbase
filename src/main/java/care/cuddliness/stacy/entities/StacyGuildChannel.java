package care.cuddliness.stacy.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "guild_channels")
@Entity
public class StacyGuildChannel {

    @EmbeddedId
    private StacyGuildId id;
    private String name;
    private Long channelId;

    public StacyGuildId getId() {
        return id;
    }

    public void setId(StacyGuildId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }
}
