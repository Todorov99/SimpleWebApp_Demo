package signinloginpagesdemo.simplesignin.areas.users.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = isConfirmedValidator.class)
public @interface IsConfirmed {

    String message() default "Passwords Not Matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
