package tests;

import utilities.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Test extends Setup {


    @org.testng.annotations.Test
    public void porscheTest() throws InterruptedException {

        //3. Select model 718
        driver.findElement(By.xpath("//a[@class='b-teaser-link'][@href='/usa/modelstart/all/?modelrange=718']")).click();

        //4. Remember the price of 718 Cayman Model S
        String priceOfCaymanModelS = driver.findElement(By.xpath("//div[@id='m982130']//div[@class='m-14-model-price']"))
                .getText().substring(6, 13).trim();

        //5. Click on 718 Cayman S
        driver.findElement(By.xpath("//img[@title='Porsche 718 Cayman S']")).click();

        //6. Verify that Base price displayed on the page is same as the price from step 4
        String targetTitle = "Porsche Car Configurator";
        List<String> tagList = new ArrayList<String>(driver.getWindowHandles());

        for (String tag : tagList) {
            driver.switchTo().window(tag);
            if (driver.getTitle().equals(targetTitle)) {
                break;
            }
        }
        Thread.sleep(3000);
        String basePrice = driver.findElement(By.xpath("(//section[@id='s_price']/div/div/div[2])[1]")).getText().substring(1);
        Assert.assertTrue(priceOfCaymanModelS.equals(basePrice));

        //7. Verify that Price for Equipment is 0
        String priceForEquipment = driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).getText();
        Assert.assertTrue(priceForEquipment.equals("$0"));

        //8. Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
        Assert.assertTrue(matchWithTotal());

        //9. Select color “Miami Blue”
        driver.findElement(By.xpath("//li[@id='s_exterieur_x_FJ5']")).click();

        //10.Verify that Price for Equipment is Equal to Miami Blue price
        String miamiBluePrice = driver.findElement(By.xpath(" //li[@id='s_exterieur_x_FJ5']")).getAttribute("data-price");
        Assert.assertTrue(matchWithPriceForEqpt(miamiBluePrice));

        //11.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Thread.sleep(2000);
        Assert.assertTrue(matchWithTotal());

        //12.Select 20" Carrera Sport Wheels
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']//span[@class='img-element']")).click();

        //13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
        String carreraSportWheelPrice = driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price");
        Assert.assertTrue(matchWithPriceForEqpt(carreraSportWheelPrice));

        //14.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Assert.assertTrue(matchWithTotal());

        //15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
        Thread.sleep(1000);
        action.sendKeys(Keys.PAGE_DOWN).perform();
        action.sendKeys(Keys.ARROW_DOWN).perform();
        action.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@id='s_interieur_x_PP06']")).click();

        //16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package
        String powerSportSeats = driver.findElement(By.xpath("//span[@id='s_interieur_x_PP06']//following-sibling::div/div/div")).getText();
        Thread.sleep(2000);
        Assert.assertTrue(matchWithPriceForEqpt(powerSportSeats));

        //17.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Assert.assertTrue(matchWithTotal());

        //18.Click on Interior Carbon Fiber
        action.sendKeys(Keys.PAGE_DOWN).perform();
        action.sendKeys(Keys.PAGE_DOWN).perform();
        action.sendKeys(Keys.ARROW_UP).perform();
        action.sendKeys(Keys.ARROW_UP).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='IIC_subHdl']")).click();

        //19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior
        driver.findElement(By.xpath("//span[@id='vs_table_IIC_x_PEKH_x_c01_PEKH']")).click();

        //20.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
        // + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w.Standard Interior
        String interiorTrimInCarbon =driver.findElement(By.xpath("//div[@id='vs_table_IIC_x_PEKH_x_c04_PEKH_x_shorttext']//following-sibling::div/div")).getText();
        Assert.assertTrue(matchWithPriceForEqpt(interiorTrimInCarbon));

        //21.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Assert.assertTrue(matchWithTotal());

        //22.Click on Performance
       action.sendKeys(Keys.PAGE_UP).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@id='IMG_subHdl']")).click();

        //23.Select 7-speed Porsche Doppelkupplung (PDK)
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@id='vs_table_IMG_x_M250_x_c11_M250']")).click();



        //24.Select Porsche Ceramic Composite Brakes (PCCB)
        action.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("//span[@id='vs_table_IMG_x_M450_x_c81_M450']")).click();

        //25.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w.
        //Standard Interior + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
        String _7SpeedDoppelkupplung = driver.findElement(By.xpath("//div[@id='vs_table_IMG_x_M250']/div/div/div")).getText();
        matchWithPriceForEqpt(_7SpeedDoppelkupplung);
        String ceramicCompositeBreaks =  driver.findElement(By.xpath("//div[@id='vs_table_IMG_x_M450']/div/div/div")).getText();
        Assert.assertTrue(matchWithPriceForEqpt(ceramicCompositeBreaks));

        //26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
        Assert.assertTrue(matchWithTotal());

    }

    public String price(int whichOne) {
        List<WebElement> list = driver.findElements(By.xpath("//section[@id='s_price']//div[@class='ccaPrice']"));

        return list.get(whichOne - 1).getText().substring(1).replace(",", "");
    }

    public boolean matchWithTotal() {
        int totalPrice = Integer.parseInt(driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[4]"))
                .getText().substring(1).replace(",", ""));
        int DPHFee = Integer.parseInt(driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[3]"))
                .getText().substring(1).replace(",", "").trim());
        int priceForEquipment = Integer.parseInt(driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).
                getText().replace(",", "").substring(1));
        int basePrice = Integer.parseInt(driver.findElement(By.xpath("(//section[@id='s_price']/div/div/div[2])[1]")).
                getText().substring(1).replace(",", ""));
        return DPHFee + priceForEquipment + basePrice == totalPrice?true:false;
    }

    List<String> pricesOfEqpts = new ArrayList<>();

    public boolean matchWithPriceForEqpt(String priceOfNewEqpt) {
        int priceForEqpt = Integer.parseInt(driver.findElement(By.xpath("(//section[@id='s_price']//div[@class='ccaPrice'])[2]")).
                getText().replace(",", "").substring(1));
        pricesOfEqpts.add(priceOfNewEqpt);

        int totalPriceOfEachEqpt = 0;
        for (String price : pricesOfEqpts) {
            totalPriceOfEachEqpt += Integer.parseInt(price.replace(",", "").substring(1));
        }
        return totalPriceOfEachEqpt == priceForEqpt ? true : false;
    }

}
