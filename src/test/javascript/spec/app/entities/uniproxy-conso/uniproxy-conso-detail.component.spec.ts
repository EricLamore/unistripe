import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { UnistripeTestModule } from '../../../test.module';
import { UniproxyConsoDetailComponent } from 'app/entities/uniproxy-conso/uniproxy-conso-detail.component';
import { UniproxyConso } from 'app/shared/model/uniproxy-conso.model';

describe('Component Tests', () => {
  describe('UniproxyConso Management Detail Component', () => {
    let comp: UniproxyConsoDetailComponent;
    let fixture: ComponentFixture<UniproxyConsoDetailComponent>;
    const route = ({ data: of({ uniproxyConso: new UniproxyConso(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [UnistripeTestModule],
        declarations: [UniproxyConsoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UniproxyConsoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UniproxyConsoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load uniproxyConso on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.uniproxyConso).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
