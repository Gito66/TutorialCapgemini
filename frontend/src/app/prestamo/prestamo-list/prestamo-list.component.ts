import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DialogConfirmationComponent } from 'src/app/core/dialog-confirmation/dialog-confirmation.component';
import { Pageable } from 'src/app/core/model/page/Pageable';
import { PrestamoEditComponent } from '../prestamo-edit/prestamo-edit.component';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';

import { Clients } from 'src/app/clients/model/Clients';
import { ClientsService } from 'src/app/clients/clients.service';
import { Game } from 'src/app/game/model/Game';
import { GameService } from 'src/app/game/game.service';


@Component({
selector: 'app-prestamo-list',
templateUrl: './prestamo-list.component.html',
styleUrls: ['./prestamo-list.component.scss']
})
export class PrestamoListComponent implements OnInit {

    pageNumber: number = 0;
    pageSize: number = 5;
    totalElements: number = 0;

    prestamo: Prestamo[];
    clients: Clients[];
    games: Game[];
    filterDate: Date;
    filterGame: Game;
    filterClient: Clients;

    dataSource = new MatTableDataSource<Prestamo>();
    displayedColumns: string[] = ['id', 'game_id', 'clients_id', 'fechainicio', 'fechadevolucion', 'action'];


    constructor(
        private prestamoService: PrestamoService,
        private clientsService: ClientsService,
        private gameService: GameService,
        public dialog: MatDialog,
    ) { 
    }

    ngOnInit(): void {
        this.onSearch();
        this.gameService.getGames().subscribe(result => this.games = result);
        this.clientsService.getClients().subscribe(result => this.clients = result);
    }

    onCleanFilter(): void {
        this.filterDate = null;
        this.filterClient = null;
        this.filterGame = null;
        this.onSearch();
    }

    onSearch(): void {
        let date = this.filterDate;
        let clientId = this.filterClient!=null ? this.filterClient.id : null;
        let gameId = this.filterGame!=null ? this.filterGame.id : null;
    
        let pageable: Pageable = {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort:[{
            property:'id',
            direction:'ASC'
            }]
        }
    
        this.prestamoService.getPrestamo(pageable, gameId, clientId, date).subscribe(data =>{ 
            this.dataSource.data = data.content;
            this.pageNumber = data.pageable.pageNumber;
            this.pageSize = data.pageable.pageSize;
            this.totalElements = data.totalElements;
        });
        }

  //  formatDate(date: Date): string {
    //    return date.toLocaleDateString('es-ES');
    //}

    createPrestamo() {      
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: {
                clients: this.clients,
                games: this.games,
            }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });      
    }  

    editPrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(PrestamoEditComponent, {
            data: { prestamo: prestamo }
        });

        dialogRef.afterClosed().subscribe(result => {
            this.ngOnInit();
        });    
    }

    deletePrestamo(prestamo: Prestamo) {    
        const dialogRef = this.dialog.open(DialogConfirmationComponent, {
            data: { title: "Eliminar prestamo", description: "Atención si borra el prestamo se perderán sus datos.<br> ¿Desea eliminar el prestamo?" }
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.prestamoService.deletePrestamo(prestamo.id).subscribe(result =>  {
                    this.ngOnInit();
                }); 
            }
        });
    }
    
    getPage(): Pageable {
        let pageable: Pageable = {
            pageNumber: this.pageNumber,
            pageSize: this.pageSize,
            sort: [{
                property: 'id',
                direction: 'ASC'
            }]
        }

        return pageable;
    }

    loadPage(event?: PageEvent) {
        let pageable = this.getPage();

        if(event != null ) {
            pageable.pageSize = event.pageSize;
            pageable.pageNumber = event.pageIndex;
        }

        this.prestamoService.getPrestamo(pageable).subscribe(data => {
            this.loadData(data);
        });
    }

    loadData(data: any) {
        data.content.forEach(prestamo => {
            prestamo.fechainicio = new Date(prestamo.fechainicio);
            prestamo.fechadevolucion = new Date(prestamo.fechadevolucion);
        });
        this.dataSource.data = data.content;
        this.pageNumber = data.pageable.pageNumber;
        this.pageSize = data.pageable.pageSize;
        this.totalElements = data.totalElements;
    }
}