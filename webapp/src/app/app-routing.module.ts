import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoutePathEnum} from './enums/route-path.enum';
import {DashboardComponent} from './components/main/dashboard/dashboard.component';
import {NewsComponent} from './components/main/news/news.component';

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
    path: RoutePathEnum.ACTU,
    component: NewsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
