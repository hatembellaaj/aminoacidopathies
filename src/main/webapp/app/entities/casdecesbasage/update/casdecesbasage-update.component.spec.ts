import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CasdecesbasageFormService } from './casdecesbasage-form.service';
import { CasdecesbasageService } from '../service/casdecesbasage.service';
import { ICasdecesbasage } from '../casdecesbasage.model';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';

import { CasdecesbasageUpdateComponent } from './casdecesbasage-update.component';

describe('Casdecesbasage Management Update Component', () => {
  let comp: CasdecesbasageUpdateComponent;
  let fixture: ComponentFixture<CasdecesbasageUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let casdecesbasageFormService: CasdecesbasageFormService;
  let casdecesbasageService: CasdecesbasageService;
  let ficheService: FicheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CasdecesbasageUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(CasdecesbasageUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CasdecesbasageUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    casdecesbasageFormService = TestBed.inject(CasdecesbasageFormService);
    casdecesbasageService = TestBed.inject(CasdecesbasageService);
    ficheService = TestBed.inject(FicheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Fiche query and add missing value', () => {
      const casdecesbasage: ICasdecesbasage = { id: 456 };
      const fiche: IFiche = { id: 66243 };
      casdecesbasage.fiche = fiche;

      const ficheCollection: IFiche[] = [{ id: 85086 }];
      jest.spyOn(ficheService, 'query').mockReturnValue(of(new HttpResponse({ body: ficheCollection })));
      const additionalFiches = [fiche];
      const expectedCollection: IFiche[] = [...additionalFiches, ...ficheCollection];
      jest.spyOn(ficheService, 'addFicheToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ casdecesbasage });
      comp.ngOnInit();

      expect(ficheService.query).toHaveBeenCalled();
      expect(ficheService.addFicheToCollectionIfMissing).toHaveBeenCalledWith(
        ficheCollection,
        ...additionalFiches.map(expect.objectContaining)
      );
      expect(comp.fichesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const casdecesbasage: ICasdecesbasage = { id: 456 };
      const fiche: IFiche = { id: 11798 };
      casdecesbasage.fiche = fiche;

      activatedRoute.data = of({ casdecesbasage });
      comp.ngOnInit();

      expect(comp.fichesSharedCollection).toContain(fiche);
      expect(comp.casdecesbasage).toEqual(casdecesbasage);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasdecesbasage>>();
      const casdecesbasage = { id: 123 };
      jest.spyOn(casdecesbasageFormService, 'getCasdecesbasage').mockReturnValue(casdecesbasage);
      jest.spyOn(casdecesbasageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casdecesbasage });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: casdecesbasage }));
      saveSubject.complete();

      // THEN
      expect(casdecesbasageFormService.getCasdecesbasage).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(casdecesbasageService.update).toHaveBeenCalledWith(expect.objectContaining(casdecesbasage));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasdecesbasage>>();
      const casdecesbasage = { id: 123 };
      jest.spyOn(casdecesbasageFormService, 'getCasdecesbasage').mockReturnValue({ id: null });
      jest.spyOn(casdecesbasageService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casdecesbasage: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: casdecesbasage }));
      saveSubject.complete();

      // THEN
      expect(casdecesbasageFormService.getCasdecesbasage).toHaveBeenCalled();
      expect(casdecesbasageService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICasdecesbasage>>();
      const casdecesbasage = { id: 123 };
      jest.spyOn(casdecesbasageService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ casdecesbasage });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(casdecesbasageService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareFiche', () => {
      it('Should forward to ficheService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(ficheService, 'compareFiche');
        comp.compareFiche(entity, entity2);
        expect(ficheService.compareFiche).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
