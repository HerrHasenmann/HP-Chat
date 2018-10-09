package io.hasenpower.hpchat.rest;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.Room;
import io.hasenpower.hpchat.model.User;
import io.hasenpower.hpchat.model.util.Clone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rooms")
public class RoomRestController {

    private static final Chat CHAT = Chat.getInstance();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Room>> getRooms() {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(Set.class, Room.class);
        Set<Room> rooms = Clone.of(CHAT.getRooms(), type);

        if (rooms != null) {
            rooms.forEach(room -> {
                room.getUsers().forEach(user -> {
                    user.setUuid(null);
                });
            });
        }

        return ResponseEntity.ok().body(rooms);
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{name}")
    public ResponseEntity<Void> roomExists (@PathVariable("name") String name) {

        if(CHAT.roomExists(name)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{name}")
    public ResponseEntity<Room> createRoom(@PathVariable String name) {
        return ResponseEntity.ok().body(CHAT.createRoom(name));
    }

}
