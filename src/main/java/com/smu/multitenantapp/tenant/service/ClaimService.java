package com.smu.multitenantapp.tenant.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smu.multitenantapp.tenant.entity.Claim;

public interface ClaimService {
	List<Claim> getAllClaims();
	List<Claim> getUserClaims(String user);
	boolean approveClaim(Claim claim);
	Claim createOrUpdateClaim(Claim claim);
	Claim getClaimById(Long long1);
}
