package io.hasenpower.hpchat.websocket.config;

import io.hasenpower.hpchat.model.Chat;
import io.hasenpower.hpchat.model.User;
import io.hasenpower.hpchat.websocket.ChatWebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Arrays;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new ChatWebSocket(), "/rooms/*/join/*").addInterceptors(handshakeInterceptor()).setAllowedOrigins("*");
    }

    @Bean
    public HandshakeInterceptor handshakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> sessionAttributes) throws Exception {

                String path = serverHttpRequest.getURI().getPath();
                if (path.startsWith("/")){
                    path = path.substring(1);
                }
                String[] splittedPath = path.split("/");
                if(!splittedPath[0].equals("rooms") || !splittedPath[2].equals("join")){
                    throw new IllegalArgumentException("The path for the requested socket should follow this pattern: '/rooms/{roomName}/join/{uuid}'");
                }

                String roomName = splittedPath[1];
                String uuid = splittedPath[3];

                if(!Chat.getInstance().roomExists(roomName)){
                    throw new IllegalArgumentException("Room with name '" + roomName + "' does not exist.");
                }

                User user = Chat.getInstance().getUser(uuid);

                sessionAttributes.put("roomName", roomName);
                sessionAttributes.put("uuid", user.getUuid());

                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, @Nullable Exception e) {
                // Nothing to do
            }
        };
    }
}
