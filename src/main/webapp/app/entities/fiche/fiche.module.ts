import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FicheComponent } from './list/fiche.component';
import { FicheDetailComponent } from './detail/fiche-detail.component';
import { FicheUpdateComponent } from './update/fiche-update.component';
import { FicheDeleteDialogComponent } from './delete/fiche-delete-dialog.component';
import { FicheRoutingModule } from './route/fiche-routing.module';
import { CasconfirmeLineComponent } from './casconfirme-line/casconfirme-line.component';
import { CassuspecteLineComponent } from './cassuspecte-line/cassuspecte-line.component';
import { StructureficheLineComponent } from './structurefiche-line/structurefiche-line.component';

@NgModule({
  imports: [SharedModule, FicheRoutingModule],
  declarations: [
    FicheComponent,
    FicheDetailComponent,
    FicheUpdateComponent,
    FicheDeleteDialogComponent,
    StructureficheLineComponent,
    CassuspecteLineComponent,
    CasconfirmeLineComponent,
  ],
})
export class FicheModule {}
