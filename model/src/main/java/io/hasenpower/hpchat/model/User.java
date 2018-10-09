package io.hasenpower.hpchat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    static final User SERVER = new User("Server");

    private String uuid;
    private String name;
    private WebSocketSession session;

    User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void sendMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(message);
            session.sendMessage(new TextMessage(json));
        } catch (java.io.IOException e) {
            LOGGER.error("Could not parse to json string. " + message, e);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    @JsonIgnore
    public WebSocketSession getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
