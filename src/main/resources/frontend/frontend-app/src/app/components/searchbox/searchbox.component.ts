import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-searchbox',
  templateUrl: './searchbox.component.html',
  styleUrls: ['./searchbox.component.css']
})
export class SearchboxComponent implements OnInit {
  @Input() public searchText: string;
  @Output() searchBoxEvent = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {
  }

  search() {
    this.searchBoxEvent.emit(this.searchText.toLowerCase());
  }
}
