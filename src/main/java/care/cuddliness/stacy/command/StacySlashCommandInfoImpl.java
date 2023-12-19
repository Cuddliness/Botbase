package care.cuddliness.stacy.command;

import care.cuddliness.stacy.command.data.StacySlashCommandInfo;
import org.jetbrains.annotations.NotNull;

public record StacySlashCommandInfoImpl(String name, long id) implements StacySlashCommandInfo {
    @Override
    public long getId() {
        return 0;
    }

    @NotNull
    @Override
    public String getName() {
        return null;
    }
}
