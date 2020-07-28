package de.fh.albsig.hs88455.services;

import de.fh.albsig.hs88455.models.Weather;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

// import de.fh.albsig.hs88455.spring_weather_app.models.Weather;

/**
 * Service for handling openweatherapi-calls.
 *
 * <p>TODO: Sven Bartos: Exception Handling fuer falschen Eingaben
 *
 * @author svenb
 */
public class WeatherService {

  private final String apiKey = "0faf94f3aa783dbf3030947b5c36cf41";

  /**
   * WeatherService class constructor.
   */
  public WeatherService() {

  }

  /**
   * Getter method for returning the used api-key.
   *
   * @return apiKey | String value
   */
  private String getApiKey() {
    return apiKey;
  }

  // TODO: SvenBartos: implementieren verschiedener Methoden zur Abfrage der
  // Wetterdaten

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

  private Weather requestWeatherData(String[] parameters, String[] types) {
    String respBody;

    CloseableHttpClient httpClient = HttpClients.createDefault();

    try {
      HttpGet get = new HttpGet(this.buildUrl(parameters, types));

      CloseableHttpResponse resp = httpClient.execute(get);

      // TODO: Handle status codes

      respBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);

      resp.close();

      JSONObject jsonObj = new JSONObject(respBody);

      WeatherParser weatherParser = new WeatherParser();
      Weather weather = weatherParser.parseFromJson(jsonObj);

      return weather;
    } catch (ClientProtocolException e) {
      // TODO: Exception Handling
      System.out.println(e.toString());
    } catch (IOException e) {
      // TODO: Exception Handling
      System.out.println(e.toString());
    }

    return new Weather();
  }

  /**
   * Get weather information by cityName (and countryCode).
   *
   * @param cityName cityName
   * @param countryCode countryCode
   * @return weather object
   */
  public Weather getWeatherByCityName(String cityName, String countryCode) {
    String[] parameters = {cityName.concat(",").concat(countryCode)};
    String[] types = {"q"};

    return this.requestWeatherData(parameters, types);
  }

  /**
   * Get weather information by cityId.
   *
   * @param cityId city's id
   * @return weather object
   */
  public Weather getWeatherByCityId(int cityId) {
    String[] parameters = {Integer.toString(cityId)};
    String[] types = {"id"};

    return this.requestWeatherData(parameters, types);
  }

  /**
   * Get weather information by coordinates.
   *
   * @param lat latitude
   * @param lon longitude
   * @return weather object
   */
  public Weather getWeatherByCoords(double lat, double lon) {
    String[] parameters = {Double.toString(lat), Double.toString(lon)};
    String[] types = {"lat", "lon"};

    return this.requestWeatherData(parameters, types);

  }
}
