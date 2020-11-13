import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { EventUsesUpdateComponent } from 'app/entities/event-uses/event-uses-update.component';
import { EventUsesService } from 'app/entities/event-uses/event-uses.service';
import { EventUses } from 'app/shared/model/event-uses.model';

describe('Component Tests', () => {
  describe('EventUses Management Update Component', () => {
    let comp: EventUsesUpdateComponent;
    let fixture: ComponentFixture<EventUsesUpdateComponent>;
    let service: EventUsesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [EventUsesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EventUsesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EventUsesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EventUsesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventUses(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EventUses();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
