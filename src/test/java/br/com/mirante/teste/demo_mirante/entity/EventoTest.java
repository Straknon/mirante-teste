package br.com.mirante.teste.demo_mirante.entity;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;

@SpringBootTest
public class EventoTest {

    @Test
    void classTest(){
        final String strTest = "teste";
        final Timestamp timeTest = Timestamp.from(Instant.now());

        EventoDTO dto = new EventoDTO();
        dto.setCreatedAt(timeTest);
        dto.setDeleted(false);
        dto.setDescricao(strTest);
        dto.setId(0l);
        dto.setLocal(strTest);
        dto.setTitulo(strTest);
        dto.setUpdatedAt(timeTest);

        Evento evento = new Evento(dto);
        evento = new Evento();
        evento.setCreatedAt(timeTest);
        evento.setDeleted(false);
        evento.setDescricao(strTest);
        evento.setId(0l);
        evento.setLocal(strTest);
        evento.setTitulo(strTest);
        evento.setUpdatedAt(timeTest);
                
        Assertions.assertNotNull(evento.getDescricao());
        Assertions.assertNotNull(evento.getId());
        Assertions.assertNotNull(evento.getLocal());
        Assertions.assertNotNull(evento.getTitulo());
        Assertions.assertNotNull(evento.getCreatedAt());
        Assertions.assertNotNull(evento.getUpdatedAt());
        Assertions.assertNotNull(evento.isDeleted());
    }
}
