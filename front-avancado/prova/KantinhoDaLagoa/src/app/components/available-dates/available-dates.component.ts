import { Component, OnInit } from '@angular/core';
import { AvailableDatesService } from '../../service/available-dates.service';
import { AvailableDate } from '../../interfaces/available-date';

@Component({
  selector: 'app-available-dates',
  standalone: true,
  templateUrl: './available-dates.component.html',
  styleUrls: ['./available-dates.component.css'],
  providers: [AvailableDatesService]
})
export class AvailableDatesComponent implements OnInit {
  
  availableDates: AvailableDate[] = [];

  constructor(private availableDatesService: AvailableDatesService) {}

  ngOnInit() {
    this.availableDatesService.getDates().subscribe((dates: AvailableDate[]) => {
      this.availableDates = dates;
    });
  }
}
