import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { SignatureDetailsComponent } from './signature-details.component';
import { SignatureDetailsDetailComponent } from './signature-details-detail.component';
import { SignatureDetailsUpdateComponent } from './signature-details-update.component';
import { SignatureDetailsDeleteDialogComponent } from './signature-details-delete-dialog.component';
import { signatureDetailsRoute } from './signature-details.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(signatureDetailsRoute)],
  declarations: [
    SignatureDetailsComponent,
    SignatureDetailsDetailComponent,
    SignatureDetailsUpdateComponent,
    SignatureDetailsDeleteDialogComponent,
  ],
  entryComponents: [SignatureDetailsDeleteDialogComponent],
})
export class UnistripeSignatureDetailsModule {}
