import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pageable } from '../core/model/page/Pageable';
import { Prestamo } from './model/Prestamo';
import { PrestamoPage } from './model/PrestamoPage';



@Injectable({
providedIn: 'root'
})
export class PrestamoService {

    constructor(
        private http: HttpClient
    ) { }
    
    getPrestamo(pageable:Pageable,  gameId?: number, clientId?:number, date?: Date): Observable<PrestamoPage> {
        let body:any = {pageable: pageable};

        if(gameId !== undefined)
            body.game_id = gameId;

        if(clientId !== undefined)
            body.clients_id = clientId;

        if(date !== undefined)
            body.date = date;

        return this.http.post<PrestamoPage>('http://localhost:8080/prestamo', body);
    }


    savePrestamo(prestamo: Prestamo): Observable<void> {
        let url = 'http://localhost:8080/prestamo';

        if (prestamo.id != null) {
            url += '/'+prestamo.id;
        }
        
        prestamo.fechainicio.setDate(prestamo.fechainicio.getDate() +1) ;
        prestamo.fechadevolucion.setDate(prestamo.fechadevolucion.getDate() +1) ;
        return this.http.put<void>(url, prestamo);
    }

    deletePrestamo(idPrestamo : number): Observable<void> {
        return this.http.delete<void>('http://localhost:8080/prestamo/'+idPrestamo);
    }    
    
}
