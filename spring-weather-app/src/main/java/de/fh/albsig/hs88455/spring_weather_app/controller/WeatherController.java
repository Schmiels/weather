package de.fh.albsig.hs88455.spring_weather_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.fh.albsig.hs88455.spring_weather_app.models.Weather;
import de.fh.albsig.hs88455.spring_weather_app.services.WeatherService;

@RestController
public class WeatherController {

	@GetMapping("/")
	public String home(@RequestParam(value="cityName", defaultValue="") String cityName) {
		WeatherService weatherService = new WeatherService();
		System.out.println(cityName);
		Weather weather = weatherService.getWeatherByCityName(cityName);
		
		return weather.toXML();
	}
	
	// TODO: SvenBartos: Eine Methode für Darstellung einer Startseite mit Optionen für Abfrage der Wetterdaten
	// TODO: SvenBartos: Eine Methode für Darstellung der abgefragten Daten (Objekt als XML)
}
