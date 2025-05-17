package Java.webapp;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Lazy;
import java.time.Duration;
import java.util.List;

@Route("main")
@PermitAll
@CssImport("MainView.css")
public class MainView extends VerticalLayout {
    Long[] prices = new Long[3];
    @Lazy
    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        Button load = new Button("Get Prices");
        add(load);
        load.addClickListener(event -> {
            remove(load);
            getPrices();
            String[] labels = {"Kaspi" ,"Technodom", "MobilePhones"};
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            long min = Long.MAX_VALUE;
            String minShop = null;
            long max = Long.MIN_VALUE;
            String maxShop = null;
            long sum = 0;
            int i = 0;
            for (Long price : prices) {
                if (price < min) {
                    min = price;
                    minShop = labels[i];
                }
                if (price > max) {
                    max = price;
                    maxShop = labels[i];
                }
                VerticalLayout layout = new VerticalLayout(new H1(labels[i]), new H1(price.toString()));
                i++;
                sum += price;
                horizontalLayout.add(layout);
            }
            add(horizontalLayout);
            Hr line = new Hr();
            line.getStyle()
                    .set("border", "none")
                    .set("height", "1px")
                    .set("background-color", "#ccc")
                    .set("width", "50%")
                    .set("margin-left", "auto")
                    .set("margin-right", "auto");

            add(line);
            add(new H1("The minimum price is " + min + " in " + minShop + "."));
            add(new H1("The maximum price is " + max + " in " + maxShop + "."));
            add(new H1("The average price is " + sum / prices.length + "."));
        });
    }
    private void getPrices(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://kaspi.kz/shop/p/apple-iphone-16e-128gb-chernyi-135186486/?c=750000000");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement closeButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("dialog__close"))
        );
        closeButton.click();
        List<WebElement> priceElementKaspi = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("item__price-once"))
        );
        String priceKaspi = priceElementKaspi.get(0).getText();
        priceKaspi  = priceKaspi.replaceAll("[^\\d.]", "");
        prices[0] = Long.valueOf(priceKaspi);

        driver.get("https://www.technodom.kz/p/smartfon-gsm-apple-iphone-16e-128gb-8-128-6-1-48-black-289076");
        List<WebElement> priceElementTechno = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".Typography.Typography__Heading.Typography__Heading_H1"))
        );
        String priceTechno = priceElementTechno.get(0).getText();
        priceTechno = priceTechno.replaceAll("[^\\d.]", "");
        prices[1] = Long.valueOf(priceTechno);

        driver.get("https://mobilephones.kz/p119986407-smartfon-apple-iphone.html");
        WebElement priceElementMob = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("b-product-cost__price")
        ));
        String priceMob = priceElementMob.getText();
        priceMob = priceMob.replaceAll("[^\\d.]", "");
        prices[2] = Long.valueOf(priceMob);
        driver.quit();
    }

}
