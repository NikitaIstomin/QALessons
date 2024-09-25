package test;
import main.PaymentMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

public class MtsTests {

    private WebDriver driver;
    private PaymentMethods paymentPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        paymentPage = new PaymentMethods(driver);

        paymentPage.openHomePage();
        paymentPage.acceptCookies();
    }

    @Test
    @DisplayName ("Проверяем наличие текста Онлайн пополнение без комиссии")
    public void blockAvailability() {
        String blockText = paymentPage.getOnlineTopUpHeaderText();
        Assert.assertTrue(blockText.contains("Онлайн пополнение"), "Текст 'Онлайн пополнение' не найден в блоке");
        Assert.assertTrue(blockText.contains("без комиссии"), "Текст 'без комиссии' не найден в блоке");
    }

    @Test
    @DisplayName ("Проверяем наличие логотипов платежных систем")
    public void logoAvailability() {
        String[] logos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String logo : logos) {
            boolean isPresent = paymentPage.isLogoPresent(logo);
            Assert.assertTrue(isPresent, "Логотип " + logo + " не найден или не отображается на странице");
        }
    }

    @Test
    @DisplayName("Проверяем работу ссылки 'Подробнее о сервисе'")
    public void linkAvailability() {
        paymentPage.clickLearnMoreLink();
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL после клика по ссылке 'Подробнее о сервисе' не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверяем работу кнопки продолжить")
    public void nextButtonAvailability() throws InterruptedException {
         paymentPage.fillFormAndClickNext("297777777", "100");
        try {
            paymentPage.switchToPaymentFrame();
            Assert.assertTrue(true, "Фрейм оплаты успешно найден и переключение на него выполнено.");
        } catch (NoSuchElementException | TimeoutException e) {
            Assert.fail("Не удалось найти фрейм оплаты после нажатия на кнопку 'Далее'.");
        }
    }

    @Test
    @DisplayName("Проверяем надписи в полях каждого варианта оплаты услуг")
    public void testPlaceholders() {
        // Услуги связи
        Assert.assertTrue(paymentPage.isPhonePlaceholderConnectionCorrect(), "Плейсхолдер для номера телефона некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderConnectionCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderConnectionCorrect(), "Плейсхолдер для email некорректный.");
        // Домашний интернет
        Assert.assertTrue(paymentPage.isPhonePlaceholderInternetCorrect(), "Плейсхолдер для номера абонента некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderInternetCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderInternetCorrect(), "Плейсхолдер для email некорректный.");
        // Рассрочка
        Assert.assertTrue(paymentPage.isScorePlaceholderInstallmentCorrect(), "Плейсхолдер для номера счета на 44 некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderInstallmentCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderInstallmentCorrect(), "Плейсхолдер для email некорректный.");
        // Задолженность
        Assert.assertTrue(paymentPage.isScorePlaceholderArrearsCorrect(), "Плейсхолдер для номера счета на 2073 некорректный.");
        Assert.assertTrue(paymentPage.isSumPlaceholderArrearsCorrect(), "Плейсхолдер для суммы некорректный.");
        Assert.assertTrue(paymentPage.isEmailPlaceholderArrearsCorrect(), "Плейсхолдер для email некорректный.");
    }

    @Test
    @DisplayName("Проверяем корректность данных и надписи фрейма")
    public void testServicePaymentFlow() throws InterruptedException {
        //driver.get("https://www.mts.by/");
        paymentPage.fillPhoneField("297777777");
        paymentPage.fillSumField("1");
        paymentPage.fillEmailField("test@example.com");

        paymentPage.clickContinueButton();
        paymentPage.switchToPaymentFrame();
        //Thread.sleep(10000);
        Assert.assertEquals(paymentPage.getAmountText(), "1.00 BYN", "Сумма некорректна.");
        Assert.assertTrue(paymentPage.getPhoneText().contains("375297777777"), "Номер телефона некорректен.");
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