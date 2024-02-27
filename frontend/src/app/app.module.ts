import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CoreModule } from './core/core.module';
import { CategoryModule } from './category/category.module';
import { AuthorModule } from './author/author.module';
import { GameModule } from './game/game.module';
import { ClientsModule } from './clients/clients.module';
import { PrestamoModule } from './prestamo/prestamo.module';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';

// Importa los módulos de localización
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

// Registra el locale español
registerLocaleData(localeEs);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    CategoryModule,
    AuthorModule,
    GameModule,
    ClientsModule,
    BrowserAnimationsModule,
    PrestamoModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
  ],
  providers: [
    {provide: LOCALE_ID, useValue: 'es'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
