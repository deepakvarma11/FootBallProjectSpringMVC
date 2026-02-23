#!/bin/bash

# Football Match Manager - Automated Verification Script
# This script checks if all fixes are properly applied and the project is ready to run

echo "╔═══════════════════════════════════════════════════════════════════════════╗"
echo "║           Football Match Manager - Verification Script                    ║"
echo "╚═══════════════════════════════════════════════════════════════════════════╝"
echo ""

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

PASS_COUNT=0
FAIL_COUNT=0

# Function to print test result
print_result() {
    if [ $1 -eq 0 ]; then
        echo -e "${GREEN}✓${NC} $2"
        ((PASS_COUNT++))
    else
        echo -e "${RED}✗${NC} $2"
        ((FAIL_COUNT++))
    fi
}

echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "CHECKING PREREQUISITES"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Check Java
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
    print_result 0 "Java is installed (version: $JAVA_VERSION)"
else
    print_result 1 "Java is NOT installed"
fi

# Check Maven
if [ -f "./mvnw" ]; then
    print_result 0 "Maven wrapper (mvnw) found"
else
    print_result 1 "Maven wrapper (mvnw) NOT found"
fi

# Check if mvnw is executable
if [ -x "./mvnw" ]; then
    print_result 0 "Maven wrapper is executable"
else
    print_result 1 "Maven wrapper is NOT executable (run: chmod +x mvnw)"
fi

# Check MySQL
if command -v mysql &> /dev/null; then
    MYSQL_VERSION=$(mysql --version 2>&1 | head -n 1)
    print_result 0 "MySQL client is installed"
else
    print_result 1 "MySQL client is NOT installed"
fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "CHECKING REQUIRED FILES"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Check source files
[ -f "src/main/resources/static/index.html" ] && print_result 0 "index.html exists" || print_result 1 "index.html is MISSING"
[ -f "src/main/resources/static/Football.html" ] && print_result 0 "Football.html exists" || print_result 1 "Football.html is MISSING"
[ -f "src/main/resources/schema.sql" ] && print_result 0 "schema.sql exists" || print_result 1 "schema.sql is MISSING"
[ -f "src/main/resources/application.properties" ] && print_result 0 "application.properties exists" || print_result 1 "application.properties is MISSING"

# Check documentation
[ -f "README.md" ] && print_result 0 "README.md exists" || print_result 1 "README.md is MISSING"
[ -f "QUICKSTART.md" ] && print_result 0 "QUICKSTART.md exists" || print_result 1 "QUICKSTART.md is MISSING"
[ -f "SETUP_COMPLETE.md" ] && print_result 0 "SETUP_COMPLETE.md exists" || print_result 1 "SETUP_COMPLETE.md is MISSING"
[ -f "TESTING_GUIDE.md" ] && print_result 0 "TESTING_GUIDE.md exists" || print_result 1 "TESTING_GUIDE.md is MISSING"

# Check setup scripts
[ -f "setup-db.sh" ] && print_result 0 "setup-db.sh exists" || print_result 1 "setup-db.sh is MISSING"
[ -f "setup-db.bat" ] && print_result 0 "setup-db.bat exists" || print_result 1 "setup-db.bat is MISSING"

# Check Java source files
[ -f "src/main/java/com/mindtree/Football/FootballApplication.java" ] && print_result 0 "FootballApplication.java exists" || print_result 1 "FootballApplication.java is MISSING"
[ -f "src/main/java/com/mindtree/utility/Utility.java" ] && print_result 0 "Utility.java exists" || print_result 1 "Utility.java is MISSING"

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "CHECKING FILE CONTENTS"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Check if application.properties has database configuration
if grep -q "spring.datasource.url" src/main/resources/application.properties 2>/dev/null; then
    print_result 0 "application.properties has database URL configured"
else
    print_result 1 "application.properties is MISSING database URL"
fi

# Check if Football.html has correct URLs (not http:/localhost)
if grep -q "http:/localhost" src/main/resources/static/Football.html 2>/dev/null; then
    print_result 1 "Football.html still has URL typos (http:/localhost)"
else
    print_result 0 "Football.html URLs are correct"
fi

# Check if Utility.java uses Spring @Value
if grep -q "@Value" src/main/java/com/mindtree/utility/Utility.java 2>/dev/null; then
    print_result 0 "Utility.java uses Spring @Value injection"
else
    print_result 1 "Utility.java is NOT using Spring @Value injection"
fi

# Check if schema.sql creates tables
if grep -q "CREATE TABLE.*teams" src/main/resources/schema.sql 2>/dev/null; then
    print_result 0 "schema.sql creates teams table"
else
    print_result 1 "schema.sql is MISSING teams table creation"
fi

if grep -q "CREATE TABLE.*matches" src/main/resources/schema.sql 2>/dev/null; then
    print_result 0 "schema.sql creates matches table"
else
    print_result 1 "schema.sql is MISSING matches table creation"
fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "CHECKING BUILD"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Try to compile
echo "Attempting to compile the project..."
if ./mvnw clean compile -DskipTests > /tmp/build.log 2>&1; then
    print_result 0 "Project compiles successfully"
else
    print_result 1 "Project compilation FAILED (see /tmp/build.log for details)"
    echo -e "${YELLOW}Last 20 lines of build log:${NC}"
    tail -20 /tmp/build.log
fi

# Check if JAR can be created
if [ -f "target/Football-0.0.1-SNAPSHOT.jar" ] || ./mvnw package -DskipTests > /tmp/package.log 2>&1; then
    if [ -f "target/Football-0.0.1-SNAPSHOT.jar" ]; then
        JAR_SIZE=$(du -h target/Football-0.0.1-SNAPSHOT.jar | cut -f1)
        print_result 0 "JAR file created successfully ($JAR_SIZE)"
    else
        print_result 1 "JAR file creation FAILED"
    fi
else
    print_result 1 "Package creation FAILED"
fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "CHECKING DATABASE (Optional)"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Check if MySQL is running
if pgrep -x "mysqld" > /dev/null 2>&1; then
    print_result 0 "MySQL server is running"
    
    # Try to check if database exists (will fail if no credentials, but that's ok)
    if mysql -u root -e "USE football_match_db; SHOW TABLES;" 2>/dev/null | grep -q "teams"; then
        print_result 0 "Database 'football_match_db' exists with tables"
    else
        echo -e "${YELLOW}ℹ${NC}  Database not yet configured (run ./setup-db.sh)"
    fi
else
    echo -e "${YELLOW}ℹ${NC}  MySQL server is not running or not installed"
fi

echo ""
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo "VERIFICATION SUMMARY"
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

TOTAL_TESTS=$((PASS_COUNT + FAIL_COUNT))
PASS_PERCENT=$((PASS_COUNT * 100 / TOTAL_TESTS))

echo ""
echo -e "Tests Passed:  ${GREEN}$PASS_COUNT${NC}"
echo -e "Tests Failed:  ${RED}$FAIL_COUNT${NC}"
echo -e "Total Tests:   $TOTAL_TESTS"
echo -e "Success Rate:  $PASS_PERCENT%"
echo ""

if [ $FAIL_COUNT -eq 0 ]; then
    echo -e "${GREEN}╔═══════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${GREEN}║  ✓ ALL CHECKS PASSED - Project is ready to run!                  ║${NC}"
    echo -e "${GREEN}╚═══════════════════════════════════════════════════════════════════╝${NC}"
    echo ""
    echo -e "${BLUE}Next steps:${NC}"
    echo "  1. Setup database: ./setup-db.sh"
    echo "  2. Run application: ./mvnw spring-boot:run"
    echo "  3. Open browser: http://localhost:8080"
    echo ""
    echo "See TESTING_GUIDE.md for detailed testing instructions."
    exit 0
else
    echo -e "${RED}╔═══════════════════════════════════════════════════════════════════╗${NC}"
    echo -e "${RED}║  ✗ SOME CHECKS FAILED - Please review the issues above           ║${NC}"
    echo -e "${RED}╚═══════════════════════════════════════════════════════════════════╝${NC}"
    echo ""
    echo -e "${YELLOW}Troubleshooting:${NC}"
    echo "  • See TESTING_GUIDE.md for help with specific issues"
    echo "  • Check that you're on the 'copilot/fix-project-setup-issues' branch"
    echo "  • Ensure all files were pulled from the repository"
    echo "  • Review build logs in /tmp/build.log if compilation failed"
    exit 1
fi
