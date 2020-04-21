import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() searchHeaderEvent = new EventEmitter<string>();
  @Input() public searchText: string = '';
  constructor() { }

  ngOnInit(): void {
  }

  searchBoxEvent($event){
    this.searchHeaderEvent.emit($event);
  }
}
