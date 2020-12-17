import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { ProductRatePlanPriceLinkComponent } from './product-rate-plan-price-link.component';
import { ProductRatePlanPriceLinkDetailComponent } from './product-rate-plan-price-link-detail.component';
import { ProductRatePlanPriceLinkUpdateComponent } from './product-rate-plan-price-link-update.component';
import { ProductRatePlanPriceLinkDeleteDialogComponent } from './product-rate-plan-price-link-delete-dialog.component';
import { productRatePlanPriceLinkRoute } from './product-rate-plan-price-link.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(productRatePlanPriceLinkRoute)],
  declarations: [
    ProductRatePlanPriceLinkComponent,
    ProductRatePlanPriceLinkDetailComponent,
    ProductRatePlanPriceLinkUpdateComponent,
    ProductRatePlanPriceLinkDeleteDialogComponent,
  ],
  entryComponents: [ProductRatePlanPriceLinkDeleteDialogComponent],
})
export class UnistripeProductRatePlanPriceLinkModule {}
