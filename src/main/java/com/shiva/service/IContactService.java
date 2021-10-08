package com.shiva.service;

import java.util.List;

import com.shiva.entity.Contact;

public interface IContactService {

	public boolean saveContact(Contact contact);

	public boolean updateContact(Contact contact);

	public Contact getContactById(Integer contactId);

	public List<Contact> getAllContacts();

	public boolean deleteContactById(Integer contactId);
}
