import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {

    public void saveWeatherData(Location location, WeatherData weatherData) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO weather_data (city, country, temperature, humidity, pressure) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, location.getCity());
            stmt.setString(2, location.getCountry());
            stmt.setDouble(3, weatherData.getTemperature());
            stmt.setDouble(4, weatherData.getHumidity());
            stmt.setDouble(5, weatherData.getPressure());

            stmt.executeUpdate();
            System.out.println("Weather data saved successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving weather data: " + e.getMessage());
        }
    }

    public WeatherData getWeatherData(Location location) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT temperature, humidity, pressure FROM weather_data WHERE city = ? AND country = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, location.getCity());
            stmt.setString(2, location.getCountry());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double temperature = rs.getDouble("temperature");
                double humidity = rs.getDouble("humidity");
                double pressure = rs.getDouble("pressure");
                return new WeatherData(temperature, humidity, pressure);
            } else {
                System.out.println("No data found for the specified location.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving weather data: " + e.getMessage());
        }
        return null;
    }

    public void updateWeatherDataByCity(String city, double temperature, double humidity, double pressure) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "UPDATE weather_data SET temperature = ?, humidity = ?, pressure = ? WHERE city = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, temperature);
            stmt.setDouble(2, humidity);
            stmt.setDouble(3, pressure);
            stmt.setString(4, city);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Weather data updated successfully.");
            } else {
                System.out.println("No data found for the specified city.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating weather data: " + e.getMessage());
        }
    }

    public void deleteWeatherDataByCity(String city) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM weather_data WHERE city = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, city);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Weather data deleted successfully.");
            } else {
                System.out.println("No data found for the specified city.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting weather data: " + e.getMessage());
        }
    }

    public void viewAllWeatherData() {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM weather_data";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<String> records = new ArrayList<>();
            while (rs.next()) {
                records.add("ID: " + rs.getInt("id") +
                        ", City: " + rs.getString("city") +
                        ", Country: " + rs.getString("country") +
                        ", Temperature: " + rs.getDouble("temperature") +
                        ", Humidity: " + rs.getDouble("humidity") +
                        ", Pressure: " + rs.getDouble("pressure"));
            }

            if (records.isEmpty()) {
                System.out.println("No weather data found.");
            } else {
                records.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing weather data: " + e.getMessage());
        }
    }

    public void viewWeatherDataById(int id) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM weather_data WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", City: " + rs.getString("city") +
                        ", Country: " + rs.getString("country") +
                        ", Temperature: " + rs.getDouble("temperature") +
                        ", Humidity: " + rs.getDouble("humidity") +
                        ", Pressure: " + rs.getDouble("pressure"));
            } else {
                System.out.println("No data found for the specified ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing weather data: " + e.getMessage());
        }
    }
}
