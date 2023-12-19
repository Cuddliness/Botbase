package care.cuddliness.stacy.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class StacyUser {


    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private Long userId;
    private Integer messageCount = 0;
    private StacyGuildId guildId;

    public UUID getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public StacyGuildId getGuildId() {
        return guildId;
    }

    public void setGuildId(StacyGuildId guildId) {
        this.guildId = guildId;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}
