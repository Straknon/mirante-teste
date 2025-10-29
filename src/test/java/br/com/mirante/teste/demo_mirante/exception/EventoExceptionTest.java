package br.com.mirante.teste.demo_mirante.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventoExceptionTest {
    @Test
    void classTest(){
        new EventoNaoEncontradoException("expcption test");
    }
}
