package signinloginpagesdemo.simplesignin.areas.users.annotations;

import signinloginpagesdemo.simplesignin.areas.users.domain.models.binding.RegisterUserBinding;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isConfirmedValidator implements ConstraintValidator<IsConfirmed, RegisterUserBinding> {

    @Override
    public void initialize(IsConfirmed constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegisterUserBinding registerUserBinding, ConstraintValidatorContext constraintValidatorContext) {
//        if(userClass instanceof RegisterUserBinding){
//            return ((RegisterUserBinding) userClass).getPassword().equals(((RegisterUserBinding) userClass).getConfirmPassword());
//        }
        return registerUserBinding.getPassword().equals(registerUserBinding.getConfirmPassword());
    }
}
