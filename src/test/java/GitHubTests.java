import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class GitHubTests {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }
    public static final String REPOSITORY = "eroshenkoam/allure-example";
    public static final String ISSUE_NUMBER = "#68";

    @Test
    void testGitHubPageWithListner(){
       SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(linkText(REPOSITORY)).click();
        $(partialLinkText("Issues")).click();
        $(withText("#76")).should(Condition.visible);
    }



    @Test
    void testGitHubPageWithLambdaSteps() {

        step("Открываем главную страницу github", ()->{
            open("https://github.com");
        });

        step("Ищем репозиторий " + REPOSITORY, ()->{
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Переход по репозиторию " + REPOSITORY, ()->{
            $(linkText("eroshenkoam/allure-example")).click();
        });

        step("Клик по Issues", ()->{
            $(partialLinkText("Issues")).click();
        });

        step("Проверка, что Issue с номером существует " + ISSUE_NUMBER, ()->{
            $(withText(ISSUE_NUMBER)).should(Condition.visible);
        });
    }

    @Test
    void testGitHubPageWithSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository();
        steps.clickOnRepositoryLink();
        steps.openIssuesTab();
        steps.checkIssuesNumber();
    }
}
