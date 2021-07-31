package TestInvertedIndex;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import InvertedIndex.utility.FileReaderUtil;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FileReaderUtilTest {

    @Test
    public void testReadFiles(){
        List<String> result = FileReaderUtil.readFiles();

        //should contains
        assertTrue(result.contains("58051"));
        assertTrue(result.contains("58894"));
        assertTrue(result.contains("59014"));
        assertTrue(result.contains("59334"));

        //not contains
        assertFalse(result.contains("152"));
        assertFalse(result.contains("49"));
    }
}