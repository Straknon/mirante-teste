package br.com.mirante.teste.demo_mirante.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResourceExceptionHandler {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceExceptionHandler.class);

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest req) {

		List<String> messageList = new ArrayList<>();
		if (e instanceof ConstraintViolationException) {
			Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) e).getConstraintViolations();
			Set<String> messages = new HashSet<>(violations.size());
			messages.addAll(violations.stream().map(constraintViolation -> constraintViolation.getMessage())
					.collect(Collectors.toList()));

			for (Iterator<String> it = messages.iterator(); it.hasNext();) {
				messageList.add(it.next());
			}
		}


		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), messageList, req.getRequestURI());
		
		final String warnMessage = "[ConstraintViolationException] - ".concat(err.toString());
		LOGGER.warn(warnMessage);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), messagesList, req.getRequestURI());
				
		final String warnMessage = "[IllegalArgumentException] - ".concat(err.toString());
		LOGGER.warn(warnMessage);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(EventoNaoEncontradoException.class)
	public ResponseEntity<StandardError> eventoException(EventoNaoEncontradoException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), messagesList, req.getRequestURI());
				
		final String warnMessage = "[EventoException] - ".concat(err.toString());
		LOGGER.warn(warnMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<StandardError> nullPointer(NullPointerException e, HttpServletRequest req) {
		List<String> messagesList = new ArrayList<>();
		messagesList.add(e.getMessage());
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), messagesList, req.getRequestURI());
		
		final String warnMessage = "[NullPointerException] - ".concat(err.toString());
		LOGGER.warn(warnMessage);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
