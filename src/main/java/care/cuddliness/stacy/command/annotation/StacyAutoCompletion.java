package care.cuddliness.stacy.command.annotation;

import net.dv8tion.jda.api.entities.Role;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface StacyAutoCompletion {

    String[] auto();


}
