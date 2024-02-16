package care.cuddliness.stacy.command.commands.setup.interview;

import care.cuddliness.stacy.command.annotation.StacyCommandOption;
import care.cuddliness.stacy.command.annotation.StacySubCommandComponent;
import care.cuddliness.stacy.command.data.StacySubCommandInterface;
import care.cuddliness.stacy.entities.modules.StacyInterviewQuestion;
import care.cuddliness.stacy.command.commands.setup.SetupCommand;
import care.cuddliness.stacy.repositories.InterviewQuestionRepository;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@StacySubCommandComponent(subCommandId = "addquestion", subCommandGroupid = "interview", parent = SetupCommand.class)
@StacyCommandOption(name = "title", t = OptionType.STRING, descrip = "Title of question", required = true)
@StacyCommandOption(name = "description", t = OptionType.STRING, descrip = "Content of the question", required = true)
public class InterviewAddCommand implements StacySubCommandInterface {
        @Autowired
        private InterviewQuestionRepository repository;

        @Override
        public void onExecute(@NotNull Member sender, @NotNull SlashCommandInteractionEvent event) {
            OptionMapping optionTitle = event.getOption("title");
            OptionMapping optionDescription = event.getOption("description");
            List<StacyInterviewQuestion> questions = repository.findStacyInterviewQuestionByGuildId(event.getIdLong());
            if(questions.stream().anyMatch(stacyInterviewQuestion -> stacyInterviewQuestion.getTitle().
                    equalsIgnoreCase(optionTitle.getAsString()))){
                event.reply("A question with the name: " + optionTitle.getAsString() + " already exists").
                        setEphemeral(true).queue();
            }else{
                StacyInterviewQuestion question = new StacyInterviewQuestion(event.getGuild().getIdLong(),
                        optionTitle.getAsString(), optionDescription.getAsString());
                repository.save(question);
                event.reply("A question with the name: " + optionTitle.getAsString() + " has been created").
                        setEphemeral(true).queue();
            }


        }
}
