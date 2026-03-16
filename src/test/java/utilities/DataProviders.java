package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String  [][] getData() throws Exception
	{
		String path=".\\testData\\Opencart_LoginData.xlsx"; // Taking excel file from testData folder
		
		ExcelUtility xlutil=new ExcelUtility(path);  //Creating an object for ExcelUtility class to invoke the methods of ExcelUtility class
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellount("Sheet1", 1);
		
		String  logindata[][]=new String[totalrows][totalcols];  //create for two dimensional array which can store the data
		
		for(int i=1;i<=totalrows;i++)   // i=0 is not taking because zero is header part 
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //[i-1] because array index start from zero
			}
		}
		return logindata;
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}



