import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Repository {
  path: string;
  currentBranch: string;
  branches: string[];
  headCommitId: string;
  createdAt: string;
  lastModified: string;
  trackedFiles: string[];
  ignoredPatterns: string[];
}

export interface Commit {
  id: string;
  message: string;
  timestamp: string;
  parentId?: string;
  branch: string;
  changes: FileChange[];
  author: string;
}

export interface FileChange {
  filePath: string;
  oldPath?: string;
  changeType: 'ADDED' | 'MODIFIED' | 'DELETED' | 'RENAMED';
  fileHash: string;
  fileSize: number;
  binary: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class VcsApiService {
  
  private readonly baseUrl = '/api';
  
  constructor(private readonly http: HttpClient) {}
  
  // Repository operations
  initRepository(path: string): Observable<Repository> {
    const params = new HttpParams().set('path', path);
    return this.http.post<Repository>(`${this.baseUrl}/repository/init`, null, { params });
  }
  
  loadRepository(path: string): Observable<Repository> {
    const params = new HttpParams().set('path', path);
    return this.http.get<Repository>(`${this.baseUrl}/repository/load`, { params });
  }
  
  checkRepository(path: string): Observable<boolean> {
    const params = new HttpParams().set('path', path);
    return this.http.get<boolean>(`${this.baseUrl}/repository/check`, { params });
  }
  
  getIgnorePatterns(repositoryPath: string): Observable<string[]> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.get<string[]>(`${this.baseUrl}/repository/ignore-patterns`, { params });
  }
  
  // Commit operations
  createCommit(repositoryPath: string, message: string, author: string = 'user'): Observable<Commit> {
    const params = new HttpParams()
      .set('repositoryPath', repositoryPath)
      .set('message', message)
      .set('author', author);
    return this.http.post<Commit>(`${this.baseUrl}/commit/create`, null, { params });
  }
  
  getCommitHistory(repositoryPath: string, limit: number = 20): Observable<Commit[]> {
    const params = new HttpParams()
      .set('repositoryPath', repositoryPath)
      .set('limit', limit.toString());
    return this.http.get<Commit[]>(`${this.baseUrl}/commit/history`, { params });
  }
  
  getCommit(repositoryPath: string, commitId: string): Observable<Commit> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.get<Commit>(`${this.baseUrl}/commit/${commitId}`, { params });
  }
  
  checkoutCommit(repositoryPath: string, commitId: string): Observable<string> {
    const params = new HttpParams()
      .set('repositoryPath', repositoryPath)
      .set('commitId', commitId);
    return this.http.post<string>(`${this.baseUrl}/commit/checkout`, null, { params });
  }
  
  getDiff(repositoryPath: string, commitId1: string, commitId2: string): Observable<string[]> {
    const params = new HttpParams()
      .set('repositoryPath', repositoryPath)
      .set('commitId1', commitId1)
      .set('commitId2', commitId2);
    return this.http.get<string[]>(`${this.baseUrl}/commit/diff`, { params });
  }
  
  // File operations
  getFileChanges(repositoryPath: string): Observable<FileChange[]> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.get<FileChange[]>(`${this.baseUrl}/files/changes`, { params });
  }
  
  getUntrackedFiles(repositoryPath: string): Observable<string[]> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.get<string[]>(`${this.baseUrl}/files/untracked`, { params });
  }
  
  addFilesToTracking(repositoryPath: string, filePaths: string[]): Observable<string> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.post<string>(`${this.baseUrl}/files/add`, filePaths, { params });
  }
  
  removeFilesFromTracking(repositoryPath: string, filePaths: string[]): Observable<string> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.post<string>(`${this.baseUrl}/files/remove`, filePaths, { params });
  }
  
  getTrackedFiles(repositoryPath: string): Observable<string[]> {
    const params = new HttpParams().set('repositoryPath', repositoryPath);
    return this.http.get<string[]>(`${this.baseUrl}/files/tracked`, { params });
  }
}