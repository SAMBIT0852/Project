import { Component, OnInit } from '@angular/core';
import { VcsApiService, Commit } from '../../services/vcs-api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-commit-history',
  template: `
    <mat-card>
      <mat-card-header>
        <mat-card-title>Commit History</mat-card-title>
        <mat-card-subtitle>{{ repositoryPath }}</mat-card-subtitle>
      </mat-card-header>
      <mat-card-content>
        <div *ngIf="commits.length === 0" class="no-commits">
          <mat-icon>info</mat-icon>
          <p>No commits found</p>
        </div>
        
        <mat-list *ngIf="commits.length > 0">
          <mat-list-item *ngFor="let commit of commits" class="commit-item">
            <mat-icon mat-list-icon>commit</mat-icon>
            <div mat-line class="commit-message">{{ commit.message }}</div>
            <div mat-line class="commit-details">
              <span class="commit-id">{{ commit.id.substring(0, 8) }}</span>
              <span class="commit-author">by {{ commit.author }}</span>
              <span class="commit-date">{{ commit.timestamp | date:'medium' }}</span>
            </div>
            <div mat-line class="commit-changes">
              {{ commit.changes.length }} file(s) changed
            </div>
            <button mat-icon-button (click)="checkoutCommit(commit.id)">
              <mat-icon>restore</mat-icon>
            </button>
          </mat-list-item>
        </mat-list>
      </mat-card-content>
      <mat-card-actions>
        <button mat-raised-button color="primary" (click)="loadHistory()">
          <mat-icon>refresh</mat-icon>
          Refresh
        </button>
        <button mat-button routerLink="/dashboard">
          <mat-icon>arrow_back</mat-icon>
          Back to Dashboard
        </button>
      </mat-card-actions>
    </mat-card>
  `,
  styles: [`
    .commit-item {
      border-bottom: 1px solid #eee;
      margin-bottom: 10px;
    }
    
    .commit-message {
      font-weight: 500;
      font-size: 16px;
    }
    
    .commit-details {
      color: #666;
      font-size: 14px;
    }
    
    .commit-id {
      font-family: monospace;
      background: #f5f5f5;
      padding: 2px 6px;
      border-radius: 3px;
      margin-right: 10px;
    }
    
    .commit-author, .commit-date {
      margin-right: 10px;
    }
    
    .commit-changes {
      color: #888;
      font-size: 12px;
    }
    
    .no-commits {
      text-align: center;
      color: #666;
      padding: 40px;
    }
  `]
})
export class CommitHistoryComponent implements OnInit {
  
  commits: Commit[] = [];
  repositoryPath = '';
  
  constructor(
    private readonly vcsApi: VcsApiService,
    private readonly snackBar: MatSnackBar
  ) {}
  
  ngOnInit(): void {
    this.repositoryPath = localStorage.getItem('currentRepositoryPath') || '';
    if (this.repositoryPath) {
      this.loadHistory();
    }
  }
  
  loadHistory(): void {
    if (!this.repositoryPath) {
      this.snackBar.open('No repository selected', 'Close', { duration: 3000 });
      return;
    }
    
    this.vcsApi.getCommitHistory(this.repositoryPath).subscribe({
      next: (commits) => {
        this.commits = commits;
      },
      error: (error) => {
        this.snackBar.open(`Error loading history: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
  
  checkoutCommit(commitId: string): void {
    if (confirm(`Are you sure you want to checkout commit ${commitId.substring(0, 8)}? This will overwrite your current files.`)) {
      this.vcsApi.checkoutCommit(this.repositoryPath, commitId).subscribe({
        next: () => {
          this.snackBar.open('Commit checked out successfully!', 'Close', { duration: 3000 });
        },
        error: (error) => {
          this.snackBar.open(`Error checking out commit: ${error.error}`, 'Close', { duration: 5000 });
        }
      });
    }
  }
}