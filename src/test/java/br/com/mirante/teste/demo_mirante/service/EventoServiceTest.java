package br.com.mirante.teste.demo_mirante.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import br.com.mirante.teste.demo_mirante.entity.Evento;
import br.com.mirante.teste.demo_mirante.exception.EventoNaoEncontradoException;

@SpringBootTest
public class EventoServiceTest {

    @Autowired
    EventoService service;
    
    @Test
    void fluxoSaveTest(){
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
        dto.setId(null);
        dto.setLocal(strTest);
        dto.setTitulo(strTest);
        dto.setUpdatedAt(timeTest);

        //Salvar novo
        EventoDTO eventoSalvo = service.save(dto);
        Assertions.assertNotNull(eventoSalvo);
        Assertions.assertNotNull(eventoSalvo.getId());

        //Atualizar
        final String novoTitulo = "teste novo titulo";
        eventoSalvo.setTitulo(novoTitulo);
        eventoSalvo = service.update(eventoSalvo);
        Assertions.assertNotNull(eventoSalvo);
        Assertions.assertEquals(novoTitulo, eventoSalvo.getTitulo());

        //Achar por id
        Assertions.assertNotNull(service.findById(eventoSalvo.getId()));
        
        //Listagem por paginar
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Direction.fromString("asc"), "id"));
        Assertions.assertNotNull(service.listByPage(pageable, false));
        Assertions.assertEquals(Boolean.TRUE,service.delete(eventoSalvo.getId()));

        //Soft Delete
        Assertions.assertEquals(Boolean.TRUE, service.findById(eventoSalvo.getId()).isDeleted());
    }

    @Test
    void failTest(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            service.update(new EventoDTO());
        });
        Assertions.assertThrows(EventoNaoEncontradoException.class, ()->{
            service.findById(500L);
        });
        
    }
}
