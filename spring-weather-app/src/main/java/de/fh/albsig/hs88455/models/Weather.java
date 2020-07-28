package de.fh.albsig.hs88455.models;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for holding weather data and displaying it as xml.
 *
 * <p>TODO: Sven Bartos: sunset sunsire broken
 *
 * @author Sven Bartos
 */
@XmlRootElement(name = "weather")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Logger.
   */
  private static Logger logger = LogManager.getLogger(Weather.class);

  /**
   * A city's id.
   */
  @XmlElement(name = "cityId")
  private int cityId;
  /**
   * Name of a city.
   */
  @XmlElement(name = "cityName")
  private String cityName;
  /**
   * Country code of the country the city is located in.
   */
  @XmlElement(name = "countryCode")
  private String countryCode;
  /**
   * Current weather description.
   */
  @XmlElement(name = "weatherDesc")
  private String weatherDesc;
  /**
   * Current temperature.
   */
  @XmlElement(name = "temp")
  private double temp;
  /**
   * Maximum temperature.
   */
  @XmlElement(name = "tempMax")
  private double tempMax;
  /**
   * Minimum temperature.
   */
  @XmlElement(name = "tempMin")
  private double tempMin;
  /**
   * Current humidity.
   */
  @XmlElement(name = "humidity")
  private int humidity;
  /**
   * Current air pressure.
   */
  @XmlElement(name = "pressure")
  private int pressure;
  /**
   * Sunrise time.
   */
  @XmlElement(name = "sunrise")
  private Date sunrise;
  /**
   * Sunset time.
   */
  @XmlElement(name = "sunset")
  private Date sunset;
  /**
   * Location's longitude.
   */
  @XmlElement(name = "lon")
  private double lon;
  /**
   * Location's latitude.
   */
  @XmlElement(name = "lat")
  private double lat;
  /**
   * Current wind degree.
   */
  @XmlElement(name = "windDeg")
  private int windDeg;
  /**
   * Current wind speed.
   */
  @XmlElement(name = "windSpeed")
  private double windSpeed;

  /**
   * Weather class constructor.
   */
  public Weather() {

  }

  /**
   * Getter method for returning a weather object's cityId attribute.
   *
   * @return cityId integer value to identify the city
   */
  public int getCityId() {
    return cityId;
  }

  /**
   * Setter method for setting a weather object's cityId attribute.
   *
   * @param id integer value
   */
  public void setCityId(final int id) {
    this.cityId = id;
  }

  /**
   * Getter method for returning a weather object's cityName attribute.
   *
   * @return cityName city's name as a String
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Setter method for setting a weather object's cityName attribute.
   *
   * @param name String value
   */
  public void setCityName(final String name) {
    this.cityName = name;
  }

  /**
   * Getter method for returning a weather object's countryCode attribute.
   *
   * @return countryCode international country code displaying the country the city is located in
   *         as a String
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Setter method for setting a weather object's countryCode attribute.
   *
   * @param countryCode String value
   */
  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * Getter method for returning a weather object's weatherDesc attribute.
   *
   * @return weatherDesc short description for the current weather situation as a String
   */
  public String getWeatherDesc() {
    return weatherDesc;
  }

  /**
   * Setter method for setting a weather object's weatherDesc attribute.
   *
   * @param weatherDesc String value
   */
  public void setWeatherDesc(final String weatherDesc) {
    this.weatherDesc = weatherDesc;
  }

  /**
   * Getter method for returning a weather object's temp attribute.
   *
   * @return temp current temperature for the queried city as a double
   */
  public double getTemp() {
    return temp;
  }

  /**
   * Setter method for setting a weather object's weatherDesc attribute.
   *
   * @param temperature double value
   */
  public void setTemp(final double temperature) {
    this.temp = temperature;
  }

  /**
   * Getter method for returning a weather object's tempMax attribute.
   *
   * @return tempMax maximum temperature on the queried day as a double
   */
  public double getTempMax() {
    return tempMax;
  }

  /**
   * Setter method for setting a weather object's tempMax attribute.
   *
   * @param maxTemperature double value
   */
  public void setTempMax(final double maxTemperature) {
    this.tempMax = maxTemperature;
  }

  /**
   * Getter method for returning a weather object's tempMin attribute.
   *
   * @return tempMin minimum temperature on the queried day as a double
   */
  public double getTempMin() {
    return tempMin;
  }

  /**
   * Setter method for setting a weather object's tempMin attribute.
   *
   * @param minTemperature double value
   */
  public void setTempMin(final double minTemperature) {
    this.tempMin = minTemperature;
  }

  /**
   * Getter method for returning a weather object's humidity attribute.
   *
   * @return humidity integer value for the queried city
   */
  public int getHumidity() {
    return humidity;
  }

  /**
   * Setter method for setting a weather object's humidity attribute.
   *
   * @param humidity integer value
   */
  public void setHumidity(final int humidity) {
    this.humidity = humidity;
  }

  /**
   * Getter method for returning a weather object's pressure attribute.
   *
   * @return pressure integer value for the air pressure in the queried city
   */
  public int getPressure() {
    return pressure;
  }

  /**
   * Setter method for setting a weather object's pressure attribute.
   *
   * @param pressure integer value
   */
  public void setPressure(final int pressure) {
    this.pressure = pressure;
  }

  /**
   * Getter method for returning a weather object's sunrise attribute.
   *
   * @return sunrise information about the queried city's sunrise time
   */
  public Date getSunrise() {
    return sunrise;
  }

  /**
   * Setter method for setting a weather object's sunrise attribute.
   *
   * @param sunRise Date
   */
  public void setSunrise(final int sunRise) {
    logger.info("sunrise as in:" + Integer.toString(sunRise));
    this.sunrise = new Date(sunRise);
    logger.info("sunrise as date:" + this.sunrise);
  }

  /**
   * Getter method for returning a weather object's sunset attribute.
   *
   * @return sunset information about the queried city's sunset time
   */
  public Date getSunset() {
    return sunset;
  }

  /**
   * Setter method for setting a weather object's sunset attribute.
   *
   * @param sunSet Date
   */
  public void setSunset(final int sunSet) {
    logger.info("sunset as in:" + Integer.toString(sunSet));
    this.sunset = new Date(sunSet);
    logger.info("sunset as date:" + this.sunset);
  }

  /**
   * Getter method for returning a weather object's lon attribute.
   *
   * @return lon city's longitude as a double
   */
  public double getLon() {
    return lon;
  }

  /**
   * Setter method for setting a weather object's lon attribute.
   *
   * @param longitude double value
   */
  public void setLon(final double longitude) {
    this.lon = longitude;
  }

  /**
   * Getter method for returning a weather object's lat attribute.
   *
   * @return lat city's latitude as a double
   */
  public double getLat() {
    return lat;
  }

  /**
   * Setter method for setting a weather object's lat attribute.
   *
   * @param latitude double value
   */
  public void setLat(final double latitude) {
    this.lat = latitude;
  }

  /**
   * Getter method for returning a weather object's windDeg attribute.
   *
   * @return integer value for the current wind degree
   */
  public int getWindDeg() {
    return windDeg;
  }

  /**
   * Setter method for setting a weather object's lat attribute.
   *
   * @param windDegree integer value
   */
  public void setWindDeg(final int windDegree) {
    this.windDeg = windDegree;
  }

  /**
   * Getter method for returning a weather object's windSpeed attribute.
   *
   * @return current wind speed as a double
   */
  public double getWindSpeed() {
    return windSpeed;
  }

  /**
   * Setter method for setting a weather object's windSpeed attribute.
   *
   * @param windSpeed String value
   */
  public void setWindSpeed(final double windSpeed) {
    this.windSpeed = windSpeed;
  }

  /**
   * Parsing a weather object into a String that represents an xml document.
   *
   * @return String value
   */
  public String toXml() {
    try {
      StringWriter writer = new StringWriter();

      JAXBContext jaxbContext = JAXBContext.newInstance(Weather.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.marshal(this, writer);

      return writer.getBuffer().toString();
    } catch (JAXBException e) {
      logger.error(e.getStackTrace());
    }

    return "";
  }
}
