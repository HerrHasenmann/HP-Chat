package io.hasenpower.hpchat.model;

import java.util.HashSet;
import java.util.Set;

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

    public void addRoom(Room room) {
        if (roomExists(room)) {
            throw new IllegalArgumentException("Room with name '" + room.getName() + "' already exists.");
        }
        rooms.add(room);
    }

    public boolean roomExists(String name) {
        Room room = new Room();
        room.setName(name);

        return roomExists(room);
    }

    public boolean roomExists(Room room) {
        return rooms.contains(room);
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addUser(User user) {
        if (userExists(user)) {
            throw new IllegalArgumentException("User with name '" + user.getName() + "' already exists.");
        }
        users.add(user);
    }

    public boolean userExists(String name) {
        User user = new User();
        user.setName(name);

        return userExists(user);
    }

    public boolean userExists(User user) {
        return users.contains(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
