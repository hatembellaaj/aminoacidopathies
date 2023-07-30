import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MetaboliqueFormService } from './metabolique-form.service';
import { MetaboliqueService } from '../service/metabolique.service';
import { IMetabolique } from '../metabolique.model';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';

import { MetaboliqueUpdateComponent } from './metabolique-update.component';

describe('Metabolique Management Update Component', () => {
  let comp: MetaboliqueUpdateComponent;
  let fixture: ComponentFixture<MetaboliqueUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let metaboliqueFormService: MetaboliqueFormService;
  let metaboliqueService: MetaboliqueService;
  let ficheService: FicheService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MetaboliqueUpdateComponent],
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
      .overrideTemplate(MetaboliqueUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MetaboliqueUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    metaboliqueFormService = TestBed.inject(MetaboliqueFormService);
    metaboliqueService = TestBed.inject(MetaboliqueService);
    ficheService = TestBed.inject(FicheService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Fiche query and add missing value', () => {
      const metabolique: IMetabolique = { id: 456 };
      const fiche: IFiche = { id: 78408 };
      metabolique.fiche = fiche;

      const ficheCollection: IFiche[] = [{ id: 32389 }];
      jest.spyOn(ficheService, 'query').mockReturnValue(of(new HttpResponse({ body: ficheCollection })));
      const additionalFiches = [fiche];
      const expectedCollection: IFiche[] = [...additionalFiches, ...ficheCollection];
      jest.spyOn(ficheService, 'addFicheToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      expect(ficheService.query).toHaveBeenCalled();
      expect(ficheService.addFicheToCollectionIfMissing).toHaveBeenCalledWith(
        ficheCollection,
        ...additionalFiches.map(expect.objectContaining)
      );
      expect(comp.fichesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const metabolique: IMetabolique = { id: 456 };
      const fiche: IFiche = { id: 32094 };
      metabolique.fiche = fiche;

      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      expect(comp.fichesSharedCollection).toContain(fiche);
      expect(comp.metabolique).toEqual(metabolique);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueFormService, 'getMetabolique').mockReturnValue(metabolique);
      jest.spyOn(metaboliqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: metabolique }));
      saveSubject.complete();

      // THEN
      expect(metaboliqueFormService.getMetabolique).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(metaboliqueService.update).toHaveBeenCalledWith(expect.objectContaining(metabolique));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueFormService, 'getMetabolique').mockReturnValue({ id: null });
      jest.spyOn(metaboliqueService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: metabolique }));
      saveSubject.complete();

      // THEN
      expect(metaboliqueFormService.getMetabolique).toHaveBeenCalled();
      expect(metaboliqueService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMetabolique>>();
      const metabolique = { id: 123 };
      jest.spyOn(metaboliqueService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ metabolique });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(metaboliqueService.update).toHaveBeenCalled();
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
