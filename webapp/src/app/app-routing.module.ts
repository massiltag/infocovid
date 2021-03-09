import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './components/main/main.component';
import {RoutePathEnum} from './enums/route-path.enum';
import {DashboardComponent} from './components/main/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: RoutePathEnum.DASHBOARD
  },
  {
    path: RoutePathEnum.DASHBOARD,
    component: DashboardComponent
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: RoutePathEnum.VACCINS
  },
  {
    path: RoutePathEnum.VACCINS,
    component: MainComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
