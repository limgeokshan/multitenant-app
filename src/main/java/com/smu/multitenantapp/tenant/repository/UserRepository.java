package com.smu.multitenantapp.tenant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smu.multitenantapp.tenant.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom / Named query for selecting a user based on the username and
     * tenant id
     * 
     * @param username
     * @param tenant
     * @return
     */
    @Query("select p from User p where p.username = :username and p.tenant = :tenant")
    User findByUsernameAndTenantname(@Param("username") String username,
            @Param("tenant") String tenant);

    @Query("select a from User a JOIN a.roles r where r.role=:role")
    List<User> findByRole(@Param("role") String role);
    
    User findByUsername(@Param("username") String username);
 
}
