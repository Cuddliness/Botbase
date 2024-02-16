package care.cuddliness.stacy.command.commands.setup.modules;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.AutoCompletableInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.repositories.GuildModulesRepository;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StacySubCommandComponent(subCommandId = "setmodule", subCommandGroupid = "modules", parent = SetupCommand.class)
@StacyCommandOption(name = "module", descrip = "The module to change", t = OptionType.STRING, required = true, auto = true)
@StacyCommandOption(name = "toggle", descrip = "True or false ?", t = OptionType.BOOLEAN, required = true)

public class ModulesSetCommand implements StacySubCommandInterface, AutoCompletableInterface {
    @Autowired
    GuildModulesRepository modulesRepository;
    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        String module = event.getOption("module").getAsString().toUpperCase();
        boolean toggle = event.getOption("toggle").getAsBoolean();

        StacyGuildModule stacyGuildModule = modulesRepository.findById(event.getGuild().getIdLong());
        switch (module) {
            case "RULES" -> {
                stacyGuildModule.setRules(toggle);
                modulesRepository.save(stacyGuildModule);
            }
            case "INTERVIEWS" -> {
                stacyGuildModule.setInterviews(toggle);
                modulesRepository.save(stacyGuildModule);
            }
        }
        event.reply("Updated: ``" + module+ "`` to: ``" + toggle + "``").setEphemeral(true).queue();
    }

    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        String[] modules = new String[]{"rules", "interviews"};
        if (event.getFocusedOption().getName().equals("module")) {
            List<Command.Choice> options = Stream.of(modules)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
                    .map(word -> new Command.Choice(word, word))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();

        }
    }
}
