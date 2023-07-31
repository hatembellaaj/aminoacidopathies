import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { FicheFormService, FicheFormGroup } from './fiche-form.service';
import { IFiche } from '../fiche.model';
import { FicheService } from '../service/fiche.service';
import { IPathologie } from 'app/entities/pathologie/pathologie.model';
import { PathologieService } from 'app/entities/pathologie/service/pathologie.service';
import { esexe } from 'app/entities/enumerations/esexe.model';
import { estatut } from 'app/entities/enumerations/estatut.model';
import { ecirconstance } from 'app/entities/enumerations/ecirconstance.model';
import { elieudeces } from 'app/entities/enumerations/elieudeces.model';
import { econsanguinite } from 'app/entities/enumerations/econsanguinite.model';
import { egouvernorat } from 'app/entities/enumerations/egouvernorat.model';
import { egouvernoratmere } from 'app/entities/enumerations/egouvernoratmere.model';
import { ecouverture } from 'app/entities/enumerations/ecouverture.model';
import { eactivite } from 'app/entities/enumerations/eactivite.model';
import { escolarisetype } from 'app/entities/enumerations/escolarisetype.model';
import { eniveauscolarisation } from 'app/entities/enumerations/eniveauscolarisation.model';
import { ecasfamiliaux } from 'app/entities/enumerations/ecasfamiliaux.model';
import { edecesbasage } from 'app/entities/enumerations/edecesbasage.model';
import { ecircondecouverte } from 'app/entities/enumerations/ecircondecouverte.model';
import { eage_premier_symptome } from 'app/entities/enumerations/eage-premier-symptome.model';
import { eagepremiereconsultation } from 'app/entities/enumerations/eagepremiereconsultation.model';
import { ehandicapmental } from 'app/entities/enumerations/ehandicapmental.model';
import { eQI } from 'app/entities/enumerations/e-qi.model';
import { eMoteur } from 'app/entities/enumerations/e-moteur.model';
import { egrade } from 'app/entities/enumerations/egrade.model';
import { edeficitneuro } from 'app/entities/enumerations/edeficitneuro.model';
import { edeficitneurosensorielval } from 'app/entities/enumerations/edeficitneurosensorielval.model';
import { edeficiencepsychique } from 'app/entities/enumerations/edeficiencepsychique.model';
import { edeficiencepsychiqueval } from 'app/entities/enumerations/edeficiencepsychiqueval.model';
import { eregime } from 'app/entities/enumerations/eregime.model';
import { eregimeval } from 'app/entities/enumerations/eregimeval.model';
import { emedicamentspecifique } from 'app/entities/enumerations/emedicamentspecifique.model';
import { emedicamentspecifiqueval } from 'app/entities/enumerations/emedicamentspecifiqueval.model';
import { evitamines } from 'app/entities/enumerations/evitamines.model';
import { evitamineval } from 'app/entities/enumerations/evitamineval.model';
import { egreffehepatique } from 'app/entities/enumerations/egreffehepatique.model';
import { erededucationfonctionnelle } from 'app/entities/enumerations/erededucationfonctionnelle.model';
import { eappareillage } from 'app/entities/enumerations/eappareillage.model';
import { epsychologie } from 'app/entities/enumerations/epsychologie.model';
import { eergotherapie } from 'app/entities/enumerations/eergotherapie.model';
import { edepistage_post_natal_oriente } from 'app/entities/enumerations/edepistage-post-natal-oriente.model';
import { eechelledepistage } from 'app/entities/enumerations/eechelledepistage.model';
import { enouveaux_cas_depistes } from 'app/entities/enumerations/enouveaux-cas-depistes.model';
import { elienparente1 } from 'app/entities/enumerations/elienparente-1.model';
import { elienparente2 } from 'app/entities/enumerations/elienparente-2.model';
import { ICasconfirme } from 'app/entities/casconfirme/casconfirme.model';
import { ICassuspecte } from 'app/entities/cassuspecte/cassuspecte.model';
import { IStructurefiche } from 'app/entities/structurefiche/structurefiche.model';
import { etypestructure } from 'app/entities/enumerations/etypestructure.model';
import { elienparente } from 'app/entities/enumerations/elienparente.model';
import { elien_parente } from 'app/entities/enumerations/elien-parente.model';
import { IMetabolique } from 'app/entities/metabolique/metabolique.model';
import { efait } from 'app/entities/enumerations/efait.model';
import { elaboratoire } from 'app/entities/enumerations/elaboratoire.model';
import { eResultat } from 'app/entities/enumerations/e-resultat.model';
import { ename } from 'app/entities/enumerations/ename.model';

@Component({
  selector: 'jhi-fiche-update',
  templateUrl: './fiche-update.component.html',
})
export class FicheUpdateComponent implements OnInit {
  isSaving = false;
  fiche: IFiche | null = null;
  esexeValues = Object.keys(esexe);
  estatutValues = Object.keys(estatut);
  ecirconstanceValues = Object.keys(ecirconstance);
  elieudecesValues = Object.keys(elieudeces);
  econsanguiniteValues = Object.keys(econsanguinite);
  egouvernoratValues = Object.keys(egouvernorat);
  egouvernoratmereValues = Object.keys(egouvernoratmere);
  ecouvertureValues = Object.keys(ecouverture);
  eactiviteValues = Object.keys(eactivite);
  escolarisetypeValues = Object.keys(escolarisetype);
  eniveauscolarisationValues = Object.keys(eniveauscolarisation);
  ecasfamiliauxValues = Object.keys(ecasfamiliaux);
  edecesbasageValues = Object.keys(edecesbasage);
  ecircondecouverteValues = Object.keys(ecircondecouverte);
  eage_premier_symptomeValues = Object.keys(eage_premier_symptome);
  eagepremiereconsultationValues = Object.keys(eagepremiereconsultation);
  ehandicapmentalValues = Object.keys(ehandicapmental);
  eQIValues = Object.keys(eQI);
  eMoteurValues = Object.keys(eMoteur);
  egradeValues = Object.keys(egrade);
  edeficitneuroValues = Object.keys(edeficitneuro);
  edeficitneurosensorielvalValues = Object.keys(edeficitneurosensorielval);
  edeficiencepsychiqueValues = Object.keys(edeficiencepsychique);
  edeficiencepsychiquevalValues = Object.keys(edeficiencepsychiqueval);
  eregimeValues = Object.keys(eregime);
  eregimevalValues = Object.keys(eregimeval);
  emedicamentspecifiqueValues = Object.keys(emedicamentspecifique);
  emedicamentspecifiquevalValues = Object.keys(emedicamentspecifiqueval);
  evitaminesValues = Object.keys(evitamines);
  evitaminevalValues = Object.keys(evitamineval);
  egreffehepatiqueValues = Object.keys(egreffehepatique);
  erededucationfonctionnelleValues = Object.keys(erededucationfonctionnelle);
  eappareillageValues = Object.keys(eappareillage);
  epsychologieValues = Object.keys(epsychologie);
  eergotherapieValues = Object.keys(eergotherapie);
  edepistage_post_natal_orienteValues = Object.keys(edepistage_post_natal_oriente);
  eechelledepistageValues = Object.keys(eechelledepistage);
  enouveaux_cas_depistesValues = Object.keys(enouveaux_cas_depistes);
  elienparente1Values = Object.keys(elienparente1);
  elienparente2Values = Object.keys(elienparente2);

  pathologiesSharedCollection: IPathologie[] = [];

  editForm: FicheFormGroup = this.ficheFormService.createFicheFormGroup();

  casConfirmeLines: ICasconfirme[] = [];
  casSuspectesLines: ICassuspecte[] = [];
  structureFicheLines: IStructurefiche[] = [];
  metaboliqueLines: IMetabolique[] = [];

  constructor(
    protected ficheService: FicheService,
    protected ficheFormService: FicheFormService,
    protected pathologieService: PathologieService,
    protected activatedRoute: ActivatedRoute
  ) {}

  comparePathologie = (o1: IPathologie | null, o2: IPathologie | null): boolean => this.pathologieService.comparePathologie(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fiche }) => {
      this.fiche = fiche;
      if (fiche) {
        this.updateForm(fiche);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fiche = this.ficheFormService.getFiche(this.editForm);
    if (fiche.id !== null) {
      this.subscribeToSaveResponse(this.ficheService.update(fiche));
    } else {
      this.subscribeToSaveResponse(this.ficheService.create(fiche));
    }
  }

  addMetaboliqueLine(): void {
    console.log('Hello this is addMetaboliqueLine');
    // Create a new instance of the metabolique line
    const newLine: IMetabolique = {
      id: 0,
      name: ename.BRANDT,
      fait: efait.NP,
      laboratoire: elaboratoire.LABORATOIREPRIVE,
      resultat: eResultat.EN_COURS,
      fiche: this.fiche,
    };

    // Add the new estimate line to the array
    this.metaboliqueLines.push(newLine);
  }

  handleMetaboliqueLineDeleted(index: any): void {
    this.metaboliqueLines.splice(index, 1);
  }

  addCasConfirmeLine(): void {
    console.log('Hello this is addCasConfirmeLine');
    // Create a new instance of the estimate line
    const newLine: ICasconfirme = {
      id: 0,
      code_registre: '',
      lien_parente: elien_parente.NP,
      fiche: this.fiche,
    };

    // Add the new estimate line to the array
    this.casConfirmeLines.push(newLine);
  }

  handleCasConfirmeLineDeleted(index: any): void {
    this.casConfirmeLines.splice(index, 1);
  }

  addCasSuspecteLine(): void {
    console.log('Hello this is addCasSuspecteLine');
    // Create a new instance of the estimate line
    const newLine: ICassuspecte = {
      id: 0,
      lienparente: elienparente.NP,
      lienparenteautre: '',
      signes_neurologiques: false,
      troubles_de_la_conscience: false,
      retard_psychomoteur: false,
      retard_mental: false,
      signes_du_spectre_autistique: false,
      epilepsie: false,
      crise_pseudoporphyrique: false,
      autres_signes_neurologiques: '',
      signes_hepatiques: false,
      ictere: false,
      ballonnement: false,
      syndrome_hemorragique: false,
      autres_signes_hepatiques: '',
      signes_osseux: false,
      signes_de_rachitisme: false,
      autre_signes_osseux: '',
      manifestations_thrombotiques: false,
      cerebrale: false,
      autre_manifestations_thrombotiques: '',
      manifestations_ophtalmologiques: false,
      luxation: false,
      ectopie_cristalinienne: false,
      cataracte: false,
      glaucome: false,
      myopie: false,
      manifestations_ophtalmologiques_autre: '',
      autre_criteres: false,
      str_autres_criteres: '',
      critere_non_precise: false,
    };

    // Add the new estimate line to the array
    this.casSuspectesLines.push(newLine);
  }

  handleCasSuspecteLineDeleted(index: any): void {
    this.casSuspectesLines.splice(index, 1);
  }

  addStructureFicheLine(): void {
    console.log('Hello this is addStructureFicheLine');
    // Create a new instance of the estimate line
    const newLine: IStructurefiche = {
      id: 0,
      typestructure: etypestructure.ORIGINE,
      ordre: 0,
      etablissement: { id: 0 },
      servicesante: { id: 0 },
      medecin: { id: 0 },
      fiche: this.fiche,
    };

    // Add the new estimate line to the array
    this.structureFicheLines.push(newLine);
  }

  handleStructureficheLineDeleted(index: any): void {
    this.structureFicheLines.splice(index, 1);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFiche>>): void {
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

  protected updateForm(fiche: IFiche): void {
    this.fiche = fiche;
    this.ficheFormService.resetForm(this.editForm, fiche);

    this.pathologiesSharedCollection = this.pathologieService.addPathologieToCollectionIfMissing<IPathologie>(
      this.pathologiesSharedCollection,
      fiche.pathologie
    );
  }

  protected loadRelationshipsOptions(): void {
    this.pathologieService
      .query()
      .pipe(map((res: HttpResponse<IPathologie[]>) => res.body ?? []))
      .pipe(
        map((pathologies: IPathologie[]) =>
          this.pathologieService.addPathologieToCollectionIfMissing<IPathologie>(pathologies, this.fiche?.pathologie)
        )
      )
      .subscribe((pathologies: IPathologie[]) => (this.pathologiesSharedCollection = pathologies));
  }
}
