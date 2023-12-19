package care.cuddliness.stacy.command;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.data.StacyCommandInterface;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public record StacyCommand(@NotNull StacyCommandInterface command, Map<String, StacySubCommand> subCommands, List<StacyCommandOption> options, String name) {

    public static @NotNull Map<String, StacySubCommand> computeSubCommands(@NotNull Collection<StacySubCommand> subCommands) {
        Map<String, StacySubCommand> map = new HashMap<>();

        for (StacySubCommand subCommand : subCommands) {
            if (!subCommand.subCommandId().isEmpty())
                map.put(subCommand.SubCommandGroup() + "/" + subCommand.subCommandId(), subCommand);
            else
                map.put(subCommand.subCommandId(), subCommand);
        }

        return map;
    }

    public CommandData createCommandData() {
        Map<String, StacySubCommand> map = new HashMap<>();
        List<SubcommandData> cmddata = new ArrayList<>();
        SlashCommandData commandData = Commands.slash(name, name);
        for (StacySubCommand subCommand : subCommands.values()) {
            SubcommandData data = new SubcommandData(subCommand.subCommandId(), "HII");

            subCommand.options().forEach(option -> data.addOption(option.t(), option.name(), option.descrip(),
                    false, option.auto()));
            cmddata.add(data);
            map.putIfAbsent(subCommand.subCommandId(), subCommand);

        }
        commandData.addSubcommands(cmddata);
        this.subCommands.putAll(map);
        return commandData;
    }

}
