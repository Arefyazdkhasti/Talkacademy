package Phase1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdvancedInvertedIndex {


    public static void main(String[] args) throws IOException {

        List<MyFile> files = new ArrayList<>();
        AdvancedIndex advancedIndex = new AdvancedIndex();
        Log log = new Log();

        //read files
        advancedIndex.readFiles(files,advancedIndex);

        log.log("Type search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();

        advancedIndex.findAdvanced(phrase);
    }
}
