package care.cuddliness.stacy.command.commands.setup.interview;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.command.data.AutoCompletableInterface;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.repositories.InterviewQuestionRepository;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@StacySubCommandComponent(subCommandId = "removequestion", subCommandGroupid = "interview", parent = SetupCommand.class)
@StacyCommandOption(name = "title", t = OptionType.STRING, descrip = "Title of question", required = true, auto = true)

public class InterviewRemoveCommand implements StacySubCommandInterface, AutoCompletableInterface {
    @Autowired
    private InterviewQuestionRepository repository;

    @Override
    public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
        String title = event.getOption("title").getAsString();
        repository.deleteByGuildIdAndTitle(event.getGuild().getIdLong(), title);
        event.reply("Removed rule with title: " + title).setEphemeral(true).queue();
    }

    @Override
    public void onAutoComplete(CommandAutoCompleteInteractionEvent event) {
        List<String> title = new ArrayList<>();
        repository.findStacyInterviewQuestionByGuildId(event.getGuild().getIdLong()).forEach(question ->
                title.add(question.getTitle()));
        if(event.getFocusedOption().getName().equals("title")){
            List<Command.Choice> options = Stream.of(title.toArray(new String[title.size()]))
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
                    .map(word -> new Command.Choice(word, word))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
