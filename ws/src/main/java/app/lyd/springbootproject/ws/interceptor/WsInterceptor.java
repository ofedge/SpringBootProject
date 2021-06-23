package app.lyd.springbootproject.ws.interceptor;

import app.lyd.springbootproject.ws.WsUtils;
import app.lyd.springbootproject.ws.handler.WsChannelHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

public class WsInterceptor implements ChannelInterceptor {

    private final WsChannelHandler handler;


    public WsInterceptor(WsChannelHandler handler) {
        this.handler = handler;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = WsUtils.getStompHeaderAccessor(message);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            handler.handleConnect(accessor);
        }
        return message;
    }
}
