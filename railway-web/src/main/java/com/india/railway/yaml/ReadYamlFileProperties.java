package com.india.railway.yaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.passengers")
public class ReadYamlFileProperties {
    private String defaultSeat;
    private GlobalSettings globalSettings;
    private List<Passenger> list;

    @Setter
    @Getter
    public static class GlobalSettings {
        private boolean allowChildren;
        private int maxCount;

    }

    @Setter
    @Getter
    public static class Passenger {
        private String name;
        private int age;
        private Ticket ticket;

        @Setter
        @Getter
        public static class Ticket {
            private String type;
            private String number;

        }
    }

}
