package br.com.mirante.teste.demo_mirante.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StandardErrorTest {
    @Test
    void classTest(){
        final Long now = Timestamp.from(Instant.now()).getTime();
        final List<String> messages = Arrays.asList("messageTest");
        final String path = "/api/test";
        StandardError errorTest = new StandardError(now, 0, messages, path);
        errorTest = new StandardError();
        errorTest.setError(0);
        errorTest.setMessages(messages);
        errorTest.setPath(path);
        errorTest.setTimestamp(now);
        Assertions.assertNotNull(errorTest.getError());
        Assertions.assertNotNull(errorTest.getPath());
        Assertions.assertNotNull(errorTest.getTimestamp());
        Assertions.assertNotNull(errorTest.getMessages());
        Assertions.assertNotNull(errorTest.toString());
    }
}
