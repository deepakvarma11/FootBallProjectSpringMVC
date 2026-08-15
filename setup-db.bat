@echo off
REM Football Match Manager - Database Setup Script for Windows
REM This script sets up the MySQL database for the Football Match Manager application

echo ===================================================================
echo Football Match Manager - Database Setup
echo ===================================================================
echo.

REM Database configuration
set DB_NAME=football_match_db
set DB_USER=root
set DB_PASS=Welcome123
set DB_HOST=localhost

REM Check if MySQL is in PATH
where mysql >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: MySQL is not installed or not in PATH
    echo Please install MySQL and add it to your PATH, then try again
    pause
    exit /b 1
)

echo MySQL found. Proceeding with database setup...
echo.

REM Prompt for password
set /p USER_PASS=Enter MySQL root password (press Enter to use default 'Welcome123'): 
if "%USER_PASS%"=="" set USER_PASS=%DB_PASS%

echo.
echo Creating database and tables...

REM Execute SQL file
mysql -h %DB_HOST% -u %DB_USER% -p%USER_PASS% < src\main\resources\schema.sql

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ===================================================================
    echo SUCCESS: Database setup completed!
    echo ===================================================================
    echo.
    echo Database: %DB_NAME%
    echo Tables created: teams, matches
    echo Sample data has been inserted
    echo.
    echo You can now run the application with: mvnw.cmd spring-boot:run
    echo.
) else (
    echo.
    echo ===================================================================
    echo ERROR: Database setup failed
    echo ===================================================================
    echo.
    echo Please check:
    echo 1. MySQL is running
    echo 2. The password is correct
    echo 3. You have necessary permissions
    echo.
)

pause
