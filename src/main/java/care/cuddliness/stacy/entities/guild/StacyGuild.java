package care.cuddliness.stacy.entities.guild;

import jakarta.persistence.*;

@Entity
@Table(name = "guilds")
public class StacyGuild {


    @Id
    private Long id;
    public Long getId() {
        return id;
    }
    public String naughtyWords;
    public StacyGuild(){

    }
    public void setId(Long id) {
        this.id = id;
    }

    public static StacyGuild createGuild() {
        StacyGuild guild = new StacyGuild();
        StacyGuildModule module = new StacyGuildModule(guild.getId());
        return guild;
    }

    }
