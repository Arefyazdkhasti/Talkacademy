package TestInvertedIndex;

import InvertedIndex.utility.LogUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class LogUtilsTest {


    @Test
    public void testSimpleLog(){
        //https://stackoverflow.com/questions/20638036/junit-test-a-static-void-method
        String s = "test message simple";
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink, true));
        LogUtils.log(s);
        assertThat(new String(sink.toByteArray()), containsString(s));
    }


    @Test
    public void testCatchExpLog(){
        String s = "test message for catch";
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink, true));
        LogUtils.logCatchExe(s);
        assertThat(new String(sink.toByteArray()), containsString("Catch Exception-> "+ s));
    }


    @Test
    public void testInfoLog(){
        String s = "test message for info";
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink, true));
        LogUtils.logInfo(s);
        assertThat(new String(sink.toByteArray()), containsString("Info-> "+ s));
    }
}