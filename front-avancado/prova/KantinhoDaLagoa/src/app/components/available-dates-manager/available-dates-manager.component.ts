import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AvailableDatesService } from '../../service/available-dates.service';
import { AvailableDate } from '../../interfaces/available-date';

@Component({
  selector: 'app-available-dates-manager',
  standalone: true,
  imports: [],
  templateUrl: './available-dates-manager.component.html',
  styleUrl: './available-dates-manager.component.css'
})
export class AvailableDatesManagerComponent {

  dateForm!: FormGroup;

  constructor(private fb: FormBuilder, private availableDatesService: AvailableDatesService) {
    this.dateForm = this.fb.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.dateForm.valid) {
      const availableDate: AvailableDate = {
        startDate: new Date(this.dateForm.value.startDate),
        endDate: new Date(this.dateForm.value.endDate)
      };

}


  }

}
