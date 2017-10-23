package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Igor on 17-Jun-16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    //@Resource
    private UserRepository userRepository;

    @Autowired
    //@Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Iterable<User> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
