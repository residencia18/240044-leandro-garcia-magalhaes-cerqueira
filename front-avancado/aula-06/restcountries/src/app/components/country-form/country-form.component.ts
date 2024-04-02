import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-country-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './country-form.component.html',
  styleUrl: './country-form.component.css'
})

export class CountryFormComponent implements OnInit {
  camposDoForm: { tipo: string, nome: string, rotulo: string }[] = [];
  countryForm!: FormGroup;
  camposCarregados: boolean = false; // Variável de controle

  constructor(private http: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.http.get<any>('https://restcountries.com/v3.1/all').subscribe({
      next: (data: any[]) => {
        if (data && data.length > 0) {
          const firstCountry = data[0];
          this.transformToFormFields(firstCountry);
          this.camposCarregados = true; // Indicar que os campos foram carregados
          this.createForm(); // Criar o formulário aqui depois que os campos estiverem carregados
        }
      },
      error: (error: any) => {
        console.error('Error fetching data:', error);
      }
    });
  }

  transformToFormFields(countryData: any): void {
    for (const key of Object.keys(countryData)) {
      const field = {
        tipo: this.getFieldType(countryData[key]),
        nome: key,
        rotulo: key
      };
      this.camposDoForm.push(field);
    }
  }

  createForm(): void {
    const formControls: { [key: string]: any } = {};

    for (const field of this.camposDoForm) {
      formControls[field.nome] = new FormControl(''); // Crie cada controle do formulário explicitamente usando FormControl
    }

    this.countryForm = new FormGroup(formControls); // Use os controles do formulário criados para inicializar FormGroup
  }

  getFieldType(value: any): string {
    if (typeof value === 'string') {
      return 'text';
    } else if (typeof value === 'number') {
      return 'number';
    } else if (typeof value === 'boolean') {
      return 'checkbox';
    } else {
      return 'text';
    }
  }
}
