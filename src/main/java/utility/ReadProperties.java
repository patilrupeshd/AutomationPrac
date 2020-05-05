package utility;

import java.io.FileReader;
import java.util.Properties;

public class ReadProperties {
	private static final String PROPERTY_FILE_PATH = "src/main/java/resourse/envDetails.properties";

	public static String getProperty(String propertyName) {
		String propertyValue = null;
		FileReader propertyFileReader = null;
		try {
			propertyFileReader = new FileReader(PROPERTY_FILE_PATH);
			Properties readProperty = new Properties();
			readProperty.load(propertyFileReader);
			propertyValue = readProperty.getProperty(propertyName);

		} catch (Exception e) {
			System.err.println("There error While read a file " + PROPERTY_FILE_PATH + " Possible reson " + e);
		}
		return propertyValue;
	}

	public static void main(String[] args) {
		System.out.println(ReadProperties.getProperty("url"));
	}
}
