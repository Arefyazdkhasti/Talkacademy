package Phase1;

import Phase1.utility.FileReaderUtil;
import Phase1.utility.Log;

import java.io.*;
import java.util.*;

public class InvertedIndex {

    public static final String FILE_LOCATION = "EnglishData\\";
    private static final String SIMPLE_SEARCH = "simple";
    private static final String ADVANCED_SEARCH = "advance";


    public static void main(String[] args) throws IOException {

        List<Integer> files = new ArrayList<>();
        Index simpleIndex = new Index();
        AdvancedIndex advancedIndex = new AdvancedIndex();
        Log log = new Log();
        FileReaderUtil fileReaderUtil = new FileReaderUtil();

        //read files
        List<Integer> fileList = fileReaderUtil.readFiles(files);

        //choose type
        log.log("Enter type of search: " + SIMPLE_SEARCH + " or " + ADVANCED_SEARCH);
        Scanner scanner = new Scanner(System.in);
        String inputType = scanner.nextLine();

        log.log("Type search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();

        if (inputType.equals(SIMPLE_SEARCH)){
            //build index
            simpleIndex.buildIndex(fileList);
            simpleIndex.find(phrase);
        }
        else if (inputType.equals(ADVANCED_SEARCH)){
            //build index
            advancedIndex.buildIndex(fileList);
            advancedIndex.findAdvanced(phrase);
        }
        else {
            log.logError("No such type to search");
            System.exit(0);
        }
    }


}