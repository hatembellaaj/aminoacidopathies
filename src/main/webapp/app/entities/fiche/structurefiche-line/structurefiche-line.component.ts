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
  }

  deleteStructureficheLine(): void {
    alert(this.structureficheLineDeleted);
    this.structureficheLineDeleted.emit(this.index);
  }
}
