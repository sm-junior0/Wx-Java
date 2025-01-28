public class WeatherFormatter {
    public static String formatTemperature(double temp) {
        return temp + "°C";
    }

    public static String formatHumidity(double humidity) {
        return humidity + "%";
    }

    public static String formatPressure(double pressure) {
        return pressure + " hPa";
    }
}
