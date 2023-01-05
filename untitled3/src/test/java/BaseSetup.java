import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class BaseSetup {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    By s0=By.xpath("//android.view.View[@content-desc=\"Yemek Ekle\n" +
            "Tab 3 of 6\"]");
    By s1=By.xpath("//android.widget.Button[@content-desc=\"Yemek Ekle\"]");
    By s2=By.xpath("//android.widget.Button[@content-desc=\"Corba\"]");
    By s3=By.xpath("//android.view.View[@content-desc=\"Zeytinyağlılar\"]");
    By s4=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]");
    By s5=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]");
    By s6=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.CheckBox");
    By s7=By.xpath("//android.view.View[@content-desc=\"Salata\"]");
    By s8=By.xpath("//android.view.View[@content-desc=\"Menu\n" +
            "Tab 1 of 6\"]");
    By s9=By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText");
    By s10=By.xpath("//android.widget.Button[@content-desc=\"Otur\"]");
    By add1=By.xpath("(//android.widget.Button[@content-desc=\"Ekle\"])[1]");
    By add2=By.xpath("(//android.widget.Button[@content-desc=\"Ekle\"])[2]");
    By sepet=By.xpath("//android.view.View[@content-desc=\"Sepet\n" +
            "Tab 2 of 6\"]");
    By sil=By.xpath("(//android.widget.Button[@content-desc=\"Sil\"])[1]");
    By siparis =By.xpath("//android.widget.Button[@content-desc=\"Siparis Ver\"]");
    By son=By.xpath("//android.view.View[@content-desc=\"Geçmiş\n" +
            "Tab 6 of 6\"]");
    By pasif=By.xpath("//android.view.View[@content-desc=\"Yemek Pasife Al\n" +
            "Tab 5 of 6\"]");
    @BeforeMethod
    public void setup() throws MalformedURLException {
        // set desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", " Pixel 4 XL API 28");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.example.odev");
        caps.setCapability("appActivity", "com.example.odev.MainActivity");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("noReset", "false");

        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 30);
    }
    @Test

    public void test() throws InterruptedException {

        yemekekle();
        System.out.println("yemek eklendi");
        sepeteekleme();
        System.out.println("masaya oturuldu ve istenilen yemekler sepete eklendi");
        siparisver();
        System.out.println("Sepetteki yemeklerin sipari verildi");
        pasifeal();
        System.out.println("Secilen yemek pasife alindi");
        yemekaktif();

    }
    public void yemekekle(){
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement menuekle=driver.findElement(s0);
        menuekle.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //⦁	Bütün alanlar doldurulmadan yemek eklenemez.
        // Aksi halde “Lütfen geçerli alanları doldurun!” uyarısı verilmelidir.
        WebElement yemekekle = driver.findElement(s1);
        yemekekle.click();
        WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen geçerli alanları doldurun!\"]")));
        String expected = text.getAttribute("content-desc");
        assertEquals("Lütfen geçerli alanları doldurun!",expected);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement kategorisec= driver.findElement(s2);
        kategorisec.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement secim2= driver.findElement(s3);
        secim2.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Eklenecek yemeğin her kelimesinin ilk harfi büyük, geri kalan harfleri küçük olmak zorundadır
        // (Örn: Mercimek Çorbası). Aksi halde “Lütfen eklenecek yemek adını doğru girin!” uyarısı verilmelidir
        WebElement yemekyaz=driver.findElement(s4);
        yemekyaz.click();
        driver.getKeyboard().sendKeys("yaprak sarma");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement fiyatbutonu= driver.findElement(s5);
        fiyatbutonu.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.getKeyboard().sendKeys("25");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement onaybutonu= driver.findElement(s6);
        onaybutonu.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement text1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen eklenecek yemek adını doğru girin!\"]")));
        String expected1 = text1.getAttribute("content-desc");
        assertEquals("Lütfen eklenecek yemek adını doğru girin!",expected1);
        //⦁	Yemek adı Maksimum 20 karakter içermelidir.
        // Aksi halde “Lütfen daha kısa bir yemek adı girin!” uyarısı verilmelidir

        yemekyaz.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekyaz.sendKeys("Bol Limonlu Yaprak Sarma");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekyaz.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Basarılı yemek adı girisi
        yemekyaz.sendKeys("Yaprak Sarma");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        kategorisec.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement secim1= driver.findElement(s7);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        secim1.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement yemekyaz2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        yemekyaz2.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        yemekyaz2.clear();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        yemekyaz2.sendKeys("Mevsim Salata");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        fiyatbutonu.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fiyatbutonu.clear();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Yemek fiyatı textbox’una herhangi bir harf girilemez. Aksi halde
        //“Lütfen geçerli bir karakter girin!” uyarısı verilmelidir
        fiyatbutonu.sendKeys("k");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fiyatbutonu.clear();
        //⦁	Yemek fiyatı negatif olamaz. Aksi halde
        // “Lütfen yemek fiyatını pozitif sayı olarak girin!” uyarısı verilmelidir.
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fiyatbutonu.sendKeys("-5");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        WebElement text3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen yemek fiyatını pozitif sayı olarak girin!\"]")));
        String expected3 = text3.getAttribute("content-desc");
        assertEquals("Lütfen yemek fiyatını pozitif sayı olarak girin!",expected3);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fiyatbutonu.clear();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //⦁	Fiyat textbox’u maksimum 6 karakter alır.
        // Aksi halde “Lütfen 6 karakterden fazla girmeyin!” uyarısı verilmelidir.
        fiyatbutonu.sendKeys("1234567");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        yemekekle.click();
        WebElement text4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen 6 karakterden fazla girmeyin!\"]")));
        String expected4 = text4.getAttribute("content-desc");
        assertEquals("Lütfen 6 karakterden fazla girmeyin!",expected4);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        fiyatbutonu.clear();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Doğru fiyat girisi
        fiyatbutonu.sendKeys("24");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        yemekekle.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    public void sepeteekleme(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Masa numarası girilmeden sepete yemek eklenemez.
        // Aksi halde “Lütfen bir masaya oturun!” uyarısı verilmelidir.
        WebElement menubutton=driver.findElement(s8);
        menubutton.click();
        WebElement ekle1=driver.findElement(add1);
        ekle1.click();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        WebElement text5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen bir masaya oturun!\"]")));
        String expected5 = text5.getAttribute("content-desc");
        assertEquals("Lütfen bir masaya oturun!",expected5);
        //	Masa numarası alanına harf girilemez.
        // Aksi halde “Lütfen geçerli bir karakter girin!” uyarısı verilmelidir.
        WebElement masabutton= driver.findElement(s9);
        masabutton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        masabutton.sendKeys("a"); //hata
        WebElement oturbutton=driver.findElement(s10);
        oturbutton.click();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        masabutton.click();
        masabutton.clear();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        //Masa numarası 1 ve 200 arasında olmalıdır.
        // Aksi halde “Lütfen geçerli bir masa numarası seçin!” uyarısı verilmelidir.
        masabutton.sendKeys("250");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        oturbutton.click();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        WebElement text7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Lütfen geçerli bir masa numarası seçin!\"]")));
        String expected7 = text7.getAttribute("content-desc");
        assertEquals("Lütfen geçerli bir masa numarası seçin!",expected7);
        masabutton.clear();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        // Basarili masa numarası girisi
        masabutton.sendKeys("12");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        oturbutton.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ekle1.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement ekle2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//android.widget.Button[@content-desc=\"Ekle\"])[2]")));
        ekle2.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    public void siparisver() {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement sepetbutton = driver.findElement(sepet);
        sepetbutton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement silbutton = driver.findElement(sil);
        silbutton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement siparisbutton = driver.findElement(siparis);
        siparisbutton.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement ad=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")));
        ad.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ad.sendKeys("Yusra");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement kart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[2]")));
        kart.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        kart.sendKeys("1234567891011123");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement ay=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[3]")));
        ay.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ay.sendKeys("1024");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement cvv=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[4]")));
        cvv.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        cvv.sendKeys("698");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement odeme=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Ödeme Yap\"]")));
        odeme.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }
    public void pasifeal() {
        WebElement sonsiparis=driver.findElement(son);
        sonsiparis.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement pasifbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Yemek Pasife Al\n" +
                "Tab 4 of 6\"]")));
        pasifbutton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement pasifkategori=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Yaprak Sarma\"]")));
        pasifkategori.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement pasifsecimi=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Mevsim Salata\"]")));
        pasifsecimi.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement yonteciadi=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText")));
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        yonteciadi.click();
        //⦁	Yemek pasife alacak yönetici kendi adını ilk harfleri büyük olarak girmek zorundadır.
        // Aksi halde “Lütfen yönetici adını doğru şekilde girin!” uyarısı verilmelidir.
        yonteciadi.sendKeys("abd alrahim");
        WebElement pasifonay=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.CheckBox")));
        pasifonay.click();
        WebElement pasifalonay=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Pasife Al\"]")));
        pasifalonay.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        yonteciadi.sendKeys("Abd Alrahim");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        pasifalonay.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        pasifonay.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        pasifalonay.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    public void yemekaktif(){
        WebElement menu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Menu\n" +
                "Tab 1 of 6\"]")));
        menu.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement aktifbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@content-desc=\"Yemek Aktif Et\n" +
                "Tab 5 of 6\"]")));
        aktifbutton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement secim=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Mevsim Salata\"]")));
        secim.click();
        secim.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement ad=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText")));
        ad.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        ad.sendKeys("Abd Alrahim");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement onay=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.CheckBox")));
        onay.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement aktifet=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@content-desc=\"Aktife Al\"]")));
        aktifet.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
