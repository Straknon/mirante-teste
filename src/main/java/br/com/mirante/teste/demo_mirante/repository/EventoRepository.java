package br.com.mirante.teste.demo_mirante.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.teste.demo_mirante.entity.Evento;

@Repository
public interface EventoRepository  extends JpaRepository<Evento, Long>{

    @Transactional
	@Modifying
    @Query("UPDATE Evento "
    		+ "SET deleted = ?1 , "
            + "updatedAt = CURRENT_TIMESTAMP "
    		+ "WHERE "
    		+ "id = ?2 ")
    void deleteEventoFlag(boolean deleted, Long id);

    
    @Query("SELECT e FROM Evento as e "
    		+ " WHERE deleted = ?1 ")
    Page<Evento> listAllPage(boolean deleted, Pageable pageable);
}
