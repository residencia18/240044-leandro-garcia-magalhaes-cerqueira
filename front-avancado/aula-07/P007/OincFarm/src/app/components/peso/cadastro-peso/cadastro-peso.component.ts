import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../../shared/material/material.module';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Suino } from '../../suino/suino';
import { BancoService } from '../../../core/banco.service';
import { ReactiveFormsModule } from '@angular/forms';
import { PesoSuino } from '../pesoSuino';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-cadastro-peso',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, MaterialModule],
  templateUrl: './cadastro-peso.component.html',
  styleUrl: './cadastro-peso.component.css'
})


export class CadastroPesoComponent implements OnInit {
  pesoForm!: FormGroup;
  suinos!: Suino[];
  pesoData!: PesoSuino;
  indicePeso!: number;
  constructor(
    private fb: FormBuilder,
    private servico: BancoService,
    private dialogRef: MatDialogRef<CadastroPesoComponent>,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public data: { opcao: string, peso: PesoSuino, indice: number },) {
    this.pesoForm = this.fb.group({
      brincoSuino: [data.peso?.brincoSuino || '', Validators.required],
      dataPesagem: [data.peso?.dataPesagem || '', Validators.required],
      pesoKg: [data.peso?.pesoKg || '', Validators.required]
    });
  }

  ngOnInit(): void {
    this.carregarSuinos();
  }

  openDialog(): void {
    if (this.data.opcao === 'editar') {
      this.pesoForm.patchValue({
        brincoSuino: this.data.peso?.brincoSuino,
        dataPesagem: this.data.peso?.dataPesagem,
        pesoKg: this.data.peso?.pesoKg
      });
    }
  }
  carregarSuinos() {
    this.servico.getSuinos().subscribe(suinos => {
      this.suinos = suinos;
    });
  }
 
  onEdit(): void {
    const id = this.data.peso?.id!;
    this.servico.editarPesoSuino(this.pesoForm.value.brincoSuino, id, this.pesoForm.value).subscribe(() => {
      this.pesoForm.reset();
      this.mostrarMensagem('Pesagem editada com sucesso');
      this.servico.atualizarListaPesos();
      this.dialogRef.close();
    });
  }
  onAdd(): void {
    this.servico.adicionarPesoSuino(this.pesoForm.value.brincoSuino, this.pesoForm.value).subscribe(() => {
      this.pesoForm.reset();
      this.mostrarMensagem('Pesagem adicionada com sucesso');
      this.servico.atualizarListaPesos();
      this.dialogRef.close();
    });
  }


  mostrarMensagem(messagem: string): void {
    this.snackBar.open(messagem, 'Fechar', {
      duration: 3000, // 3 segundos
    });
  }
  onCancelClick(): void {
    this.dialogRef.close();
  }

  onSaveClick(): void {
    if (this.pesoForm.valid) {
      if (this.data.opcao === 'editar') {
        this.onEdit();
      } else {
        this.onAdd();
      }
    }
  }
}
