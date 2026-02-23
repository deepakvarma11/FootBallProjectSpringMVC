# Quick Start Guide 🚀

## First Time Setup?

**If you need to pull changes to your local machine:**
👉 See [HOW_TO_PULL.md](HOW_TO_PULL.md) for step-by-step git instructions

**Want to verify everything is working?**
👉 Run `./verify.sh` to automatically check your setup

## Prerequisites
- Java 8 or higher
- Maven 3.x
- MySQL 5.7+

## Setup in 3 Steps

### 1️⃣ Setup Database
```bash
# Linux/Mac
./setup-db.sh

# Windows
setup-db.bat

# Or manually
mysql -u root -p < src/main/resources/schema.sql
```

### 2️⃣ Configure (Optional)
Edit `src/main/resources/application.properties` if your MySQL credentials differ:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3️⃣ Run Application
```bash
# Option 1: Using Maven
./mvnw spring-boot:run

# Option 2: Using JAR (after building)
./mvnw clean package
java -jar target/Football-0.0.1-SNAPSHOT.jar
```

## Access
Open browser: **http://localhost:8080**

## Features
- ✅ Add Teams
- ✅ Add Matches
- ✅ View Teams
- ✅ View Match History
- ✅ Search Matches by Team

## Troubleshooting

### Database Connection Failed
1. Ensure MySQL is running: `sudo systemctl status mysql`
2. Check credentials in `application.properties`
3. Verify database exists: `SHOW DATABASES;`

### Port 8080 Already in Use
Change port in `application.properties`:
```properties
server.port=8081
```
Then update URLs in `Football.html` accordingly.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/Team` | Get all teams |
| POST | `/addteams` | Add a new team |
| GET | `/DisplayMatches?name={team}` | Get matches for team |
| POST | `/addMatch` | Add a new match |

## Sample Teams Included
- Bengaluru FC
- Chennaiyin FC
- Delhi Dynamos
- FC Goa
- Kerala Blasters
- Mumbai City FC
- ATK Mohun Bagan
- Hyderabad FC

## Verification & Testing

### Quick Verification
```bash
./verify.sh
```
This automated script checks:
- Prerequisites (Java, Maven, MySQL)
- All required files
- Configuration correctness
- Build success

### Comprehensive Testing
See [TESTING_GUIDE.md](TESTING_GUIDE.md) for:
- Step-by-step testing instructions
- Web interface testing
- API endpoint testing
- Troubleshooting guide

---
📖 For detailed documentation, see **[README.md](README.md)**
🧪 For comprehensive testing, see **[TESTING_GUIDE.md](TESTING_GUIDE.md)**
📥 For pulling changes, see **[HOW_TO_PULL.md](HOW_TO_PULL.md)**
✅ For complete list of changes, see **[SETUP_COMPLETE.md](SETUP_COMPLETE.md)**
