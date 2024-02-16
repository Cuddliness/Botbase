package care.cuddliness.stacy.entities.user;

import care.cuddliness.stacy.entities.guild.StacyGuildId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
public class StacyUser {


    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Getter @Setter private UUID id;
    @Getter @Setter private Long userId;
    @Getter @Setter private Integer messageCount = 0;
    @Getter @Setter private StacyGuildId guildId;
    @Getter @Setter private Long applicationId;

    public StacyUser(Long longId){}
    public StacyUser(){}
}
