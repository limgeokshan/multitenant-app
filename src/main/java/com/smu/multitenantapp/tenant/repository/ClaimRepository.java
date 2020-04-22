package com.smu.multitenantapp.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smu.multitenantapp.tenant.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
  
    List<Claim> findByEmployee_id(Long user_id);
    List<Claim> findByManager_id(Long user_id);
}
