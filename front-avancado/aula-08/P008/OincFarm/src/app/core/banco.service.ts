import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Suino } from '../components/suino/suino';
import { PesoSuino } from '../components/peso/pesoSuino';
import { Sessao } from '../components/sessoes/sessao';

@Injectable({
  providedIn: 'root'
})
export class BancoService {

  private readonly baseURL = 'https://oincfarm-97b0a-default-rtdb.firebaseio.com';

  constructor(private http: HttpClient) {}

  private getResourceUrl(resource: string, id?: string, subResource?: string): string {
    let url = `${this.baseURL}/${resource}`;
    if (id) {
      url += `/${id}`;
    }
    if (subResource) {
      url += `/${subResource}`;
    }
    return `${url}.json`;
  }

  
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed:`, error);
      return of(result as T);
    };
  }

  verificarBrincoExistente(brinco: string): Observable<boolean> {
    const url = this.getResourceUrl('suinos');
    const params = new HttpParams().set('orderBy', '"brinco"').set('equalTo', `"${brinco}"`);
    return this.http.get(url, { params }).pipe(
      map(response => response !== null && Object.keys(response).length > 0)
    );
  }

  // SUÍNOS ---------------------------------------------------------

  addSuino(idSuino: string, suinoData: Suino) {
    const url = this.getResourceUrl('suinos', idSuino);
    this.http.post(url, suinoData).subscribe((responseData => {
      console.log(responseData);
    }))     
  }
  
  getSuinos(): Observable<Suino[]> {
    const url = this.getResourceUrl('suinos');
    return this.http.get<{ [key: string]: Suino }>(url).pipe(
      map(responseData => {
        return Object.keys(responseData).map(key => ({ ...responseData[key], id: key }));
      })
    );
  }

  apagarTodosSuinos() {
    const url = this.getResourceUrl('suinos');
    this.http.delete(url).subscribe((responseData => {
      console.log(responseData);
    }))
  }

  
  apagarSuino(id: string): Observable<any> {
    const url = this.getResourceUrl('suinos', id);
    return this.http.delete(url);
  }

  getSuino(id: string): Observable<any> {
    const url = this.getResourceUrl('suinos', id);
    return this.http.get(url);
  }
  

  editarSuino(id: string, suino: Suino){
    const url = this.getResourceUrl('suinos', id);
    return this.http.put(url, suino, { observe: 'response' });

    
  }

  // PESOS DE SUÍNOS ------------------------------------------------

  adicionarPesoSuino(idSuino: string, pesoSuino: PesoSuino): Observable<any> {
    const url = this.getResourceUrl('pesos', idSuino);
    return this.http.post(url, pesoSuino);
  }

  apagarTodosPesosSuino(idSuino: string): Observable<any> {
    const url = this.getResourceUrl('pesos', idSuino);
    return this.http.delete(url);
  }

  getPesoSuino(idSuino: string, idPeso: string): Observable<PesoSuino> {
    const url = this.getResourceUrl('pesos', idSuino, idPeso);
    return this.http.get<PesoSuino>(url);
  }

  editarPesoSuino(idSuino: string, idPeso: string, pesoSuino: PesoSuino): Observable<any> {
    const url = this.getResourceUrl('pesos', idSuino, idPeso);
    return this.http.put(url, pesoSuino);
  }

  apagarPesoSuino(idSuino: string, idPeso: string): Observable<any> {
    const url = this.getResourceUrl('pesos', idSuino, idPeso);
    return this.http.delete(url);
  }

  atualizarListaPesos(): Observable<PesoSuino[]> {
    const url = this.getResourceUrl('pesos');
    return this.http.get<{ [key: string]: { [key: string]: PesoSuino } }>(url).pipe(
      map(responseData => {
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
        return listaPesos;
      })
    );
  }

  obterTodosPesos(): Observable<PesoSuino[]> {
    const url = this.getResourceUrl('pesos');
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

  getPesosSuino(idSuino: string): Observable<any[]> {
    const url = this.getResourceUrl('pesos', idSuino);
    return this.http.get<any[]>(url).pipe(
      map(pesosSuino => {
        return Object.values(pesosSuino).map((pesoSuino: any) => ({
          dataPesagem: pesoSuino.dataPesagem,
          pesoKg: pesoSuino.pesoKg
        }));
      }),
      catchError(this.error)
    );
  }

  error(error: any): Observable<any> {
    console.error('Erro ao obter pesos de suíno:', error);
    throw error;
  }

  // SESSÕES --------------------------------------------------------

  adicionarSessao(sessao: any){
    const url = this.getResourceUrl('sessoes');
    this.http.post(url,sessao).subscribe((responseData =>{
      console.log(responseData);
    }))  
  }

  getSessoes(): Observable<Sessao[]> {
    const url = this.getResourceUrl('sessoes');
    return this.http.get<{ [key: string]: Sessao }>(url).pipe(
      map(responseData => {
        const listaArray: Sessao[] = [];
        for (const key in responseData) {
          if (responseData.hasOwnProperty(key)) {
            listaArray.push({ ...(responseData as any)[key], id: key });
          }
        }
        return listaArray;
      })
    );
  }

  apagarTodasSessoes(): Observable<any> {
    const url = this.getResourceUrl('sessoes');
    return this.http.delete(url);
  }

  apagarSessao(id: string): Observable<any> {
    const url = this.getResourceUrl('sessoes', id);
    return this.http.delete(url);
  }

  getSessao(id: string): Observable<Sessao> {
    const url = this.getResourceUrl('sessoes', id);
    return this.http.get<Sessao>(url).pipe(
      tap((sessao: Sessao) => {
        console.log('Detalhes da sessao:', sessao);
      }),
      catchError(this.handleError<any>('getSessao'))
    );
  }

  getSessoesByBrinco(brincoSuino: string): Observable<Sessao[]> {
    return this.getSessoes().pipe(
      map(sessoes => sessoes.filter(sessao => sessao.brincos.some((brinco: any) => brinco === brincoSuino)))
    );
  }

}
