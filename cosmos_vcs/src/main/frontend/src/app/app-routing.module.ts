import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RepositoryComponent } from './components/repository/repository.component';
import { CommitHistoryComponent } from './components/commit-history/commit-history.component';
import { FileChangesComponent } from './components/file-changes/file-changes.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'repository', component: RepositoryComponent },
  { path: 'history', component: CommitHistoryComponent },
  { path: 'changes', component: FileChangesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }