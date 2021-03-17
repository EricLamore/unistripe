import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { UniproxyConsoComponent } from './uniproxy-conso.component';
import { UniproxyConsoDetailComponent } from './uniproxy-conso-detail.component';
import { UniproxyConsoUpdateComponent } from './uniproxy-conso-update.component';
import { UniproxyConsoDeleteDialogComponent } from './uniproxy-conso-delete-dialog.component';
import { uniproxyConsoRoute } from './uniproxy-conso.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(uniproxyConsoRoute)],
  declarations: [UniproxyConsoComponent, UniproxyConsoDetailComponent, UniproxyConsoUpdateComponent, UniproxyConsoDeleteDialogComponent],
  entryComponents: [UniproxyConsoDeleteDialogComponent],
})
export class UnistripeUniproxyConsoModule {}
