package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropReader
{
	
	public static String readPropFile(String PropToBeRead) throws IOException
	
	
	{
		Properties prop = new Properties();
		
		String projectPath = System.getProperty("user.dir");
		
		String configFilePath = projectPath + "/src/test/resources/Config.properties";
		
		FileInputStream fis = new FileInputStream(configFilePath);

		prop.load(fis);
		

		String value = prop.getProperty(PropToBeRead);
		
       System.out.println(value);
		
		return value;
		
		
		
	}
	public static void main(String[] args) throws IOException {
		readPropFile("refresh_token");
	}

}
