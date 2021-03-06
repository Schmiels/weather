package de.fh.albsig.hs88455.services;

import de.fh.albsig.hs88455.models.Weather;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class for parsing weather data.
 *
 * @author svenb
 *
 */
public class WeatherParser {

  private static Logger logger = LogManager.getLogger(WeatherParser.class);

  /**
   * WeatherParser class constructor.
   */
  public WeatherParser() {

  }

  /**
   * Method to format given JSON into JSON that can be used to create a weather object.
   *
   * @param weatherData data received from API
   * @return JSONObject
   * @throws JSONException Exception thrown while formatting JSON
   */
  private JSONObject formatJson(final JSONObject weatherData) throws JSONException {
    try {
      JSONObject main = (JSONObject) weatherData.get("main");
      JSONObject sys = (JSONObject) weatherData.get("sys");
      JSONObject coord = (JSONObject) weatherData.get("coord");
      JSONObject wind = (JSONObject) weatherData.get("wind");
      JSONArray weatherArray = (JSONArray) weatherData.get("weather");
      JSONObject weatherInformation = (JSONObject) weatherArray.get(0);

      // ternary operators are necessary because some attributes are not set
      // for each city
      JSONObject obj = new JSONObject().put("cityId", weatherData.get("id"))
          .put("cityName", weatherData.get("name"))
          .put("countryCode", sys.get("country"))
          .put("weatherDesc", weatherInformation.get("description"))
          .put("temp", main.get("temp"))
          .put("tempMax", main.get("temp_max"))
          .put("tempMin", main.get("temp_min"))
          .put("tempMax", main.get("temp_max"))
          .put("pressure", main.has("pressure") ? main.get("pressure") : "")
          .put("sunrise", sys.has("sunrise") ? sys.get("sunrise") : "")
          .put("sunset", sys.has("sunset") ? sys.get("sunset") : "").put("lon", coord.get("lon"))
          .put("lat", coord.get("lat")).put("windDeg", wind.has("deg") ? wind.get("deg") : "")
          .put("windSpeed", wind.has("speed") ? wind.get("speed") : "");

      JSONObject weather = new JSONObject().put("weather", obj);

      return weather;
    } catch (JSONException e) {
      logger.error(e.getMessage());
      throw new JSONException(e.getMessage());
    }
  }

  /**
   * Parse a JSONObject into a Weather-object. This method expects a JSONObject that fits the format
   * of a openweatherapi-response.
   *
   * @param jsonObj | JSONObject
   * @return Weather-object
   * @throws JSONException Exception thrown while formatting JSON
   */
  public Weather parseFromJson(JSONObject jsonObj) throws JSONException {
    Weather weather = new Weather();

    JAXBContext jaxbContext;
    try {
      Map<String, Object> properties = new HashMap<>();
      properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
      properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, true);

      jaxbContext = JAXBContextFactory
          .createContext(new Class[] {Weather.class, ObjectFactory.class}, properties);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      StreamSource data = new StreamSource(new StringReader(this.formatJson(jsonObj).toString()));

      return (Weather) jaxbUnmarshaller.unmarshal(data);
    } catch (JAXBException e) {
      logger.error(e.getMessage());
    }

    return weather;
  }
}
