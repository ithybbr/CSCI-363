package Java.webapp;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
public class SecurityConfiguration extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http
                .securityContext(context -> context
                        .securityContextRepository(new HttpSessionSecurityContextRepository()) // Store authentication in session
                );

        setLoginView(http, LoginView.class);
    }
}
