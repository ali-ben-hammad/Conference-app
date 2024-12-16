import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferencesComponent } from './conferences.component';

describe('WalletsComponent', () => {
  let component: ConferencesComponent;
  let fixture: ComponentFixture<ConferencesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConferencesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConferencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
