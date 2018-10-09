package io.hasenpower.hpchat.websocket;

import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.Message;
import io.hasenpower.hpchat.model.Room;
import io.hasenpower.hpchat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatWebSocket extends TextWebSocketHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatWebSocket.class);
    private static final Chat CHAT = Chat.getInstance();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        User user = CHAT.getUser(String.valueOf(session.getAttributes().get("uuid")));
        Room room = CHAT.getRoom(String.valueOf(session.getAttributes().get("roomName")));

        LOGGER.info("User '" + user.getName() + "' joined Room '" + room.getName() + "' ");

        user.setSession(session);
        room.login(user);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        User user = CHAT.getUser(String.valueOf(session.getAttributes().get("uuid")));
        Room room = CHAT.getRoom(String.valueOf(session.getAttributes().get("roomName")));
        Message message = new Message(textMessage.getPayload(), user);

        room.sendMessageToOther(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = CHAT.getUser(String.valueOf(session.getAttributes().get("uuid")));
        Room room = CHAT.getRoom(String.valueOf(session.getAttributes().get("roomName")));

        LOGGER.info("User(" + user.getName() + ") has left the Room '" + room.getName() + "' ");

        room.logout(user);
    }


}
