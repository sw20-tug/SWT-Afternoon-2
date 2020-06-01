import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { UsernameValidator, PasswordValidator } from './validators/custom.validators';
import { ConfirmationDialogService } from './confirmation-dialogue/confirmation-dialogue.service';
import {TranslateService} from "@ngx-translate/core";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";
import {Hotel} from "../../hotel-list/hotel.model";
import {HotelService} from "../../../service/hotel.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  @Input() isAdmin = false;
  @Output() loginEvent = new EventEmitter<boolean>();
  showModal: boolean;
  registerForm: FormGroup;
  submitted = false;
  _isAdmin = false;
  constructor(private formBuilder: FormBuilder, private confirmationDialogService: ConfirmationDialogService,  public translate: TranslateService,
              private cookieService: CookieService, private readonly router: Router, private readonly hotelService: HotelService) {

  }


  public changeLanguage(language: string) {
    this.translate.setDefaultLang(language);
    this.translate.use(language);
    this.hotelService.translateHotels(true);
  }
  show()
  {
    this.showModal = true;

  }
  signOut() {
    this._isAdmin = false;
    this.cookieService.set('isAdmin', "false");
    this.cookieService.set('username', "");
    this.cookieService.set('password', "");
    this.login();

    this.confirmationDialogService.confirm(Hotel.translateService.instant('SIGNOUT'), Hotel.translateService.instant('SIGNOUT MSG'))
      .then((confirmed) => this.isAdmin = !confirmed)
      .catch(() => console.log('User dismissed the dialog (e.g., by using ESC, clicking the cross icon, or clicking outside the dialog)'));

  }
  hide()
  {
    this.showModal = false;
    this.registerForm.reset();
    this.resetForm(this.registerForm);
  }
  ngOnInit() {
    this._isAdmin = this.isAdmin = this.cookieService.get("isAdmin") === "true" ? true : false;
    this.registerForm = this.formBuilder.group({
      username: ['', [UsernameValidator]],
      password: ['', [PasswordValidator]]
    });
  }

  get f() { return this.registerForm.controls; }
  checkIfFormValid() {
    if(!this.f.username.errors.usernameValid || !this.f.password.errors.passwordValid ||
      this.f.password.errors.required || this.f.username.errors.required) {
      return false;
    }
    return true;
  }
  onSubmit() {
    this.submitted = true;
    console.log(this.registerForm.controls.username.value);
    if (!this.checkIfFormValid()) {
      this._isAdmin = false;
      return;
    }
    else {
      this.showModal = false;
      this._isAdmin = true;
      this.cookieService.set('username', this.registerForm.controls.username.value);
      this.cookieService.set('password', this.registerForm.controls.password.value);
      this.cookieService.set('isAdmin', "true");

      this.resetForm(this.registerForm);
      this.login();
    }
  }

  resetForm(form: FormGroup) {
    this.submitted = false;
    form.reset();

    Object.keys(form.controls).forEach(key => {
      form.get(key).setErrors(null) ;
    });
  }
  login() {
    this.isAdmin = this._isAdmin;
    this.loginEvent.emit(this.isAdmin);
  }
  logoutEvent($event){
    this.loginEvent.emit($event);
  }
}
