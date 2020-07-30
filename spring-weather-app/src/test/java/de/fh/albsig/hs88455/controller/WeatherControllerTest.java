package de.fh.albsig.hs88455.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import de.fh.albsig.hs88455.models.Weather;
import de.fh.albsig.hs88455.services.WeatherService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherController.class)
@TestInstance(Lifecycle.PER_CLASS)
class WeatherControllerTest {

  private final String outputFilePath = "src/test/resources/output.xml";

  String output;

  private Weather weather;

  @Autowired
  private MockMvc mvc;

  @MockBean
  private WeatherService service;

  @BeforeAll
  public void beforeAll() throws IOException {
    this.output = new String(Files.readAllBytes(Paths.get(outputFilePath)));
  }

  @BeforeEach
  public void BeforeEach() {
    this.weather = mock(Weather.class);
  }

  @Test
  @DisplayName("testing /weather/id/{id}")
  void testGetWeatherByCityId() throws Exception {
    when(service.getWeatherByCityId(Mockito.anyInt())).thenReturn(weather);
    when(weather.toXml()).thenReturn(output);
    this.mvc.perform(get("/weather/id/1")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.output)));
  }

  @Test
  @DisplayName("testing /weather/name/{name}")
  void testGetWeatherByCityName() throws Exception {
    when(service.getWeatherByCityName(Mockito.anyString())).thenReturn(weather);
    when(weather.toXml()).thenReturn(output);
    this.mvc.perform(get("/weather/name/any")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.output)));
  }

  @Test
  @DisplayName("testing /weather/name/{name}/country/{countryCode}")
  void testGetWeatherByCityNameAndCountryCode() throws Exception {
    when(service.getWeatherByCityNameAndCountryCode(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(weather);
    when(weather.toXml()).thenReturn(output);
    this.mvc.perform(get("/weather/name/any/country/any")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.output)));
  }

  @Test
  @DisplayName("testing /weather/lat/{lat}/lon/{lon}")
  void testGetWeatherByCoords() throws Exception {
    when(service.getWeatherByCoords(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(weather);
    when(weather.toXml()).thenReturn(output);
    this.mvc.perform(get("/weather/lat/1.0/lon/1.0")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.output)));
  }

  @Test
  @DisplayName("testing /weather/zipcode/{zipCode}/country/{countryCode}")
  void testGetWeatherByZipCode() throws Exception {
    when(service.getWeatherByZipCode(Mockito.anyInt(), Mockito.anyString())).thenReturn(weather);
    when(weather.toXml()).thenReturn(output);
    this.mvc.perform(get("/weather/zipcode/1/country/any")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString(this.output)));
  }

  @AfterEach
  public void afterEach() {
    this.weather = null;
  }

  @AfterAll
  public void afterAll() {
    this.output = null;
  }
}
