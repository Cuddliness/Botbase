package care.cuddliness.stacy.entities.guild;

import jakarta.persistence.*;

@Entity
@Table(name = "guild_modules")
public class StacyGuildModule {

    @Id
    private long id;
    @Column(nullable = false)
    private Boolean rules = false;
    private Boolean interviews = false;

    public StacyGuildModule(long id) {
        this.id = id;
    }

    public StacyGuildModule() {}

    public long getGuildId() {
        return id;
    }

    public void setGuildId(long guildId) {
        this.id = guildId;
    }

    public Boolean getRules() {
        return rules;
    }

    public void setRules(Boolean rules) {
        this.rules = rules;
    }

    public Boolean getInterviews() {
        return interviews;
    }

    public void setInterviews(Boolean interviews) {
        this.interviews = interviews;
    }
}
