package TestInvertedIndex;

import InvertedIndex.AdvancedIndex;
import InvertedIndex.Index;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdvancedIndexTest {

    private AdvancedIndex advancedIndex;

    @InjectMocks
    Index index;
    @Mock
    List<String> lovedSource;
    @Mock
    List<String> hateSource;
    @Mock
    List<String> searchSources;

    @Before
    public void setUp() {
        advancedIndex = new AdvancedIndex();
        index = spy(new Index());
        lovedSource = new ArrayList<>();
        hateSource = new ArrayList<>();
        searchSources = new ArrayList<>();
    }

    @Test
    public void testRemoveDuplicate() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("4");
        List<String> result = new ArrayList<>();
        result.add("1");
        result.add("2");
        result.add("3");
        result.add("4");
        assertEquals(result, advancedIndex.removeDuplicate(list));
    }

    @Test
    public void testPrintAdvancedSearchResult() {
        lovedSource.add("57110");
        hateSource.add("58045");
        searchSources.add("58111");
        List<String> result = advancedIndex.printAdvancedSearchResult(lovedSource, hateSource, searchSources);
        assertTrue(result.contains("58111"));
    }

    @Test
    public void testDiscoverWords() {
        index.buildIndex(Arrays.asList("57110","59063","58047"));

        doReturn(Arrays.asList("57110", "59063")).when(index).find("love");
        doReturn(Arrays.asList("57110", "58047")).when(index).find("hell");

        List<String> result = advancedIndex.discoverWords(new String[]{"live", "-love", "+hell"});
        System.out.println(result);

        List<String> finalWords = Collections.singletonList("live");
        assertEquals(finalWords, result);
    }

    @Test
    public void notNullFindAdvancedResult(){
        advancedIndex.buildIndex(Arrays.asList("57110" ,"59063","58047"));
        List<String> result = advancedIndex.findAdvanced("hi +primary");
        assertNotNull(result);
    }

    @Test
    public void testFindAdvanced() {
        //fill index and then search fo result
        advancedIndex.buildIndex(Arrays.asList("57110" ,"59063","58047"));
        List<String> result = advancedIndex.findAdvanced("hi +primary");
        assertTrue(result.contains("58047"));
        assertFalse(result.contains("59063"));
    }

    @After
    public void finish(){
        lovedSource.clear();
        hateSource.clear();
        searchSources.clear();
    }
}