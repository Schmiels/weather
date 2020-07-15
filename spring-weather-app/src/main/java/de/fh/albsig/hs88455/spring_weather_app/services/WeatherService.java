package de.fh.albsig.hs88455.spring_weather_app.services;

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

import de.fh.albsig.hs88455.spring_weather_app.models.Weather;

//import de.fh.albsig.hs88455.spring_weather_app.models.Weather;

/**
 * Service for handling openweatherapi-calls.
 * 
 * TODO: add more options/methods to perform the api-call with different parameters
 * 
 * @author svenb
 */
public class WeatherService {
	
	private String apiKey = "0faf94f3aa783dbf3030947b5c36cf41";

	/**
	 * WeatherService class constructor
	 */
	public WeatherService() {
		
	}
	
	// TODO: SvenBartos: implementieren verschiedener Methoden zur Abfrage der Wetterdaten 
	
	/**
	 * Execute an api-call with a city's name to retrieve the weather data for
	 * that city.
	 * 
	 * TODO: Sven Bartos: Methode soll nur URL für Request bauen
	 * 
	 * @param cityName | String value
	 * @return city's weather data as a JSONObject
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Weather getWeatherByCityName(String cityName) {
		
		String url;
		String respBody;
		
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http");
		builder.setHost("api.openweathermap.org");
		builder.setPath("/data/2.5/weather");
		builder.addParameter("q", cityName);
		builder.addParameter("appid", this.getApiKey());
		
		url = builder.toString();
		
		// TODO: SvenBartos: Auslagern in einzelne, wiederverwendbare Methode (requestData(url))
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		try {
			HttpGet get = new HttpGet(url);

			CloseableHttpResponse resp = httpClient.execute(get);
			
			// TODO: Handle status codes
			
			respBody = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
			
			resp.close();
			
			JSONObject jsonObj = new JSONObject(respBody);
			
			WeatherParser weatherParser = new WeatherParser();
			Weather weather = weatherParser.parseFromJSON(jsonObj);
			
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
	 * Getter method for returning the used api-key.
	 * 
	 * @return apiKey | String value
	 */
	private String getApiKey() {
		return apiKey;
	}

	/**
	 * Setter method for setting the api-key.
	 * 
	 * @param apiKey | String value
	 */
	private void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
