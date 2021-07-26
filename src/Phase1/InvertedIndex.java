package Phase1;

import java.io.*;
import java.util.*;

public class InvertedIndex {

    static final String FILE_LOCATION = "C:\\Users\\Acer\\IdeaProjects\\talkademy\\EnglishData\\";


    public static void main(String[] args) throws IOException {

        List<MyFile> files = new ArrayList<>();
        Index simpleIndex = new Index();
        Log log = new Log();

        //read files
        simpleIndex.readFiles(files, simpleIndex);

        log.log("Type search phrase: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String phrase = in.readLine();

        simpleIndex.find(phrase);
    }


}