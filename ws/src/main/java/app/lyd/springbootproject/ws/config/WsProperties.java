package app.lyd.springbootproject.ws.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "spb.ws")
public class WsProperties {

    private List<String> endpoints = Arrays.asList("ws-websocket");

    /**
     * server: @SendTo("/subscribe/greeting"), client: subscribe("/subscribe/greeting")<br>
     * server: @SendToUser("/subscribe/greeting"), client: subscribe("/user/subscribe/greeting")
     */
    private List<String> simpleBrokers = Arrays.asList("/subscribe");

    /**
     * server: @MessageMapping("/hello"), client: send("/app/hello")
     */
    private List<String> destinations = Arrays.asList("/app");

    private boolean enableCors = false;

    /**
     * if use default WsUserInterceptor, nameKey is required
     */
    private String nameKey = "name";

    private Heartbeat heartbeat = new Heartbeat();

    public static class Heartbeat {

        /**
         * whether enable heartbeat
         */
        private boolean enable = false;

        /**
         * heartbeat thread pool size
         */
        private int poolSize = 5;

        private long period = 1500;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getPoolSize() {
            return poolSize;
        }

        public void setPoolSize(int poolSize) {
            this.poolSize = poolSize;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    public List<String> getSimpleBrokers() {
        return simpleBrokers;
    }

    public void setSimpleBrokers(List<String> simpleBrokers) {
        this.simpleBrokers = simpleBrokers;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public boolean isEnableCors() {
        return enableCors;
    }

    public void setEnableCors(boolean enableCors) {
        this.enableCors = enableCors;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public Heartbeat getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Heartbeat heartbeat) {
        this.heartbeat = heartbeat;
    }
}