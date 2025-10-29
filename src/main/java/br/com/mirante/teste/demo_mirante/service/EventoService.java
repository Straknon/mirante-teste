package br.com.mirante.teste.demo_mirante.service;


import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_NAO_ENCONTRADO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_NAO_NULO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_ID_NAO_NULO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import br.com.mirante.teste.demo_mirante.entity.Evento;
import br.com.mirante.teste.demo_mirante.exception.EventoNaoEncontradoException;
import br.com.mirante.teste.demo_mirante.repository.EventoRepository;
import jakarta.validation.constraints.NotNull;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


@Service
public class EventoService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);

    @Autowired
    private EventoRepository repository;

    public EventoDTO save(@NotNull(message = MESSAGE_EVENTO_NAO_NULO) EventoDTO dto){
		final Timestamp now = Timestamp.from(Instant.now());
        dto.setId(null);
        dto.setCreatedAt(now);
        dto.setUpdatedAt(now);

        //Realiza transformação para classe de entidade e Salva no Banco de dados
        Evento retorno = repository.save(new Evento(dto));

        //Pega o retorno e realiza transformação para objeto de saida para o front
        return new EventoDTO(retorno);
    }

    public EventoDTO update(@NotNull(message = MESSAGE_EVENTO_NAO_NULO) EventoDTO dto){
        if(dto.getId() == null){
            throw new IllegalArgumentException(MESSAGE_ID_NAO_NULO);
        }

		final Timestamp now = Timestamp.from(Instant.now());
        dto.setUpdatedAt(now);

        //Realiza transformação para classe de entidade e Atualiza no Banco de dados
        Evento retorno = repository.save(new Evento(dto));

        //Pega o retorno e realiza transformação para objeto de saida para o front
        return new EventoDTO(retorno);
    }

    public boolean delete(@NotNull(message = MESSAGE_ID_NAO_NULO) Long id){
        repository.deleteEventoFlag(true, id);
        return true;
    }

    public Page<EventoDTO> listByPage(@NotNull Pageable pageable, boolean deleted){
        Page<EventoDTO> pageDto = repository.listAllPage(deleted, pageable).map(e -> new EventoDTO(e));
        return pageDto;
    }

    public EventoDTO findById(@NotNull(message = MESSAGE_ID_NAO_NULO) Long id){
        Optional<Evento> opt = repository.findById(id);
        if(opt.isPresent()){
            return new EventoDTO(opt.get());
        }
        throw new EventoNaoEncontradoException(MESSAGE_EVENTO_NAO_ENCONTRADO);
    }

}
