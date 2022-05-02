import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем главную страницу github")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий ")
    public void searchForRepository() {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(GitHubTests.REPOSITORY);
        $(".header-search-input").submit();
    }

    @Step("Переход по репозиторию ")
    public void clickOnRepositoryLink() {
        $(linkText("eroshenkoam/allure-example")).click();
    }

    @Step("Клик по Issues")
    public void openIssuesTab() {
        $(partialLinkText("Issues")).click();
    }

    @Step("Проверка, что Issue с номером существует ")
    public void checkIssuesNumber() {
        $(withText(GitHubTests.ISSUE_NUMBER)).should(Condition.visible);
    }

}
