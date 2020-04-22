package com.smu.multitenantapp.tenant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smu.multitenantapp.exception.LookupNotFoundException;
import com.smu.multitenantapp.tenant.entity.Lookup;
import com.smu.multitenantapp.tenant.repository.LookupRepository;

@Service
public class LookupServiceImpl implements LookupService {

    private static final Logger LOG = LoggerFactory.getLogger(LookupServiceImpl.class);

    @Autowired
    private LookupRepository lookupRepository;


	@Override
	public Lookup findByProcess(String process) {
		Lookup lookup = lookupRepository.findByProcess(process);
		if (lookup==null) {
			throw new LookupNotFoundException("Lookup value not found forprocess name="+process);
		}
		LOG.info("Found lookup value: "+lookup.getWorkflow()+ " with process name:"+lookup.getProcess() + "");
		return lookup;
	}


	@Override
	public String findWorkflowByProcess(String process) {
		return findByProcess(process).getWorkflow();
	}

}
