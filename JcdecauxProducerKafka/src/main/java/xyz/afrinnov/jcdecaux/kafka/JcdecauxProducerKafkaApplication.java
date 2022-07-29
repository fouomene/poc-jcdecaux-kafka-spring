package xyz.afrinnov.jcdecaux.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import xyz.afrinnov.jcdecaux.kafka.messages.Station;

import java.util.Objects;

@SpringBootApplication
public class JcdecauxProducerKafkaApplication {

    private static final Logger log = LoggerFactory.getLogger(JcdecauxProducerKafkaApplication.class);

    @Value("${jcdecaux.api.key}")
    private String apiKeyJcdecaux;

    public static void main(String[] args) {
        SpringApplication.run(JcdecauxProducerKafkaApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(RestTemplate restTemplate, KafkaTemplate<String, Station> stationKafkaTemplate) {
        return args -> {

            Station[] stations;
            for (int j = 0; j < 18; j++) {

                stations = restTemplate.getForObject("https://api.jcdecaux.com/vls/v1/stations?apiKey=" + apiKeyJcdecaux, Station[].class);

                log.info(" Nbre de stations = " + Objects.requireNonNull(stations).length);

                for (Station station : stations) {
                    stationKafkaTemplate.send("stationsjcdecaux", station);
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
    }
}
