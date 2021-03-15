import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { BillsComponent } from './bills.component';
import { BillsDetailComponent } from './bills-detail.component';
import { BillsUpdateComponent } from './bills-update.component';
import { BillsDeleteDialogComponent } from './bills-delete-dialog.component';
import { billsRoute } from './bills.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(billsRoute)],
  declarations: [BillsComponent, BillsDetailComponent, BillsUpdateComponent, BillsDeleteDialogComponent],
  entryComponents: [BillsDeleteDialogComponent],
})
export class UnistripeBillsModule {}
