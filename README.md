# Football Match Manager - Spring MVC Application

A Spring Boot web application for managing football matches and teams. This application allows users to add teams, record matches, and view match statistics.

## 📚 Documentation

- **[QUICKSTART.md](QUICKSTART.md)** - Get started in 3 simple steps
- **[TESTING_GUIDE.md](TESTING_GUIDE.md)** - Complete testing and verification guide
- **[HOW_TO_PULL.md](HOW_TO_PULL.md)** - Instructions for pulling changes to your local machine
- **[SETUP_COMPLETE.md](SETUP_COMPLETE.md)** - Summary of all fixes and changes made

## 🚀 Quick Start

```bash
# 1. Pull the changes (if needed)
git checkout copilot/fix-project-setup-issues

# 2. Verify everything is working
./verify.sh

# 3. Setup database
./setup-db.sh          # Linux/Mac
# or
setup-db.bat           # Windows

# 4. Run the application
./mvnw spring-boot:run

# 5. Open your browser
# http://localhost:8080
```

For detailed instructions, see [QUICKSTART.md](QUICKSTART.md).

## Features

- Add and view football teams
- Record match results with scores
- View match history for specific teams
- Display teams and their cities
- RESTful API endpoints
- Simple web interface

## Prerequisites

Before running this application, make sure you have the following installed:

- Java 8 or higher
- Maven 3.x
- MySQL 5.7 or higher

## Database Setup

1. **Install MySQL** (if not already installed)

2. **Create the database and tables:**

   ```bash
   mysql -u root -p < src/main/resources/schema.sql
   ```

   Or manually run the SQL commands:

   ```sql
   CREATE DATABASE IF NOT EXISTS football_match_db;
   USE football_match_db;
   
   -- Create teams table
   CREATE TABLE IF NOT EXISTS teams (
       team_name VARCHAR(100) PRIMARY KEY,
       team_city VARCHAR(100) NOT NULL
   );
   
   -- Create matches table
   CREATE TABLE IF NOT EXISTS matches (
       match_id INT AUTO_INCREMENT PRIMARY KEY,
       match_date VARCHAR(50) NOT NULL,
       first_team_name VARCHAR(100) NOT NULL,
       second_team_name VARCHAR(100) NOT NULL,
       first_team_goals INT NOT NULL DEFAULT 0,
       second_team_goals INT NOT NULL DEFAULT 0,
       FOREIGN KEY (first_team_name) REFERENCES teams(team_name),
       FOREIGN KEY (second_team_name) REFERENCES teams(team_name)
   );
   ```

3. **Configure Database Credentials:**

   Update `src/main/resources/application.properties` if your MySQL credentials differ from the default:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/football_match_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=Welcome123
   ```

## Building the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/deepakvarma11/FootBallProjectSpringMVC.git
   cd FootBallProjectSpringMVC
   ```

2. **Build with Maven:**

   ```bash
   ./mvnw clean install
   ```

   Or on Windows:

   ```bash
   mvnw.cmd clean install
   ```

## Running the Application

1. **Start the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

   Or on Windows:

   ```bash
   mvnw.cmd spring-boot:run
   ```

2. **Access the application:**

   Open your web browser and navigate to:
   ```
   http://localhost:8080
   ```

   Or directly access:
   ```
   http://localhost:8080/Football.html
   ```

## API Endpoints

### Teams

- **GET** `/Team` - Get all teams
- **POST** `/addteams` - Add a new team
  ```json
  {
    "teamName": "Team Name",
    "teamcity": "City Name"
  }
  ```

### Matches

- **GET** `/DisplayMatches?name={teamName}` - Get matches for a specific team
- **POST** `/addMatch` - Add a new match
  ```json
  {
    "matchdate": "2024-01-15",
    "firstTeam": "Team A",
    "secondteam": "Team B",
    "firstTeamScore": 2,
    "secondTeamScore": 1
  }
  ```

## Application Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com/mindtree/
│   │   │   ├── Football/         # Main application class
│   │   │   ├── dao/              # Data Access Objects
│   │   │   ├── entity/           # Entity classes
│   │   │   ├── exception/        # Custom exceptions
│   │   │   ├── service/          # Service layer
│   │   │   └── utility/          # Utility classes
│   │   └── controller/           # REST controllers
│   └── resources/
│       ├── static/               # Static web resources
│       │   ├── index.html
│       │   └── Football.html
│       ├── application.properties
│       └── schema.sql
└── test/                         # Test classes
```

## Sample Data

The schema.sql file includes sample teams:

- Bengaluru FC
- Chennaiyin FC
- Delhi Dynamos
- FC Goa
- Kerala Blasters
- Mumbai City FC
- ATK Mohun Bagan
- Hyderabad FC

And some sample matches to get you started.

## Testing and Verification

To verify everything is working correctly:

1. **Run the automated verification script:**
   ```bash
   ./verify.sh
   ```
   This checks all prerequisites, files, and builds the project.

2. **Follow the comprehensive testing guide:**
   See [TESTING_GUIDE.md](TESTING_GUIDE.md) for detailed testing instructions including:
   - Web interface testing
   - API endpoint testing
   - Database verification
   - Common troubleshooting

3. **Manual verification:**
   - Access http://localhost:8080
   - Test adding teams and matches
   - Verify data persistence

## Troubleshooting

### Database Connection Issues

If you encounter database connection errors:

1. Ensure MySQL is running: `sudo systemctl status mysql` (Linux) or check Services (Windows)
2. Verify database exists: `SHOW DATABASES;` in MySQL
3. Check credentials in `application.properties`
4. Ensure MySQL is listening on port 3306

### Port Already in Use

If port 8080 is already in use, change it in `application.properties`:

```properties
server.port=8081
```

Then update the URLs in `Football.html` accordingly.

## Technologies Used

- Spring Boot 2.0.6
- Spring Web MVC
- MySQL
- JDBC
- jQuery
- HTML/CSS

## License

This project is created for demonstration purposes.

## Contributors

- Deepak Varma
