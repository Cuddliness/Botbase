package care.cuddliness.stacy.repositories;

import care.cuddliness.stacy.entities.guild.StacyGuildModule;
import care.cuddliness.stacy.entities.guild.StacyGuildRule;
import care.cuddliness.stacy.entities.modules.StacyInterviewQuestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterviewQuestionRepository extends CrudRepository<StacyInterviewQuestion, Long> {

    long deleteByGuildIdAndTitle(long guildId, String title);

    List<StacyInterviewQuestion> findStacyInterviewQuestionByGuildId(Long guildId);



}
