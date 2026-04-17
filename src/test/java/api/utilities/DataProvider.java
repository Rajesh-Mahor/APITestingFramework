package api.utilities;


public class DataProvider {

	@org.testng.annotations.DataProvider(name = "AllUserData")
	public static Object[][] getAllUserData() throws Exception {
		
		String fileName = "D:\\eclipse-workspace\\Java Selenium Project\\RestAPITestingFramework\\RestAssuredAPIFramework\\TestDataExcel\\UserTestData.xlsx";
		String sheetName = "TestDadaForUser";
		int rowCount = ReadExcelData.getRowCount(fileName, sheetName);
		int cellCount = ReadExcelData.getCellCount(fileName, sheetName);
		
		Object[][] alldata = new Object[rowCount][cellCount];
		
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<cellCount; j++) {
				alldata[i-1][j] = ReadExcelData.getCellValue(fileName, sheetName, i, j);
				//System.out.println(alldata [i-1][j]);
			}
		}
		
		return alldata;
		
	}
	
	@org.testng.annotations.DataProvider(name = "UserNameData")
	public static Object[] getUserNameData() throws Exception {
		
		String fileName = "D:\\eclipse-workspace\\Java Selenium Project\\RestAPITestingFramework\\RestAssuredAPIFramework\\TestDataExcel\\UserTestData.xlsx";
		String sheetName = "TestDadaForUser";
		int rowCount = ReadExcelData.getRowCount(fileName, sheetName);
		
		Object[] allUserNamedata = new Object[rowCount];
		
		for(int i=1; i<=rowCount; i++) {
			allUserNamedata[i-1] = ReadExcelData.getCellValue(fileName, sheetName, i, 1);
			//System.out.println(allUserNamedata [i-1]);
		}
		
		return allUserNamedata;
		
	}
	
}
