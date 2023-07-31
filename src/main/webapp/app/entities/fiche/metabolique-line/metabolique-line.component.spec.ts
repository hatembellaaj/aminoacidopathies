import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MetaboliqueLineComponent } from './metabolique-line.component';

describe('MetaboliqueLineComponent', () => {
  let component: MetaboliqueLineComponent;
  let fixture: ComponentFixture<MetaboliqueLineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MetaboliqueLineComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MetaboliqueLineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
