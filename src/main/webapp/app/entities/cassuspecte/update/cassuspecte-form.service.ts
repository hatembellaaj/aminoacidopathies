import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICassuspecte, NewCassuspecte } from '../cassuspecte.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICassuspecte for edit and NewCassuspecteFormGroupInput for create.
 */
type CassuspecteFormGroupInput = ICassuspecte | PartialWithRequiredKeyOf<NewCassuspecte>;

type CassuspecteFormDefaults = Pick<
  NewCassuspecte,
  | 'id'
  | 'signes_neurologiques'
  | 'troubles_de_la_conscience'
  | 'retard_psychomoteur'
  | 'retard_mental'
  | 'signes_du_spectre_autistique'
  | 'epilepsie'
  | 'crise_pseudoporphyrique'
  | 'signes_hepatiques'
  | 'ictere'
  | 'ballonnement'
  | 'syndrome_hemorragique'
>;

type CassuspecteFormGroupContent = {
  id: FormControl<ICassuspecte['id'] | NewCassuspecte['id']>;
  lienparente: FormControl<ICassuspecte['lienparente']>;
  lienparenteautre: FormControl<ICassuspecte['lienparenteautre']>;
  signes_neurologiques: FormControl<ICassuspecte['signes_neurologiques']>;
  troubles_de_la_conscience: FormControl<ICassuspecte['troubles_de_la_conscience']>;
  retard_psychomoteur: FormControl<ICassuspecte['retard_psychomoteur']>;
  retard_mental: FormControl<ICassuspecte['retard_mental']>;
  signes_du_spectre_autistique: FormControl<ICassuspecte['signes_du_spectre_autistique']>;
  epilepsie: FormControl<ICassuspecte['epilepsie']>;
  crise_pseudoporphyrique: FormControl<ICassuspecte['crise_pseudoporphyrique']>;
  autres_signes_neurologiques: FormControl<ICassuspecte['autres_signes_neurologiques']>;
  signes_hepatiques: FormControl<ICassuspecte['signes_hepatiques']>;
  ictere: FormControl<ICassuspecte['ictere']>;
  ballonnement: FormControl<ICassuspecte['ballonnement']>;
  syndrome_hemorragique: FormControl<ICassuspecte['syndrome_hemorragique']>;
  autres_signes_hepatiques: FormControl<ICassuspecte['autres_signes_hepatiques']>;
};

export type CassuspecteFormGroup = FormGroup<CassuspecteFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CassuspecteFormService {
  createCassuspecteFormGroup(cassuspecte: CassuspecteFormGroupInput = { id: null }): CassuspecteFormGroup {
    const cassuspecteRawValue = {
      ...this.getFormDefaults(),
      ...cassuspecte,
    };
    return new FormGroup<CassuspecteFormGroupContent>({
      id: new FormControl(
        { value: cassuspecteRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      lienparente: new FormControl(cassuspecteRawValue.lienparente),
      lienparenteautre: new FormControl(cassuspecteRawValue.lienparenteautre),
      signes_neurologiques: new FormControl(cassuspecteRawValue.signes_neurologiques),
      troubles_de_la_conscience: new FormControl(cassuspecteRawValue.troubles_de_la_conscience),
      retard_psychomoteur: new FormControl(cassuspecteRawValue.retard_psychomoteur),
      retard_mental: new FormControl(cassuspecteRawValue.retard_mental),
      signes_du_spectre_autistique: new FormControl(cassuspecteRawValue.signes_du_spectre_autistique),
      epilepsie: new FormControl(cassuspecteRawValue.epilepsie),
      crise_pseudoporphyrique: new FormControl(cassuspecteRawValue.crise_pseudoporphyrique),
      autres_signes_neurologiques: new FormControl(cassuspecteRawValue.autres_signes_neurologiques),
      signes_hepatiques: new FormControl(cassuspecteRawValue.signes_hepatiques),
      ictere: new FormControl(cassuspecteRawValue.ictere),
      ballonnement: new FormControl(cassuspecteRawValue.ballonnement),
      syndrome_hemorragique: new FormControl(cassuspecteRawValue.syndrome_hemorragique),
      autres_signes_hepatiques: new FormControl(cassuspecteRawValue.autres_signes_hepatiques),
    });
  }

  getCassuspecte(form: CassuspecteFormGroup): ICassuspecte | NewCassuspecte {
    return form.getRawValue() as ICassuspecte | NewCassuspecte;
  }

  resetForm(form: CassuspecteFormGroup, cassuspecte: CassuspecteFormGroupInput): void {
    const cassuspecteRawValue = { ...this.getFormDefaults(), ...cassuspecte };
    form.reset(
      {
        ...cassuspecteRawValue,
        id: { value: cassuspecteRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CassuspecteFormDefaults {
    return {
      id: null,
      signes_neurologiques: false,
      troubles_de_la_conscience: false,
      retard_psychomoteur: false,
      retard_mental: false,
      signes_du_spectre_autistique: false,
      epilepsie: false,
      crise_pseudoporphyrique: false,
      signes_hepatiques: false,
      ictere: false,
      ballonnement: false,
      syndrome_hemorragique: false,
    };
  }
}