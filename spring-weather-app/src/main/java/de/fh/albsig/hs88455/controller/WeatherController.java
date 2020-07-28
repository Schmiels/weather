package de.fh.albsig.hs88455.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.fh.albsig.hs88455.models.Weather;
import de.fh.albsig.hs88455.services.WeatherService;

/**
 * WeatherController.
 *
 * @author svenb
 *
 */
@RestController
public class WeatherController {

	/**
	 * REST-Api endpoint.
	 *
	 * Retrieve weather data with a POST request
	 * <p>
	 * test
	 *
	 * @param cityId
	 * @param cityName
	 * @param lat
	 * @param lon
	 * @param countryCode
	 * @return String
	 */
	@PostMapping("/weather")
	public String weather(@RequestParam(value = "cityId", required = false, defaultValue = "0") final int cityId,
			@RequestParam(value = "cityName", required = false, defaultValue = "") final String cityName,
			@RequestParam(value = "lat", required = false, defaultValue = "0") final double lat,
			@RequestParam(value = "lon", required = false, defaultValue = "0") final double lon,
			@RequestParam(value = "countryCode", required = false, defaultValue = "") final String countryCode) {
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
			logger.error("The given input could not be used to request weather " + "data.");
			return "The given input could not be used to request weather " + "data.";
		}

		return weather.toXML();
	}
}
