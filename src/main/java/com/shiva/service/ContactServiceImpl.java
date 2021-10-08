package com.shiva.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shiva.entity.Contact;
import com.shiva.exception.RecordNotFoundException;
import com.shiva.repository.ContactRepository;

@Component
public class ContactServiceImpl implements IContactService {
	private static final String EX_MSG = "Contact Record Not Found With Given ID:";
	private ContactRepository contactRepo;

	public ContactServiceImpl(ContactRepository contactRepo) {
		this.contactRepo = contactRepo;
	}

	@Override
	public boolean saveContact(Contact contact) {
		Contact savedContact = contactRepo.save(contact);
		return savedContact != null ? true : false;
	}

	@Override
	public boolean updateContact(Contact contact) {
		Contact existingUser = contactRepo.findById(contact.getContactId())
				.orElseThrow(() -> new RecordNotFoundException(EX_MSG + contact.getContactId() + " to Update"));
		existingUser.setContactName(contact.getContactName());
		existingUser.setContactEmail(contact.getContactEmail());
		existingUser.setContactNumber(contact.getContactNumber());
		return contactRepo.save(existingUser) != null ? true : false;
	}

	@Override
	public Contact getContactById(Integer contactId) {
		return contactRepo.findById(contactId)
				.orElseThrow(() -> new RecordNotFoundException(EX_MSG + contactId + " to Display"));

	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		boolean isExists = contactRepo.existsById(contactId);
		if (isExists) {
			contactRepo.deleteById(contactId);
			return true;
		} else {
			throw new RecordNotFoundException(EX_MSG + contactId + " to Delete");
		}
	}

}
