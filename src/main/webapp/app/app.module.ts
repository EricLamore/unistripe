import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { UnistripeSharedModule } from 'app/shared/shared.module';
import { UnistripeCoreModule } from 'app/core/core.module';
import { UnistripeAppRoutingModule } from './app-routing.module';
import { UnistripeHomeModule } from './home/home.module';
import { UnistripeEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    UnistripeSharedModule,
    UnistripeCoreModule,
    UnistripeHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    UnistripeEntityModule,
    UnistripeAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class UnistripeAppModule {}
