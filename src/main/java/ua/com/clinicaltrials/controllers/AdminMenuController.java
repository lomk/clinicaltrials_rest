package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.errors.CustomErrorType;
import ua.com.clinicaltrials.repositories.MenuRepository;

@RestController
@RequestMapping("/admin/menu")
public class AdminMenuController {

    @Autowired
    MenuRepository menuRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Menu menu){
        Menu currentMenu = menuRepository.findOne(id);
        if (currentMenu == null){
            return new ResponseEntity<>(new CustomErrorType(
                    "Unable to upate. Menu with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        menuRepository.save(currentMenu);
        return new ResponseEntity<>(currentMenu, HttpStatus.OK);
    }

    @RequestMapping(value="add", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> add(@RequestBody Menu menu){

        if (menu == null){
            return new ResponseEntity<>(new CustomErrorType("No menu"),HttpStatus.NOT_ACCEPTABLE);
        }
        if (menu.getNameUa() == null ||
                menu.getNameEn() == null ||
                menu.getNameRu() == null ||
                menu.getNameUa().isEmpty() ||
                menu.getNameEn().isEmpty() ||
                menu.getNameRu().isEmpty()
                ) {
            return new ResponseEntity(new CustomErrorType("No menu name"),HttpStatus.NOT_ACCEPTABLE);
        }

        menuRepository.save(menu);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Menu menu = menuRepository.findOne(id);
        if (menu == null ){
            return new ResponseEntity<>(new CustomErrorType(
                    "Menu with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        try {
            menuRepository.delete(menu);
        } catch (Exception e){
            return new ResponseEntity<>(new CustomErrorType(
                    "SQL error."),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
