import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Suino } from '../models/suino.model';
import {Observable, map} from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class DataBaseService implements OnInit {

  loadedSuinos: Suino[] = [];
  private baseUrl = 'https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos.json';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  verificarBrincoExistente(brinco: string): Observable<boolean> {
    return this.http.get(`${this.baseUrl}?orderBy="brinco"&equalTo="${brinco}"`)
      .pipe(
        map(response => {
          // Se a resposta não for nula e tiver propriedades, significa que o brinco existe
          return response !== null && Object.keys(response).length > 0;
        })
      );
  }

  addSuino(suinoData: any) {
    this.http
      .post(
        'https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos.json',
        suinoData
      )
      .subscribe((responseData) => {
        console.log(responseData);
      });
  }

  getSuinos() {
    return this.http
      .get<{ [key: string]: Suino }>(
        'https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos.json',
        {
          params: new HttpParams().set('print', 'pretty'),
        }
      )
      .pipe(
        map((responseData) => {
          const postArray: Suino[] = [];
          for (const key in responseData) {
            if (responseData.hasOwnProperty(key)) {
              postArray.push({ ...(responseData as any)[key], id: key });
            }
          }
          return postArray;
        })
      );
  }

  deleteSuino(id: string) {
    return this.http.delete(
      `https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos/${id}.json`
    );
  }
  

  getSuino(id: string) {
    return this.http.get<Suino>(
      `https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos/${id}.json`
    );
  }

  editarSuino(
    id: string,
    suinoData: {
      brinco: number;
      brincoPai: number;
      brincoMae: number;
      dataNascimento: string;
      dataSaida: string;
      status: 'Ativo' | 'Vendido' | 'Morto';
      sexo: 'M' | 'F';
    }
  ) {
    return this.http.put(
      `https://oincfarm-97b0a-default-rtdb.firebaseio.com/suinos/${id}.json`,
      suinoData,
      { observe: 'response' }
    );
  }


}
