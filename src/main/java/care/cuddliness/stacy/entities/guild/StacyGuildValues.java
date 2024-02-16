package care.cuddliness.stacy.entities.guild;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "guild_values")
@Entity
@Getter @Setter
public class StacyGuildValues {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    private Long guildId;
    private String name;
    private Long valueId;

}
