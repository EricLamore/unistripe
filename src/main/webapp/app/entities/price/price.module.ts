import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { PriceComponent } from './price.component';
import { PriceDetailComponent } from './price-detail.component';
import { PriceUpdateComponent } from './price-update.component';
import { PriceDeleteDialogComponent } from './price-delete-dialog.component';
import { priceRoute } from './price.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(priceRoute)],
  declarations: [PriceComponent, PriceDetailComponent, PriceUpdateComponent, PriceDeleteDialogComponent],
  entryComponents: [PriceDeleteDialogComponent],
})
export class UnistripePriceModule {}
