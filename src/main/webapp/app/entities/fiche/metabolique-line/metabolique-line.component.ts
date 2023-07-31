import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { eResultat } from 'app/entities/enumerations/e-resultat.model';
import { efait } from 'app/entities/enumerations/efait.model';
import { elaboratoire } from 'app/entities/enumerations/elaboratoire.model';
import { ename } from 'app/entities/enumerations/ename.model';
import { IMetabolique } from 'app/entities/metabolique/metabolique.model';
import { MetaboliqueService } from 'app/entities/metabolique/service/metabolique.service';
import { MetaboliqueFormGroup, MetaboliqueFormService } from 'app/entities/metabolique/update/metabolique-form.service';
import { FicheService } from '../service/fiche.service';

@Component({
  selector: 'jhi-metabolique-line',
  templateUrl: './metabolique-line.component.html',
  styleUrls: ['./metabolique-line.component.scss'],
})
export class MetaboliqueLineComponent implements OnInit {
  @Input() metaboliqueLine!: IMetabolique;
  @Input() index!: number;
  @Output() metaboliqueDeleted = new EventEmitter<number>();

  enameValues = Object.keys(ename);
  efaitValues = Object.keys(efait);
  elaboratoireValues = Object.keys(elaboratoire);
  eResultatValues = Object.keys(eResultat);

  isSaving = false;
  metabolique: IMetabolique | null = null;
  myControl = new FormControl('');
  selectedMetabolique: number = 0;

  editForm: MetaboliqueFormGroup = this.metaboliqueFormService.createMetaboliqueFormGroup();

  constructor(
    protected metaboliqueService: MetaboliqueService,
    protected metaboliqueFormService: MetaboliqueFormService,
    protected ficheService: FicheService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    /*      this.activatedRoute.data.subscribe(({ metabolique }) => {
      this.metabolique = metabolique;
      if (metabolique) {
        this.updateForm(metabolique);
      }

      this.loadRelationshipsOptions();
    });*/
  }

  deletMetaboliqueLine(): void {
    alert(this.selectedMetabolique);
    this.metaboliqueDeleted.emit(this.index);
  }
}
