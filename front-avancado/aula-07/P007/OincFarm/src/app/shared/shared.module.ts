import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

//@angular/material
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
  declarations: [],
  imports: [ CommonModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatButtonModule,  HttpClientModule, MatInputModule, MatIconModule, MatSnackBarModule],
  exports: [MatFormFieldModule,FormsModule, ReactiveFormsModule, MatButtonModule,  HttpClientModule, MatInputModule, MatIconModule, MatSnackBarModule]
})
export class SharedModule { }
