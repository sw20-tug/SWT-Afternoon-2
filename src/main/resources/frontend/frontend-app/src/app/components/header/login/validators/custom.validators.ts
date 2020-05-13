import { AbstractControl } from '@angular/forms';

export function UsernameValidator(control: AbstractControl) {
    console.log('vrijednost control.value = ' + control.value)
  if (control.value === "admin") {

    return { required : false, usernameValid: true };
  }
  else if (control.value === "") {

    return { required : true, usernameValid: false };
  }
  return { required : false, usernameValid: false };
}

export function PasswordValidator(control: AbstractControl) {
    if (control.value === "adminpass") {
      return { required : false, passwordValid: true };
    }
    else if (control.value === "") {

      return { required : true, passwordValid: false };
    }
    return { required : false, passwordValid: false };
  }