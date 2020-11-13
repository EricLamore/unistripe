import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { CustomerOrganizationLinkComponent } from 'app/entities/customer-organization-link/customer-organization-link.component';
import { CustomerOrganizationLinkService } from 'app/entities/customer-organization-link/customer-organization-link.service';
import { CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

describe('Component Tests', () => {
  describe('CustomerOrganizationLink Management Component', () => {
    let comp: CustomerOrganizationLinkComponent;
    let fixture: ComponentFixture<CustomerOrganizationLinkComponent>;
    let service: CustomerOrganizationLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [CustomerOrganizationLinkComponent],
      })
        .overrideTemplate(CustomerOrganizationLinkComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CustomerOrganizationLinkComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CustomerOrganizationLinkService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CustomerOrganizationLink(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.customerOrganizationLinks && comp.customerOrganizationLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
