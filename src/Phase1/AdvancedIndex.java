package Phase1;


import java.util.*;

class AdvancedIndex extends Index {

    void findAdvanced(String phrase) {

        String[] words = phrase.split(" ");
        //words without + and -
        List<String> finalWords = new ArrayList<>();

        discoverWords(words,finalWords);

        //remove duplication from loved
        lovedSources = removeDuplicate(lovedSources);

        //remove duplication from hated
        hatedSources = removeDuplicate(hatedSources);

        try {
            if (!finalWords.isEmpty()) {
                HashSet<Integer> res = new HashSet<>(index.get(finalWords.get(0).toLowerCase()));

                //print main phrase search result
                printSearchResult(res, searchesSources);
            }
        } catch (NullPointerException e) {
            log.logCatchExe("Not found " + e.toString());
        }

        printAdvancedSearchResult();
    }


    private void discoverWords(String[] words, List<String> finalWords) {
        for (String item : words) {
            switch (item.charAt(0)) {
                case '+':
                    log.logInfo("+ =>" + item);
                    //remove + from item and add to loved
                    List<Integer> love = find(item.substring(1));
                    if (love != null)
                        lovedSources.addAll(love);
                    break;

                case '-':
                    log.logInfo("- =>" + item);
                    //remove - from item and add to hated
                    List<Integer> hate = find(item.substring(1));
                    if (hate != null)
                        hatedSources.addAll(hate);
                    break;

                default:
                    finalWords.add(item);
                    break;
            }
        }
    }

    private List<Integer> removeDuplicate(List<Integer> list) {
        List<Integer> result = new ArrayList<>(list);
        list.clear();
        result.addAll(list);
        return result;
    }

    private void printAdvancedSearchResult() {
        //make result list
        List<Integer> result = new ArrayList<>(searchesSources);

        //check to add if lovedSources didnt exist
        for (Integer i : lovedSources) {
            if (!result.contains(i))
                result.add(i);
        }
        //remove hatedSource from result
        for (Integer i : hatedSources) {
            result.remove(i);
        }

        log.log("Final search result:");
        //print result
        for (Integer sourceFile : result) {
            log.log("\t" + sourceFile);
        }
    }
}
