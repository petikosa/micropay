package com.micro.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping(value = "/anonymous", method = GET)
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @PreAuthorize("hasRole('ROLE_user')")
    @RequestMapping(value = "/user", method = GET)
    public ResponseEntity<String> getUser()
    {
        return ResponseEntity.ok("Hello Secured with user role.");
    }
}