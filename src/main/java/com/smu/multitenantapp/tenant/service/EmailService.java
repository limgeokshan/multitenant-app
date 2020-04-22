package com.smu.multitenantapp.tenant.service;

import java.util.List;

public interface EmailService {
	boolean emailAllManagers(List<String> emails);
	boolean emailApprovingManager(String email);
}
