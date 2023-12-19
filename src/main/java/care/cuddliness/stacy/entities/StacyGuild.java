package care.cuddliness.stacy.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "guilds")
public class StacyGuild {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
