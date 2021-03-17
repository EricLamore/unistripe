import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { UniproxyConsoUpdateComponent } from 'app/entities/uniproxy-conso/uniproxy-conso-update.component';
import { UniproxyConsoService } from 'app/entities/uniproxy-conso/uniproxy-conso.service';
import { UniproxyConso } from 'app/shared/model/uniproxy-conso.model';

describe('Component Tests', () => {
  describe('UniproxyConso Management Update Component', () => {
    let comp: UniproxyConsoUpdateComponent;
    let fixture: ComponentFixture<UniproxyConsoUpdateComponent>;
    let service: UniproxyConsoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [UniproxyConsoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UniproxyConsoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UniproxyConsoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UniproxyConsoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UniproxyConso(123);
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
        const entity = new UniproxyConso();
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
