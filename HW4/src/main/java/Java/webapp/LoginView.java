package Java.webapp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.servlet.http.HttpSession;

@Route("login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    //no usages new*
    private final AuthService authService;
    public LoginView(AuthService authService) {
        this.authService = authService;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        var loginForm = new LoginForm();
        loginForm.setForgotPasswordButtonVisible(false);
        loginForm.addLoginListener(event -> {
            try {
                VaadinServletRequest vaadinRequest = (VaadinServletRequest) VaadinService.getCurrentRequest();
                HttpSession httpSession = vaadinRequest.getHttpServletRequest().getSession();
                this.authService.authenticate(event.getUsername(), event.getPassword(), httpSession);
                UI.getCurrent().navigate(MainView.class);
            }
            catch (Exception e) {
                Notification.show("Wrong username or password");
                event.unregisterListener();
                loginForm.onEnabledStateChanged(true);
            }
        });
        var registerButton = new Button("Register", event -> UI.getCurrent().navigate("register"));
        registerButton.addClassName("register-inside");
        Div wrapper = new Div();
        wrapper.addClassName("login-wrapper");
        wrapper.add(loginForm, registerButton);
        add(wrapper);
    }
}
