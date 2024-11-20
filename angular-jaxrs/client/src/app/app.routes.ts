import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { DownloadComponent } from './components/download/download.component';

export const routes: Routes = [  
    { path: '', redirectTo: 'index', pathMatch: 'full' },
    { path: 'index', component: IndexComponent },
    { path: 'download', component: DownloadComponent },
];
