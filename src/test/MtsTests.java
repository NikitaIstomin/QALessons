package test;
import main.PaymentMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class MtsTests {

    private WebDriver driver;
    private PaymentMethods paymentPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        paymentPage = new PaymentMethods(driver);

        driver.get("https://www.mts.by/");
        try {
            WebElement CookiesButton = driver.findElement(By.id("cookie-agree"));
            CookiesButton.click();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void blockAvailability() {
        String blockText = paymentPage.getOnlineTopUpBlock().findElement(By.tagName("h2")).getText().trim();
        Assert.assertTrue(blockText.contains("Онлайн пополнение"));
        Assert.assertTrue(blockText.contains("без комиссии"));
    }

    @Test
    public void logoAvailability() {
        String[] logos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String logo : logos) {
            WebElement logoElement = paymentPage.getPaymentPartners().findElement(By.xpath(".//img[@alt='" + logo + "']"));
            Assert.assertNotNull(logoElement, "Логотип " + logo + " не найден");
            Assert.assertTrue(logoElement.isDisplayed(), "Логотип " + logo + " не отображается на странице");
        }
    }

    @Test
    public void linkAvailability() {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        link.click();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL после клика по ссылке 'Подробнее о сервисе' не соответствует ожидаемому");
    }

    @Test
    public void nextButtonAvailability() throws InterruptedException {
         paymentPage.fillFormAndClickNext("297777777", "100");
        try {
            paymentPage.switchToPaymentFrame();
            // Добавляем ассерт, что фрейм доступен и переключение произошло
            Assert.assertTrue(true, "Фрейм оплаты успешно найден и переключение на него выполнено.");
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Не удалось найти фрейм оплаты после нажатия на кнопку 'Далее'.");
        }
    }

    @Test
    public void testPlaceholders() {

        // Проверка плейсхолдеров для "Услуги связи"
        Assert.assertTrue(paymentPage.isPhonePlaceholderConnectionCorrect(), "Плейсхолдер для номера телефона некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderConnectionCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderConnectionCorrect(), "Плейсхолдер для email некорректный.");

        // Проверка плейсхолдеров для "Домашнего интернета"
        Assert.assertTrue(paymentPage.isPhonePlaceholderInternetCorrect(), "Плейсхолдер для номера абонента некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderInternetCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderInternetCorrect(), "Плейсхолдер для email некорректный.");

        // Проверка плейсхолдеров для "Рассрочки"
        Assert.assertTrue(paymentPage.isScorePlaceholderInstallmentCorrect(), "Плейсхолдер для номера счета на 44 некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderInstallmentCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderInstallmentCorrect(), "Плейсхолдер для email некорректный.");

        // Проверка плейсхолдеров для "Задолженности"
        Assert.assertTrue(paymentPage.isScorePlaceholderArrearsCorrect(), "Плейсхолдер для номера счета на 2073 некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderArrearsCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderArrearsCorrect(), "Плейсхолдер для email некорректный.");
    }

    @Test
    public void testServicePaymentFlow() throws InterruptedException {
        //driver.get("https://www.mts.by/");

        // Заполнение полей для "Услуги связи"
        paymentPage.fillPhoneField("297777777");
        paymentPage.fillSumField("1");
        paymentPage.fillEmailField("test@example.com");

        // Нажатие на кнопку "Продолжить"
        paymentPage.clickContinueButton();

        // Переключение на фрейм оплаты
        paymentPage.switchToPaymentFrame();

        // Проверка корректности суммы
        Assert.assertEquals(paymentPage.getAmountText(), "1.00 BYN", "Сумма некорректна.");
        //Thread.sleep(10000);

        // Проверка корректности номера телефона
        Assert.assertTrue(paymentPage.getPhoneText().contains("375297777777"), "Номер телефона некорректен.");

        // Проверка текста на кнопке
        Assert.assertTrue(paymentPage.getPayButtonText().contains("Оплатить 1.00 BYN"), "Текст на кнопке некорректен.");

        // Проверка плейсхолдеров для реквизитов карты
        Assert.assertEquals(paymentPage.getPlaceholder(paymentPage.cardNumberPlaceholder), "Номер карты", "Плейсхолдер номера карты некорректен.");
        Assert.assertEquals(paymentPage.getPlaceholder(paymentPage.expirationDatePlaceholder), "Срок действия", "Плейсхолдер срока действия некорректен.");
        Assert.assertEquals(paymentPage.getPlaceholder(paymentPage.cvcPlaceholder), "CVC", "Плейсхолдер CVC некорректен.");
        Assert.assertEquals(paymentPage.getPlaceholder(paymentPage.cardHolderPlaceholder), "Имя держателя (как на карте)", "Плейсхолдер имени держателя некорректен.");

        // Проверка иконок платежных систем
        Assert.assertTrue(paymentPage.isPaymentIconVisible(paymentPage.visaIcon), "Иконка Visa не отображается.");
        Assert.assertTrue(paymentPage.isPaymentIconVisible(paymentPage.mastercardIcon), "Иконка Mastercard не отображается.");
        Assert.assertTrue(paymentPage.isPaymentIconVisible(paymentPage.belkartIcon), "Иконка Belkart не отображается.");
        Assert.assertTrue(paymentPage.isPaymentIconVisible(paymentPage.maestroIcon), "Иконка Maestro не отображается.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}