import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MySurveysComponent } from './my-surveys';

describe('MySurveys', () => {
  let component: MySurveysComponent;
  let fixture: ComponentFixture<MySurveysComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MySurveysComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MySurveysComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
