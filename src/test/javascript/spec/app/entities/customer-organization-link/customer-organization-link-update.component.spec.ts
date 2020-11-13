import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { CustomerOrganizationLinkUpdateComponent } from 'app/entities/customer-organization-link/customer-organization-link-update.component';
import { CustomerOrganizationLinkService } from 'app/entities/customer-organization-link/customer-organization-link.service';
import { CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

describe('Component Tests', () => {
  describe('CustomerOrganizationLink Management Update Component', () => {
    let comp: CustomerOrganizationLinkUpdateComponent;
    let fixture: ComponentFixture<CustomerOrganizationLinkUpdateComponent>;
    let service: CustomerOrganizationLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [CustomerOrganizationLinkUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CustomerOrganizationLinkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerOrganizationLinkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerOrganizationLinkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CustomerOrganizationLink(123);
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
        const entity = new CustomerOrganizationLink();
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
