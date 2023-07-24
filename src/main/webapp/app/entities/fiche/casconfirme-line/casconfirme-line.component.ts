import { Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ICasconfirme, NewCasconfirme } from '../../casconfirme/casconfirme.model';
import { CasconfirmeService } from '../../casconfirme/service/casconfirme.service';
import { CasconfirmeFormService } from '../../casconfirme/update/casconfirme-form.service';
import { IFiche } from '../fiche.model';
import { FicheService } from '../service/fiche.service';
import { elien_parente } from '../../enumerations/elien-parente.model';

type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

type CasconfirmeFormGroupInput = ICasconfirme | PartialWithRequiredKeyOf<NewCasconfirme>;

type CasconfirmeFormGroupContent = {
  id: FormControl<ICasconfirme['id'] | NewCasconfirme['id']>;
  code_registre: FormControl<ICasconfirme['code_registre']>;
  lien_parente: FormControl<ICasconfirme['lien_parente']>;
  fiche: FormControl<ICasconfirme['fiche']>;
};

export type CasconfirmeFormGroup = FormGroup<CasconfirmeFormGroupContent>;

@Component({
  selector: 'jhi-casconfirme-line',
  templateUrl: './casconfirme-line.component.html',
  styleUrls: ['./casconfirme-line.component.scss'],
})
export class CasconfirmeLineComponent implements OnInit {
  @Input() casConfirmeLine!: ICasconfirme;
  @Input() index!: number;
  //@Output() estimateLineChanged = new EventEmitter<{lineIndex : number, estimateLine: EstimateLine}>();
  @Output() casConfirmeDeleted = new EventEmitter<number>();

  elien_parenteValues = Object.keys(elien_parente);
  myControl = new FormControl('');
  selectedCasConfirme: number = 0;
  isSaving = false;
  casconfirme: ICasconfirme | null = null;

  editForm: CasconfirmeFormGroup = this.casconfirmeFormService.createCasconfirmeFormGroup();

  constructor(
    protected casconfirmeService: CasconfirmeService,
    protected casconfirmeFormService: CasconfirmeFormService,
    protected ficheService: FicheService
  ) {
    console.log('I am in casconfirme-line constructor ');
  }

  compareFiche = (o1: IFiche | null, o2: IFiche | null): boolean => this.ficheService.compareFiche(o1, o2);

  ngOnInit(): void {
    console.log('I am in casconfirme-line ');
  }

  deletCasConfirmeLine(): void {
    alert(this.selectedCasConfirme);
    this.casConfirmeDeleted.emit(this.index);
  }
}
