import { PesoSuino } from "../peso/pesoSuino";
import { Sessao } from "../sessoes/sessao";

export interface HistoricoSuino {
    pesos : PesoSuino []
    sessoes : Sessao []
}