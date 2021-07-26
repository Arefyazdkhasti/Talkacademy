package Phase1;


import Phase1.utility.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Index {
      Log log = new Log();

     HashMap<String, HashSet<Integer>> index;

    //advanced search list
     List<Integer> lovedSources = new ArrayList<>();
     List<Integer> hatedSources = new ArrayList<>();
     List<Integer> searchesSources = new ArrayList<>();


    Index() {
        index = new HashMap<>();
    }

    void buildIndex(List<Integer> files) {

        for (Integer _file : files) {
            try (BufferedReader file = new BufferedReader(new FileReader(InvertedIndex.FILE_LOCATION+""+_file))) {
                String ln;
                while ((ln = file.readLine()) != null) {
                    String[] words = ln.split("\\W+");
                    //fill index with words
                    fillIndex(words,_file);
                }
            } catch (IOException e) {
                log.logCatchExe("File " + _file + " not found. Skip it");
            }
        }
    }

    private void fillIndex(String[] words, Integer file) {
        for (String word : words) {
            word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<>());
            index.get(word).add(file);
        }
    }


    List<Integer> find(String phrase) {

        String[] words = phrase.split("\\W+");
        List<Integer> sourceFiles = new ArrayList<>();

        try {
            HashSet<Integer> res = new HashSet<>(index.get(words[0].toLowerCase()));

            //only retain index that has the word in them
            for (String word : words) {
                res.retainAll(index.get(word));
            }

            printSearchResult(res,sourceFiles);
            return sourceFiles;

        } catch (NullPointerException e) {
            log.logCatchExe("Not found " + e.toString());
            return null;
        }
    }

    void printSearchResult(HashSet<Integer> res,List<Integer> sourceFiles) {
        if (res.size() == 0) {
            log.log("Not found");
        }
        System.out.println("Found in: ");
        for (int num : res) {
            log.log("\t\tFile Number:" + num);
            sourceFiles.add(num);
        }
    }
}
