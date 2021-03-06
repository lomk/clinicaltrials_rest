package ua.com.clinicaltrials.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Role;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by Igor on 18-Jul-16.
 */
@RestController
@RequestMapping("/admin/role")
public class AdminRoleController {

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<?> listRole() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList == null){
            return new ResponseEntity(new CustomErrorType("No data found"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRole(@PathVariable("id") Integer id) {
        Role role = roleRepository.findOne(id);
        if (role == null){
            return new ResponseEntity(new CustomErrorType(
                    "Role with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody Role role){
        Role currentRole = roleRepository.findOne(id);
        if (currentRole == null){
            return new ResponseEntity(new CustomErrorType(
                    "Unable to upate. Role with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentRole.setName(role.getName());
        roleRepository.save(currentRole);
        return new ResponseEntity<>(currentRole, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addRole(@RequestBody Role role){

        if (role == null){
            return new ResponseEntity(new CustomErrorType("No role"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (role.getName() == null || role.getName().isEmpty()){
            return new ResponseEntity(new CustomErrorType("No role name"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (roleRepository.findByName(role.getName()) != null){
            return new ResponseEntity(new CustomErrorType("Unable to create. A role with address " +
                    role.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        roleRepository.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delRole(@PathVariable("id") Integer id) {
        Role role = roleRepository.findOne(id);
        if (role == null ){
            return new ResponseEntity(new CustomErrorType(
                    "Role with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            roleRepository.delete(role);
        } catch (Exception e){
            return new ResponseEntity(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
