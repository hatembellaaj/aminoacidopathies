import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CassuspecteLineComponent } from './cassuspecte-line.component';

describe('CassuspecteLineComponent', () => {
  let component: CassuspecteLineComponent;
  let fixture: ComponentFixture<CassuspecteLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CassuspecteLineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CassuspecteLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
