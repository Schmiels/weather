package de.fh.albsig.hs88455.spring_weather_app.services;

import org.json.JSONArray;
import org.json.JSONObject;

import de.fh.albsig.hs88455.spring_weather_app.models.Weather;

/**
 * 
 * @author svenb
 *
 */
public class WeatherParser {
	
	/**
	 *  WeatherParser class constructor
	 */
	public WeatherParser() {
		
	}
	
	/**
	 * Parse a JSONObject into a Weather-object. This method expects a JSONObject
	 * that fits the format of a openweatherapi-response.
	 * 
	 * @param jsonObj | JSONObject
	 * @return Weather-object
	 * 
	 * TODO: SvenBartos: einzelne Teile des Weather-Objektes in Methoden unterteilen (parseCoordFromJson()) sollte einfacher zu testen sein
	 */
	public Weather parseFromJSON(JSONObject jsonObj) {
		Weather weather = new Weather();
		
		// Extracting data from given JSONObject
		// TODO: Error Handling
		//	- what if there is no "main" etc
		JSONObject main = (JSONObject) jsonObj.get("main");
		JSONObject sys = (JSONObject) jsonObj.get("sys");
		JSONObject coord = (JSONObject) jsonObj.get("coord");
		JSONObject wind = (JSONObject) jsonObj.get("wind");		
		JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
		JSONObject weatherData = (JSONObject) weatherArray.get(0);
		
		if (jsonObj.has("id")) {
			weather.setCityId(jsonObj.getInt("id"));
		}
		if (jsonObj.has("name")) {
			weather.setCityName(jsonObj.getString("name"));
		}
		if (sys.has("country")) {
			weather.setCountryCode(sys.getString("country"));
		}
		if (weatherData.has("description")) {
			weather.setWeatherDesc(weatherData.getString("description"));
		} 
		if (main.has("temp")) {
			weather.setTemp(main.getDouble("temp"));
		}
		if (main.has("temp_max")) {
			weather.setTempMax(main.getDouble("temp_max"));
		}
		if (main.has("temp_min")) {
			weather.setTempMin(main.getDouble("temp_min"));
		}
		if (main.has("humidity")) {
			weather.setHumidity(main.getInt("humidity"));
		}
		if (main.has("pressure")) {
			weather.setPressure(main.getInt("pressure"));
		}
		if (sys.has("sunrise")) {
			weather.setSunrise(sys.getInt("sunrise"));
		}
		if (sys.has("sunset")) {
			weather.setSunset(sys.getInt("sunset"));
		}
		if (coord.has("lon")) {
			weather.setLon(coord.getDouble("lon"));
		}
		if (coord.has("lat")) {
			weather.setLat(coord.getDouble("lat"));
		}
		if (wind.has("deg")) {
			weather.setWindDeg(wind.getInt("deg"));
		}
		if (wind.has("speed")) {
			weather.setWindSpeed(wind.getDouble("speed"));
		}
		
		return weather;
	}
}
