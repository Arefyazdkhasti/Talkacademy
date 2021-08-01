package TestInvertedIndex;


import InvertedIndex.utility.FileReaderUtil;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static InvertedIndex.utility.FileReaderUtil.FILE_LOCATION;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class FileReaderUtilTest {

    @Test
    public void testResultNotNull() {
        List<String> result = FileReaderUtil.readFiles();
        assertNotNull(result);
    }

    @Test
    public void testReadFiles() {

        File file1 = new File(FILE_LOCATION + "testFile1");
        File file2 = new File(FILE_LOCATION + "testFile2");

        try {

            if (!file1.exists())
                file1.createNewFile();
            if (!file2.exists())
                file2.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    List<String> result = FileReaderUtil.readFiles();

    //should contains
    assertTrue(result.contains("58051"));

    assertTrue(result.contains("58894"));

    assertTrue(result.contains("59014"));

    assertTrue(result.contains("59334"));

    assertTrue(result.contains("testFile1"));
    //not contains
    assertFalse(result.contains("152"));

    assertFalse(result.contains("49"));

    assertFalse(result.contains("testFile"));
}
}