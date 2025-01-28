public class ConsoleUI implements UIComponent {
    //inheritance
    @Override
    public void displayWeather(WeatherData weatherData, Location location) {
        System.out.println("Weather in " + location.getCity() + ", " + location.getCountry());
        System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
        System.out.println("Humidity: " + weatherData.getHumidity() + "%");
        System.out.println("Pressure: " + weatherData.getPressure() + " hPa");
    }
}
