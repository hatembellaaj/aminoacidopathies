import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CassuspecteFormService } from './cassuspecte-form.service';
import { CassuspecteService } from '../service/cassuspecte.service';
import { ICassuspecte } from '../cassuspecte.model';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';

import { CassuspecteUpdateComponent } from './cassuspecte-update.component';

describe('Cassuspecte Management Update Component', () => {
  let comp: CassuspecteUpdateComponent;
  let fixture: ComponentFixture<CassuspecteUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let cassuspecteFormService: CassuspecteFormService;
  let cassuspecteService: CassuspecteService;
  let ficheService: FicheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CassuspecteUpdateComponent],
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
      .overrideTemplate(CassuspecteUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CassuspecteUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    cassuspecteFormService = TestBed.inject(CassuspecteFormService);
    cassuspecteService = TestBed.inject(CassuspecteService);
    ficheService = TestBed.inject(FicheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Fiche query and add missing value', () => {
      const cassuspecte: ICassuspecte = { id: 456 };
      const fiche: IFiche = { id: 99662 };
      cassuspecte.fiche = fiche;

      const ficheCollection: IFiche[] = [{ id: 32055 }];
      jest.spyOn(ficheService, 'query').mockReturnValue(of(new HttpResponse({ body: ficheCollection })));
      const additionalFiches = [fiche];
      const expectedCollection: IFiche[] = [...additionalFiches, ...ficheCollection];
      jest.spyOn(ficheService, 'addFicheToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ cassuspecte });
      comp.ngOnInit();

      expect(ficheService.query).toHaveBeenCalled();
      expect(ficheService.addFicheToCollectionIfMissing).toHaveBeenCalledWith(
        ficheCollection,
        ...additionalFiches.map(expect.objectContaining)
      );
      expect(comp.fichesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const cassuspecte: ICassuspecte = { id: 456 };
      const fiche: IFiche = { id: 14718 };
      cassuspecte.fiche = fiche;

      activatedRoute.data = of({ cassuspecte });
      comp.ngOnInit();

      expect(comp.fichesSharedCollection).toContain(fiche);
      expect(comp.cassuspecte).toEqual(cassuspecte);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICassuspecte>>();
      const cassuspecte = { id: 123 };
      jest.spyOn(cassuspecteFormService, 'getCassuspecte').mockReturnValue(cassuspecte);
      jest.spyOn(cassuspecteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cassuspecte });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cassuspecte }));
      saveSubject.complete();

      // THEN
      expect(cassuspecteFormService.getCassuspecte).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(cassuspecteService.update).toHaveBeenCalledWith(expect.objectContaining(cassuspecte));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICassuspecte>>();
      const cassuspecte = { id: 123 };
      jest.spyOn(cassuspecteFormService, 'getCassuspecte').mockReturnValue({ id: null });
      jest.spyOn(cassuspecteService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cassuspecte: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: cassuspecte }));
      saveSubject.complete();

      // THEN
      expect(cassuspecteFormService.getCassuspecte).toHaveBeenCalled();
      expect(cassuspecteService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICassuspecte>>();
      const cassuspecte = { id: 123 };
      jest.spyOn(cassuspecteService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ cassuspecte });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(cassuspecteService.update).toHaveBeenCalled();
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
