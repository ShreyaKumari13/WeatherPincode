
# Weather Info for Pincode

This is a Spring Boot-based REST API project that provides weather information for a given pincode and date. The project integrates with the OpenWeather API to fetch weather data and caches the results in a MySQL database. Subsequent requests for the same pincode and date retrieve data directly from the database, optimizing API calls.

## Features
- Fetch weather information for a specific pincode and date.
- Cache weather data in MySQL to avoid repeated API calls.
- API is testable via Postman or Swagger.
- Built with Spring Boot and JPA for database integration.

## Prerequisites
- Java 17 or higher
- Maven
- MySQL
- OpenWeather API Key
- Postman (for testing the API)

## Project Structure
```
weatherinfo
│
├── src
│   ├── main
│   │   ├── java/com/example/weatherinfo
│   │   │   ├── WeatherInfoApplication.java
│   │   │   ├── controller
│   │   │   │   └── WeatherController.java
│   │   │   ├── model
│   │   │   │   └── WeatherInfo.java
│   │   │   ├── repository
│   │   │   │   └── WeatherRepository.java
│   │   │   ├── service
│   │   │   │   └── WeatherService.java
│   │   │   └── dto
│   │   │       └── WeatherApiResponse.java
│   └── resources
│       ├── application.properties
├── pom.xml
└── README.md
```

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/weatherinfo.git
cd weatherinfo
```

### 2. Configure MySQL Database
Make sure you have MySQL installed and running on your machine. Create a database named `weather_db`:

```sql
CREATE DATABASE weather_db;
```

Update the `src/main/resources/application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/weather_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Set OpenWeather API Key
Obtain your API key from [OpenWeather](https://home.openweathermap.org/api_keys) and set it in the `application.properties` file:

```properties
openweather.api.key=your_openweather_api_key
```

### 4. Build and Run the Application
Build and run the application using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

### 5. Testing the API in Postman
- **URL**: `http://localhost:8080/api/weather`
- **Method**: `GET`
- **Parameters**:
  - `pincode`: Enter a valid pincode (e.g., `411014`).
  - `forDate`: Enter the date for which you want the weather info (e.g., `2023-10-15`).

**Example Request**:
```
http://localhost:8080/api/weather?pincode=411014&forDate=2023-10-15
```

**Example Response**:
```json
{
  "id": 1,
  "pincode": "411014",
  "latitude": "18.5204",
  "longitude": "73.8567",
  "weatherDescription": "clear sky",
  "temperature": "25",
  "forDate": "2023-10-15"
}
```

### 6. API Workflow
1. The application checks the MySQL database for weather data based on the requested pincode and date.
2. If the data is available, it returns the cached data from the database.
3. If the data is not available, it calls the OpenWeather API to fetch the data, caches the result in the database, and returns the weather information.

## Technology Stack
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL** (for data storage)
- **RestTemplate** (for API calls)
- **OpenWeather API**

## Important Files
- `WeatherInfoApplication.java`: Main application class to run the Spring Boot application.
- `WeatherController.java`: Controller class handling REST API endpoints.
- `WeatherService.java`: Service class containing business logic to fetch weather data and interact with the database.
- `WeatherInfo.java`: Entity class representing weather data stored in the MySQL database.
- `WeatherApiResponse.java`: DTO class representing the structure of the OpenWeather API response.
- `application.properties`: Configuration file for database and API keys.

## Future Enhancements
- Add support for more weather parameters such as humidity, wind speed, etc.
- Implement rate limiting for external API calls.
- Add automated unit tests for better test coverage.

## License
This project is licensed under the MIT License.

---

Feel free to reach out if you encounter any issues or have any questions!
