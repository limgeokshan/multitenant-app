package com.smu.multitenantapp.tenant.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smu.multitenantapp.tenant.entity.Lookup;

public interface LookupService {
	
    @Query("select p from Lookup p where p.process = :process")
    Lookup findByProcess(@Param("process") String process);
    
    @Query("select p from Lookup p where p.process = :process")
    String findWorkflowByProcess(@Param("process") String process);

}
