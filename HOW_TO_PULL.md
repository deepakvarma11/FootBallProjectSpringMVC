# How to Pull and Test Changes Locally

This guide will walk you through pulling the fixes to your local machine and testing them.

## Prerequisites

Ensure you have Git installed:
```bash
git --version
```

## Option 1: Fresh Clone (Recommended if you don't have the repo locally)

### Step 1: Clone the Repository

```bash
# Navigate to your desired directory
cd ~/projects  # or wherever you want to clone

# Clone the repository
git clone https://github.com/deepakvarma11/FootBallProjectSpringMVC.git

# Navigate into the repository
cd FootBallProjectSpringMVC
```

### Step 2: Checkout the Fix Branch

```bash
# Fetch all branches
git fetch origin

# Checkout the fixes branch
git checkout copilot/fix-project-setup-issues

# Verify you're on the correct branch
git branch
```

**Expected Output**: `* copilot/fix-project-setup-issues`

### Step 3: Verify Changes

```bash
# See what changed
git log --oneline -10

# See files that were added/modified
git diff main --name-status
```

---

## Option 2: Pull Changes to Existing Local Repository

If you already have the repository cloned:

### Step 1: Save Any Local Changes

```bash
# Navigate to your repository
cd path/to/FootBallProjectSpringMVC

# Check current status
git status

# If you have uncommitted changes, stash them
git stash save "My local changes before pulling fixes"
```

### Step 2: Fetch Latest Changes

```bash
# Fetch all branches from remote
git fetch origin

# List all available branches
git branch -a
```

### Step 3: Switch to the Fix Branch

```bash
# Checkout the fixes branch
git checkout copilot/fix-project-setup-issues

# Pull latest changes
git pull origin copilot/fix-project-setup-issues
```

### Step 4: Verify Branch

```bash
# Confirm you're on the correct branch
git branch
```

**Expected Output**: `* copilot/fix-project-setup-issues`

---

## Option 3: Merge Changes to Your Main Branch

If you want to merge the fixes into your main branch:

### Step 1: Update Both Branches

```bash
# Fetch all changes
git fetch origin

# Update main branch
git checkout main
git pull origin main

# Update fix branch
git checkout copilot/fix-project-setup-issues
git pull origin copilot/fix-project-setup-issues
```

### Step 2: Merge Fix Branch into Main

```bash
# Switch to main branch
git checkout main

# Merge the fixes
git merge copilot/fix-project-setup-issues

# If there are conflicts, resolve them
# Then commit the merge:
# git add .
# git commit -m "Merge fixes from copilot/fix-project-setup-issues"
```

### Step 3: Push to Remote (Optional)

```bash
# Push merged changes to your main branch
git push origin main
```

---

## Verify the Changes Were Pulled

### Check New Files

```bash
# List new resource files
ls src/main/resources/static/index.html
ls src/main/resources/schema.sql
ls src/main/resources/application.properties

# List documentation
ls README.md QUICKSTART.md SETUP_COMPLETE.md TESTING_GUIDE.md

# List setup scripts
ls setup-db.sh setup-db.bat
```

### View Commit History

```bash
# See recent commits
git log --oneline -10
```

**Expected commits** (most recent first):
- `ab1c4e3` - Add final project status summary
- `c0145ef` - Improve code quality in Utility.java
- `7ee1e61` - Fix Utility.java anti-pattern
- `affe7f0` - Add quick start guide
- `a4d6450` - Add comprehensive setup documentation
- `99e2c0d` - Add component scan and database setup scripts
- `376a370` - Add missing resources and database configuration

### View File Changes

```bash
# See what was modified in application.properties
git show HEAD:src/main/resources/application.properties

# See what changed in Utility.java
git log -p src/main/java/com/mindtree/utility/Utility.java
```

---

## Compare with Main Branch

### See All Changes

```bash
# View summary of changes
git diff main --stat

# View detailed changes
git diff main
```

### See Files Added

```bash
# List only new files
git diff main --name-status | grep "^A"
```

**Expected New Files**:
- `A  README.md`
- `A  QUICKSTART.md`
- `A  SETUP_COMPLETE.md`
- `A  TESTING_GUIDE.md`
- `A  HOW_TO_PULL.md`
- `A  PROJECT_STATUS.txt`
- `A  setup-db.sh`
- `A  setup-db.bat`
- `A  src/main/resources/schema.sql`
- `A  src/main/resources/static/index.html`

### See Files Modified

```bash
# List modified files
git diff main --name-status | grep "^M"
```

**Expected Modified Files**:
- `M  src/main/resources/application.properties`
- `M  src/main/resources/static/Football.html`
- `M  src/main/java/com/mindtree/utility/Utility.java`
- `M  src/main/java/com/mindtree/Football/FootballApplication.java`

---

## Verify Your Local Setup

### Make Scripts Executable (Linux/Mac)

```bash
chmod +x mvnw
chmod +x setup-db.sh
```

### Check Java and Maven

```bash
# Verify Java (should be 8+)
java -version

# Verify Maven (should be 3.x)
./mvnw --version
```

---

## Next Steps

After pulling the changes:

1. **Review the Documentation**:
   ```bash
   # Quick overview
   cat QUICKSTART.md
   
   # Full documentation
   cat README.md
   
   # Summary of changes
   cat SETUP_COMPLETE.md
   ```

2. **Follow the Testing Guide**:
   ```bash
   # Open the testing guide
   cat TESTING_GUIDE.md
   ```

3. **Setup and Test**:
   - Setup database: `./setup-db.sh` (or `setup-db.bat` on Windows)
   - Build project: `./mvnw clean compile`
   - Run application: `./mvnw spring-boot:run`
   - Open browser: http://localhost:8080

---

## Troubleshooting

### Issue: "fatal: couldn't find remote ref"

**Solution**: The branch might have a different name. List all branches:
```bash
git branch -a
```

### Issue: "error: Your local changes would be overwritten by checkout"

**Solution**: Stash or commit your changes:
```bash
git stash save "My local changes"
# or
git commit -am "Save my local changes"
```

### Issue: Merge conflicts

**Solution**: 
```bash
# View conflicted files
git status

# Open each conflicted file and resolve conflicts
# Look for markers: <<<<<<<, =======, >>>>>>>

# After resolving, mark as resolved
git add <resolved-file>

# Complete the merge
git commit -m "Resolve merge conflicts"
```

### Issue: Permission denied for scripts

**Solution** (Linux/Mac):
```bash
chmod +x mvnw setup-db.sh
```

---

## Understanding the Changes

### Files Created

All these are new additions to help you:

1. **index.html** - Entry point for the web application
2. **schema.sql** - Database structure and sample data
3. **README.md** - Complete project documentation
4. **QUICKSTART.md** - Fast setup guide
5. **SETUP_COMPLETE.md** - Detailed change log
6. **TESTING_GUIDE.md** - Testing instructions
7. **setup-db.sh/.bat** - Database setup automation

### Files Modified

These existing files were improved:

1. **application.properties** - Now has database configuration
2. **Football.html** - Fixed URL typos
3. **Utility.java** - Uses Spring configuration instead of hardcoded values
4. **FootballApplication.java** - Improved component scanning

---

## Git Commands Cheat Sheet

```bash
# Check current branch
git branch

# Switch to another branch
git checkout <branch-name>

# Pull latest changes
git pull origin <branch-name>

# View commit history
git log --oneline

# View changes in a file
git diff <file-name>

# View all changed files
git status

# Discard local changes to a file
git checkout -- <file-name>

# See all branches (local and remote)
git branch -a

# Update all remote branches
git fetch origin
```

---

## Need More Help?

- **Testing Issues**: See [TESTING_GUIDE.md](TESTING_GUIDE.md)
- **Setup Issues**: See [README.md](README.md)
- **What Changed**: See [SETUP_COMPLETE.md](SETUP_COMPLETE.md)
- **Quick Start**: See [QUICKSTART.md](QUICKSTART.md)

---

**Ready to test your fixed application!** 🚀
