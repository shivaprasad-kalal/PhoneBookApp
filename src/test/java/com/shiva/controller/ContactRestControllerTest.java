package com.shiva.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shiva.entity.Contact;
import com.shiva.props.AppProperties;
import com.shiva.service.IContactService;

@WebMvcTest(value = ContactRestController.class)
public class ContactRestControllerTest {
	@MockBean
	private IContactService service;
	@MockBean
	private AppProperties props;
	@Autowired
	private MockMvc mockMvc;

//save contact
	@Test
	public void test_saveContact() throws Exception {
		when(service.saveContact(Mockito.any())).thenReturn(true);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(201, status);
	}

	@Test
	public void test_saveContact_1() throws Exception {
		when(service.saveContact(Mockito.any())).thenReturn(false);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void test_saveContact_2() throws Exception {
		when(service.saveContact(Mockito.any())).thenThrow(RuntimeException.class);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	// get all contacts
	@Test
	public void test_getAllContacts() throws Exception {
		ArrayList<Contact> list = new ArrayList<Contact>();
		Contact c = new Contact(21, "shiva", "shiva@gmail.com", "9676076596");
		Contact c1 = new Contact(21, "prasad", "prasad@gmail.com", "9676076596");
		list.add(c);
		list.add(c1);
		when(service.getAllContacts()).thenReturn(list);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllContacts");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_getAllContacts_1() throws Exception {

		when(service.getAllContacts()).thenReturn(Collections.emptyList());

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllContacts");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void test_getAllContacts_2() throws Exception {

		when(service.getAllContacts()).thenThrow(RuntimeException.class);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllContacts");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	// get contact by id
	@Test
	public void test_getContactById() throws Exception {

		when(service.getContactById(72)).thenReturn(new Contact());

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_getContactById_1() throws Exception {

		when(service.getContactById(72)).thenReturn(null);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_getContactById_2() throws Exception {

		when(service.getContactById(72)).thenThrow(RuntimeException.class);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	// update contact
	@Test
	public void test_updateContact() throws Exception {
		when(service.updateContact(Mockito.any())).thenReturn(true);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_updateContact_1() throws Exception {
		when(service.updateContact(Mockito.any())).thenReturn(false);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void test_updateContact_2() throws Exception {
		when(service.updateContact(Mockito.any())).thenThrow(RuntimeException.class);
		Contact c = new Contact(101, "shiva", "shiva@gmail.com", "9676076596");
		// convert object to json
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/updateContact")
				.contentType("application/json").content(json);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	// delete contact by id
	@Test
	public void test_deleteContactById() throws Exception {

		when(service.deleteContactById(72)).thenReturn(true);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_deleteContactById_1() throws Exception {

		when(service.deleteContactById(72)).thenReturn(false);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

	@Test
	public void test_deleteContactById_2() throws Exception {

		when(service.deleteContactById(72)).thenThrow(RuntimeException.class);

		// prepare and send request to rest api method
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deleteContactById/72");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(500, status);
	}

}
