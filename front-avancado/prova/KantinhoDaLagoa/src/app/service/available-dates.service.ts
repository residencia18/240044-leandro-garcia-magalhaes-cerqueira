import { Injectable } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/compat/database';
import { AvailableDate } from '../interfaces/available-date';

@Injectable({
  providedIn: 'root'
})
export class AvailableDatesService {
  private dbPath = '/available-dates';

  constructor(private db: AngularFireDatabase) {}

  addDate(date: AvailableDate): void {
    this.db.list(this.dbPath).push(date);
  }

  getDates() {
    return this.db.list<AvailableDate>(this.dbPath).valueChanges();
  }
  
}
