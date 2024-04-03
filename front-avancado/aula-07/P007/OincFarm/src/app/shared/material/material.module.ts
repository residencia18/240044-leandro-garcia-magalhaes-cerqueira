import { NgModule } from '@angular/core';

//@angular/material
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatButton } from '@angular/material/button';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatOption } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatFormField} from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIcon } from '@angular/material/icon';
import { MatLabel } from '@angular/material/form-field';

@NgModule({
  imports: [
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatButton,
    MatOption,
    MatTableModule,
    MatFormField,
    MatDialogModule,
    MatIcon,
    MatLabel

  ],
  exports: [
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatButton,
    MatOption,
    MatTableModule,
    MatFormField,
    MatDialogModule,  
    MatIcon,
    MatLabel
  ]
})
export class MaterialModule { }
