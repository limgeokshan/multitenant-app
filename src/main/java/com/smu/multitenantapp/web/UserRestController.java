package com.smu.multitenantapp.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smu.multitenantapp.tenant.entity.User;
import com.smu.multitenantapp.tenant.service.UserService;
import com.smu.multitenantapp.util.TenantContextHolder;

/**
 * Rest Controller to handle all requests to the /user end point
 * 
 * @author Sunit Katkar, sunitkatkar@gmail.com
 *         (https://sunitkatkar.blogspot.com/)
 * @since ver 1.0 (May 2018)
 * @version 1.0
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * @param tenantId
     * @return
     */
    @GetMapping("/user/{tenantId}")
    @ResponseBody
    public List<String> getUsersForTenant(
            @PathVariable("tenantId") String tenantId) {
        TenantContextHolder.setTenantId(tenantId);
        List<User> users = userService.findAllUsers();
        List<String> userList = users.stream().map(result -> result.toString())
                .collect(Collectors.toList());
        return userList;
    }
    
    @GetMapping("/user-save")
    @ResponseBody
    public String saveUser() {
        TenantContextHolder.setTenantId("tenant_1");
        User user = new User();
        user.setActive(true);
        user.setPassword("test");
        user.setTenant("tenant_1");
        user.setUsername("testsave");
        user = userService.save(user);
        return user.getUsername();
    }

}
