package br.com.mirante.teste.demo_mirante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import br.com.mirante.teste.demo_mirante.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.BASE_URL;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.CONSUMED_MEDIA_TYPE;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_DELETADO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_INSERIDO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.PRODUCED_MEDIA_TYPE;

import java.util.Map;

@RestController
@RequestMapping(path = BASE_URL+"events",
    produces = PRODUCED_MEDIA_TYPE)
public class EventoController {

    @Autowired
    private EventoService service;

    @Operation(summary = "Listar eventos paginados", description = "Retorna uma lista paginada de eventos, com filtros e ordenação.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eventos listados com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<EventoDTO>> listAllPage(
        @Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
        @Parameter(description = "Tamanho da página") @RequestParam(defaultValue = "10") int size,
        @Parameter(description = "Campo para ordenação") @RequestParam(defaultValue = "id") String sortBy,
        @Parameter(description = "Direção da ordenação asc/desc") @RequestParam(defaultValue = "asc") String direction,
        @Parameter(description = "Filtrar eventos excluídos") @RequestParam(defaultValue = "false") Boolean deleted
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sortBy));
    	return ResponseEntity.ok(service.listByPage(pageable, deleted));
    }
    
    @Operation(summary = "Buscar evento por ID", description = "Retorna os dados de um evento específico.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento encontrado com sucesso")
    })
    @GetMapping(path = "/{id}")
    public ResponseEntity<EventoDTO> findById(@Parameter(description = "ID do evento") @PathVariable Long id) {
    	return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Criar novo evento", description = "Insere um novo evento no sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento criado com sucesso")
    })
    @PostMapping(consumes = CONSUMED_MEDIA_TYPE)
    public ResponseEntity<Map<String,Object>> create(@Parameter(description = "Dados do evento") @RequestBody EventoDTO evento) {
    	return ResponseEntity.ok(Map.of("message", MESSAGE_EVENTO_INSERIDO, "body" , service.save(evento)));
    }

    @Operation(summary = "Atualizar evento", description = "Atualiza os dados de um evento existente.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso")
    })
    @PutMapping(path = "/{id}",consumes = CONSUMED_MEDIA_TYPE)
    public ResponseEntity<Map<String,Object>> update(@Parameter(description = "Dados atualizados do evento") @RequestBody EventoDTO evento) {
    	return ResponseEntity.ok(Map.of("message", MESSAGE_EVENTO_INSERIDO, "body" , service.update(evento)));
    }

    @Operation(summary = "Excluir evento", description = "Remove logicamente um evento do sistema.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Evento excluído com sucesso"),
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable Long id) {
    	return ResponseEntity.ok(Map.of("message", MESSAGE_EVENTO_DELETADO, "body" , service.delete(id)));
    }
}
