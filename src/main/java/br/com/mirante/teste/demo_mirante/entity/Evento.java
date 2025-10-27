package br.com.mirante.teste.demo_mirante.entity;

import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_EVENTO_NAO_NULO;

import java.sql.Timestamp;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_TIULO_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_DESCRICAO_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_LOCAL_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_CREATE_DH_OBRIGATORIO;
import static br.com.mirante.teste.demo_mirante.config.ProjectDefaults.MESSAGE_UPDATE_DH_OBRIGATORIO;

@Entity
public class Evento {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotBlank(message = MESSAGE_TIULO_OBRIGATORIO)
	@Column
	private String titulo;

    @NotBlank(message = MESSAGE_DESCRICAO_OBRIGATORIO)
	@Column
	private String descricao;

    @NotBlank(message = MESSAGE_LOCAL_OBRIGATORIO)
	@Column
	private String local;

    @NotNull(message = MESSAGE_CREATE_DH_OBRIGATORIO)
	@Column(name = "created_at")
	private Timestamp createdAt;
    
    @NotNull(message = MESSAGE_UPDATE_DH_OBRIGATORIO)
	@Column(name = "updated_at")
	private Timestamp updatedAt;

    @Column
    private boolean deleted;

    public Evento(){}

    public Evento(@NotNull(message = MESSAGE_EVENTO_NAO_NULO) EventoDTO dto){
        this.id = dto.getId();
        this.titulo = dto.getTitulo();
        this.descricao = dto.getDescricao();
        this.local = dto.getLocal();
        this.createdAt = dto.getCreatedAt();
        this.updatedAt = dto.getUpdatedAt();
        this.deleted = dto.isDeleted();
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
