package InvertedIndex;


import InvertedIndex.utility.FileReaderUtil;
import InvertedIndex.utility.LogUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Index {

    public HashMap<String, HashSet<String>> index;

    public Index() {
        index = new HashMap<>();
    }

    public HashMap<String, HashSet<String>> buildIndex(List<String> files) {
        if (files == null) return null;

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
                    LogUtils.logCatchExe("File " + _file + " not found. Skip it");
                }
            }
        }
        return index;
    }

    public HashMap<String, HashSet<String>> fillIndex(String[] words, String file) {
        for (String word : words) {
            word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<>());
            index.get(word).add(file);
        }
        //make return type for the test file
        return index;
    }


    public List<String> find(String phrase) {

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
            LogUtils.logCatchExe("Not found " + e.toString());
            return null;
        }
    }

    public void printSearchResult(HashSet<String> res, List<String> sourceFiles) {
        if (res.size() == 0) {
            LogUtils.log("Not found");
        }
        System.out.println("Found in: ");
        for (String num : res) {
            LogUtils.log("\t\tFile Number:" + num);
            sourceFiles.add(num);
        }
    }
}
