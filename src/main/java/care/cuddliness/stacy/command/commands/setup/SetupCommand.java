package care.cuddliness.stacy.command.commands.setup;

import care.cuddliness.stacy.command.annotation.StacyCommandComponent;
import care.cuddliness.stacy.command.data.StacyCommandInterface;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

@StacyCommandComponent(name = "setup")

public class SetupCommand implements StacyCommandInterface {
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {

    }

}
