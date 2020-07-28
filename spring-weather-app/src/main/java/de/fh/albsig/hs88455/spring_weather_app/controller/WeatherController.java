package de.fh.albsig.hs88455.spring_weather_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import de.fh.albsig.hs88455.spring_weather_app.models.Weather;
import de.fh.albsig.hs88455.spring_weather_app.services.WeatherService;

@RestController
public class WeatherController {
	
	@PostMapping("/weather")
	public String weather(
			@RequestParam(value="cityId", required=false, defaultValue="0") int cityId,
			@RequestParam(value="cityName", required=false, defaultValue="") String cityName,
			@RequestParam(value="lat", required=false, defaultValue="0") double lat,
			@RequestParam(value="lon", required=false, defaultValue="0") double lon,
			@RequestParam(value="countryCode", required=false, defaultValue="") String countryCode) {
		Logger logger = LogManager.getLogger(WeatherController.class);
		
		WeatherService weatherService = new WeatherService();
		Weather weather = new Weather();
		
		
		if (cityId != 0) {
			weather = weatherService.getWeatherByCityId(cityId);
		} else if (!cityName.equals("")) {
			weather = weatherService.getWeatherByCityName(cityName, countryCode);
		} else if (lat != 0 && lon != 0) {
			weather = weatherService.getWeatherByCoords(lat, lon);
		} else {
			logger.error("The given input could not be used to request weather data.");
			return "The given input could not be used to request weather data.";
		}
		
		return weather.toXML();
	}
}
