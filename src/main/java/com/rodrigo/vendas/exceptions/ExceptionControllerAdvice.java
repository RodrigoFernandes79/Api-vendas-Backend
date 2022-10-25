package com.rodrigo.vendas.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigo.vendas.services.exceptions.DataViolationException;
import com.rodrigo.vendas.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiException> EntityNotFound(EntityNotFoundException e, HttpServletRequest request) {
		ApiException err = new ApiException();
		err.setTimestamp(LocalDateTime.now());
		err.setMensagem(e.getMessage());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(DataViolationException.class)
	public ResponseEntity<ApiException> dataIntegrityViolation(DataViolationException e, HttpServletRequest request) {

		ApiException err = new ApiException();
		err.setTimestamp(LocalDateTime.now());
		err.setMensagem(e.getMessage());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiException> methodArgumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		List<String> getErro = new ArrayList<>();
		e.getBindingResult().getAllErrors().forEach((obj) -> {
			String erroMensagem = obj.getDefaultMessage();
			getErro.add(erroMensagem);
		});

		ApiException err = new ApiException();
		err.setTimestamp(LocalDateTime.now());
		err.setMensagem(getErro.toString());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
}