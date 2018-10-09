package io.hasenpower.hpchat.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Chat {

    private static Chat instance = null;

    private Set<Room> rooms;
    private Set<User> users;

    public static Chat getInstance() {
        if (instance == null) {
            synchronized (Chat.class) {
                if (instance == null) {
                    instance = new Chat();
                }
            }
        }
        return instance;
    }

    private Chat() {
        rooms = new HashSet<>();
        users = new HashSet<>();
    }

    /*
    ROOM
     */

    public Room createRoom(String name) {
        if (!roomExists(name)) {
            Room room = new Room();
            room.setName(name);

            rooms.add(room);
            return room;
        } else {
            throw new IllegalArgumentException("Room with name '" + name + "' already exists.");
        }
    }

    public boolean roomExists(String name) {
        return rooms.stream().anyMatch(room -> room.getName().equals(name));
    }

    public Room getRoom(String name) {
        return rooms.stream()
                .filter(room -> room.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Room with the name '" + name + "' does not exist."));
    }

    /*
    USER
     */

    public User createUser(String name) {
        if (!userExists(name)) {
            User user = new User();
            user.setName(name);
            user.setUuid(UUID.randomUUID().toString());

            users.add(user);
            return user;
        } else {
            throw new IllegalArgumentException("User with name '" + name + "' already exists.");
        }
    }

    public boolean userExists(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }

    public User getUser(String uuid) {
        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("A user with the uuid '" + uuid + "' does not exist."));
    }

    /*
    GETTER & SETTER
     */

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "rooms=" + rooms +
                ", users=" + users +
                '}';
    }
}
