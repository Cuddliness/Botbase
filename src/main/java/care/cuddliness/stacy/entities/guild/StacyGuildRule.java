package care.cuddliness.stacy.entities.guild;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "guild_rules")
@Entity
public class StacyGuildRule {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private Long guildId;
    private String title;
    @Column(nullable = true)
    private String emoji;
    private String content;

    public StacyGuildRule(){}
    public StacyGuildRule(Long guildId, String title, String content) {
        this.guildId = guildId;
        this.title = title;
        this.content = content;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

    public Long getGuildId() {
        return guildId;
    }

    public void setGuildId(Long guildId) {
        this.guildId = guildId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
