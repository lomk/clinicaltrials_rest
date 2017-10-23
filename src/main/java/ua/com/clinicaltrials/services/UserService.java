package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.User;

/**
 * Created by Igor on 17-Jun-16.
 */
public interface UserService {
    Iterable<User> listAllUsers();
    User getUserById(Integer id);
    void deleteUser(Integer id);
    void save(User user);
    User findByUsername(String username);
}
