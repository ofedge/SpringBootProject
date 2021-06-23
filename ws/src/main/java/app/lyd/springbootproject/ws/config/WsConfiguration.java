package app.lyd.springbootproject.ws.config;

import app.lyd.springbootproject.ws.handler.WsChannelHandler;
import app.lyd.springbootproject.ws.handler.impl.DefaultWsChannelHandler;
import app.lyd.springbootproject.ws.interceptor.WsHandshakeInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
public class WsConfiguration {

    private final WsProperties properties;


    public WsConfiguration(WsProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean(WsChannelHandler.class)
    public WsChannelHandler wsChannelHandler() {
        return new DefaultWsChannelHandler(properties);
    }

    @Bean
    @ConditionalOnMissingBean(HandshakeInterceptor.class)
    public HandshakeInterceptor handshakeInterceptor() {
        return new WsHandshakeInterceptor();
    }
}
