# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is Cosmos VCS - a local-only version control system designed for personal project tracking. The system will be built as a hybrid application combining Angular frontend with Spring Boot microservices backend, deployed as a single JAR file.

## Architecture

### Core Components
- **Angular Frontend**: Web-based UI for VCS operations
- **Spring Boot Backend**: RESTful microservices handling VCS logic
- **Local Storage**: `.cosmos/` directory structure for metadata and file versions
- **Command Line Interface**: Commands like `cosmos init`, `cosmos commit`, `cosmos log`, etc.

### Data Storage Structure
- `.cosmos/commits/<commit-id>/` - Individual commit directories
- `.cosmos/index.json` or `.cosmos/metadata.json` - Repository metadata
- `.cosmosignore` - Files to ignore during tracking

### Key Features Implementation
- File tracking using `java.nio.file` operations
- SHA-256 hashing for version IDs
- JSON serialization for metadata storage
- Delta storage optimization for large files
- Branch and merge support with conflict resolution

## Development Setup

Since this appears to be a greenfield project with no existing code structure, development will need to start with:

1. **Backend Setup**: Spring Boot application with appropriate dependencies
2. **Frontend Setup**: Angular application with build configuration
3. **Build System**: Maven or Gradle for Java backend, npm/yarn for Angular frontend
4. **Integration**: Configuration to serve Angular from Spring Boot for single JAR deployment

## Core VCS Commands to Implement

- `cosmos init` - Initialize repository
- `cosmos commit -m "Message"` - Commit changes
- `cosmos log` - View commit history
- `cosmos checkout <commit-id>` - Restore files from commit
- `cosmos diff <commit-id1> <commit-id2>` - Show differences between commits

## Key Implementation Considerations

- Handle large files efficiently with delta storage
- Implement safety checks before file overwrites during checkout
- Support file compression for commits
- Ensure `.cosmos` folder is backup-friendly
- Handle merge conflicts with proper markers