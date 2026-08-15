#!/bin/bash

# Football Match Manager - Database Setup Script
# This script sets up the MySQL database for the Football Match Manager application

echo "==================================================================="
echo "Football Match Manager - Database Setup"
echo "==================================================================="
echo ""

# Database configuration
DB_NAME="football_match_db"
DB_USER="root"
DB_PASS="Welcome123"
DB_HOST="localhost"

# Check if MySQL is installed
if ! command -v mysql &> /dev/null; then
    echo "ERROR: MySQL is not installed or not in PATH"
    echo "Please install MySQL and try again"
    exit 1
fi

echo "MySQL found. Proceeding with database setup..."
echo ""

# Prompt for password (optional)
read -p "Enter MySQL root password (press Enter to use default 'Welcome123'): " USER_PASS
if [ -z "$USER_PASS" ]; then
    USER_PASS=$DB_PASS
fi

echo ""
echo "Creating database and tables..."

# Execute SQL file
mysql -h $DB_HOST -u $DB_USER -p$USER_PASS < src/main/resources/schema.sql

if [ $? -eq 0 ]; then
    echo ""
    echo "==================================================================="
    echo "SUCCESS: Database setup completed!"
    echo "==================================================================="
    echo ""
    echo "Database: $DB_NAME"
    echo "Tables created: teams, matches"
    echo "Sample data has been inserted"
    echo ""
    echo "You can now run the application with: ./mvnw spring-boot:run"
    echo ""
else
    echo ""
    echo "==================================================================="
    echo "ERROR: Database setup failed"
    echo "==================================================================="
    echo ""
    echo "Please check:"
    echo "1. MySQL is running"
    echo "2. The password is correct"
    echo "3. You have necessary permissions"
    echo ""
    exit 1
fi
