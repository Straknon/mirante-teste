package br.com.mirante.teste.demo_mirante.dto;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.mirante.teste.demo_mirante.entity.Evento;

@SpringBootTest
public class EventoDTOTest {

    @Test
    void classTest(){
        final String strTest = "teste";
        final Timestamp timeTest = Timestamp.from(Instant.now());

        Evento evento = new  Evento();
        evento.setCreatedAt(timeTest);
        evento.setDeleted(false);
        evento.setDescricao(strTest);
        evento.setId(0l);
        evento.setLocal(strTest);
        evento.setTitulo(strTest);
        evento.setUpdatedAt(timeTest);

        EventoDTO dto = new EventoDTO(strTest, strTest, strTest, timeTest, timeTest);
        dto = new EventoDTO(evento);
        dto = new EventoDTO();
        dto.setCreatedAt(timeTest);
        dto.setDeleted(false);
        dto.setDescricao(strTest);
        dto.setId(0l);
        dto.setLocal(strTest);
        dto.setTitulo(strTest);
        dto.setUpdatedAt(timeTest);
                
        Assertions.assertNotNull(dto.getDescricao());
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getLocal());
        Assertions.assertNotNull(dto.getTitulo());
        Assertions.assertNotNull(dto.getCreatedAt());
        Assertions.assertNotNull(dto.getUpdatedAt());
        Assertions.assertNotNull(dto.isDeleted());
    }
    
}
