package com.india.railway.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse badCredentialsException(BadCredentialsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "username or password is incorrect");
	}
	// org.springframework.security.authentication.BadCredentialsException
	// @ExceptionHandler(value = NullPointerException.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public @ResponseBody ErrorResponse handleException(NullPointerException ex) {
	// return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
	// ex.getCause().toString());
	// }

	@ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(InvalidDataAccessApiUsageException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "id must not be null");
	}

	@ExceptionHandler(value = NoSuchEmployeeExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(NoSuchEmployeeExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = EmployeeAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(EmployeeAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = NoSuchPassengerExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(NoSuchPassengerExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = PassengerAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(PassengerAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

}
