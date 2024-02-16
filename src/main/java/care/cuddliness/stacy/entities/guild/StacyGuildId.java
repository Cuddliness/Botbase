package care.cuddliness.stacy.entities.guild;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StacyGuildId implements Serializable {

    private long guildId;


    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }
}
