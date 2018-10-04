package io.hasenpower.hpchat.model;

import java.util.HashSet;
import java.util.Set;

public class Room {

    private String name;
    private Set<User> users;

    public Room() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        if(users == null) {
            users = new HashSet<>();
        }
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return name.equals(room.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
