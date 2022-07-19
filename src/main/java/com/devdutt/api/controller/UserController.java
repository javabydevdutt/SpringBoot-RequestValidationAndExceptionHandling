package com.devdutt.api.controller;

import com.devdutt.api.dto.UserDTO;
import com.devdutt.api.exception.UserNotFoundException;
import com.devdutt.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserDTO userDTO) {
        ResponseEntity<String> resp = null;
        try {
            int result = userService.registerUser(userDTO);
            resp = new ResponseEntity<String>("User: '" + result + "' created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to save User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<?> getAllUserDetails() {
        ResponseEntity<?> resp = null;
        List<UserDTO> userDTO = null;
        try {
            userDTO = userService.fetchAllUser();
            resp = new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to get User Details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        ResponseEntity<?> resp = null;
        try {
            UserDTO dto = userService.fetchUserById(id);
            resp = new ResponseEntity<UserDTO>(dto, HttpStatus.OK);
        } catch (UserNotFoundException ufe) {
            throw ufe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>("Unable to find User", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}
