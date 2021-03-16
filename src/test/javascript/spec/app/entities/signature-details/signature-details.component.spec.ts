import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { UnistripeTestModule } from '../../../test.module';
import { SignatureDetailsComponent } from 'app/entities/signature-details/signature-details.component';
import { SignatureDetailsService } from 'app/entities/signature-details/signature-details.service';
import { SignatureDetails } from 'app/shared/model/signature-details.model';

describe('Component Tests', () => {
  describe('SignatureDetails Management Component', () => {
    let comp: SignatureDetailsComponent;
    let fixture: ComponentFixture<SignatureDetailsComponent>;
    let service: SignatureDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [SignatureDetailsComponent],
      })
        .overrideTemplate(SignatureDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SignatureDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignatureDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SignatureDetails(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.signatureDetails && comp.signatureDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
