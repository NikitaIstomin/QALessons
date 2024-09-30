package main;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentMethods {

    private WebDriver driver;

    public void openHomePage() {
        driver.get("https://www.mts.by/");
    }

    public void acceptCookies() {
        try {
            WebElement cookiesButton = driver.findElement(By.id("cookie-agree"));
            cookiesButton.click();
        } catch (Exception ignored) {
        }
    }

    public WebElement getOnlineTopUpBlock() {
        return driver.findElement(By.className("pay__wrapper"));
    }

    public WebElement getPaymentPartners() {
        return driver.findElement(By.className("pay__partners"));
    }

    public void clickLearnMoreLink() {
        driver.findElement(By.linkText("Подробнее о сервисе")).click();
    }

    public void fillFormAndClickNext(String phoneNumber, String sum) throws InterruptedException {
        WebElement form = driver.findElement(By.id("pay-connection"));
        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        phoneField.sendKeys(phoneNumber);

        WebElement sumField = form.findElement(By.id("connection-sum"));
        sumField.sendKeys(sum);
        WebElement nextButton = driver.findElement(By.cssSelector(".button.button__default"));
        nextButton.click();
    }

    private By serviceLinck = By.linkText("Подробнее о сервисе");
    private By onlineTopUpBlock = By.className("pay__wrapper");
    private By paymentPartners = By.className("pay__partners");
    private By onlineTopUpHeader = By.tagName("h2");
    private By phoneFieldConnection = By.id("connection-phone");
    private By sumFieldConnection = By.id("connection-sum");
    private By emailFieldConnection = By.id("connection-email");
    private By continueButton = By.cssSelector(".button.button__default");  // Кнопка "Продолжить"
    private By phoneFieldInternet = By.id("internet-phone");
    private By sumFieldInternet = By.id("internet-sum");
    private By emailFieldInternet = By.id("internet-email");

    private By scoreFieldInstallment = By.id("score-instalment");
    private By sumFieldInstallment = By.id("instalment-sum");
    private By emailFieldInstallment = By.id("instalment-email");

    private By scoreFieldArrears = By.id("score-arrears");
    private By sumFieldArrears = By.id("arrears-sum");
    private By emailFieldArrears = By.id("arrears-email");

    private By paymentFrame = By.tagName("iframe");
    private By amountText = By.xpath("//div[contains(@class, 'pay-description__cost')]/span");
    private By phoneText = By.xpath("//div[contains(@class, 'pay-description__text')]/span");
    private By payButton = By.xpath("//button[contains(text(), 'Оплатить')]");

    public By cardNumberPlaceholder = By.xpath("//input[@formcontrolname='creditCard']");
    public By expirationDatePlaceholder = By.xpath("//input[@formcontrolname='expirationDate']");
    public By cvcPlaceholder = By.xpath("//input[@formcontrolname='cvc']");
    public By cardHolderPlaceholder = By.xpath("//input[@formcontrolname='holder']");

    public By visaIcon = By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']");
    public By mastercardIcon = By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']");
    public By belkartIcon = By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']");
    public By maestroIcon = By.xpath("//img[@src='assets/images/payment-icons/card-types/maestro-system.svg']");

    public PaymentMethods(WebDriver driver) {
        this.driver = driver;
    }
    public void focusLink(){
        driver.findElement(serviceLinck);
    }

    public void fillPhoneField(String phone) {
        driver.findElement(phoneFieldConnection).sendKeys(phone);
    }

    public void fillSumField(String sum) {
        driver.findElement(sumFieldConnection).sendKeys(sum);
    }

    public void fillEmailField(String email) {
        driver.findElement(emailFieldConnection).sendKeys(email);
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public void switchToPaymentFrame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ожидание до 10 секунд
        WebElement frameElement = wait.until(ExpectedConditions.presenceOfElementLocated(paymentFrame)); // Ждём, пока фрейм станет доступным
        driver.switchTo().frame(driver.findElement(paymentFrame));
    }

    public String getOnlineTopUpHeaderText() {
        WebElement block = driver.findElement(onlineTopUpBlock); // Ищем блок
        return block.findElement(onlineTopUpHeader).getText().trim(); // Ищем заголовок внутри блока и возвращаем текст
    }

    public String getAmountText() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(amountText))).getText();
    }

    public String getPhoneText() {
        return driver.findElement(phoneText).getText();
    }

    public String getPayButtonText() {
        return driver.findElement(payButton).getText();
    }

    public String getPlaceholder(By fieldLocator) {
        WebElement field = driver.findElement(fieldLocator);
        return field.getAttribute("placeholder");
    }

    public boolean isPaymentIconVisible(By iconLocator) {
        return driver.findElement(iconLocator).isDisplayed();
    }

    public boolean isLogoPresent(String logoAltText) {
        try {
            WebElement logoElement = getPaymentPartners().findElement(By.xpath(".//img[@alt='" + logoAltText + "']"));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPhonePlaceholderConnectionCorrect() {
        return getPlaceholder(phoneFieldConnection).equals("Номер телефона");
    }

    public boolean isSumPlaceholderConnectionCorrect() {
        return getPlaceholder(sumFieldConnection).equals("Сумма");
    }

    public boolean isEmailPlaceholderConnectionCorrect() {
        return getPlaceholder(emailFieldConnection).equals("E-mail для отправки чека");
    }

    public boolean isPhonePlaceholderInternetCorrect() {
        return getPlaceholder(phoneFieldInternet).equals("Номер абонента");
    }

    public boolean isSumPlaceholderInternetCorrect() {
        return getPlaceholder(sumFieldInternet).equals("Сумма");
    }

    public boolean isEmailPlaceholderInternetCorrect() {
        return getPlaceholder(emailFieldInternet).equals("E-mail для отправки чека");
    }

    public boolean isScorePlaceholderInstallmentCorrect() {
        return getPlaceholder(scoreFieldInstallment).equals("Номер счета на 44");
    }

    public boolean isSumPlaceholderInstallmentCorrect() {
        return getPlaceholder(sumFieldInstallment).equals("Сумма");
    }

    public boolean isEmailPlaceholderInstallmentCorrect() {
        return getPlaceholder(emailFieldInstallment).equals("E-mail для отправки чека");
    }

    public boolean isScorePlaceholderArrearsCorrect() {
        return getPlaceholder(scoreFieldArrears).equals("Номер счета на 2073");
    }

    public boolean isSumPlaceholderArrearsCorrect() {
        return getPlaceholder(sumFieldArrears).equals("Сумма");
    }

    public boolean isEmailPlaceholderArrearsCorrect() {
        return getPlaceholder(emailFieldArrears).equals("E-mail для отправки чека");
    }
}