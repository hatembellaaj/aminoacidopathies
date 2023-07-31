import { Component, OnInit } from '@angular/core';
import { IStructurefiche, NewStructurefiche } from '../../structurefiche/structurefiche.model';
import { CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { IFiche } from '../fiche.model';
import { FicheService } from '../service/fiche.service';
import { elien_parente } from '../../enumerations/elien-parente.model';
import { StructureficheService } from '../../structurefiche/service/structurefiche.service';
import { StructureficheFormGroup, StructureficheFormService } from '../../structurefiche/update/structurefiche-form.service';

import { IEtablissement } from '../../etablissement/etablissement.model';
import { EtablissementService } from '../../etablissement/service/etablissement.service';
import { IServicesante } from '../../servicesante/servicesante.model';
import { ServicesanteService } from '../../servicesante/service/servicesante.service';
import { IMedecin } from '../../medecin/medecin.model';
import { MedecinService } from '../../medecin/service/medecin.service';
import { etypestructure } from '../../enumerations/etypestructure.model';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
@Component({
  selector: 'jhi-structurefiche-line',
  templateUrl: './structurefiche-line.component.html',
  styleUrls: ['./structurefiche-line.component.scss'],
})
export class StructureficheLineComponent implements OnInit {
  @Input() structureficheLine!: IStructurefiche;
  @Input() index!: number;
  @Output() structureficheLineDeleted = new EventEmitter<number>();

  elien_parenteValues = Object.keys(elien_parente);
  myControl = new FormControl('');
  selectedCasConfirme: number = 0;
  isSaving = false;
  structurefiche: IStructurefiche | null = null;

  etypestructureValues = Object.keys(etypestructure);
  etablissementsSharedCollection: IEtablissement[] = [];
  servicesantesSharedCollection: IServicesante[] = [];
  medecinsSharedCollection: IMedecin[] = [];

  editForm: StructureficheFormGroup = this.structureficheFormService.createStructureficheFormGroup();

  constructor(
    protected structureficheService: StructureficheService,
    protected structureficheFormService: StructureficheFormService,
    protected etablissementService: EtablissementService,
    protected servicesanteService: ServicesanteService,
    protected medecinService: MedecinService,
    protected activatedRoute: ActivatedRoute,

    protected ficheService: FicheService
  ) {
    console.log('I am in casconfirme-line constructor ');
  }

  compareEtablissement = (o1: IEtablissement | null, o2: IEtablissement | null): boolean =>
    this.etablissementService.compareEtablissement(o1, o2);

  compareServicesante = (o1: IServicesante | null, o2: IServicesante | null): boolean =>
    this.servicesanteService.compareServicesante(o1, o2);

  compareMedecin = (o1: IMedecin | null, o2: IMedecin | null): boolean => this.medecinService.compareMedecin(o1, o2);

  compareFiche = (o1: IFiche | null, o2: IFiche | null): boolean => this.ficheService.compareFiche(o1, o2);

  ngOnInit(): void {
    console.log('I am in casconfirme-line ');

    this.activatedRoute.data.subscribe(({ structurefiche }) => {
      this.structurefiche = structurefiche;
      if (structurefiche) {
        this.updateForm(structurefiche);
      }

      this.loadRelationshipsOptions();
    });
  }

  deleteStructureficheLine(): void {
    //alert(this.structureficheLineDeleted);
    this.structureficheLineDeleted.emit(this.index);
  }

  protected updateForm(structurefiche: IStructurefiche): void {
    this.structurefiche = structurefiche;
    this.structureficheFormService.resetForm(this.editForm, structurefiche);

    this.etablissementsSharedCollection = this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(
      this.etablissementsSharedCollection,
      structurefiche.etablissement
    );
    this.servicesantesSharedCollection = this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(
      this.servicesantesSharedCollection,
      structurefiche.servicesante
    );
    this.medecinsSharedCollection = this.medecinService.addMedecinToCollectionIfMissing<IMedecin>(
      this.medecinsSharedCollection,
      structurefiche.medecin
    );
  }

  protected loadRelationshipsOptions(): void {
    this.etablissementService
      .query()
      .pipe(map((res: HttpResponse<IEtablissement[]>) => res.body ?? []))
      .pipe(
        map((etablissements: IEtablissement[]) =>
          this.etablissementService.addEtablissementToCollectionIfMissing<IEtablissement>(
            etablissements,
            this.structurefiche?.etablissement
          )
        )
      )
      .subscribe((etablissements: IEtablissement[]) => (this.etablissementsSharedCollection = etablissements));

    this.servicesanteService
      .query()
      .pipe(map((res: HttpResponse<IServicesante[]>) => res.body ?? []))
      .pipe(
        map((servicesantes: IServicesante[]) =>
          this.servicesanteService.addServicesanteToCollectionIfMissing<IServicesante>(servicesantes, this.structurefiche?.servicesante)
        )
      )
      .subscribe((servicesantes: IServicesante[]) => (this.servicesantesSharedCollection = servicesantes));

    this.medecinService
      .query()
      .pipe(map((res: HttpResponse<IMedecin[]>) => res.body ?? []))
      .pipe(
        map((medecins: IMedecin[]) => this.medecinService.addMedecinToCollectionIfMissing<IMedecin>(medecins, this.structurefiche?.medecin))
      )
      .subscribe((medecins: IMedecin[]) => (this.medecinsSharedCollection = medecins));
  }
}
