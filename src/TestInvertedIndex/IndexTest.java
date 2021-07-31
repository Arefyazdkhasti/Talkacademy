package TestInvertedIndex;

import InvertedIndex.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexTest {

    private Index index = new Index();

    @Test
    public void testBuildIndex() {
        HashMap<String, HashSet<String>> result= index.buildIndex(Arrays.asList("57110", "16"));
        System.out.println(result);

        //make index with 57110 file which should contains there words in it
        assertTrue(result.containsKey("glucocerebroside"));
        assertTrue(result.containsKey("genzyme"));
        assertTrue(result.containsKey("symptoms"));
    }

    @Test
    public void testFillIndex() {
        String[] words =new String[]{"test1", "test2","test3"};
        HashMap<String, HashSet<String>> result =  index.fillIndex(words,"57110");
        System.out.println(result);
        assertTrue(result.containsKey("test1"));

        HashSet<String> value = new HashSet<>();
        value.add("57110");
        assertEquals(value,result.get("test1"));
    }


    @Test
    public void testFind() {
        //fill index with call buildIndex fun then find a word
        index.buildIndex(Arrays.asList("57110","59063","58047"));
        List<String> result = index.find("symptoms");
        //symptoms is is in 57100 but not in 58047
        assertTrue(result.contains("57110"));
        assertFalse(result.contains("58047"));
    }

    @Test
    public void testPrintSearchResult() {
        HashSet<String> s = new HashSet<>();
        s.add("57110");
        s.add("58045");
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        index.printSearchResult(s,list);
    }
}