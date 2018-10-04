package io.hasenpower.hpchat.rest;

import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(Chat.getInstance().getUsers());
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{name}")
    public ResponseEntity<Void> getUsers(@PathVariable("name") String name) {

        if(Chat.getInstance().userExists(name)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        Chat.getInstance().addUser(user);

        return ResponseEntity.ok().build();
    }
}
