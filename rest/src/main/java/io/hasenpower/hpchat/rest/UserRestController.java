package io.hasenpower.hpchat.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.User;
import io.hasenpower.hpchat.model.util.Clone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    private static final Chat CHAT = Chat.getInstance();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<User>> getUsers() {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(Set.class, User.class);
        Set<User> users = Clone.of(CHAT.getUsers(), type);

        if (users != null) {
            users.forEach(user -> {
                user.setUuid(null);
            });
        }

        return ResponseEntity.ok().body(users);
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{name}")
    public ResponseEntity<Void> getUser(@PathVariable("name") String name) {

        if(CHAT.userExists(name)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{name}")
    public ResponseEntity<User> createUser(@PathVariable String name) {
        return ResponseEntity.ok().body(CHAT.createUser(name));
    }
}
