package Phase1;

import Phase1.utility.ExitUtils;
import Phase1.utility.FileReaderUtil;
import Phase1.utility.Log;

import java.io.*;
import java.util.*;

public class InvertedIndex {

    private static final String SIMPLE_SEARCH = "simple";
    private static final String ADVANCED_SEARCH = "advance";
    private static final String TYPE_YOUR_PHRASE = "Type search phrase: ";
    private static final String CHOOSE_TYPE = "Enter type of search: " + SIMPLE_SEARCH + " or " + ADVANCED_SEARCH;
    private static final String NO_SUCH_TYPE = "No such type to search";

    public static void main(String[] args) throws IOException {

        Index simpleIndex = new Index();
        AdvancedIndex advancedIndex = new AdvancedIndex();

        //read files
        List<String> fileList = FileReaderUtil.readFiles();

        //choose type
        Log.log(CHOOSE_TYPE);
        Scanner scanner = new Scanner(System.in);
        String inputType = scanner.nextLine();

        Log.log(TYPE_YOUR_PHRASE);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();

        if (SIMPLE_SEARCH.equals(inputType)){
            //build index
            simpleIndex.buildIndex(fileList);
            simpleIndex.find(phrase);
        }
        else if (ADVANCED_SEARCH.equals(inputType)){
            //build index
            advancedIndex.buildIndex(fileList);
            advancedIndex.findAdvanced(phrase);
        }
        else {
            Log.logError(NO_SUCH_TYPE);
            ExitUtils.exit();
        }
    }


}