package com.bookstore.book.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
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
class BookControllerTest {

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

//	@Test
//	public void add_address_test_with_positive_input_value() throws Exception {
//	String addAddressUri = "/books/add";
//	objectMapper = new ObjectMapper();
//	String addressDto = objectMapper.writeValueAsString(new Book());
//	  HttpHeaders headers = new HttpHeaders();
//	       headers.set("X-COM-PERSIST", "true");  
//	MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//	.post(addAddressUri)
//	.header("token", headers)
//	.content(addressDto)
//	.contentType(MediaType.APPLICATION_JSON);
//	Mockito.when(generateToken.generateToken(3L)).thenReturn(token);
//	Mockito.when(bookServiceImpl.addBook(book, token)(Mockito.any(), Mockito.anyString()))
//	.thenReturn(true));
//
//	ResultActions resultActions = mockMvc.perform(requestBuilder);
//	MvcResult result = resultActions.andReturn();
//	String fetchedResult = result.getResponse().getContentAsString();
//	System.out.println("result :::::::::::::::::::::::::::::" + fetchedResult);;
//	}

//	 @Test
//	    public void shouldGenerateAuthToken() throws Exception {
//	        String token = TokenAuthenBOOK_UPDATE_URLticationService.createToken("john");
//
//	        assertNotNull(token);
//	        mvc.perform(MockMvcRequestBuilders.get("/test").header("Authorization", token)).andExpect(status().isOk());
//	    }

//	public void updateBook() throws Exception {
//		objectMapper = new ObjectMapper();
//		String book = objectMapper.writeValueAsString(new Book());
//		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BOOK_UPDATE_URL).content(book)
//				.contentType(MediaType.APPLICATION_JSON);
//		MockHttpServletResponse fetchedResponse = mockMvc.perform(requestBuilder).andReturn().getResponse();
//		Assert.assertEquals("Quantity Updatd", fetchedResponse.getStatus(), HttpStatus.BAD_REQUEST.value());
//	}
}