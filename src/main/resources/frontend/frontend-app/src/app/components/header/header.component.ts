import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() searchHeaderEvent = new EventEmitter<string>();
  @Output() loginHeaderEvent = new EventEmitter<boolean>();
  @Input() public searchText: string = '';

  @Input() public isAdmin: boolean = this.cookieService.get("isAdmin") === "true" ? true : false;

  @Input() public isSearchboxVisible: boolean = true;

  constructor(private cookieService: CookieService) { }

  ngOnInit(): void {
  }

  ngOnChanges(x) {
    
  }

  searchBoxEvent($event){
    this.searchHeaderEvent.emit($event);
  }
  loginEvent($event){
    this.loginHeaderEvent.emit($event);
  }
}
