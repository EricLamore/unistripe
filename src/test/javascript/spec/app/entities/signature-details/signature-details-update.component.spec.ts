import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { SignatureDetailsUpdateComponent } from 'app/entities/signature-details/signature-details-update.component';
import { SignatureDetailsService } from 'app/entities/signature-details/signature-details.service';
import { SignatureDetails } from 'app/shared/model/signature-details.model';

describe('Component Tests', () => {
  describe('SignatureDetails Management Update Component', () => {
    let comp: SignatureDetailsUpdateComponent;
    let fixture: ComponentFixture<SignatureDetailsUpdateComponent>;
    let service: SignatureDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [SignatureDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SignatureDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SignatureDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignatureDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SignatureDetails(123);
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
        const entity = new SignatureDetails();
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
