package br.com.mirante.teste.demo_mirante.config;

import org.springframework.http.MediaType;


public class ProjectDefaults {
	
	//Geral
	public static final String BASE_URL = "/api/";
	public static final String CONSUMED_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE ;
	public static final String PRODUCED_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;
	public static final String MESSAGE_EVENTO_NAO_ENCONTRADO = "Evento com esse id não foi encontrado";
	public static final String MESSAGE_EVENTO_NAO_NULO = "Evento não pode ser nulo";
	public static final String MESSAGE_ID_NAO_NULO = "Id não pode ser nulo";
	public static final String MESSAGE_EVENTO_INSERIDO = "Evento inserido/atualizado";
	public static final String MESSAGE_EVENTO_DELETADO = "Evento removido com sucesso";
	public static final String MESSAGE_TIULO_OBRIGATORIO = "O título é obrigatório";
	public static final String MESSAGE_DESCRICAO_OBRIGATORIO = "A descrição é obrigatória";
	public static final String MESSAGE_LOCAL_OBRIGATORIO = "O Local é obrigatório";
	public static final String MESSAGE_CREATE_DH_OBRIGATORIO = "A data de criação é obrigatória";
	public static final String MESSAGE_UPDATE_DH_OBRIGATORIO = "A data de atualização é obrigatória";
	
}