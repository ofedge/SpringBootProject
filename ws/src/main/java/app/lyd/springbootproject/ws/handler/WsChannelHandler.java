package app.lyd.springbootproject.ws.handler;

import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public interface WsChannelHandler {

    /**
     * handle stomp connect event
     *
     * @param accessor
     */
    void handleConnect(StompHeaderAccessor accessor);
}
