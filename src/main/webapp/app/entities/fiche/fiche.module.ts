import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FicheComponent } from './list/fiche.component';
import { FicheDetailComponent } from './detail/fiche-detail.component';
import { FicheUpdateComponent } from './update/fiche-update.component';
import { FicheDeleteDialogComponent } from './delete/fiche-delete-dialog.component';
import { FicheRoutingModule } from './route/fiche-routing.module';
import { CasconfirmeLineComponent } from './casconfirme-line/casconfirme-line.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
@NgModule({
  imports: [FormsModule, SharedModule, FicheRoutingModule],
  declarations: [FicheComponent, FicheDetailComponent, FicheUpdateComponent, FicheDeleteDialogComponent, CasconfirmeLineComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class FicheModule {}
