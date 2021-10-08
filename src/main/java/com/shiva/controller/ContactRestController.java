package com.shiva.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.constants.AppConstants;
import com.shiva.entity.Contact;
import com.shiva.props.AppProperties;
import com.shiva.service.IContactService;

@RestController
public class ContactRestController {
	Logger logger = LoggerFactory.getLogger(ContactRestController.class);
	private IContactService service;
	private AppProperties props;

	public ContactRestController(IContactService service, AppProperties props) {
		this.service = service;
		this.props = props;
	}
//Save Contact

	@PostMapping(value = "/saveContact")
	public ResponseEntity<String> addContact(@RequestBody Contact contact) {
		logger.debug("addContact(-) -> execution started");
		try {
			boolean saveContact = service.saveContact(contact);
			if (saveContact) {
				logger.info("addContact(-) -> contact saved");
				String succMsg = props.getMessages().get(AppConstants.SAVE_CONTACT_SUCCESS);
				return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			logger.error("Exception occured -> " + e.getMessage(), e);
		}
		logger.info("addContact(-) -> contact not saved");
		logger.debug("addContact(-) -> execution ended");
		String failMsg = props.getMessages().get(AppConstants.SAVE_CONTACT_FAILED);
		return new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//GetContact By Id

	@GetMapping(value = "/getContactById/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(name = "id") Integer contactId) {
		logger.debug("getContactById(-) -> execution started");
		Contact contactById = null;
		try {
			contactById = service.getContactById(contactId);
			if (contactById != null)
				logger.info("getContactById(-) -> record retrived");
			return new ResponseEntity<>(contactById, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("getContactById(-) -> Exception occured " + e.getMessage(), e);
		}
		logger.info("getContactById(-) -> record not retrived");
		logger.debug("getContactById(-) -> Execution ended");
		return new ResponseEntity<>(contactById, HttpStatus.INTERNAL_SERVER_ERROR);

	}

//Get All COntacts

	@GetMapping(value = "/getAllContacts")
	public ResponseEntity<List<Contact>> showAllContacts() {
		logger.debug("showAllContacts() -> Execution Started");
		List<Contact> allContacts = null;
		try {
			allContacts = service.getAllContacts();
			if (allContacts.isEmpty()) {
				logger.info("showAllContacts() -> Records Not Available");
				return new ResponseEntity<>(allContacts, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			logger.error("showAllContacts() ->  Exception Occured " + e.getMessage(), e);
		}
		logger.info("showAllContacts() -> Records retrived");
		logger.debug("showAllContacts() -> Execution Ended");
		return new ResponseEntity<>(allContacts, HttpStatus.OK);

	}

	// Update Contact

	@PutMapping(value = "/updateContact")
	public ResponseEntity<String> updateContact(@RequestBody Contact contact) {
		logger.debug("updateContact(-) -> Execution Started");
		try {
			boolean isUpdated = service.updateContact(contact);
			if (isUpdated) {
				logger.info("updateContact(-) -> contact updated");
				String updtMsg = props.getMessages().get(AppConstants.UPDATE_CONTACT_SUCCESS);
				return new ResponseEntity<>(updtMsg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("updateContact(-) -> exception occured  " + e.getMessage(), e);
		}
		logger.info("updateContact(-) -> contact not updated");
		logger.debug("updateContact(-) -> Execution ended");
		String updtFailMsg = props.getMessages().get(AppConstants.UPDATE_CONTACT_FAILED);
		return new ResponseEntity<>(updtFailMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//Delete Contact

	@DeleteMapping(value = "/deleteContactById/{contactId}")
	public ResponseEntity<String> removeContact(@PathVariable Integer contactId) {
		logger.debug("removeContact(-) -> Execution started");
		try {
			boolean deleteContactById = service.deleteContactById(contactId);
			if (deleteContactById) {
				logger.info("removeContact(-) -> contact deleted");
				String deleteMsg = props.getMessages().get(AppConstants.DELETE_CONTACT_SUCCESS);
				return new ResponseEntity<>(deleteMsg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("removeContact(-) -> Exception occured " + e.getMessage(), e);
		}
		logger.info("removeContact(-) -> contact not deleted");
		logger.debug("removeContact(-) -> Execution ended");
		String deleteFailMsg = props.getMessages().get(AppConstants.DELETE_CONTACT_FAILED);
		return new ResponseEntity<>(deleteFailMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
