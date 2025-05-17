package Java.webapp;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {
    private final AuthService authService;

    public RegisterView(AuthService authService) {
        this.authService = authService;
        setSizeFull();
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");
        Button registerButton = new Button("Register", event -> register(usernameField, passwordField, confirmPasswordField));

        RouterLink loginLink = new RouterLink("Already have an account? Login", LoginView.class);
        Div wrapper = new Div();
        wrapper.addClassName("register-wrapper");
        loginLink.addClassName("login-link");
        wrapper.add(usernameField, passwordField, confirmPasswordField, registerButton, loginLink);
        add(wrapper);
    }

    private void register(TextField usernameField, PasswordField passwordField, PasswordField confirmPasswordField) {
        String username = usernameField.getValue().trim();
        String password = passwordField.getValue();
        String confirmPassword = confirmPasswordField.getValue();

        if (username.isEmpty() || password.isEmpty()) {
            Notification.show("Username and password cannot be empty.", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            Notification.show("Passwords do not match.", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (!authService.UsernameAvailable(username)) {
            Notification.show("Username already taken.", 3000, Notification.Position.MIDDLE);
            return;
        }

        authService.registerUser(username, password);
        Notification.show("Registration successful!", 3000, Notification.Position.MIDDLE);
        getUI().ifPresent(ui -> ui.navigate(LoginView.class));
    }
}
