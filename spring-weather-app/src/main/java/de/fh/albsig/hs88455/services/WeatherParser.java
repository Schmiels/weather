package de.fh.albsig.hs88455.services;

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
import org.json.JSONObject;

import de.fh.albsig.hs88455.models.Weather;

/**
 *
 * @author svenb
 *
 */
public class WeatherParser {

	/**
	 * WeatherParser class constructor.
	 */
	public WeatherParser() {

	}

	/**
	 *
	 *
	 * @param weatherData
	 * @return
	 */
	private JSONObject formatJSON(final JSONObject weatherData) {
		JSONObject main = (JSONObject) weatherData.get("main");
		JSONObject sys = (JSONObject) weatherData.get("sys");
		JSONObject coord = (JSONObject) weatherData.get("coord");
		JSONObject wind = (JSONObject) weatherData.get("wind");
		JSONArray weatherArray = (JSONArray) weatherData.get("weather");
		JSONObject weatherInformation = (JSONObject) weatherArray.get(0);

		// ternary operators are necessary because some attributes are not set
		// for each city
		JSONObject obj = new JSONObject().put("cityId", weatherData.get("id")).put("cityName", weatherData.get("name"))
				.put("countryCode", sys.get("country")).put("weatherDesc", weatherInformation.get("description"))
				.put("temp", main.get("temp")).put("tempMax", main.get("temp_max")).put("tempMin", main.get("temp_min"))
				.put("tempMax", main.get("temp_max")).put("pressure", main.has("pressure") ? main.get("pressure") : "")
				.put("sunrise", sys.has("sunrise") ? sys.get("sunrise") : "")
				.put("sunset", sys.has("sunset") ? sys.get("sunset") : "").put("lon", coord.get("lon"))
				.put("lat", coord.get("lat")).put("windDeg", wind.has("deg") ? wind.get("deg") : "")
				.put("windSpeed", wind.has("speed") ? wind.get("speed") : "");

		JSONObject weather = new JSONObject().put("weather", obj);

		return weather;
	}

	/**
	 * Parse a JSONObject into a Weather-object. This method expects a JSONObject
	 * that fits the format of a openweatherapi-response.
	 *
	 * @param jsonObj | JSONObject
	 * @return Weather-object
	 */
	public Weather parseFromJSON(JSONObject jsonObj) {
		Logger logger = LogManager.getLogger(WeatherParser.class);

		Weather weather = new Weather();

		JAXBContext jaxbContext;
		try {
			Map<String, Object> properties = new HashMap<>();
			properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
			properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, true);

			jaxbContext = JAXBContextFactory.createContext(new Class[] { Weather.class, ObjectFactory.class },
					properties);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			StreamSource data = new StreamSource(new StringReader(this.formatJSON(jsonObj).toString()));

			return (Weather) jaxbUnmarshaller.unmarshal(data);
		} catch (JAXBException e) {
			logger.error(e.getStackTrace());
		}

		return weather;
	}
}
