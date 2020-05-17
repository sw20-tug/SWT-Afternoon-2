import {Injectable, Injector} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InjectorService {
  public static injector: Injector;

  constructor() { }
}
