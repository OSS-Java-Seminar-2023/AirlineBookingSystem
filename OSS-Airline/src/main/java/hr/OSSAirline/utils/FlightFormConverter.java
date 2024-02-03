package hr.OSSAirline.utils;

import hr.OSSAirline.dto.FlightDto;
import hr.OSSAirline.models.FlightForm;
import hr.OSSAirline.services.AirplaneService;
import hr.OSSAirline.services.AirportService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor

public class FlightFormConverter {

    public final AirportService airportService;
    public final AirplaneService airplaneService;

    public Pair<FlightDto, Map<String, Float>> convertToFlightDto(FlightForm flightForm) {
        var flight = new FlightDto();
        flight.setFlightNumber(flightForm.flightNumber);
        flight.setFrom(airportService.getAirportById(flightForm.fromAirportId));
        flight.setTo(airportService.getAirportById(flightForm.toAirportId));
        flight.setAirplane(airplaneService.getAirplaneById(flightForm.airplaneId));
        flight.setDate(flightForm.date);
        flight.setTime(Time.valueOf(flightForm.time));
        flight.setGate(flightForm.gate);
        flight.setDuration(Time.valueOf(flightForm.duration));
        var prices = new HashMap<String, Float>();
        prices.put("firstClassPrice", flightForm.getFirstClassPrice().floatValue());
        prices.put("businessClassPrice", flightForm.getBusinessClassPrice().floatValue());
        prices.put("economyClassPrice", flightForm.getEconomyClassPrice().floatValue());

        return new Pair<>(flight, prices);
    }
}
