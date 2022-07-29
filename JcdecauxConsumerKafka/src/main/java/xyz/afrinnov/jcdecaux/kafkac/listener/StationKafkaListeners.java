package xyz.afrinnov.jcdecaux.kafkac.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import xyz.afrinnov.jcdecaux.kafkac.messages.Station;

import java.util.HashMap;
import java.util.Map;

@Component
public class StationKafkaListeners {

    private static final Logger log = LoggerFactory.getLogger(StationKafkaListeners.class);
    private static Map<String, Station> mapOfStation = new HashMap<>();

    @KafkaListener(
            topics = "stationsjcdecaux",
            groupId = "afrinnov2",
            containerFactory = "stationFactory"
    )
    void listener(Station station) {

        // log.info(station.toString());

        String name = station.getName() + station.getContractName();
        int available_bike_stands = station.getAvailableBikeStands();
        if (!mapOfStation.containsKey(name)) {
            mapOfStation.put(name, station);
        }

        Station city_station = mapOfStation.get(name);
        int count_diff = available_bike_stands - city_station.getAvailableBikeStands();
        if (count_diff != 0) {
            mapOfStation.put(name, station);
            if (count_diff > 0)
                log.info(count_diff + "****" + station.getAddress() + "****" + station.getContractName());
        }
    }
}
