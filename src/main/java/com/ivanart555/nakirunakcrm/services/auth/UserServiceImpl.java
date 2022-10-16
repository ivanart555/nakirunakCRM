package com.ivanart555.nakirunakcrm.services.auth;

import com.ivanart555.nakirunakcrm.entities.auth.Authority;
import com.ivanart555.nakirunakcrm.entities.auth.User;
import com.ivanart555.nakirunakcrm.exception.ServiceException;
import com.ivanart555.nakirunakcrm.repository.auth.AuthorityRepository;
import com.ivanart555.nakirunakcrm.repository.auth.UserRepository;
import com.ivanart555.nakirunakcrm.security.AuthoritiesConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


    @Override
    public List<User> findAll() throws ServiceException {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        return userRepository.findById(id).orElseThrow(() -> new ServiceException(
                String.format("User with id %d not found!", id)));
    }

    @Override
    public int save(User user) {
        User createdUser = userRepository.save(user);
        log.info("User with id {} saved successfully.", createdUser.getId());
        return createdUser.getId();
    }

    @Override
    public void deleteById(Integer id) throws ServiceException {

    }

    public User createUserInformation(String login, String password) {

        User newUser = new User();
        Optional<Authority> authority = authorityRepository.findById(AuthoritiesConstants.ADMIN);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);

        newUser.setPassword(encryptedPassword);
        newUser.setEnabled(true);
        authorities.add(authority.get());
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }


}

