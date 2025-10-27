package br.com.mirante.teste.demo_mirante.controller;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import br.com.mirante.teste.demo_mirante.service.EventoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.BASE_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EventoService service;

    @Test
    void testListAllPage() throws Exception {
        final int pageNumber = 0;
        final int pageSize = 5;
        final Direction dir = Direction.ASC;
        final String sortBy = "id";


        EventoDTO evento = new EventoDTO();
        Page<EventoDTO> page = new PageImpl<>(List.of(evento));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(dir, sortBy));

        Mockito.when(service.listByPage(pageable, false)).thenReturn(page);

        mockMvc.perform(get(BASE_URL+ "events")
                .param("page", String.valueOf(pageNumber))
                .param("size",  String.valueOf(pageSize))
                .param("sortBy", sortBy)
                .param("direction", dir.toString())
                .param("deleted", "false"))
            .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        EventoDTO evento = new EventoDTO();
        Mockito.when(service.findById(1L)).thenReturn(evento);

        mockMvc.perform(get(BASE_URL+ "events/1"))
            .andExpect(status().isOk());
    }

    @Test
    void testCreate() throws Exception {
        final String json = "{"
            + "\"titulo\":\"teste\","
            + "\"descricao\":\"teste\","
            + "\"local\":\"teste\","
            + "\"deleted\":false"
            + "}";

        final String strTest = "teste";
        final Timestamp timeTest = Timestamp.from(Instant.now());
        EventoDTO evento = new EventoDTO();
        evento.setCreatedAt(timeTest);
        evento.setDeleted(false);
        evento.setDescricao(strTest);
        evento.setLocal(strTest);
        evento.setTitulo(strTest);
        evento.setUpdatedAt(timeTest);

        Mockito.when(service.save(Mockito.any())).thenReturn(evento);

        mockMvc.perform(post(BASE_URL+ "events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        final String json = "{"
            + "\"id\":1,"
            + "\"titulo\":\"teste\","
            + "\"descricao\":\"teste\","
            + "\"local\":\"teste\","
            + "\"deleted\":false,"
            + "\"createdAt\":\"1761594605\""
            + "}";

        final String strTest = "teste";
        final Timestamp timeTest = Timestamp.from(Instant.now());

        EventoDTO evento = new EventoDTO();
        evento.setCreatedAt(timeTest);
        evento.setDeleted(false);
        evento.setDescricao(strTest);
        evento.setId(1L);
        evento.setLocal(strTest);
        evento.setTitulo(strTest);
        evento.setUpdatedAt(timeTest);
        Mockito.when(service.update(Mockito.any())).thenReturn(evento);

        mockMvc.perform(put(BASE_URL+ "events/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Mockito.when(service.delete(1L)).thenReturn(true);

        mockMvc.perform(delete(BASE_URL+ "events/1"))
            .andExpect(status().isOk());
    }
}