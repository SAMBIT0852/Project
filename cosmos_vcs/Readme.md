--- Use
echo "# cosmos_vcs" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/abhijit4981/cosmos_vcs.git
git push -u origin main
--- Prompt
I want to design and implement a local-only version control system (VCS) , for personal project tracking purposes.
It will use angular as the UI and spring boot micro service as backend in one code and deployed as a single jar.
It should
    Track changes to files in a given directory.
    Allow committing snapshots of changes with messages.
    Be able to view history of changes.
    Allow restoring an older version of files.
    Store all data locally.
Core Features
    Initialize Repository
    Create a hidden folder (e.g., .cosmos) in the root of the project to store metadata, commits, and file versions.
    Track Files
    Decide whether to track all files or only selected files.
    Handle ignoring files via a .cosmosignore file.
    Commit
    Save a snapshot of tracked files.
    must Store metadata for every commit
        Commit ID (hash or incremental number)
        Timestamp
        Commit message
        List of changed files
        Log
        View the list of commits in reverse chronological order.
        Show commit ID, timestamp, and message.
        Checkout
        Restore files from a specific commit ID.
        Overwrite working directory with older versions.
        Diff
        Show changes between two commits.
        Show which files were modified, added, or deleted.
        Branches
        Support multiple branches.
        Switch between branches.
        Merge
        Merge changes from one branch into another.
        Conflict Resolution
        Handle merge conflicts with markers.
        Technical Implementation Requirements
Use java.nio.file for file operations.
Serialize metadata in JSON files.
Data Storage
    Store commits in .cosmos/commits/<commit-id>/ directories.
    Store metadata in .cosmos/index.json or .cosmos/metadata.json.
Hashing

Use SHA-256 or any latest hash of file contents for version IDs.
Support for commands like 
    bash
    Copy
    Edit
    cosmos init
    cosmos commit -m "Message"
    cosmos log
    cosmos checkout <commit-id>
    cosmos diff <commit-id1> <commit-id2>
    Extra
    Handle large files efficiently.

Optimize storage by storing only changes (deltas) instead of full copies for every commit.
Make the .cosmos folder easy to back up.
Include safety checks before overwriting files on checkout.
Documentation explaining the architecture and commands.
    File compression for commits
make it production ready.