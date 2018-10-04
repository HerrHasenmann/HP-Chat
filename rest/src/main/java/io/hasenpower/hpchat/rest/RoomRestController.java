package io.hasenpower.hpchat.rest;

import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/rooms")
public class RoomRestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Set<Room>> getRooms() {
        return ResponseEntity.ok().body(Chat.getInstance().getRooms());
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{name}")
    public ResponseEntity<Void> roomExists (@PathVariable("name") String name) {

        if(Chat.getInstance().roomExists(name)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRoom(@RequestBody Room room) {

        Chat.getInstance().addRoom(room);

        return ResponseEntity.ok().build();
    }

}
