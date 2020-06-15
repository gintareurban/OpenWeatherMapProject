package resources.utils;

import java.io.*;
import java.util.List;

public class FileWriterTxtUtils {

    public static void writeDataToFile(String fileName, List<String> data) throws IOException {

        try (FileOutputStream fos = new FileOutputStream(new File(fileName));
             BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(fos));
        ) {
            for (String line : data) {
                buffer.write(line);
                buffer.newLine();
            }
        }
    }
}
