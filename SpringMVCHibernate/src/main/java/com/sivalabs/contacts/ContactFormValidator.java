package com.sivalabs.contacts;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author SivaLabs
 * 
 */
@Component("contactFormValidator")
public class ContactFormValidator implements Validator {
	public boolean supports(Class<?> clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	public void validate(Object model, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"required.name", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob",
				"required.dob", "dob is required.");
	}

}
