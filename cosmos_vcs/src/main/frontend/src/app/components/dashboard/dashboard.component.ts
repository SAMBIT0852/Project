import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VcsApiService, Repository } from '../../services/vcs-api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-dashboard',
  template: `
    <div class="dashboard-container">
      <mat-card class="welcome-card">
        <mat-card-header>
          <mat-card-title>Welcome to Cosmos VCS</mat-card-title>
          <mat-card-subtitle>Local Version Control System</mat-card-subtitle>
        </mat-card-header>
        <mat-card-content>
          <p>Manage your projects with a simple, local-only version control system.</p>
        </mat-card-content>
      </mat-card>

      <div class="action-cards">
        <mat-card class="action-card" (click)="openRepository()">
          <mat-card-header>
            <mat-icon mat-card-avatar>folder_open</mat-icon>
            <mat-card-title>Open Repository</mat-card-title>
            <mat-card-subtitle>Load an existing repository</mat-card-subtitle>
          </mat-card-header>
        </mat-card>

        <mat-card class="action-card" (click)="initializeRepository()">
          <mat-card-header>
            <mat-icon mat-card-avatar>add_circle</mat-icon>
            <mat-card-title>Initialize Repository</mat-card-title>
            <mat-card-subtitle>Create a new repository</mat-card-subtitle>
          </mat-card-header>
        </mat-card>
      </div>

      <mat-card class="repository-form" *ngIf="showRepositoryForm">
        <mat-card-header>
          <mat-card-title>{{ isInitializing ? 'Initialize' : 'Open' }} Repository</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <mat-form-field appearance="outline" style="width: 100%;">
            <mat-label>Repository Path</mat-label>
            <input matInput [(ngModel)]="repositoryPath" placeholder="/path/to/your/project">
          </mat-form-field>
        </mat-card-content>
        <mat-card-actions>
          <button mat-raised-button color="primary" (click)="handleRepositoryAction()" [disabled]="!repositoryPath">
            {{ isInitializing ? 'Initialize' : 'Open' }}
          </button>
          <button mat-button (click)="cancelRepositoryAction()">Cancel</button>
        </mat-card-actions>
      </mat-card>

      <mat-card class="current-repository" *ngIf="currentRepository">
        <mat-card-header>
          <mat-card-title>Current Repository</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <p><strong>Path:</strong> {{ currentRepository.path }}</p>
          <p><strong>Branch:</strong> {{ currentRepository.currentBranch }}</p>
          <p><strong>Last Modified:</strong> {{ currentRepository.lastModified | date:'medium' }}</p>
          <p><strong>Tracked Files:</strong> {{ currentRepository.trackedFiles?.length || 0 }}</p>
        </mat-card-content>
        <mat-card-actions>
          <button mat-raised-button color="primary" routerLink="/changes">
            <mat-icon>edit</mat-icon>
            View Changes
          </button>
          <button mat-raised-button color="accent" routerLink="/history">
            <mat-icon>history</mat-icon>
            Commit History
          </button>
        </mat-card-actions>
      </mat-card>
    </div>
  `,
  styles: [`
    .dashboard-container {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }
    
    .welcome-card {
      text-align: center;
    }
    
    .action-cards {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
      gap: 20px;
    }
    
    .action-card {
      cursor: pointer;
      transition: transform 0.2s ease-in-out;
    }
    
    .action-card:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }
    
    .repository-form, .current-repository {
      max-width: 600px;
      margin: 0 auto;
    }
  `]
})
export class DashboardComponent implements OnInit {
  
  showRepositoryForm = false;
  isInitializing = false;
  repositoryPath = '';
  currentRepository: Repository | null = null;
  
  constructor(
    private readonly vcsApi: VcsApiService,
    private readonly snackBar: MatSnackBar,
    private readonly router: Router
  ) {}
  
  ngOnInit(): void {
    const savedPath = localStorage.getItem('currentRepositoryPath');
    if (savedPath) {
      this.loadRepository(savedPath);
    }
  }
  
  openRepository(): void {
    this.showRepositoryForm = true;
    this.isInitializing = false;
    this.repositoryPath = '';
  }
  
  initializeRepository(): void {
    this.showRepositoryForm = true;
    this.isInitializing = true;
    this.repositoryPath = '';
  }
  
  handleRepositoryAction(): void {
    if (this.isInitializing) {
      this.initRepo();
    } else {
      this.loadRepository(this.repositoryPath);
    }
  }
  
  cancelRepositoryAction(): void {
    this.showRepositoryForm = false;
    this.repositoryPath = '';
  }
  
  private initRepo(): void {
    this.vcsApi.initRepository(this.repositoryPath).subscribe({
      next: (repository) => {
        this.currentRepository = repository;
        localStorage.setItem('currentRepositoryPath', repository.path);
        this.showRepositoryForm = false;
        this.snackBar.open('Repository initialized successfully!', 'Close', { duration: 3000 });
      },
      error: (error) => {
        this.snackBar.open(`Error: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
  
  private loadRepository(path: string): void {
    this.vcsApi.loadRepository(path).subscribe({
      next: (repository) => {
        this.currentRepository = repository;
        localStorage.setItem('currentRepositoryPath', repository.path);
        this.showRepositoryForm = false;
        this.snackBar.open('Repository loaded successfully!', 'Close', { duration: 3000 });
      },
      error: (error) => {
        this.snackBar.open(`Error: ${error.error}`, 'Close', { duration: 5000 });
      }
    });
  }
}