package Phase1.utility;

import Phase1.Index;
import Phase1.InvertedIndex;

import java.io.File;
import java.util.List;

public class FileReaderUtil {

    private Log log=new Log();

    public List<Integer> readFiles(List<Integer> files){
        File folder = new File(InvertedIndex.FILE_LOCATION);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    files.add(Integer.valueOf(file.getName()));
                }
            }
        }else{
            log.logError("There is no file in this directory");
        }
        return files;
    }
}
