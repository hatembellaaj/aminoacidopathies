import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MetaboliqueFormService, MetaboliqueFormGroup } from './metabolique-form.service';
import { IMetabolique } from '../metabolique.model';
import { MetaboliqueService } from '../service/metabolique.service';
import { IFiche } from 'app/entities/fiche/fiche.model';
import { FicheService } from 'app/entities/fiche/service/fiche.service';
import { ename } from 'app/entities/enumerations/ename.model';
import { efait } from 'app/entities/enumerations/efait.model';
import { elaboratoire } from 'app/entities/enumerations/elaboratoire.model';
import { eResultat } from 'app/entities/enumerations/e-resultat.model';

@Component({
  selector: 'jhi-metabolique-update',
  templateUrl: './metabolique-update.component.html',
})
export class MetaboliqueUpdateComponent implements OnInit {
  isSaving = false;
  metabolique: IMetabolique | null = null;
  enameValues = Object.keys(ename);
  efaitValues = Object.keys(efait);
  elaboratoireValues = Object.keys(elaboratoire);
  eResultatValues = Object.keys(eResultat);

  fichesSharedCollection: IFiche[] = [];

  editForm: MetaboliqueFormGroup = this.metaboliqueFormService.createMetaboliqueFormGroup();

  constructor(
    protected metaboliqueService: MetaboliqueService,
    protected metaboliqueFormService: MetaboliqueFormService,
    protected ficheService: FicheService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareFiche = (o1: IFiche | null, o2: IFiche | null): boolean => this.ficheService.compareFiche(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ metabolique }) => {
      this.metabolique = metabolique;
      if (metabolique) {
        this.updateForm(metabolique);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const metabolique = this.metaboliqueFormService.getMetabolique(this.editForm);
    if (metabolique.id !== null) {
      this.subscribeToSaveResponse(this.metaboliqueService.update(metabolique));
    } else {
      this.subscribeToSaveResponse(this.metaboliqueService.create(metabolique));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMetabolique>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(metabolique: IMetabolique): void {
    this.metabolique = metabolique;
    this.metaboliqueFormService.resetForm(this.editForm, metabolique);

    this.fichesSharedCollection = this.ficheService.addFicheToCollectionIfMissing<IFiche>(this.fichesSharedCollection, metabolique.fiche);
  }

  protected loadRelationshipsOptions(): void {
    this.ficheService
      .query()
      .pipe(map((res: HttpResponse<IFiche[]>) => res.body ?? []))
      .pipe(map((fiches: IFiche[]) => this.ficheService.addFicheToCollectionIfMissing<IFiche>(fiches, this.metabolique?.fiche)))
      .subscribe((fiches: IFiche[]) => (this.fichesSharedCollection = fiches));
  }
}
