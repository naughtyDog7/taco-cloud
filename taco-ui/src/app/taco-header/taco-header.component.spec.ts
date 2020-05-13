import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TacoHeaderComponent } from './taco-header.component';

describe('TacoHeaderComponent', () => {
  let component: TacoHeaderComponent;
  let fixture: ComponentFixture<TacoHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TacoHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TacoHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
