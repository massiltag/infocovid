import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from './components/main/main.component';
import {RoutePathEnum} from './enums/route-path.enum';

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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
