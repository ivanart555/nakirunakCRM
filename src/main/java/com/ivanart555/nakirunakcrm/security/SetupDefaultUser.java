package com.ivanart555.nakirunakcrm.security;

import com.ivanart555.nakirunakcrm.services.auth.UserDetailsService;
import com.ivanart555.nakirunakcrm.services.auth.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SetupDefaultUser {
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final Environment env;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        } catch (Exception e) {
            userService.createUserInformation("admin", env.getProperty("admin.pass"));
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("voke");

        } catch (Exception e) {
            userService.createUserInformation("voke", env.getProperty("user.pass"));
        }

    }

}
