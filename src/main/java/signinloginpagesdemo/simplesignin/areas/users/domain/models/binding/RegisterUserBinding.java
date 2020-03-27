package signinloginpagesdemo.simplesignin.areas.users.domain.models.binding;

import signinloginpagesdemo.simplesignin.areas.users.annotations.IsConfirmed;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@IsConfirmed
public class RegisterUserBinding {

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,}$", message = "Invalid username.")
    @NotNull(message = "Username can not be null")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$", message = "Invalid password")
    @NotNull(message = "Password can not be empty.")
    private String password;

    private String confirmPassword;

    @Pattern(regexp = "[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Invalid email")
    @NotNull(message = "Email can not be empty.")
    private String email;

    public RegisterUserBinding() {
    }

    public RegisterUserBinding(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
