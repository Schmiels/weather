Praktische Abgabe für das Modul "Professionelle Java Entwicklung - Software Engineering Instrumente".
Diese Anwendung ruft Daten von der openweathermap-api als JSON ab und gibt die Daten als XML aus.

# Installation
```
git clone https://github.com/Schmiels/weather.git
cd weather
cd spring-weather-app
mvn clean install
```

# Usage
```
mvn spring-boot:run
```
Die Daten lassen sich entweder über ein Forumaler eingeben und absenden oder über die REST-Endpunkte abfragen.

## Formular
Das Formular ist unter (https://localhost:8080) zu finden.

## REST-Endpunkte
- /weather/id/{id}
  - id: ID der Stadt
  - /weather/id/2945024 
- /weather/name/{name}
  - name: Name der Stadt
  - /weather/name/Braunschweig
- /weather/name/{name}/country/{countryCode}
  - name: Name der Stadt
  - countryCode: Ländercode des Landes entsprechend ISO 3166
  - /weather/name/Braunschweig/country/DE
- /weather/lat/{lat}/lon/{lon}
  - lat: Breitengrad der Position
  - lon: Längengrad der Position
  - /weather/lat/52.27/lon/10.53
- /weather/zipcode/{zipCode}/country/{countryCode}
  - zipcode: Postleizahl der Position
  - countryCode: Ländercode des Landes entsprechend ISO 3166
  - /weather/zipcode/38110/country/DE
