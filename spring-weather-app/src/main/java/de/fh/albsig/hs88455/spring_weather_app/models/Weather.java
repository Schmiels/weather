package de.fh.albsig.hs88455.spring_weather_app.models;

import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

//import org.json.JSONObject;

//import com.thoughtworks.xstream.XStream;

import javax.xml.bind.annotation.*;

/**
 * Class for holding weather data and displaying it as xml
 * 
 * @author Sven Bartos
 */
@XmlRootElement(name="weather")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {

	@XmlElement(name="cityId")
	private int cityId;
	@XmlElement(name="cityName")
	private String cityName;
	@XmlElement(name="countryCode")
	private String countryCode;
	@XmlElement(name="weatherDesc")
	private String weatherDesc;
	@XmlElement(name="temp")
	private double temp;
	@XmlElement(name="tempMax")
	private double tempMax;
	@XmlElement(name="tempMin")
	private double tempMin;
	@XmlElement(name="humidity")
	private int humidity;
	@XmlElement(name="pressure")
	private int pressure;
	@XmlElement(name="sunrise")
	private Date sunrise;
	@XmlElement(name="sunset")
	private Date sunset;
	@XmlElement(name="lon")
	private double lon;
	@XmlElement(name="lat")
	private double lat;
	@XmlElement(name="windDeg")
	private int windDeg;
	@XmlElement(name="windSpeed")
	private double windSpeed;
	
	/**
	 * Weather class constructor
	 */
	public Weather() {
		
	}

	/**
	 * Getter method for returning a weather object's cityId attribute.
	 * 
	 * @return cityId | integer value to identify the city 
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * Setter method for setting a weather object's cityId attribute.
	 * 
	 * @param cityId | integer value
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * Getter method for returning a weather object's cityName attribute.
	 * 
	 * @return cityName | city's name as a String
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Setter method for setting a weather object's cityName attribute.
	 * 
	 * @param cityName | String value
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * Getter method for returning a weather object's countryCode attribute.
	 * 
	 * @return countryCode | international country code displaying the country the
	 * city is located in as a String
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Setter method for setting a weather object's countryCode attribute.
	 * 
	 * @param countryCode | String value
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Getter method for returning a weather object's weatherDesc attribute.
	 * 
	 * @return weatherDesc | short description for the current weather situation as 
	 * a String
	 */
	public String getWeatherDesc() {
		return weatherDesc;
	}

	/**
	 * Setter method for setting a weather object's weatherDesc attribute.
	 * 
	 * @param weatherDesc | String value
	 */
	public void setWeatherDesc(String weatherDesc) {
		this.weatherDesc = weatherDesc;
	}

	/**
	 * Getter method for returning a weather object's temp attribute.
	 * 
	 * @return temp | current temperature for the queried city as a double
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * Setter method for setting a weather object's weatherDesc attribute.
	 * 
	 * @param temp | double value
	 */
	public void setTemp(double temp) {
		this.temp = temp;
	}

	/**
	 * Getter method for returning a weather object's tempMax attribute.
	 * 
	 * @return tempMax | maximum temperature on the queried day as a double
	 */
	public double getTempMax() {
		return tempMax;
	}

	/**
	 * Setter method for setting a weather object's tempMax attribute.
	 * 
	 * @param tempMax | double value
	 */
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}

	/**
	 * Getter method for returning a weather object's tempMin attribute.
	 * 
	 * @return tempMin | minimum temperature on the queried day as a double
	 */
	public double getTempMin() {
		return tempMin;
	}

	/**
	 * Setter method for setting a weather object's tempMin attribute.
	 * 
	 * @param tempMin | double value
	 */
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}

	/**
	 * Getter method for returning a weather object's humidity attribute.
	 * 
	 * @return humidity | integer value for the queried city
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * Setter method for setting a weather object's humidity attribute.
	 * 
	 * @param humidity | integer value
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * Getter method for returning a weather object's pressure attribute.
	 * 
	 * @return pressure | integer value for the air pressure in the queried city
	 */
	public int getPressure() {
		return pressure;
	}

	/**
	 * Setter method for setting a weather object's pressure attribute.
	 * 
	 * @param pressure | integer value
	 */
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	/**
	 * Getter method for returning a weather object's sunrise attribute.
	 * 
	 * @return sunrise | information about the queried city's sunrise time
	 */
	public Date getSunrise() {
		return sunrise;
	}

	/**
	 * Setter method for setting a weather object's sunrise attribute.
	 * 
	 * @param sunrise | Date
	 */
	public void setSunrise(int sunrise) {
		this.sunrise = new Date(sunrise);
	}

	/**
	 * Getter method for returning a weather object's sunset attribute.
	 * 
	 * @return sunset | information about the queried city's sunset time
	 */
	public Date getSunset() {
		return sunset;
	}

	/**
	 * Setter method for setting a weather object's sunset attribute.
	 * 
	 * @param sunset | Date
	 */
	public void setSunset(int sunset) {
		this.sunset = new Date(sunset);
	}

	/**
	 * Getter method for returning a weather object's lon attribute.
	 * 
	 * @return lon | city's longitude as a double
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * Setter method for setting a weather object's lon attribute.
	 * 
	 * @param lon | double value
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * Getter method for returning a weather object's lat attribute.
	 * 
	 * @return lat | city's latitude as a double
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Setter method for setting a weather object's lat attribute.
	 * 
	 * @param lat | double value
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Getter method for returning a weather object's windDeg attribute.
	 * 
	 * @return | integer value for the current wind degree
	 */
	public int getWindDeg() {
		return windDeg;
	}

	/**
	 * Setter method for setting a weather object's lat attribute.
	 * 
	 * @param windDeg | integer value
	 */
	public void setWindDeg(int windDeg) {
		this.windDeg = windDeg;
	}

	/**
	 * Getter method for returning a weather object's windSpeed attribute.
	 * 
	 * @return | current wind speed as a double
	 */
	public double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * Setter method for setting a weather object's windSpeed attribute.
	 * 
	 * @param | String value
	 */
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	/**
	 * Parsing a weather object into a String that represents an xml document.
	 * 
	 * @return | String value
	 */
	public String toXML() {
		try {
			StringWriter writer = new StringWriter();
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Weather.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, writer);
			
			return writer.getBuffer().toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
