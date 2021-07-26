package Phase1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Index {
      Log log = new Log();

     private Map<Integer, String> sources;
     HashMap<String, HashSet<Integer>> index;

    //advanced search list
     List<Integer> lovedSources = new ArrayList<>();
     List<Integer> hatedSources = new ArrayList<>();
     List<Integer> searchesSources = new ArrayList<>();


    Index() {
        sources = new HashMap<>();
        index = new HashMap<>();
    }

    void readFiles(List<MyFile> files,Index index){
        for (int i = 57110; i <= 59652; i++) {
            MyFile file = new MyFile(InvertedIndex.FILE_LOCATION + i, i);
            files.add(file);
        }
        index.buildIndex(files);
    }

    private void buildIndex(List<MyFile> myFiles) {

        for (MyFile _file : myFiles) {
            try (BufferedReader file = new BufferedReader(new FileReader(_file.getFile()))) {
                sources.put(_file.getFileNumber(), _file.getFile());
                String ln;
                while ((ln = file.readLine()) != null) {
                    String[] words = ln.split("\\W+");
                    //fill index with words
                    fillIndex(words,_file);
                }
            } catch (IOException e) {
                log.logCatchExe("File " + _file.getFileNumber() + " not found. Skip it");
            }
        }
    }

    private void fillIndex(String[] words, MyFile file) {
        for (String word : words) {
            word = word.toLowerCase();
            if (!index.containsKey(word))
                index.put(word, new HashSet<>());
            index.get(word).add(file.getFileNumber());
        }
    }


    void printSearchResult(HashSet<Integer> res,List<Integer> sourceFiles) {
        if (res.size() == 0) {
            log.log("Not found");
        }
        System.out.println("Found in: ");
        for (int num : res) {
            log.log("\t" + sources.get(num) + " \tFile Number:" + num);
            sourceFiles.add(num);
        }
    }

    List<Integer> find(String phrase) {

        String[] words = phrase.split("\\W+");
        List<Integer> sourceFiles = new ArrayList<>();

        try {
            HashSet<Integer> res = new HashSet<>(index.get(words[0].toLowerCase()));
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
}
