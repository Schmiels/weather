package de.fh.albsig.hs88455.controller;

import de.fh.albsig.hs88455.exceptions.CustomOpenWeatherException;
import de.fh.albsig.hs88455.models.Weather;
import de.fh.albsig.hs88455.services.WeatherService;
import javax.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * WeatherController.
 *
 * @author svenb
 *
 */
@RestController
public class WeatherController {

  private static Logger logger = LogManager.getLogger(WeatherService.class);

  @Autowired
  private final WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  /**
   * Processing POST from index form.
   *
   * <p>Retrieve weather data with a POST request
   *
   * @param cityId cityId
   * @param cityName cityName
   * @param lat latitude
   * @param lon longitude
   * @param countryCodeForName countryCode for the cityName
   * @param countryCodeForZip countryCode for the zip
   * @return String
   */
  @PostMapping("/weather")
  public String weather(
      @RequestParam(value = "cityId", required = false, defaultValue = "0") final int cityId,
      @RequestParam(value = "zipCode", required = false, defaultValue = "0") final int zipCode,
      @RequestParam(value = "cityName", required = false, defaultValue = "") final String cityName,
      @RequestParam(value = "lat", required = false, defaultValue = "0") final double lat,
      @RequestParam(value = "lon", required = false, defaultValue = "0") final double lon,
      @RequestParam(value = "countryCodeForName", required = false,
          defaultValue = "") final String countryCodeForName,
      @RequestParam(value = "countryCodeForZIP", required = false,
          defaultValue = "") final String countryCodeForZip) {
    Weather weather = new Weather();
    try {
      if (cityId != 0) {
        weather = weatherService.getWeatherByCityId(cityId);
      } else if (!cityName.equals("") && !countryCodeForName.equals("")) {
        weather = weatherService.getWeatherByCityNameAndCountryCode(cityName, countryCodeForName);
      } else if (zipCode != 0 && !countryCodeForZip.equals("")) {
        weather = weatherService.getWeatherByZipCode(zipCode, countryCodeForZip);
      } else if (!cityName.equals("")) {
        weather = weatherService.getWeatherByCityName(cityName);
      } else if (lat != 0 && lon != 0) {
        weather = weatherService.getWeatherByCoords(lat, lon);
      } else {
        logger.error("The given input could not be used to request weather data.");
        return "The given input could not be used to request weather data.";
      }
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * REST endpoint for the cityId.
   *
   * @param cityId cityId
   * @return String
   */
  @RequestMapping("/weather/id/{id}")
  public String getWeatherByCityId(@PathVariable(value = "id", required = true) int cityId) {
    try {
      Weather weather = weatherService.getWeatherByCityId(cityId);
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * REST endpoint for the cityName.
   *
   * @param cityName cityName
   * @return String
   */
  @RequestMapping("/weather/name/{name}")
  public String getWeatherByCityName(@PathVariable(value = "name") String cityName) {
    try {
      Weather weather = weatherService.getWeatherByCityName(cityName);
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * REST endpoint for the cityName and countryCode.
   *
   * @param cityName cityName
   * @param countryCode countryCode
   * @return String
   */
  @RequestMapping("/weather/name/{name}/country/{countryCode}")
  public String getWeatherByCityNameAndCountryCode(@PathVariable(value = "name") String cityName,
      @PathVariable(value = "countryCode") String countryCode) {
    try {
      Weather weather = weatherService.getWeatherByCityNameAndCountryCode(cityName, countryCode);
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * REST endpoint for the coordinates.
   *
   * @param lat latidute
   * @param lon longitude
   * @return String
   */
  @RequestMapping("/weather/lat/{lat}/lon/{lon}")
  public String getWeatherByCoords(@PathVariable(value = "lat") double lat,
      @PathVariable(value = "lon") double lon) {
    try {
      Weather weather = weatherService.getWeatherByCoords(lat, lon);
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  /**
   * REST endpoint for the ZIP code.
   *
   * @param zipCode zipCode
   * @return String
   */
  @RequestMapping("/weather/zipcode/{zipCode}/country/{countryCode}")
  public String getWeatherByZipCode(@PathVariable(value = "zipCode") int zipCode,
      @PathVariable(value = "countryCode") String countryCode) {
    try {
      Weather weather = weatherService.getWeatherByZipCode(zipCode, countryCode);
      return weather.toXml();
    } catch (CustomOpenWeatherException e) {
      logger.error("Failed during processing request with given parameters");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    } catch (JAXBException e) {
      logger.error("Failed during marshalling the weather object.");
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

}
