package com.smu.multitenantapp.tenant.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceImplTest {
	@Autowired
	private EmailService es;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void emailSingleUserShouldReturnTrue() {
		es = new EmailServiceImpl();
		boolean emailResult=es.emailApprovingManager("limgeokshan@gmail.com");
		assertEquals(emailResult, true);
		
	}

	@Test
	public void emailMultipleUsersShouldReturnTrue() {
		es = new EmailServiceImpl();
		ArrayList<String> emails = new ArrayList<>();
		emails.add("limgeokshan@gmail.com");
		emails.add("limgeokshan@live.com");
		boolean emailResult=es.emailAllManagers(emails);
		assertEquals(emailResult, true);
		
	}
}
