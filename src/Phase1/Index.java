package Phase1;


import Phase1.utility.FileReaderUtil;
import Phase1.utility.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Index {

    HashMap<String, HashSet<String>> index;

    Index() {
        index = new HashMap<>();
    }

    void buildIndex(List<String> files) {
        if (files == null) return;

        if (!files.isEmpty()) {
            for (String _file : files) {
                try (BufferedReader file = new BufferedReader(new FileReader(FileReaderUtil.FILE_LOCATION + "" + _file))) {
                    String ln;
                    while ((ln = file.readLine()) != null) {
                        String[] words = ln.split("\\W+");
                        //fill index with words
                        fillIndex(words, _file);
                    }
                } catch (IOException e) {
                    Log.logCatchExe("File " + _file + " not found. Skip it");
                }
            }
        }

    }

    private void fillIndex(String[] words, String file) {
        for (String word : words) {
            word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<>());
            index.get(word).add(file);
        }
    }


    List<String> find(String phrase) {

        String[] words = phrase.split("\\W+");
        List<String> sourceFiles = new ArrayList<>();

        try {
            HashSet<String> res = new HashSet<>(index.get(words[0].toLowerCase()));

            //only retain index that has the word in them
            for (String word : words) {
                res.retainAll(index.get(word));
            }

            printSearchResult(res, sourceFiles);
            return sourceFiles;

        } catch (NullPointerException e) {
            Log.logCatchExe("Not found " + e.toString());
            return null;
        }
    }

    void printSearchResult(HashSet<String> res, List<String> sourceFiles) {
        if (res.size() == 0) {
            Log.log("Not found");
        }
        System.out.println("Found in: ");
        for (String num : res) {
            Log.log("\t\tFile Number:" + num);
            sourceFiles.add(num);
        }
    }
}
