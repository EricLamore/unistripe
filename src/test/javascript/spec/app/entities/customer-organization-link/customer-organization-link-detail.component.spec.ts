import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { CustomerOrganizationLinkDetailComponent } from 'app/entities/customer-organization-link/customer-organization-link-detail.component';
import { CustomerOrganizationLink } from 'app/shared/model/customer-organization-link.model';

describe('Component Tests', () => {
  describe('CustomerOrganizationLink Management Detail Component', () => {
    let comp: CustomerOrganizationLinkDetailComponent;
    let fixture: ComponentFixture<CustomerOrganizationLinkDetailComponent>;
    const route = ({ data: of({ customerOrganizationLink: new CustomerOrganizationLink(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [CustomerOrganizationLinkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CustomerOrganizationLinkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CustomerOrganizationLinkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load customerOrganizationLink on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.customerOrganizationLink).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
