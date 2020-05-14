import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() searchHeaderEvent = new EventEmitter<string>();
  @Output() loginHeaderEvent = new EventEmitter<boolean>();
  @Input() public searchText: string = '';

  @Input() public isAdmin: boolean = false;

  @Input() public isSearchboxVisible: boolean = true;

  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(x) {
    console.log(x)
  }

  searchBoxEvent($event){
    this.searchHeaderEvent.emit($event);
  }
  loginEvent($event){
    this.loginHeaderEvent.emit($event);
  }
}
