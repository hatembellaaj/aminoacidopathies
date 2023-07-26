import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StructureficheLineComponent } from './structurefiche-line.component';

describe('StructureficheLineComponent', () => {
  let component: StructureficheLineComponent;
  let fixture: ComponentFixture<StructureficheLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StructureficheLineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(StructureficheLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
