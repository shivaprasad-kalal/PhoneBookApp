package com.shiva.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Entity
@Table(name = "CONTACT_DETAILS")
public class Contact {
	@Id
	@GeneratedValue
	@Column(name = "CONTACT_ID", length = 10)

	private Integer contactId;

	@Column(name = "CONTACT_NAME", length = 50)
	private String contactName;

	@Column(name = "CONTACT_EMAIL", length = 50)
	private String contactEmail;

	@Column(name = "CONTACT_NUMBER", length = 10)
	private String contactNumber;
}
