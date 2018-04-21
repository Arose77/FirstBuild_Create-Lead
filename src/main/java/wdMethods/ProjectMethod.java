package wdMethods;

import org.junit.Test;

public class ProjectMethod extends SeMethods{
	//@Test
	public void login() {
		startApp("chrome", "http://leaftaps.com/opentaps/control/main");
		/*WebElement uName = locateElement("id", "user name");
			type(uName, "DemoSalesManager");*/
		type(locateElement("id", "username"), "DemoSalesManager");
		//WebElement password = locateElement("id", "password");
		type(locateElement("id", "password"), "crmsfa");
		click(locateElement("class", "decorativeSubmit"));

		click(locateElement("linktext", "CRM/SFA"));
		//Click Lead tab
		//click(locateElement("linktext", "Leads"));
		
	}

}
