package com.bookstore;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bookstore.controller.BookController;
import com.bookstore.model.Book;
import com.bookstore.service.BookServiceImpl;
import com.bookstore.util.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = BookController.class)
class BookStoreApplicationTests {

	@InjectMocks
	private BookController bookController;
	@Mock
	private BookServiceImpl bookServiceImpl;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	Book book = new Book();
	String token = null;
	private static final String BOOK_ADD_URL = "/books/add";
	private static final String BOOK_UPDATE_URL = "/books/update";

	@Autowired
	private JwtTokenUtil generateToken;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	public void add_address_test_with_positive_input_value() throws Exception {
	String addAddressUri = "/books/add";
	objectMapper = new ObjectMapper();
	String bookDTO = objectMapper.writeValueAsString(new Book());
	  HttpHeaders headers = new HttpHeaders();
	       headers.set("X-COM-PERSIST", "true");  
	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
	.post(addAddressUri)
	.header("token", headers)
	.content(bookDTO)
	.contentType(MediaType.APPLICATION_JSON);
	Mockito.when(generateToken.generateToken(3L)).thenReturn(token);
	Mockito.when(bookServiceImpl.addBook(book, Mockito.anyString())(Mockito.any(), Mockito.anyString()))
	.thenReturn(true);
	ResultActions resultActions = mockMvc.perform(requestBuilder);
	MvcResult result = resultActions.andReturn();
	String fetchedResult = result.getResponse().getContentAsString();
	System.out.println("result :::::::::::::::::::::::::::::" + fetchedResult);;
	}
}