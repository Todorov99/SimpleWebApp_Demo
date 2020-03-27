package signinloginpagesdemo.simplesignin.validator;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> violation(E entity);
}
