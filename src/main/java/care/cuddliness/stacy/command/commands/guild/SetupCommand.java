package care.cuddliness.stacy.command.commands.guild;

import care.cuddliness.stacy.command.annotation.StacyCommandComponent;
import care.cuddliness.stacy.command.data.StacyCommandInterface;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

@StacyCommandComponent(name = "setup")

public class SetupCommand implements StacyCommandInterface {
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {

    }

    @NotNull
    @Override
    public CommandData createCommandData() {
        return Commands.slash("setup", "Setup commands");
    }
}
