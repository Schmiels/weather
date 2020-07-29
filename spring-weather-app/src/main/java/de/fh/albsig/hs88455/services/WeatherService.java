package de.fh.albsig.hs88455.services;

import de.fh.albsig.hs88455.exceptions.CustomOpenWeatherException;
import de.fh.albsig.hs88455.models.Weather;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

// import de.fh.albsig.hs88455.spring_weather_app.models.Weather;

/**
 * Service for handling openweatherapi-calls.
 *
 * @author svenb
 */
public class WeatherService {

  private final String apiKey = "0faf94f3aa783dbf3030947b5c36cf41";

  private static Logger logger = LogManager.getLogger(WeatherService.class);

  /**
   * WeatherService class constructor.
   */
  public WeatherService() {

  }

  /**
   * Getter method for returning the used api-key.
   *
   * @return apiKey String value
   */
  private String getApiKey() {
    return apiKey;
  }

  /**
   * Building the URL for the API request.
   *
   * @param parameters url GET parameters
   * @param types GET parameters' names
   * @return String
   */
  private String buildUrl(String[] parameters, String[] types) {
    URIBuilder builder = new URIBuilder();
    builder.setScheme("http");
    builder.setHost("api.openweathermap.org");
    builder.setPath("/data/2.5/weather");

    for (int i = 0; i < types.length; i++) {
      builder.addParameter(types[i], parameters[i]);
    }
    builder.addParameter("appid", this.getApiKey());

    String url = builder.toString();

    return url;

  }

  /**
   * Performing the API request.
   *
   * @param parameters url GET Parameters
   * @param types GET parameters' types
   * @return weather object
   * @throws CustomOpenWeatherException Exception being thrown on openweather-api calls
   */
  private Weather requestWeatherData(String[] parameters, String[] types)
      throws CustomOpenWeatherException {
    String respBody;

    CloseableHttpClient httpClient = HttpClients.createDefault();

    try {
      HttpGet get = new HttpGet(this.buildUrl(parameters, types));

      CloseableHttpResponse resp = httpClient.execute(get);

      int statusCode = resp.getStatusLine().getStatusCode();
      if (statusCode != 200) {
        String errorMsg = "Error while performing API request: ";
        logger.error(errorMsg + "{}", statusCode);
        throw new CustomOpenWeatherException(errorMsg + statusCode, null);
      }

      respBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);

      resp.close();

      JSONObject jsonObj = new JSONObject(respBody);

      WeatherParser weatherParser = new WeatherParser();
      Weather weather = weatherParser.parseFromJson(jsonObj);

      return weather;
    } catch (IOException e) {
      logger.error(e.getMessage());
      throw new CustomOpenWeatherException("An Error accured while performing the API request", e);
    }
  }

  /**
   * Get weather information by cityName (and countryCode).
   *
   * @param cityName cityName
   * @param countryCode countryCode
   * @return weather object
   * @throws CustomOpenWeatherException Exception being thrown on openweather-api calls
   */
  public Weather getWeatherByCityName(String cityName, String countryCode)
      throws CustomOpenWeatherException {
    String[] parameters = {cityName.concat(",").concat(countryCode)};
    String[] types = {"q"};

    try {
      return this.requestWeatherData(parameters, types);
    } catch (CustomOpenWeatherException e) {
      throw new CustomOpenWeatherException(e.getMessage(), e);
    }
  }

  /**
   * Get weather information by cityId.
   *
   * @param cityId city's id
   * @return weather object
   * @throws CustomOpenWeatherException Exception being thrown on openweather-api calls
   */
  public Weather getWeatherByCityId(int cityId) throws CustomOpenWeatherException {
    String[] parameters = {Integer.toString(cityId)};
    String[] types = {"id"};

    try {
      return this.requestWeatherData(parameters, types);
    } catch (CustomOpenWeatherException e) {
      throw new CustomOpenWeatherException(e.getMessage(), e);
    }
  }

  /**
   * Get weather information by coordinates.
   *
   * @param lat latitude
   * @param lon longitude
   * @return weather object
   * @throws CustomOpenWeatherException Exception being thrown on openweather-api calls
   */
  public Weather getWeatherByCoords(double lat, double lon) throws CustomOpenWeatherException {
    String[] parameters = {Double.toString(lat), Double.toString(lon)};
    String[] types = {"lat", "lon"};

    try {
      return this.requestWeatherData(parameters, types);
    } catch (CustomOpenWeatherException e) {
      throw new CustomOpenWeatherException(e.getMessage(), e);
    }
  }
}
