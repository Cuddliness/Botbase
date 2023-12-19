package care.cuddliness.stacy.command.data;

import org.jetbrains.annotations.NotNull;

public interface StacySlashCommandInfo {

    long getId();

    @NotNull String getName();
}
