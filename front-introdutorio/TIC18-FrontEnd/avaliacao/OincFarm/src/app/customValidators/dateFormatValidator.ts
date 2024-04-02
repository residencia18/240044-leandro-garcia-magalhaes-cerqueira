import { AbstractControl, ValidatorFn } from '@angular/forms';

export function dateFormatValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const value = control.value;
    
    // Verifica se o valor corresponde ao formato de data xx/xx/xxxx
    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(value)) {
      return { 'dateFormat': true };
    }

    // Verifica se a data é válida
    const parts = value.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10);
    const year = parseInt(parts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
      return { 'dateFormat': true };
    }

    const date = new Date(year, month - 1, day);
    if (date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day) {
      return { 'dateFormat': true };
    }

    return null;
  };
}
