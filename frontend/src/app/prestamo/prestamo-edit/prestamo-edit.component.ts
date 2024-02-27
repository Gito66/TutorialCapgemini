import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PrestamoService } from '../prestamo.service';
import { Prestamo } from '../model/Prestamo';
import { Clients } from 'src/app/clients/model/Clients';
import { ClientsService } from 'src/app/clients/clients.service';
import { GameService } from 'src/app/game/game.service';
import { Game } from 'src/app/game/model/Game';
@Component({
selector: 'app-prestamo-edit',
templateUrl: './prestamo-edit.component.html',
styleUrls: ['./prestamo-edit.component.scss']
})
export class PrestamoEditComponent implements OnInit {

    prestamo : Prestamo;
    games: Game[]; 
    clientes: Clients[];


    constructor(
        public dialogRef: MatDialogRef<PrestamoEditComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private prestamoService: PrestamoService,
        private gameService: GameService,
        private clientsService: ClientsService,
        
    ) { }

    ngOnInit(): void {
        if (this.data.prestamo != null) {
            this.prestamo = Object.assign({}, this.data.prestamo);
        }
        else {
            this.prestamo = new Prestamo();
        }

        this.gameService.getGames().subscribe(
            games => {
                this.games = games;

                if (this.prestamo.game != null){
                    let gameFilter: Game[] = games.filter(game => game.id == this.data.prestamo.game.id);
                    if (gameFilter != null) {
                        this.prestamo.game = gameFilter[0];
                    }
                }
            }
        );

        this.clientsService.getClients().subscribe(
            clientes => {
                this.clientes = clientes

                if (this.prestamo.clients != null) {
                    let clientFilter: Clients[] = clientes.filter(clients => clients.id == this.data.prestamo.clients.id);
                    if (clientFilter != null) {
                        this.prestamo.clients = clientFilter[0];
                    }
                }
            }
        );
    }

    onSave() {
        const fechainicio = new Date(this.prestamo.fechainicio);
        const fechadevolucion = new Date(this.prestamo.fechadevolucion);
        const diffTime = fechadevolucion.getTime() - fechainicio.getTime();
        const diffDays = diffTime / (1000 * 3600 * 24);
    
        if (diffDays < 14) {
            this.prestamoService.savePrestamo(this.prestamo).subscribe(result =>  {
            this.dialogRef.close();
        });
        } else {
        
        alert('La fecha de devolución debe ser como maximo 14 días después de la fecha de inicio del préstamo.');
        }
    }  
    
    onClose() {
        this.dialogRef.close();
    }
    
   // formatDate(dateString: string): string {
     //   return dateString.split('T')[0];
    //}
}