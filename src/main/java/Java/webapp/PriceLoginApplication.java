package Java.webapp;

import com.vaadin.flow.component.dependency.NpmPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication()
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
public class PriceLoginApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PriceLoginApplication.class, args);
	}

}
