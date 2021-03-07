import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './components/main/main.component';
import {RoutePathEnum} from './enums/route-path.enum';
import { VaccinComponent } from './components/vaccin/vaccin.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: RoutePathEnum.HOME
  },
  {
    path: RoutePathEnum.HOME,
    component: MainComponent
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: RoutePathEnum.VACCIN
  },
  {
    path: RoutePathEnum.VACCIN,
    component: VaccinComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
