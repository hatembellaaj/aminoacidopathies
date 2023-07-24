import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasconfirmeLineComponent } from './casconfirme-line.component';

describe('CasconfirmeLineComponent', () => {
  let component: CasconfirmeLineComponent;
  let fixture: ComponentFixture<CasconfirmeLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CasconfirmeLineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CasconfirmeLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
