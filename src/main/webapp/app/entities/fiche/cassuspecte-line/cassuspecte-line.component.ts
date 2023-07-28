import { HttpResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { finalize } from 'rxjs';
import { ICasconfirme } from '../../casconfirme/casconfirme.model';
import { ICassuspecte } from '../../cassuspecte/cassuspecte.model';
import { CassuspecteService } from '../../cassuspecte/service/cassuspecte.service';
import { CassuspecteFormGroup, CassuspecteFormService } from '../../cassuspecte/update/cassuspecte-form.service';
import { elienparente } from '../../enumerations/elienparente.model';

@Component({
  selector: 'jhi-cassuspecte-line',
  templateUrl: './cassuspecte-line.component.html',
  styleUrls: ['./cassuspecte-line.component.scss'],
})
export class CassuspecteLineComponent implements OnInit {
  @Input() casSuspecteLine!: ICasconfirme;
  @Input() index!: number;
  //@Output() estimateLineChanged = new EventEmitter<{lineIndex : number, estimateLine: EstimateLine}>();
  @Output() casSuspecteDeleted = new EventEmitter<number>();

  isSaving = false;
  cassuspecte: ICassuspecte | null = null;
  elienparenteValues = Object.keys(elienparente);
  myControl = new FormControl('');
  selectedCasSuspecte: number = 0;

  editForm: CassuspecteFormGroup = this.cassuspecteFormService.createCassuspecteFormGroup();

  constructor(
    protected cassuspecteService: CassuspecteService,
    protected cassuspecteFormService: CassuspecteFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    /*  this.activatedRoute.data.subscribe(({ cassuspecte }) => {
      this.cassuspecte = cassuspecte;
      if (cassuspecte) {
        this.updateForm(cassuspecte);
      }
    });*/
  }

  deletCasConfirmeLine(): void {
    alert(this.selectedCasSuspecte);
    this.casSuspecteDeleted.emit(this.index);
  }
}
