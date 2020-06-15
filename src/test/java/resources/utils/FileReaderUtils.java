package resources.utils;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;
import resources.models.City;
import resources.models.CityData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileReaderUtils {


    public static List<String> getTestDataFromTxt(String fileName) throws IOException {
        List<String> records = new ArrayList<String>();
        String record;

        try (
                BufferedReader buffer = new BufferedReader(new FileReader(fileName));
        ) {
            while ((record = buffer.readLine()) != null) {
                records.add(record);
            }
        }
        return records;

    }

    public static Object[] getCitiesFromXml(String fileName) throws IOException {
        XStream xstream = new XStream();

        xstream.processAnnotations(CityData.class);
        xstream.processAnnotations(City.class);
        CityData data = (CityData) xstream.fromXML(FileUtils.readFileToString(new File(fileName)));

        return data.getCities().toArray();
    }

    public static Properties readPropertiesFile(String fileName) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(fileName)) {
        prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop;
    }
}
