package com.example.demo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MyServiceTest {

    @Mock
    private StringToIntService stringToIntService;

    @InjectMocks
    private MyService myService;

    Logger logger = (Logger) LoggerFactory.getLogger(MyService.class);

    /**
     * Spy bean
     * Verify calls and fields value
     */
    @Test
    void onNewMessage1() {
        MyService myServiceSpy = spy(myService);
        myServiceSpy.onNewMessage("1234!");
        assert(myServiceSpy.input_type == MyService.INPUT_TYPE.ODD);
        verify(myServiceSpy, times(1)).testOdd(anyString());
        verify(myServiceSpy, times(0)).testEven(anyString());
    }

    @Test
    void onNewMessage2() {
        MyService myServiceSpy = spy(myService);
        myServiceSpy.onNewMessage("1234");
        assert(myServiceSpy.input_type == MyService.INPUT_TYPE.EVEN);
        verify(myServiceSpy, times(0)).testOdd(anyString());
        verify(myServiceSpy, times(1)).testEven(anyString());
    }

    @Test
    void onNewMessage3() {
        MyService myServiceSpy = spy(myService);
        myServiceSpy.onNewMessage("");
        assert(myServiceSpy.input_type == MyService.INPUT_TYPE.EMPTY);
        verify(myServiceSpy, times(0)).testOdd(anyString());
        verify(myServiceSpy, times(0)).testEven(anyString());
    }


    /**
     * Mock bean and method return result
     * assert logging
     */

    @Test
    void convertStringToInt_valid() {
        // mock service behavior
        String testServiceInput = "123";
        int testServiceResult = 123;
        when(stringToIntService.convertStrToInt(anyString())
        ).thenReturn(testServiceResult);

        // initialize logger
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        // perform action
        myService.convertStringToInt(String.valueOf(testServiceInput));

        // assert first line of logger message
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(String.format("convertStrToInt successfully returns %s", testServiceResult), logsList.get(0)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(0)
                .getLevel());

        verify(stringToIntService, atLeast(1)).convertStrToInt("123");

    }

    /**
     * Mock bean and throw exception on method triggered
     * assert logging
     */
    @Test
    void convertStringToInt_invalid() {
        // mock service behavior
        when(stringToIntService.convertStrToInt(anyString())
        ).thenThrow(new NumberFormatException("For input string"));

        // initialize logger
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        // perform action
        myService.convertStringToInt("This is a test message");

        // assert first line of logger message
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("For input string", logsList.get(0)
                .getMessage());
        assertEquals(Level.ERROR, logsList.get(0)
                .getLevel());
    }
}