package Phase1.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static final String FILE_LOCATION = "EnglishData\\";

    public static List<String> readFiles() {
        List<String> files = new ArrayList<>();
        File folder = new File(FILE_LOCATION);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
        } else {
            LogUtils.logError("There is no file in this directory");
        }
        return files;
    }
}
