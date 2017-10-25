package ua.com.clinicaltrials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.User;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> listUser() {
        List<User> userList = (List) userService.listAllUsers();
        if (userList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        userList.stream().forEach(user -> user.setPassword(null));
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null){
            return new ResponseEntity(new CustomErrorType(
                    "User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user){
        User currentUser = userService.getUserById(id);
        if (currentUser == null){
            return new ResponseEntity(new CustomErrorType(
                    "Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            return new ResponseEntity(new CustomErrorType("No user set"),HttpStatus.NOT_ACCEPTABLE);
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setPasswordConfirm(user.getPasswordConfirm());
        currentUser.setRole(user.getRole());
        userService.save(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addUser(@RequestBody User user){

        if (user == null){
            return new ResponseEntity(new CustomErrorType("No user set"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()){
            return new ResponseEntity(new CustomErrorType("No user set"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity(new CustomErrorType("Unable to create. A user with username " +
                    user.getUsername() + " already exist."),HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delUser(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null ){
            return new ResponseEntity(new CustomErrorType(
                    "User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            userService.deleteUser(user.getId());
        } catch (Exception e){
            return new ResponseEntity(new CustomErrorType(
                    "User used in rules."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
