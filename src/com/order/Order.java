package com.order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public static void main(String[] args) throws IOException {
		
		Random rand = new Random();
		int num = rand.nextInt(101);
		String str = String.valueOf(num);
		
		//another way of doing zip code
//		double temp0 = Math.random()* 10; 
//		int rand0 = (int) temp0;
//		String zip0 = Integer.toString(rand0);
//		
//		double temp1 = Math.random()* 10; 
//		int rand1 = (int) temp1;
//		String zip1 = Integer.toString(rand1);
//		
//		double temp2 = Math.random()* 10; 
//		int rand2 = (int) temp2;
//		String zip2 = Integer.toString(rand2);
//		
//		double temp3 = Math.random()* 10; 
//		int rand3 = (int) temp3;
//		String zip3 = Integer.toString(rand2);
//		
//		double temp4 = Math.random()* 10; 
//		int rand4 = (int) temp4;
//		String zip4 = Integer.toString(rand2);
		
		//Enter Customer Name: John <middle_name> Smith. Instead of <middle_name> your code should enter a random string every time.
		
		BufferedReader br = new BufferedReader(new FileReader("MiddleName.csv"));
        String line ="";
        
        List<String> middleName = new ArrayList<>();
        while((line=br.readLine())!=null){
            middleName.add(line);
            
        }
        
        
      //Zip Code:Enter a random 5 digit number to the zip code field 
        String name = middleName.get((new Random()).nextInt(middleName.size()));
		
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 5; i++) {
			sb.append((int)(Math.random()*10));
		}
			
		
		/*
		 Enter any card number. If you selected Visa, card number should start with 4. 
		 If you selected Master, card number should start with 5. If you selected American Express, card number should start with 3.
		 New card number should be auto generated every time you run the test. 
		 Card numbers should be 16 digits for Visa and Master, 15 for American Express.
		 */
		
		
		System.setProperty("webdriver.chrome.driver","/Users/mustafaergun/Documents/selenium-dependicies/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		for(int k=0; k<2;k++) {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester"); //username
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");  //password
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();  //click
		driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click(); //order
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(str); //quantity 1-100
		//name part is here
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("John"+" " +name+" "+ "Smith"); //name
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any st");//address
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");//city
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");//state
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(sb.toString());//zip code
		//card selection
		int card = (int)(Math.random()*3);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_"+card)).click();;
		
		//card number process
		int a=card;
		StringBuilder sbr = new StringBuilder();
		if(a==0) {
			sbr.append(4);
			for(int i =0; i<15;i++) {
				sbr.append((int)(Math.random()*10));
			}
		}else if(a==1) {
			sbr.append(5);
			for(int i =0; i<15;i++) {
				sbr.append((int)(Math.random()*10));
			}
			
		}else if(a==2) {
			sbr.append(3);
			for(int i =0; i<14;i++) {
				sbr.append((int)(Math.random()*10));
			}
		}
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(sbr.toString());
		
		
		//expire date
		StringBuilder month = new StringBuilder();
		
		int ex =((int)((Math.random()*12+1)));
		if(month.append (ex).length()==1) {
			
			month.insert(0, 0);
		}
		
		StringBuilder year = new StringBuilder();
		year.append((int)((Math.random()*10)+18));
		
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(month.toString()+"/"+year.toString());
		
		
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click(); //proceed
		
		String expected ="New order has been successfully added.";
		
		//Verify than the page contains text New order has been successfully added.		
		String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();
		System.out.println(actual); 
		
		if(expected.equals(actual)) {
			System.out.println("pass");
		}else {
			System.out.println("failed");
			System.out.println("Expected:\t"+expected);
			System.out.println("Actual:\t"+actual);
		}
		


	}
		
	}

}
