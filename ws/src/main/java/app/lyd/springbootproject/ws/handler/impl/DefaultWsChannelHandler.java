package app.lyd.springbootproject.ws.handler.impl;

import app.lyd.springbootproject.ws.config.WsProperties;
import app.lyd.springbootproject.ws.handler.WsChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class DefaultWsChannelHandler implements WsChannelHandler {
    
    private final Logger logger = LoggerFactory.getLogger(DefaultWsChannelHandler.class);
    
    private final WsProperties properties;

    public DefaultWsChannelHandler(WsProperties properties) {
        this.properties = properties;
    }

    @Override
    public void handleConnect(StompHeaderAccessor accessor) {
        if (logger.isDebugEnabled()) {
            logger.debug("Using default DefaultWsChannelHandler");
        }
        accessor.setUser(() -> accessor.getFirstNativeHeader(properties.getNameKey()));
    }
}
