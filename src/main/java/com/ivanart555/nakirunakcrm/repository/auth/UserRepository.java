package com.ivanart555.nakirunakcrm.repository.auth;

import com.ivanart555.nakirunakcrm.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findOneByLogin(String login);

}
