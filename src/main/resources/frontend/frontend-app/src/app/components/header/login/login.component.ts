import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormGroupDirective } from '@angular/forms';
import { UsernameValidator, PasswordValidator } from './validators/custom.validators';
import { ConfirmationDialogService } from './confirmation-dialogue/confirmation-dialogue.service';

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
  constructor(private formBuilder: FormBuilder, private confirmationDialogService: ConfirmationDialogService) { }
  show()
  {
    this.showModal = true;
    
  }
  signOut() {
    this._isAdmin = false;
    this.login();

    this.confirmationDialogService.confirm('Sign out', 'You have been successfully signed out!')
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