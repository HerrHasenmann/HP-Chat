package io.hasenpower.hpchat.model;

import java.util.HashSet;
import java.util.Set;

public class Room {

    private String name;
    private Set<User> users;

    Room() {
        users = new HashSet<>();
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

    public void login(User user) {
        users.add(user);
        sendMessageToOther(new Message(user.getName() + " joined the room", User.SERVER));
    }

    public void logout(User user) {
        users.removeIf(userInSet -> userInSet.getUuid().equals(user.getUuid()));
        sendMessageToOther(new Message(user.getName() + " left the room", User.SERVER));
    }

    /**
     * Send a given message to all users except the sender
     *
     * @param message Message to send to other users
     */
    public void sendMessageToOther(Message message) {
        users.stream().filter(user -> !user.getUuid().equals(message.getSender().getUuid())).forEach(user -> user.sendMessage(message));
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
