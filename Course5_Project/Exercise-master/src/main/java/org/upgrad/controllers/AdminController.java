package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.repositories.UserRepository;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @DeleteMapping("admin/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId, HttpSession session)
    {
        if(session.getAttribute("currUser")==null)
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.FORBIDDEN);
        else
        {
            String user=session.getAttribute("currUser").toString();
            String role=userService.getCurrentUserRole(user);
            if(role.equalsIgnoreCase("ADMIN"))
            {
                userService.deleteUserProfileById(userId);
                userService.deleteUserById(userId);
                return new ResponseEntity<>("User with userId  "+userId+" deleted Successfully!", HttpStatus.OK);
            }
            else
                return new ResponseEntity<>("You do not have rights to delete a user!", HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/admin/users/all")
    public ResponseEntity<?> getAllUsers(HttpSession session)
    {
        if(session.getAttribute("currUser")==null)
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.FORBIDDEN);
        String user=session.getAttribute("currUser").toString();
        String role=userService.getCurrentUserRole(user);
        if(role.equalsIgnoreCase("ADMIN"))
        {

            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);

        }
        else
            return new ResponseEntity<>("You do not have rights to access all users!", HttpStatus.FORBIDDEN);

    }

    @PostMapping("/admin/category")
    public ResponseEntity<?> categoriesCreation(@RequestParam  String categoryTitle,@RequestParam String description,HttpSession session)
    {
        if(session.getAttribute("currUser")==null)
            return new ResponseEntity<>("Please Login first to access this endpoint", HttpStatus.FORBIDDEN);
        String user=session.getAttribute("currUser").toString();
        String role=userService.getCurrentUserRole(user);
        if(role.equalsIgnoreCase("ADMIN"))
        {
            userService.addCategory(categoryTitle,description);
            return new ResponseEntity<>(categoryTitle+" Created Successfully!",HttpStatus.OK);

        }
        else
            return new ResponseEntity<>("You do not have rights to add categories.", HttpStatus.FORBIDDEN);

    }

}
