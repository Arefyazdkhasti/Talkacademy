package Test;

import InvertedIndex.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class IndexTest {

    private Index index = new Index();

    /*@Mock
    HashMap<String, HashSet<String>> mainIndex = new HashMap<>();
*/
    @Test
    public void testBuildIndex() {
        index.buildIndex(Arrays.asList("57110", "16"));
    }

    @Test
    public void testFillIndex() {
        String[] words =new String[]{"test1", "test2","test3"};
        HashMap<String, HashSet<String>> result =  index.fillIndex(words,"57110");
        System.out.println(result);
        assertTrue(result.containsKey("test1"));
    }

    //TODO how to test this ? index is empty and cant find anything in the test
    @Test
    public void testFind() {
        index.find("test");
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

    /*@Mock
    Index index = new Index();

    @Mock
    List<String> files;
    @Mock
    Index index = new Index();
    @Mock
    HashSet<String> set;

    @Before
    public void serUP() {
        files = new ArrayList<>();
        files.add("57110");
        files.add("58043");
        files.add("58044");
        files.add("58045");
    }

    @Test
    public void buildIndexTest() {
        List<String> files = new ArrayList<>();
        files.add("57110");
        files.add("58043");
        files.add("58044");
        files.add("58045");
        //doNothing().when(index).buildIndex(files);
        verify(index).buildIndex(files);
    }

    @Test
    public void fillIndexTest(){
        verify(index).fillIndex(new String[]{"hello there is stm to say","yey"},"112");
    }

    @Test
    public void testFind() {
        List<String> result = index.find("live");
    }*/
}