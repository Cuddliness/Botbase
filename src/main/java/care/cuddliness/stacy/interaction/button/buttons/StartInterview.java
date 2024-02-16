package care.cuddliness.stacy.interaction.button.buttons;

import care.cuddliness.stacy.entities.modules.StacyInterviewQuestion;
import care.cuddliness.stacy.interaction.InteractionHandler;
import care.cuddliness.stacy.interaction.button.annotation.StacyButtonComponent;
import care.cuddliness.stacy.interaction.button.data.ButtonExecutorInterface;
import care.cuddliness.stacy.repositories.InterviewQuestionRepository;
import care.cuddliness.stacy.utils.ModalBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.springframework.beans.factory.annotation.Autowired;


@StacyButtonComponent(name = "start_interview", style = ButtonStyle.SUCCESS)
public class StartInterview implements ButtonExecutorInterface {
    @Autowired
    private InterviewQuestionRepository repository;
    @Override
    public void onExecute(Member clicker, ButtonInteractionEvent event) {
        ModalBuilder builder = new ModalBuilder();
        repository.findStacyInterviewQuestionByGuildId(event.getGuild().getIdLong()).forEach(question -> {
            builder.addQuestion(question.getTitle().toLowerCase(), question.getDescription(), "Bwa?", TextInputStyle.PARAGRAPH);

        });
        event.replyModal(builder.build("application")).queue();
    }
}
