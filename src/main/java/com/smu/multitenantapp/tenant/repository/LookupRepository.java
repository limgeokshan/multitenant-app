package com.smu.multitenantapp.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smu.multitenantapp.tenant.entity.Lookup;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {

    /**
     * Custom / Named query for selecting a lookup object based on processName
     * 
     * @param process
     * @return
     */
    @Query("select p from Lookup p where p.process = :process")
    Lookup findByProcess(@Param("process") String process);
}
