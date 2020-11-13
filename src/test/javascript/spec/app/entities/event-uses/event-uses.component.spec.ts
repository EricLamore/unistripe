import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { EventUsesComponent } from 'app/entities/event-uses/event-uses.component';
import { EventUsesService } from 'app/entities/event-uses/event-uses.service';
import { EventUses } from 'app/shared/model/event-uses.model';

describe('Component Tests', () => {
  describe('EventUses Management Component', () => {
    let comp: EventUsesComponent;
    let fixture: ComponentFixture<EventUsesComponent>;
    let service: EventUsesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [EventUsesComponent],
      })
        .overrideTemplate(EventUsesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventUsesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventUsesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EventUses(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.eventUses && comp.eventUses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
