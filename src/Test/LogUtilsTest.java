package Test;

import InvertedIndex.utility.LogUtils;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LogUtilsTest {


    @Test
    public void testSimpleLog(){
        //https://stackoverflow.com/questions/20638036/junit-test-a-static-void-method
        String s = "test message";
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink, true));
        LogUtils.log(s);
        assertThat(new String(sink.toByteArray()), containsString(s));
    }


    @Test
    public void testErrorLog(){
        String s = "test message";
        ByteArrayOutputStream sink = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sink, true));
        LogUtils.logError(s);
        assertThat(new String(sink.toByteArray()), containsString("Error-> "+ s));
    }
}