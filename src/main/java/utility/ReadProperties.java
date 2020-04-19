package utility;

import java.io.FileReader;
import java.util.Properties;

public class ReadProperties {
    public static String getProperty(String propertyFilePath, String propertyName)
    {
        String propertyValue = null;
        FileReader propertyFileReader = null;
        try{
            propertyFileReader = new FileReader(propertyFilePath);
            Properties readProperty = new Properties();
            readProperty.load(propertyFileReader);
            propertyValue = readProperty.getProperty(propertyName);

        }catch (Exception e)
        {
            System.err.println("There error While read a file " + propertyFilePath + " Possible reson " + e);
        }
        return propertyValue;
    }

}
