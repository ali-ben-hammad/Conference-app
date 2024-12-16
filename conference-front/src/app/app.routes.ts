import {RouterModule, Routes} from '@angular/router';
import {ConferencesComponent} from './ui/conferences/conferences.component';
import {NgModule} from '@angular/core';
import {AuthGuard} from './guards/auth.guard';
import {KeynotesComponent} from './ui/keynotes/keynotes.component';

export const routes: Routes = [
  { path: 'conferences', component: ConferencesComponent, canActivate : [AuthGuard], data: {roles :['USER']} },
   { path: 'keynotes', component: KeynotesComponent, canActivate : [AuthGuard], data: {roles :['USER']} },
  //{ path: '/', redirectTo: '/wallets', pathMatch: 'full' },
 // { path: '**', redirectTo: '/wallets' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
