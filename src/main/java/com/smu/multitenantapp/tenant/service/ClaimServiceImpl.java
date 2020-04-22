package com.smu.multitenantapp.tenant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smu.multitenantapp.tenant.entity.Claim;
import com.smu.multitenantapp.tenant.entity.User;
import com.smu.multitenantapp.tenant.repository.ClaimRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

    private static final Logger LOG = LoggerFactory.getLogger(ClaimServiceImpl.class);

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
	private LookupService ls;
    @Autowired
	private UserService us;
    @Autowired
	private EmailService es;

	@Override
	public boolean approveClaim(Claim claim) {
		String workflow = ls.findWorkflowByProcess("claim");
		boolean result = false;
		if (workflow.equals("1")){
			result = approveClaim1(claim);
		}else if (workflow.equals("2")) {
			result = approveClaim2(claim);
		}
		return result;
	}
	
	
	private boolean approveClaim1(Claim claim) {
		//emails only the approving manager
		boolean result = es.emailApprovingManager("limgeokshan@live.com");
		return result;
	}
	
	private boolean approveClaim2(Claim claim) {
		//emails everyone
		ArrayList<String> emails = new ArrayList<>();
		emails.add("limgeokshan@gmail.com");
		emails.add("limgeokshan@live.com");
		boolean result = es.emailAllManagers(emails);
		return false;
	}


	@Override
	public List<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Claim> getUserClaims(String username) {
		List<Claim> claims = new ArrayList<>();
		User user = us.findByUsername(username);
		Long  user_id = user.getId();
	    claims = claimRepository.findByEmployee_id(user_id);
		return claims;
	}
	
	@Override
	public Claim createOrUpdateClaim(Claim claim) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User u= us.findByUsername(username);
		if (claim.getId() == null) {
			//u.getClaims().add(claim);
			claim.setEmployee(u);
			//us.save(u);
			claim = claimRepository.save(claim);
			return claim;
		}
		Optional<Claim> claimEntity = claimRepository.findById(claim.getId());
		if (claimEntity.isPresent()) {
			claim = claimEntity.get();
			claim.setAmount(claim.getAmount());
			claim.setDetails(claim.getDetails());
			claim.setEmployee(claim.getEmployee());
			claim.setClaimDate(claim.getClaimDate());
			//claim.setManagers(claim.getManager());
			claim = claimRepository.save(claim);
			return claim;
		}else {
			claim = claimRepository.save(claim);
			return claim;
		}
		
	}


	@Override
	public Claim getClaimById(Long id) {
       Optional<Claim> claimEntity = claimRepository.findById(id);
        if(claimEntity.isPresent()) {
            return claimEntity.get();
        }
		return null;
	}

}
