package com.smu.multitenantapp.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smu.multitenantapp.tenant.entity.Claim;
import com.smu.multitenantapp.tenant.entity.CustomUserDetails;
import com.smu.multitenantapp.tenant.service.ClaimService;
import com.smu.multitenantapp.tenant.service.ClaimServiceImpl;
import com.smu.multitenantapp.tenant.service.EmailService;
import com.smu.multitenantapp.tenant.service.LookupService;
import com.smu.multitenantapp.tenant.service.UserService;
 

@Controller
@RequestMapping("/tenant/claim")
public class ClaimController {
	
    private static final Logger LOG = LoggerFactory.getLogger(ClaimController.class);

	@Autowired
	private ClaimService claimService;
    @Autowired
	private LookupService ls;
    @Autowired
	private UserService us;
    @Autowired
	private EmailService es;
	
    @RequestMapping("")
    public String claimIndex(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
    	List<Claim> claims = claimService.getUserClaims(currentPrincipalName);
    	model.addAttribute("claims", claims);
        getLoggedInUsername().ifPresent(f -> {
            model.addAttribute("userName", f);
        });
        getTenantName().ifPresent(d -> {
            model.addAttribute("tenantName", d);
        });
        return "tenant/claim";
    }
    
    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Claim claim = claimService.getClaimById(id.get());
            model.addAttribute("claim", claim);
        } else {
            model.addAttribute("claim", new Claim());
        }
        getLoggedInUsername().ifPresent(f -> {
            model.addAttribute("userName", f);
        });
        getTenantName().ifPresent(d -> {
            model.addAttribute("tenantName", d);
        });
        return "tenant/claim-add-edit";
    }

    @RequestMapping(path = "/createClaim", method = RequestMethod.POST)
    public String createOrUpdateClaim(Claim claim) {
		createOrUpdateClaimFlow(claim);
		
        return "redirect:/tenant/claim";
    }
    
	private void createOrUpdateClaimFlow(Claim claim) {
		String workflow = ls.findWorkflowByProcess("claim");
		if (workflow.equals("1")){
			createOrUpdateClaimFlow1(claim);
		}else if (workflow.equals("2")) {
			createOrUpdateClaimFlow2(claim);
		}
	}
    
    private void createOrUpdateClaimFlow1(Claim claim) {
    	es.emailApprovingManager("limgeokshan@live.com");
    	claimService.createOrUpdateClaim(claim);
	}
    private void createOrUpdateClaimFlow2(Claim claim) {
    	es.emailApprovingManager("gslim.2016@sis.smu.edu.sg");
    	claimService.createOrUpdateClaim(claim);
	}

	private Optional<String> getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = null;
        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            userName = userDetails.getUsername();
        }
        return Optional.ofNullable(userName);
    }

    private Optional<String> getTenantName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String tenantName = null;
        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            tenantName = userDetails.getTenant();
        }
        return Optional.ofNullable(tenantName);
    }
}
