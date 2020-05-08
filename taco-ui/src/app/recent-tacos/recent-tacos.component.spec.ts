import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentTacosComponent } from './recent-tacos.component';

describe('RecentTacosComponent', () => {
  let component: RecentTacosComponent;
  let fixture: ComponentFixture<RecentTacosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecentTacosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecentTacosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
