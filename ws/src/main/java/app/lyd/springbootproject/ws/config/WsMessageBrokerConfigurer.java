package app.lyd.springbootproject.ws.config;

import app.lyd.springbootproject.ws.handler.WsChannelHandler;
import app.lyd.springbootproject.ws.interceptor.WsInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.SimpleBrokerRegistration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(WsProperties.class)
public class WsMessageBrokerConfigurer implements WebSocketMessageBrokerConfigurer {

    private final WsProperties properties;

    private final WsChannelHandler wsChannelHandler;

    private final HandshakeInterceptor handshakeInterceptor;


    public WsMessageBrokerConfigurer(WsProperties properties, WsChannelHandler wsChannelHandler, HandshakeInterceptor handshakeInterceptor) {
        this.properties = properties;
        this.wsChannelHandler = wsChannelHandler;
        this.handshakeInterceptor = handshakeInterceptor;
    }

    private ThreadPoolTaskScheduler heartbeatTaskScheduler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        SimpleBrokerRegistration registration = registry.enableSimpleBroker(properties.getSimpleBrokers().toArray(new String[0]));
        registry.setApplicationDestinationPrefixes(properties.getDestinations().toArray(new String[0]));
        if (properties.getHeartbeat().isEnable()) {
            heartbeatTaskScheduler = new ThreadPoolTaskScheduler();
            heartbeatTaskScheduler.setPoolSize(properties.getHeartbeat().getPoolSize());
            heartbeatTaskScheduler.setThreadNamePrefix("spb-ws-heartbeat-thread-");
            heartbeatTaskScheduler.initialize();
            long period = properties.getHeartbeat().getPeriod();
            registration.setHeartbeatValue(new long[]{period, period}).setTaskScheduler(heartbeatTaskScheduler);
        }
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration registration = registry.addEndpoint(properties.getEndpoints().toArray(new String[0]));
        if (properties.isEnableCors()) {
            registration.setAllowedOrigins("*");
        }
        registration.addInterceptors(handshakeInterceptor).withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new WsInterceptor(wsChannelHandler));
    }

    @EventListener
    @Async
    public void onContextClosedEvent(ContextClosedEvent event) {
        if (heartbeatTaskScheduler != null) {
            heartbeatTaskScheduler.shutdown();
        }
    }
}
