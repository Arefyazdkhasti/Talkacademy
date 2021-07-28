package Test;

import InvertedIndex.AdvancedIndex;
import InvertedIndex.Index;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AdvancedIndexTest {

    private AdvancedIndex advancedIndex= new AdvancedIndex();

    @Mock
    Index index;
    @Mock
    List<String> lovedSource = new ArrayList<>();
    @Mock
    List<String> hateSource = new ArrayList<>();
    @Mock
    List<String> searchSources = new ArrayList<>();


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
        advancedIndex.printAdvancedSearchResult(lovedSource, hateSource, searchSources);
        //verifyNoMoreInteractions(lovedSource);
    }

    @Test
    public void testDiscoverWords(){
        //TODO make when work properly
       //assertNotNull(index);
       //when(index.find("love")).thenReturn(Arrays.asList("57110", "58046"));
       //when(index.find("hell")).thenReturn(Arrays.asList("57110", "58110"));
        List<String> result = advancedIndex.discoverWords(new String[]{"live", "-love","+hell"});
        System.out.println(result);
        List<String> finalWords = Collections.singletonList("live");
        assertEquals(finalWords,result);
    }

    @Test
    public void testFindAdvanced(){
        //index is null in the body of the function
        advancedIndex.findAdvanced("test");
    }
}