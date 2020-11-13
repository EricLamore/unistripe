import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { EventUsesDetailComponent } from 'app/entities/event-uses/event-uses-detail.component';
import { EventUses } from 'app/shared/model/event-uses.model';

describe('Component Tests', () => {
  describe('EventUses Management Detail Component', () => {
    let comp: EventUsesDetailComponent;
    let fixture: ComponentFixture<EventUsesDetailComponent>;
    const route = ({ data: of({ eventUses: new EventUses(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [EventUsesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EventUsesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EventUsesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load eventUses on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.eventUses).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
