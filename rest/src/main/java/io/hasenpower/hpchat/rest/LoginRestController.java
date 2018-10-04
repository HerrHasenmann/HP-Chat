package io.hasenpower.hpchat.rest;

import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
@RequestMapping(path = "/login")
public class LoginRestController {

    @RequestMapping(method = RequestMethod.POST, path = "/{name}")
    public ResponseEntity<Void> login (@PathVariable("name") String name, HttpServletResponse response) {

        if (Chat.getInstance().userExists(name)) {
            return ResponseEntity.badRequest().build();
        } else {
            Chat.getInstance().addUser(new User(name));
            response.addCookie(new Cookie("user", Base64.getEncoder().encodeToString(name.getBytes())));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Set-Cookie","user="+Base64.getEncoder().encodeToString(name.getBytes()));
            return ResponseEntity.ok().headers(headers).build();
        }
    }
}
