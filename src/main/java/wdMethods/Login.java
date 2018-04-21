package wdMethods;

import org.testng.annotations.Test;
//import org.openqa.selenium.chrome.ChromeDriver;
import wdMethods.SeMethods;
import org.openqa.selenium.WebElement;

public class Login extends ProjectMethod{

	//public static void main(String[] args) {
		@Test
		public void loginLeaftaps() {
			
			login();
			click(locateElement("linktext", "Leads"));
			
			//Click create leads link
			click(locateElement("linktext", "Create Lead"));
			//Enter company name
			type(locateElement("id", "createLeadForm_companyName"),"Eden");
			//Enter first name
			type(locateElement("id", "createLeadForm_firstName"),"happy");
			//Enter last name
			type(locateElement("id", "createLeadForm_lastName"),"Joy");
			//Click submit button
			click(locateElement("name", "submitButton"));
			
		}
	}
