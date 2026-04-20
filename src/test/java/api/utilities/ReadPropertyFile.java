package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	public static Properties properties;
	
	public ReadPropertyFile() throws IOException {
		try {
		File file = new File("D:\\eclipse-workspace\\Java Selenium Project\\RestAPITestingFramework\\RestAssuredAPIFramework\\Config\\urlConfig.properties");
		FileInputStream inputStream = new FileInputStream(file);
		properties = new Properties();
		properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public String postUrlForTest() {
		String getPostUrl = properties.getProperty("postUrl");
		return getPostUrl;
	}
	
	public String getUrlForTest() {
		String getGetUrl = properties.getProperty("getUrl");
		return getGetUrl;
	}
	
	public String putUrlForTest() {
		String getPutUrl = properties.getProperty("putUrl");
		return getPutUrl;
	}
	
	public String deleteUrlForTest() {
		String getDeleteUrl = properties.getProperty("deleteUrl");
		return getDeleteUrl;
	}
}
