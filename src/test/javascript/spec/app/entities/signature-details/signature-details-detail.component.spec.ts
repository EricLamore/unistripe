import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { SignatureDetailsDetailComponent } from 'app/entities/signature-details/signature-details-detail.component';
import { SignatureDetails } from 'app/shared/model/signature-details.model';

describe('Component Tests', () => {
  describe('SignatureDetails Management Detail Component', () => {
    let comp: SignatureDetailsDetailComponent;
    let fixture: ComponentFixture<SignatureDetailsDetailComponent>;
    const route = ({ data: of({ signatureDetails: new SignatureDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [SignatureDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SignatureDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SignatureDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load signatureDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.signatureDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
