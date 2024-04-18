import { AbstractControl, ValidatorFn } from '@angular/forms';

export function onlyNumbersValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const valid = /^\d+$/.test(control.value);
    return valid ? null : { 'onlyNumbers': true };
  };
}
