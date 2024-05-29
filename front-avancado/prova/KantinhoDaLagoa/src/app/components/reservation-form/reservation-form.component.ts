import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { Reservation } from '../../interfaces/reservation';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reservation-form',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './reservation-form.component.html',
  styleUrl: './reservation-form.component.css'
})
export class ReservationFormComponent {

  reservationForm!: FormGroup;
  reservation!: Reservation;

  constructor(private formBuilder: FormBuilder){}

  ngOnInit(): void {

    this.reservationForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required]],
      entryDate: ['', [Validators.required]],
      quitDate: ['', [Validators.required]]
    
    });
    
  }

  onSubmit() {
    if (this.reservationForm.valid) {

        const reservation = new Reservation(
        this.reservationForm.get('name')?.value,
        this.reservationForm.get('email')?.value,
        this.reservationForm.get('phoneNumber')?.value,
        this.reservationForm.get('entryDate')?.value,
        this.reservationForm.get('quitDate')?.value,
        
      );

      this.reservationForm.reset();
 
    }
  }
  

}
