package com.ga.exception;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class ExceptionHandlerTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private ExceptionHandler exceptionHandler;
	
	@Mock
	private HttpHeaders headers;

	
	@Mock
	private WebRequest request;
	
	@Mock
	private MethodArgumentNotValidException methodArgumentNotValidException;
	
	
	@Test
	public void handleException_Exception_Test() {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "test");
		
		ResponseEntity<ErrorResponse> testResponse = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	
		ResponseEntity<ErrorResponse> result = exceptionHandler.handleException(new Exception("test"));
		assertEquals(testResponse.getStatusCode(), result.getStatusCode());
	}
	
	@Test
	public void handleEntityNotFoundException_ResponseEntity_Test() {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "test");
		ResponseEntity testResponse = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	
		ResponseEntity result = exceptionHandler.handleEntityNotFoundException(new Exception("test"));
		assertEquals(testResponse.getStatusCode(), result.getStatusCode());
	}
	
	@Test
	public void handleMethodArgumentNotValid_ReturnsResponseEntityObject_Test() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		HttpHeaders headers = new HttpHeaders();
		headers.add("status", "bad request");
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request");
		ResponseEntity<Object> errorResponseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		
		ResponseEntity<Object>  testResponse = exceptionHandler.handleMethodArgumentNotValid(methodArgumentNotValidException, 
				headers, HttpStatus.BAD_REQUEST, request);
		
		System.out.println(testResponse.getStatusCode());
		assertNotNull(testResponse.getStatusCode());
		assertEquals(testResponse.getStatusCode(), errorResponseEntity.getStatusCode());
	}
}
