import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutComponent} from './components/layout/layout.component';
import {ToolbarComponent} from './components/layout/toolbar/toolbar.component';
import {AppRoutingModule} from './app-routing.module';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MainComponent} from './components/main/main.component';
import {MatCardModule} from '@angular/material/card';
import {CardComponent} from './components/basic/card/card.component';
import {MatIconModule} from '@angular/material/icon';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {SidenavComponent} from './components/layout/sidenav/sidenav.component';
import {MessageCardComponent} from './components/basic/message-card/message-card.component';
import {HttpClientModule} from '@angular/common/http';
import {LoadingTextSpinnerComponent} from './components/basic/loading-text-spinner/loading-text-spinner.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {FabButtonComponent} from './components/basic/fab-button/fab-button.component';
import {RaisedButtonComponent} from './components/basic/raised-button/raised-button.component';
import {FlexLayoutModule} from '@angular/flex-layout';
import {DatepickerMultipleComponent} from './components/basic/datepicker-multiple/datepicker-multiple.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {SelectComponent} from './components/basic/select/select.component';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatOptionModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MomentDateModule} from '@angular/material-moment-adapter';
import {DatePipe} from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {NoDataComponent} from './components/basic/no-data/no-data.component';
import {ProgressSpinnerComponent} from './components/basic/progress-spinner/progress-spinner.component';
import {SelectCheckboxComponent} from './components/basic/select-checkbox/select-checkbox.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {LabelComponent} from './components/basic/label/label.component';
import {CheckboxComponent} from './components/basic/checkbox/checkbox.component';
import {ClipboardModule} from '@angular/cdk/clipboard';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {FrenchDateAdapter} from './util/french-date-adapter';
import {LoginComponent} from './components/main/login/login.component';
import {LoginFormComponent} from './components/main/login/login-form/login-form.component';
import {InputComponent} from './components/basic/input/input.component';
import {AgGridModule} from 'ag-grid-angular';
import {SoonComponent} from './components/basic/soon/soon.component';
import {Properties} from './enums/properties';
import {BreadcrumbComponent} from './components/basic/breadcrumb/breadcrumb.component';
import {AutocompleteOffDirective} from './util/directives/autocomplete-off.directive';
import {DashboardComponent} from './components/main/dashboard/dashboard.component';
import {MapComponent} from './components/main/dashboard/map/map.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {KeyNumberComponent} from './components/main/dashboard/key-number/key-number.component';
import {GMapModule} from 'primeng/gmap';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import {ChartComponent} from './components/main/dashboard/chart/chart.component';
import {LeafletMarkerClusterModule} from '@asymmetrik/ngx-leaflet-markercluster';
import {NewsComponent} from './components/main/news/news.component';
import {NewsCardComponent} from './components/main/news/news-card/news-card.component';
import {VaccinsComponent} from './components/main/vaccins/vaccins.component';
import {VaccinMapComponent} from './components/main/vaccins/vaccin-map/vaccin-map.component';
import {VaccinNewsComponent} from './components/main/vaccins/vaccin-news/vaccin-news.component';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import {MailDialogComponent} from './components/layout/sidenav/mail-dialog/mail-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    ToolbarComponent,
    MainComponent,
    CardComponent,
    SidenavComponent,
    MessageCardComponent,
    LoadingTextSpinnerComponent,
    FabButtonComponent,
    RaisedButtonComponent,
    DatepickerMultipleComponent,
    SelectComponent,
    NoDataComponent,
    ProgressSpinnerComponent,
    SelectCheckboxComponent,
    LabelComponent,
    CheckboxComponent,
    LoginComponent,
    LoginFormComponent,
    InputComponent,
    SoonComponent,
    BreadcrumbComponent,
    AutocompleteOffDirective,
    DashboardComponent,
    MapComponent,
    KeyNumberComponent,
    ChartComponent,
    NewsComponent,
    NewsCardComponent,
    VaccinsComponent,
    VaccinMapComponent,
    VaccinNewsComponent,
    MailDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatToolbarModule,
    MatCardModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatButtonModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    FlexLayoutModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatOptionModule,
    MatSelectModule,
    ReactiveFormsModule,
    MomentDateModule,
    FormsModule,
    MatInputModule,
    MatCheckboxModule,
    ClipboardModule,
    MatSnackBarModule,
    AgGridModule,
    AgGridModule.withComponents([]),
    MatGridListModule,
    GMapModule,
    NgxChartsModule,
    LeafletMarkerClusterModule,
    MatTooltipModule,
    MatDialogModule
  ],
  providers: [
    {
      provide: DateAdapter,
      useClass: FrenchDateAdapter,
      deps: [MAT_DATE_LOCALE],
    },
    { provide: MAT_DATE_FORMATS, useValue: Properties.DATE_FORMAT },
    DatePipe,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
