import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KeynotesComponent } from './keynotes.component';

describe('KeynotesComponent', () => {
  let component: KeynotesComponent;
  let fixture: ComponentFixture<KeynotesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KeynotesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KeynotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
