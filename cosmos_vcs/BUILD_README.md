# Cosmos VCS

A local-only version control system built with Angular frontend and Spring Boot backend, packaged as a single JAR for easy deployment.

## Features

- **Local-only storage** - All data stored in `.cosmos` directory
- **File tracking** - Track changes to files with support for ignore patterns
- **Commit management** - Create commits with messages and timestamps
- **History viewing** - Browse commit history with file changes
- **Checkout support** - Restore files from any commit
- **Diff comparison** - Compare changes between commits
- **File compression** - Automatic compression for large files
- **Web UI** - Modern Angular-based user interface
- **CLI interface** - Command-line tools for automation

## Quick Start

### Building the Application

```bash
# Build the complete application (frontend + backend)
mvn clean package

# The built JAR will be in target/cosmos-vcs-1.0.0.jar
```

### Running the Application

#### Web UI Mode (Default)
```bash
java -jar target/cosmos-vcs-1.0.0.jar
```
Then open http://localhost:8080 in your browser.

// Make as Soft link
alias cosmos='java -jar /Users/abhijitmishra/Documents/Developer/RunScriptDev/cosmos-vcs-1.0.0.jar'

#### CLI Mode
```bash
# Initialize a repository
java -jar cosmos-vcs.jar init

# Add files to tracking
java -jar cosmos-vcs.jar add file1.txt file2.txt

# Create a commit
java -jar cosmos-vcs.jar commit -m "Initial commit"

# View commit history
java -jar cosmos-vcs.jar log

# Check repository status
java -jar cosmos-vcs.jar status

# Checkout a specific commit
java -jar cosmos-vcs.jar checkout <commit-id>

# Compare two commits
java -jar cosmos-vcs.jar diff <commit-id1> <commit-id2>
```

## Development

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- Node.js 18 or higher (automatically managed by frontend-maven-plugin)

### Development Setup

```bash
# Clone the repository
git clone <repository-url>
cd cosmos-vcs

# Build and run in development mode
mvn spring-boot:run
```

For frontend development:
```bash
cd src/main/frontend
npm install
npm start  # Runs on http://localhost:4200
```

### Project Structure

```
cosmos-vcs/
├── src/main/java/com/cosmos/vcs/
│   ├── model/           # Domain models (Commit, Repository, etc.)
│   ├── service/         # Business logic services
│   ├── controller/      # REST API controllers
│   ├── util/           # Utility classes
│   ├── cli/            # Command-line interface
│   └── config/         # Spring configuration
├── src/main/frontend/   # Angular application
│   ├── src/app/
│   │   ├── components/  # Angular components
│   │   └── services/    # Angular services
│   └── package.json
└── src/main/resources/  # Application properties
```

## Architecture

### Backend (Spring Boot)
- **RepositoryService** - Repository initialization and management
- **FileTrackingService** - File change detection and tracking
- **CommitService** - Commit creation, history, and checkout
- **CompressionService** - File compression for storage optimization

### Frontend (Angular)
- **Dashboard** - Main interface for repository management
- **File Changes** - View and commit file changes
- **Commit History** - Browse commit history
- **Repository Management** - Initialize and configure repositories

### Data Storage
- `.cosmos/` directory structure:
  - `metadata.json` - Repository metadata
  - `branches.json` - Branch information
  - `commits/<commit-id>/` - Individual commit data
  - `objects/` - Compressed file storage

## Configuration

### Ignore Patterns
Create `.cosmosignore` file in your project root:
```
# Temporary files
*.tmp
*.log

# Build outputs
target/
dist/
node_modules/

# IDE files
.idea/
.vscode/
```

### Application Properties
Configure in `src/main/resources/application.properties`:
- Server port: `server.port=8080`
- Logging levels: `logging.level.com.cosmos.vcs=INFO`

## Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CommitServiceTest
```

## Production Deployment

1. Build the application:
   ```bash
   mvn clean package -Dmaven.test.skip=true
   ```

2. Copy the JAR to your target server:
   ```bash
   scp target/cosmos-vcs-1.0.0.jar user@server:/opt/cosmos-vcs/
   ```

3. Run as a service:
   ```bash
   java -jar cosmos-vcs-1.0.0.jar
   ```

## License

This project is licensed under the MIT License - see the LICENSE file for details.