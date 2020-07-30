package de.fh.albsig.hs88455.interfaces;

import de.fh.albsig.hs88455.exceptions.CustomOpenWeatherException;
import de.fh.albsig.hs88455.models.Weather;

public interface IWeatherService {
  String buildUrl(String[] parameters, String[] types);

  Weather requestWeatherData(String[] parameters, String[] types) throws CustomOpenWeatherException;

  Weather getWeatherByCityName(String cityName) throws CustomOpenWeatherException;

  Weather getWeatherByCityNameAndCountryCode(String cityName, String countryCode)
      throws CustomOpenWeatherException;

  Weather getWeatherByCityId(int cityId) throws CustomOpenWeatherException;

  Weather getWeatherByCoords(double lat, double lon) throws CustomOpenWeatherException;

  Weather getWeatherByZipCode(int zipCode, String countryCode) throws CustomOpenWeatherException;


}
