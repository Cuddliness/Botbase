package care.cuddliness.stacy.entities.modules;

import care.cuddliness.stacy.entities.guild.StacyGuild;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "interview_question")
@Entity
public class StacyInterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    private Long guildId;
    private String title;
    private String description;

    public StacyInterviewQuestion() {
    }

    public StacyInterviewQuestion(long guildId, String title, String description) {
        this.guildId = guildId;
        this.title = title;
        this.description = description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
