package app.c2.controller;

import app.c2.service.TestService2;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import ch.qos.logback.classic.Level;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class TestControllerTest {

    @Mock
    TestService2 testService2;
    Logger logger = (Logger) LoggerFactory.getLogger(TestService1.class);
    @InjectMocks
    TestService1 testService1;

    @Test
    void convertStringToInt_valid() {
        // mock service behavior
        String testServiceInput = "123";
        int testServiceResult = 123;
        when(testService2.convertStrToInt(anyString())
        ).thenReturn(testServiceResult);

        // initialize logger
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        // perform action
        testService1.convertStringToInt(String.valueOf(testServiceInput));

        // assert first line of logger message
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals(String.format("convertStrToInt successfully returns %s", testServiceResult), logsList.get(0)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(0)
                .getLevel());

        verify(testService2, atLeast(1)).convertStrToInt("123");

    }

    @Test
    void convertStringToInt_invalid() {
        // mock service behavior
        when(testService2.convertStrToInt(anyString())
        ).thenThrow(new NumberFormatException("For input string"));

        // initialize logger
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        // perform action
        testService1.convertStringToInt("This is a test message");

        // assert first line of logger message
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("For input string", logsList.get(0)
                .getMessage());
        assertEquals(Level.ERROR, logsList.get(0)
                .getLevel());
    }
}