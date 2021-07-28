package InvertedIndex;


import InvertedIndex.utility.LogUtils;

import java.util.*;
import java.util.stream.Collectors;

public class AdvancedIndex extends Index {
    //advanced search list
    private List<String> lovedSources = new ArrayList<>();
    private List<String> hatedSources = new ArrayList<>();
    private List<String> searchesSources = new ArrayList<>();

    //const values
    private static final char PLUS = '+';
    private static final char MINUS = '-';

    public void findAdvanced(String phrase) {
        if (phrase == null) return;

        String[] words = phrase.split(" ");
        //words without + and -
        List<String> finalWords = discoverWords(words);

        //remove duplication from loved
        lovedSources = removeDuplicate(lovedSources);

        //remove duplication from hated
        hatedSources = removeDuplicate(hatedSources);

        try {
            if (!finalWords.isEmpty()) {
                HashSet<String> res = new HashSet<>(index.get(finalWords.get(0).toLowerCase()));

                //print main phrase search result
                printSearchResult(res, searchesSources);
            }
        } catch (NullPointerException e) {
            LogUtils.logCatchExe("Not found : " + e.toString());
            //TODO what is the problem
            e.printStackTrace();
        }

        printAdvancedSearchResult(lovedSources,hatedSources,searchesSources);
    }


    public List<String> discoverWords(String[] words) {

        List<String> finalWords = new ArrayList<>();
        for (String item : words) {
            if (item != null) {
                switch (item.charAt(0)) {
                    case PLUS:
                        LogUtils.logInfo("+ =>" + item);
                        //remove + from item and add to loved
                        List<String> love = find(item.substring(1));
                        if (love != null)
                            lovedSources.addAll(love);
                        break;

                    case MINUS:
                        LogUtils.logInfo("- =>" + item);
                        //remove - from item and add to hated
                        List<String> hate = find(item.substring(1));
                        if (hate != null)
                            hatedSources.addAll(hate);
                        break;

                    default:
                        finalWords.add(item);
                        break;
                }
            }
        }
        return finalWords;
    }

    public List<String> removeDuplicate(List<String> list) {
        //remove duplication
        List<String> result = list.stream().distinct().collect(Collectors.toList());;
        return result;
    }

    public void printAdvancedSearchResult(List<String> lovedSources,List<String> hatedSources,List<String> searchesSources) {
        //make result list
        List<String> result = new ArrayList<>(searchesSources);

        //check to add if lovedSources didnt exist
        for (String i : lovedSources) {
            if (!result.contains(i))
                result.add(i);
        }
        //remove hatedSource from result
        result.removeAll(hatedSources);

        LogUtils.log("Final search result:");
        //print result
        for (String sourceFile : result) {
            LogUtils.log("\t" + sourceFile);
        }
    }
}
