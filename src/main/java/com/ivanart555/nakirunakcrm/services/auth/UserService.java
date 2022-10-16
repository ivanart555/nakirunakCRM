package com.ivanart555.nakirunakcrm.services.auth;


import com.ivanart555.nakirunakcrm.entities.auth.User;
import com.ivanart555.nakirunakcrm.services.generic.GenericService;

public interface UserService extends GenericService<User, Integer> {

    User createUserInformation(String login, String password);

}
