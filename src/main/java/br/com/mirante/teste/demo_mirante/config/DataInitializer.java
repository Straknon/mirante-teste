package br.com.mirante.teste.demo_mirante.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.mirante.teste.demo_mirante.dto.EventoDTO;
import br.com.mirante.teste.demo_mirante.service.EventoService;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private EventoService service;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		if (e instanceof ContextRefreshedEvent) {
			onStart((ContextRefreshedEvent) e);
		}
	}

	private void onStart(ContextRefreshedEvent event) {
		// TODO Retirar (Somente para teste)
		criarMassaDados();
	}

	private void criarMassaDados() {
		for(int i = 1 ; i <= 100 ; i++){
			Timestamp now = Timestamp.from(Instant.now());
			EventoDTO event = new EventoDTO("titulo"+i, "descricao"+i, "local"+i, now, now);
			service.save(event);
		}
	}

}
