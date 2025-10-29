package br.com.mirante.teste.demo_mirante.dto;

import java.sql.Timestamp;

import br.com.mirante.teste.demo_mirante.entity.Evento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_TIULO_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_NAO_NULO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_DESCRICAO_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_LOCAL_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_CREATE_DH_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_UPDATE_DH_OBRIGATORIO;

public class EventoDTO {
	private Long id;
	private String titulo;
	private String descricao;
	private String local;
	private Timestamp createdAt;
	private Timestamp updatedAt;
    private boolean deleted;

    public EventoDTO(){}

    //Constructor de massa de teste
    public EventoDTO( @NotBlank(message = MESSAGE_TIULO_OBRIGATORIO) String titulo,
            @NotBlank(message = MESSAGE_DESCRICAO_OBRIGATORIO) String descricao,
            @NotBlank(message = MESSAGE_LOCAL_OBRIGATORIO) String local,
            @NotNull(message = MESSAGE_CREATE_DH_OBRIGATORIO) Timestamp createdAt,
            @NotNull(message = MESSAGE_UPDATE_DH_OBRIGATORIO) Timestamp updatedAt) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //Construtor demais operações
    public EventoDTO(@NotNull(message = MESSAGE_EVENTO_NAO_NULO) Evento evento){
        this.createdAt = evento.getCreatedAt();
        this.id = evento.getId();
        this.deleted = evento.isDeleted();
        this.titulo = evento.getTitulo();
        this.local = evento.getLocal();
        this.updatedAt = evento.getUpdatedAt();
        this.descricao = evento.getDescricao();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
}
