package care.cuddliness.stacy.command.data;

import care.cuddliness.stacy.command.StacyCommand;
import jakarta.annotation.Nonnull;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface StacyCommandInterface extends StacyCommandExecutorInterface {

    @Nonnull
    CommandData createCommandData();
}
