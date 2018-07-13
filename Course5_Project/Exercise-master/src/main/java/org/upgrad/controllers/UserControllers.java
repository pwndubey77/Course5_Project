package org.upgrad.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.repositories.UserRepository;
import org.upgrad.services.UserService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class UserControllers {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/user/signup")
    public ResponseEntity<String> signup(@RequestParam String userName, @RequestParam String password, @RequestParam String email,
                                         @RequestParam String firstName, String lastName, String aboutMe,
                                         @RequestParam String dob,  String contactNumber, @RequestParam String country) throws ParseException {
        String usernameByUser =userRepository.findUsername(userName);

        String emailByUser = userService.getUserEmail(email);
        if (usernameByUser != null) {
            return new ResponseEntity<>("Username already exist ",HttpStatus.UNAUTHORIZED);

        } else if (emailByUser != null) {
            return new ResponseEntity<>("Email already exist ",HttpStatus.UNAUTHORIZED);

        }
        else
         {
           String sha256hex = Hashing.sha256().hashString(password, Charsets.US_ASCII).toString();
           userService.createUser(userName,email,sha256hex);
           int id=userService.getUserID(userName);
           String date=dob;
           Date dob1=new SimpleDateFormat("dd-mm-yyyy").parse(date);
           userService.userProfileDetails(id,firstName,lastName,aboutMe,dob1,contactNumber,country);
           System.out.println(id);
           return new ResponseEntity<>("User created!", HttpStatus.OK);
         }
    }

    @PostMapping("/users/signin")
    public ResponseEntity<String> signup(@RequestParam String userName, @RequestParam String password,HttpSession session)
    {

            String usernameByUser = userRepository.findUsername(userName);
            String passwordByUser = Hashing.sha256().hashString(password, Charsets.US_ASCII).toString();
            //String passwordByUser=userService.getPassword(password);
            String userRole=userService.getCurrentUserRole(userName);
            if (usernameByUser == null) {
                return new ResponseEntity<>("Username not exist ",HttpStatus.UNAUTHORIZED);
             }
            if(passwordByUser==null)
            {
                return new ResponseEntity<>("Invalid Credential ",HttpStatus.UNAUTHORIZED);

            }
            if(userRole!=null)
            {
                session.setAttribute("currUser",userName);
                return new ResponseEntity<>("You have logged in as admin!", HttpStatus.OK);
            }

            session.setAttribute("currUser",userName);

            return new ResponseEntity<>("You have logged in successfully!", HttpStatus.OK);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> signout(HttpSession session)
    {
            if(session.getAttribute("currUser")==null) return new ResponseEntity<>("You are currently not logged in",HttpStatus.UNAUTHORIZED);
            else
            {
            session.removeAttribute("currUser");
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);
            }
    }

    @GetMapping("/user/userprofile/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable("userId") int userId,HttpSession session)
    {
        if(session.getAttribute("currUser")==null)
            return new ResponseEntity<>("Please Login first to access this endpoint",HttpStatus.UNAUTHORIZED);
        else
        {
          if(userService.getUserProfile(userId).iterator().hasNext())
          {
              return new ResponseEntity<>(userService.getUserProfile(userId),HttpStatus.OK);
          }
          else
              return new ResponseEntity<>("User Profile not found!",HttpStatus.FORBIDDEN);
        }
    }
    

}
