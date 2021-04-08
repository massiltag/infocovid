import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RoutePathEnum} from './enums/route-path.enum';
import {DashboardComponent} from './components/main/dashboard/dashboard.component';
import {NewsComponent} from './components/main/news/news.component';
import {VaccinsComponent} from './components/main/vaccins/vaccins.component';
import { PredictionsComponent } from './components/main/predictions/predictions.component';


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
    path: RoutePathEnum.VACCINS,
    component: VaccinsComponent
  },
  {
    path: RoutePathEnum.ACTU,
    component: NewsComponent
  },
  {
    path: RoutePathEnum.PREDICTIONS,
    component: PredictionsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
