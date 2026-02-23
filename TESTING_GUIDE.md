# Testing and Verification Guide

This guide will help you verify that all the fixes are working correctly.

## Prerequisites Check

Before testing, ensure you have:
- ✅ Java 8 or higher installed (`java -version`)
- ✅ Maven 3.x installed (`mvn -version`)
- ✅ MySQL 5.7+ installed and running
- ✅ Git installed

## Step 1: Pull the Changes

If you haven't pulled the changes yet, follow the [HOW_TO_PULL.md](HOW_TO_PULL.md) guide.

## Step 2: Verify Files Are Present

Check that all the new files were created:

```bash
# Essential files
ls src/main/resources/static/index.html          # ✓ Entry point
ls src/main/resources/static/Football.html       # ✓ Main page
ls src/main/resources/schema.sql                 # ✓ Database schema
ls src/main/resources/application.properties     # ✓ Configuration
ls setup-db.sh                                   # ✓ DB setup script (Linux/Mac)
ls setup-db.bat                                  # ✓ DB setup script (Windows)

# Documentation
ls README.md                                     # ✓ Main documentation
ls QUICKSTART.md                                 # ✓ Quick start
ls SETUP_COMPLETE.md                             # ✓ Changes summary
```

## Step 3: Verify Build

Test that the project compiles without errors:

```bash
# Clean build
./mvnw clean compile

# Expected output: BUILD SUCCESS
```

**Expected Result**: No compilation errors, should see `BUILD SUCCESS` at the end.

## Step 4: Package the Application

Create the JAR file:

```bash
./mvnw clean package -DskipTests

# Check the JAR was created
ls -lh target/Football-0.0.1-SNAPSHOT.jar
```

**Expected Result**: JAR file created (~26MB)

## Step 5: Setup Database

### Option A: Using Setup Script (Recommended)

**Linux/Mac:**
```bash
./setup-db.sh
```

**Windows:**
```bash
setup-db.bat
```

### Option B: Manual Setup

```bash
mysql -u root -p < src/main/resources/schema.sql
```

### Verify Database Setup

```bash
mysql -u root -p -e "USE football_match_db; SHOW TABLES;"
```

**Expected Output:**
```
+-----------------------------+
| Tables_in_football_match_db |
+-----------------------------+
| matches                     |
| teams                       |
+-----------------------------+
```

Check sample data:
```bash
mysql -u root -p -e "USE football_match_db; SELECT * FROM teams;"
```

**Expected Output**: Should show 8 teams (Bengaluru FC, Chennaiyin FC, etc.)

## Step 6: Configure Database Credentials (If Needed)

If your MySQL credentials differ from defaults, update:

`src/main/resources/application.properties`

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Step 7: Run the Application

Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

**Expected Output:**
- Should see Spring Boot ASCII art banner
- Should see: `Tomcat started on port(s): 8080`
- Should see: `Started FootballApplication in X seconds`
- No database connection errors

**Keep this terminal open** while testing.

## Step 8: Test the Web Interface

Open a new terminal/browser and test:

### Test 1: Access the Application

Open browser and navigate to:
```
http://localhost:8080
```

**Expected Result**: 
- Should automatically redirect to `http://localhost:8080/Football.html`
- Page should load without errors
- Should see "Football Matches" header

### Test 2: View Teams

On the Football.html page:
1. Click the "Teams" button

**Expected Result**:
- Should display a table with team names and cities
- Should show 8 teams (sample data)

### Test 3: Add a New Team

1. Scroll to "Add Teams" section
2. Enter Team Name: "Test FC"
3. Enter Team City: "Test City"
4. Click Submit

**Expected Result**:
- Should see alert: "Added"
- Click "Teams" button again - new team should appear in the list

### Test 4: View Matches for a Team

1. In "Display Matches" section
2. Select a team from dropdown (e.g., "Bengaluru FC")
3. Click Submit

**Expected Result**:
- Should display a table with match history
- Should show date, opponent, scores, and goal difference
- Sample matches should be visible

### Test 5: Add a New Match

1. In "Add Matches" section
2. Select First Team Name: "Bengaluru FC"
3. Select Second Team Name: "FC Goa"
4. Enter Match Date: "2024-03-15"
5. Enter First Team Score: 3
6. Enter Second Team Score: 1
7. Click Submit

**Expected Result**:
- Should see alert: "added"
- View matches for "Bengaluru FC" - new match should appear

## Step 9: Test API Endpoints

Open another terminal and test the REST API:

### Test GET /Team
```bash
curl http://localhost:8080/Team
```

**Expected Result**: JSON array of teams

### Test POST /addteams
```bash
curl -X POST http://localhost:8080/addteams \
  -H "Content-Type: application/json" \
  -d '{"teamName":"API Test FC","teamcity":"API City"}'
```

**Expected Result**: `{"responsemessage":"Added"}`

### Test GET /DisplayMatches
```bash
curl "http://localhost:8080/DisplayMatches?name=Bengaluru%20FC"
```

**Expected Result**: JSON array of matches for Bengaluru FC

### Test POST /addMatch
```bash
curl -X POST http://localhost:8080/addMatch \
  -H "Content-Type: application/json" \
  -d '{
    "matchdate":"2024-03-20",
    "firstTeam":"Bengaluru FC",
    "secondteam":"Chennaiyin FC",
    "firstTeamScore":2,
    "secondTeamScore":2
  }'
```

**Expected Result**: `{"responsemessage":"added"}`

## Step 10: Verify Logs

Check the application logs for errors:

In the terminal where the application is running, you should see:
- ✅ No SQL connection errors
- ✅ No NullPointerExceptions
- ✅ Successful request logs (e.g., `GET "/Team"`, `POST "/addMatch"`)

## Step 11: Stop the Application

In the terminal running the application:
- Press `Ctrl+C` to stop the server

**Expected Result**: Application shuts down gracefully

## Automated Verification Script

Run the automated verification script:

```bash
./verify.sh
```

This will check:
- Java version
- Maven installation
- MySQL installation
- File existence
- Build success
- Database tables

## Common Issues and Solutions

### Issue 1: "Connection refused" or "Unable to connect to database"

**Solution**:
1. Ensure MySQL is running: `sudo systemctl status mysql` (Linux) or check Services (Windows)
2. Verify credentials in `application.properties`
3. Check database exists: `mysql -u root -p -e "SHOW DATABASES;"`

### Issue 2: "Port 8080 already in use"

**Solution**:
1. Find process using port 8080: `lsof -i :8080` (Linux/Mac) or `netstat -ano | findstr :8080` (Windows)
2. Kill the process or change port in `application.properties`:
   ```properties
   server.port=8081
   ```
3. Update URLs in `Football.html` accordingly

### Issue 3: "BUILD FAILURE" during compile

**Solution**:
1. Check Java version: `java -version` (should be 8+)
2. Clean Maven cache: `./mvnw clean`
3. Try: `./mvnw clean install -U`

### Issue 4: Teams dropdown is empty

**Solution**:
1. Check database has teams: `mysql -u root -p -e "USE football_match_db; SELECT * FROM teams;"`
2. Check browser console for AJAX errors (F12 → Console tab)
3. Verify application is running on port 8080

### Issue 5: AJAX errors in browser console

**Solution**:
1. Check URLs in `Football.html` - should be `http://localhost:8080` not `http:/localhost:8080`
2. Ensure application is running
3. Check for CORS issues (application allows cross-origin with `@CrossOrigin("*")`)

## Success Criteria

Your application is working correctly if:
- ✅ Build completes without errors
- ✅ Database tables created successfully
- ✅ Application starts without errors
- ✅ Web interface loads at http://localhost:8080
- ✅ Teams are displayed when clicking "Teams" button
- ✅ Can add new teams
- ✅ Can view matches for a team
- ✅ Can add new matches
- ✅ API endpoints return valid JSON
- ✅ No errors in application logs

## Performance Check

Verify the application responds quickly:
- Page load: < 2 seconds
- API responses: < 1 second
- Database queries: < 500ms

## Security Check

Verify no sensitive data is exposed:
- Database password is not hardcoded in Java files
- Configuration is externalized in `application.properties`
- No SQL injection vulnerabilities (using PreparedStatements)

## Next Steps

Once verification is complete:
1. Review the changes in [SETUP_COMPLETE.md](SETUP_COMPLETE.md)
2. Read the full documentation in [README.md](README.md)
3. Merge the `copilot/fix-project-setup-issues` branch to main
4. Start developing your features!

## Need Help?

If you encounter any issues:
1. Check the [README.md](README.md) troubleshooting section
2. Review application logs for specific errors
3. Verify all prerequisites are installed
4. Ensure MySQL is running and accessible

---

**Happy Testing! 🎉**
