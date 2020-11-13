import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { UnistripeSharedModule } from 'app/shared/shared.module';
import { EventUsesComponent } from './event-uses.component';
import { EventUsesDetailComponent } from './event-uses-detail.component';
import { EventUsesUpdateComponent } from './event-uses-update.component';
import { EventUsesDeleteDialogComponent } from './event-uses-delete-dialog.component';
import { eventUsesRoute } from './event-uses.route';

@NgModule({
  imports: [UnistripeSharedModule, RouterModule.forChild(eventUsesRoute)],
  declarations: [EventUsesComponent, EventUsesDetailComponent, EventUsesUpdateComponent, EventUsesDeleteDialogComponent],
  entryComponents: [EventUsesDeleteDialogComponent],
})
export class UnistripeEventUsesModule {}
