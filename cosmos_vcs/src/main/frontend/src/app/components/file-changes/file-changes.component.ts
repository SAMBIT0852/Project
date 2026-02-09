import { Component, OnInit } from '@angular/core';
import { VcsApiService, FileChange } from '../../services/vcs-api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-file-changes',
  template: `
    <div class="changes-container">
      <mat-card class="commit-card">
        <mat-card-header>
          <mat-card-title>Create Commit</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <mat-form-field appearance="outline" style="width: 100%;">
            <mat-label>Commit Message</mat-label>
            <textarea matInput [(ngModel)]="commitMessage" placeholder="Describe your changes..." rows="3"></textarea>
          </mat-form-field>
          
          <mat-form-field appearance="outline" style="width: 100%;">
            <mat-label>Author</mat-label>
            <input matInput [(ngModel)]="author" placeholder="Your name">
          </mat-form-field>
        </mat-card-content>
        <mat-card-actions>
          <button mat-raised-button color="primary" 
                  (click)="createCommit()" 
                  [disabled]="!commitMessage || changes.length === 0">
            <mat-icon>save</mat-icon>
            Commit Changes ({{ changes.length }})
          </button>
          <button mat-button (click)="refreshChanges()">
            <mat-icon>refresh</mat-icon>
            Refresh
          </button>
        </mat-card-actions>
      </mat-card>
      
      <mat-card class="changes-card">
        <mat-card-header>
          <mat-card-title>File Changes</mat-card-title>
          <mat-card-subtitle>{{ repositoryPath }}</mat-card-subtitle>
        </mat-card-header>
        <mat-card-content>
          <div *ngIf="changes.length === 0" class="no-changes">
            <mat-icon>check_circle</mat-icon>
            <p>No changes to commit</p>
          </div>
          
          <mat-list *ngIf="changes.length > 0">
            <mat-list-item *ngFor="let change of changes" class="change-item">
              <mat-icon mat-list-icon [class]="getChangeTypeClass(change.changeType)">
                {{ getChangeTypeIcon(change.changeType) }}
              </mat-icon>
              <div mat-line class="file-path">{{ change.filePath }}</div>
              <div mat-line class="change-details">
                <span class="change-type">{{ change.changeType }}</span>
                <span class="file-size" *ngIf="change.fileSize > 0">
                  {{ formatFileSize(change.fileSize) }}
                </span>
              </div>
            </mat-list-item>
          </mat-list>
        </mat-card-content>
      </mat-card>
      
      <mat-card class="untracked-card" *ngIf="untrackedFiles.length > 0">
        <mat-card-header>
          <mat-card-title>Untracked Files</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <mat-selection-list [(ngModel)]="selectedUntrackedFiles">
            <mat-list-option *ngFor="let file of untrackedFiles" [value]="file">
              <mat-icon mat-list-icon>add_circle_outline</mat-icon>
              {{ file }}
            </mat-list-option>
          </mat-selection-list>
        </mat-card-content>
        <mat-card-actions>
          <button mat-raised-button color="accent" 
                  (click)="addFilesToTracking()" 
                  [disabled]="selectedUntrackedFiles.length === 0">
            <mat-icon>add</mat-icon>
            Add Selected Files ({{ selectedUntrackedFiles.length }})
          </button>
        </mat-card-actions>
      </mat-card>
    </div>
  `,
  styles: [`
    .changes-container {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }
    
    .change-item {
      border-bottom: 1px solid #eee;
    }
    
    .file-path {
      font-family: monospace;
      font-size: 14px;
    }
    
    .change-details {
      font-size: 12px;
      color: #666;
    }
    
    .change-type {
      text-transform: uppercase;
      font-weight: 500;
      margin-right: 10px;
    }
    
    .added { color: #4caf50; }
    .modified { color: #ff9800; }
    .deleted { color: #f44336; }
    .renamed { color: #2196f3; }
    
    .no-changes {
      text-align: center;
      color: #666;
      padding: 40px;
    }
    
    .file-size {
      font-style: italic;
    }
  `]
})
export class FileChangesComponent implements OnInit {
  
  changes: FileChange[] = [];
  untrackedFiles: string[] = [];
  selectedUntrackedFiles: string[] = [];
  commitMessage = '';
  author = 'user';
  repositoryPath = '';
  
  constructor(
    private readonly vcsApi: VcsApiService,
    private readonly snackBar: MatSnackBar
  ) {}
  
  ngOnInit(): void {
    this.repositoryPath = localStorage.getItem('currentRepositoryPath') || '';
    if (this.repositoryPath) {
      this.refreshChanges();
      this.loadUntrackedFiles();
    }
  }
  
  refreshChanges(): void {
    if (!this.repositoryPath) {
      this.snackBar.open('No repository selected', 'Close', { duration: 3000 });
      return;
    }
    
    this.vcsApi.getFileChanges(this.repositoryPath).subscribe({
      next: (changes) => {
        this.changes = changes;
      },
      error: (error) => {
        this.snackBar.open(`Error loading changes: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
  
  loadUntrackedFiles(): void {
    this.vcsApi.getUntrackedFiles(this.repositoryPath).subscribe({
      next: (files) => {
        this.untrackedFiles = files;
      },
      error: (error) => {
        console.error('Error loading untracked files:', error);
      }
    });
  }
  
  createCommit(): void {
    if (!this.commitMessage) {
      this.snackBar.open('Please enter a commit message', 'Close', { duration: 3000 });
      return;
    }
    
    this.vcsApi.createCommit(this.repositoryPath, this.commitMessage, this.author).subscribe({
      next: (commit) => {
        this.snackBar.open('Commit created successfully!', 'Close', { duration: 3000 });
        this.commitMessage = '';
        this.refreshChanges();
        this.loadUntrackedFiles();
      },
      error: (error) => {
        this.snackBar.open(`Error creating commit: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
  
  addFilesToTracking(): void {
    if (this.selectedUntrackedFiles.length === 0) {
      return;
    }
    
    this.vcsApi.addFilesToTracking(this.repositoryPath, this.selectedUntrackedFiles).subscribe({
      next: () => {
        this.snackBar.open(`Added ${this.selectedUntrackedFiles.length} files to tracking`, 'Close', { duration: 3000 });
        this.selectedUntrackedFiles = [];
        this.refreshChanges();
        this.loadUntrackedFiles();
      },
      error: (error) => {
        this.snackBar.open(`Error adding files: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
  
  getChangeTypeIcon(changeType: string): string {
    switch (changeType) {
      case 'ADDED': return 'add_circle';
      case 'MODIFIED': return 'edit';
      case 'DELETED': return 'remove_circle';
      case 'RENAMED': return 'drive_file_rename_outline';
      default: return 'help';
    }
  }
  
  getChangeTypeClass(changeType: string): string {
    return changeType.toLowerCase();
  }
  
  formatFileSize(bytes: number): string {
    if (bytes === 0) return '0 B';
    const k = 1024;
    const sizes = ['B', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(1)) + ' ' + sizes[i];
  }
}