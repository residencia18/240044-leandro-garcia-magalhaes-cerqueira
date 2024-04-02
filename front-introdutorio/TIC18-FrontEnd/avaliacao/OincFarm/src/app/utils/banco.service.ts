
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, exhaustMap, map, of, take, tap } from 'rxjs';
import { Suino } from '../models/suino.model';
import { PesoSuino } from '../models/pesoSuino';

@Injectable({
  providedIn: 'root'
})

export class BancoService {

  apiURL = 'https://oincfarm-97b0a-default-rtdb.firebaseio.com/';

  constructor(private http: HttpClient) { }

  adicionarSuino(suino: Suino): Observable<any> {
    return this.http.post(`${this.apiURL}/suinos.json`, suino).pipe(
      catchError(this.handleError<any>('adicionarSuino'))
    );
  }


  getSuinos() {
    return this.http.get<{ [key: string]: Suino }>(`${this.apiURL}/suinos.json`).pipe(
      map((responseData) => {
        const listaArray: Suino[] = [];
        for (const key in responseData) {
          if ((responseData).hasOwnProperty(key)) {
            listaArray.push({ ...(responseData as any)[key], id: key });
          }
        }
        return listaArray;
      }
      ),
    );
  }

  apagarTodosSuinos() {
    return this.http.delete(`${this.apiURL}/suinos.json`);
  }

  apagarSuino(id: string): Observable<any> {
    const url = `${this.apiURL}/suinos/${id}.json`;
    return this.http.delete(url);
  }


  getSuino(id: string): Observable<Suino> {
    const url = `${this.apiURL}/suinos/${id}.json`;
    return this.http.get<Suino>(url)
      .pipe(
        tap((suino: Suino) => {
          console.log('Detalhes do Suíno:', suino);
        }),
        catchError(this.handleError<any>('getSuino'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed:`, error);
      return of(result as T);
    };

  }


  verificarBrincoExistente(brinco: string): Observable<boolean> {
    return this.http.get(`${this.apiURL}/suinos.json?orderBy="brinco"&equalTo="${brinco}"`
    )
      .pipe(
        map(response => {
          // Se a resposta não for nula e tiver propriedades, significa que o brinco existe
          return response !== null && Object.keys(response).length > 0;
        })
      );
  }

  editarSuino(id: string, suino: Suino) {
    const url = `${this.apiURL}/suinos/${id}.json`;
    return this.http.put(url,
      suino,
      { observe: 'response' }
    );
  }

  adicionarPesoSuino(idSuino: string, pesoSuino: PesoSuino) {
    return this.http.post(`${this.apiURL}/pesos/${idSuino}.json`, pesoSuino);
  }

  // getPesosSuino(idSuino: string): Observable<PesoSuino[]> {
  //   return this.http.get<{ [key: string]: PesoSuino }>(`${this.apiURL}/pesos/${idSuino}.json`)
  //     .pipe(
  //       map(responseData => {
  //         return Object.values(responseData);
  //       }),
  //       catchError(this.handleError<any>('getPesosSuino'))
  //     );
  // }


  apagarTodosPesosSuino(idSuino: string) {
    return this.http.delete(`${this.apiURL}/pesos/${idSuino}.json`);
  }

  getPesoSuino(idSuino: string, idPeso: string) {
    return this.http.get<PesoSuino>(`${this.apiURL}/pesos/${idSuino}/${idPeso}.json`);
  }

  editarPesoSuino(idSuino: string, idPeso: string, pesoSuino: PesoSuino) {
    return this.http.put(`${this.apiURL}/pesos/${idSuino}/${idPeso}.json`, pesoSuino);
  }

  apagarPesoSuino(idSuino: string, idPeso: string) {
    return this.http.delete(`${this.apiURL}/pesos/${idSuino}/${idPeso}.json`);
  }
  atualizarListaPesos(): Observable<PesoSuino[]> {
    // Construindo a  URL para buscar todos os pesos de todos os suínos
    const url = `${this.apiURL}/pesos.json`;

    // Fazendo a requisição HTTP para buscar todos os pesos
    return this.http.get<{ [key: string]: { [key: string]: PesoSuino } }>(url).pipe(
      map(responseData => {
        // Convertendo a resposta para um array de pesos
        const listaPesos: PesoSuino[] = [];
        for (const keySuino in responseData) {
          if (responseData.hasOwnProperty(keySuino)) {
            for (const keyPeso in responseData[keySuino]) {
              if (responseData[keySuino].hasOwnProperty(keyPeso)) {
                listaPesos.push({ ...(responseData[keySuino][keyPeso] as PesoSuino), id: keyPeso });
              }
            }
          }
        }
        // Retornando a lista de pesos atualizada
        return listaPesos;
      })
    );
  }


  obterTodosPesos(): Observable<PesoSuino[]> {
    const url = `${this.apiURL}/pesos.json`;
    return this.http.get<{ [key: string]: { [key: string]: PesoSuino } }>(url).pipe(
      map(responseData => {
        const listaPesos: PesoSuino[] = [];
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            for (const keyPeso in responseData[key]) {
              if (responseData[key].hasOwnProperty(keyPeso)) {
                listaPesos.push({ ...(responseData[key][keyPeso] as PesoSuino), id: keyPeso });
              }
            }
          }
        }
        return listaPesos;
      })
    );
  }
  //TESTING

  getPesosSuino(idSuino: string): Observable<any[]> {
    const url = `https://oincfarm-97b0a-default-rtdb.firebaseio.com/pesos/${idSuino}.json`;
    return this.http.get<any[]>(url)
      .pipe(
        map(pesosSuino => {
          // Mapear a lista de objetos pesoSuino para uma lista de objetos contendo dataPesagem e pesoKg
          return Object.values(pesosSuino).map((pesoSuino: any) => ({
            dataPesagem: pesoSuino.dataPesagem,
            pesoKg: pesoSuino.pesoKg
          }));
        }),
        catchError(this.handleError2)
      );
  }

  handleError2(error: any): Observable<any> {
    console.error('Erro ao obter pesos de suíno:', error);
    throw error;
  }
}
