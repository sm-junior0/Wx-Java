import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService weatherService = new WeatherService();

        while (true) {
            System.out.println("\n--- Weather App ---");
            System.out.println("1. Save Weather Data");
            System.out.println("2. View Weather Data by ID");
            System.out.println("3. View All Weather Data");
            System.out.println("4. Update Weather Data by City");
            System.out.println("5. Delete Weather Data by City");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter City: ");
                        String city = scanner.nextLine();
                        System.out.print("Enter Country: ");
                        String country = scanner.nextLine();
                        System.out.print("Enter Temperature: ");
                        double temperature = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter Humidity: ");
                        double humidity = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter Pressure: ");
                        double pressure = Double.parseDouble(scanner.nextLine());

                        Location location = new Location(city, country);
                        WeatherData weatherData = new WeatherData(temperature, humidity, pressure);
                        weatherService.saveWeatherData(location, weatherData);
                    }
                    case 2 -> {
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        weatherService.viewWeatherDataById(id);
                    }
                    case 3 -> weatherService.viewAllWeatherData();
                    case 4 -> {
                        System.out.print("Enter City: ");
                        String city = scanner.nextLine();
                        System.out.print("Enter New Temperature: ");
                        double temperature = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter New Humidity: ");
                        double humidity = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter New Pressure: ");
                        double pressure = Double.parseDouble(scanner.nextLine());
                        weatherService.updateWeatherDataByCity(city, temperature, humidity, pressure);
                    }
                    case 5 -> {
                        System.out.print("Enter City: ");
                        String city = scanner.nextLine();
                        weatherService.deleteWeatherDataByCity(city);
                    }
                    case 6 -> {
                        System.out.println("Thanks for using ...Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
