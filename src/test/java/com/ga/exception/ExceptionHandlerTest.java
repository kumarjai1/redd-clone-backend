package com.ga.exception;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class ExceptionHandlerTest {
	
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@InjectMocks
	private ExceptionHandler exceptionHandler;
	
	@Mock
	private UserDao userDao;
	
	@InjectMocks
	private User user;
	
	@Test
	public void handleException_Exception_Test() {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "test");
		ResponseEntity testResponse = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	
		ResponseEntity result = exceptionHandler.handleException(new Exception("test"));
		assertEquals(testResponse.getStatusCode(), result.getStatusCode());
	}
	
	@Test
	public void handleEntityNotFoundException_ResponseEntity_Test() {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "test");
		ResponseEntity testResponse = new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	
		ResponseEntity result = exceptionHandler.handleEntityNotFoundException(new Exception("test"));
		assertEquals(testResponse.getStatusCode(), result.getStatusCode());
	}
}
