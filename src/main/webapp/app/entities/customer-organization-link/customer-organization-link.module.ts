import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { CustomerOrganizationLinkComponent } from './customer-organization-link.component';
import { CustomerOrganizationLinkDetailComponent } from './customer-organization-link-detail.component';
import { CustomerOrganizationLinkUpdateComponent } from './customer-organization-link-update.component';
import { CustomerOrganizationLinkDeleteDialogComponent } from './customer-organization-link-delete-dialog.component';
import { customerOrganizationLinkRoute } from './customer-organization-link.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(customerOrganizationLinkRoute)],
  declarations: [
    CustomerOrganizationLinkComponent,
    CustomerOrganizationLinkDetailComponent,
    CustomerOrganizationLinkUpdateComponent,
    CustomerOrganizationLinkDeleteDialogComponent,
  ],
  entryComponents: [CustomerOrganizationLinkDeleteDialogComponent],
})
export class UnistripeCustomerOrganizationLinkModule {}
