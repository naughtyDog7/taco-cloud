import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CloudTitleComponent } from './cloud-title.component';

describe('CloudTitleComponent', () => {
  let component: CloudTitleComponent;
  let fixture: ComponentFixture<CloudTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CloudTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CloudTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
