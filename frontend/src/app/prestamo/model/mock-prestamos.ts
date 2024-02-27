import { PrestamoPage } from "./PrestamoPage";

export const PRESTAMO_DATA: PrestamoPage = {
    content: [
        { id: 1, game: 'Parchis', client: 'Jorge', prestamo_fecha_inicio: '22/02/2023', prestamo_fecha_devolucion: '23/02/2023' },
        { id: 2, game: 'Terraforming', client: 'Alicia', prestamo_fecha_inicio: '22/01/2023', prestamo_fecha_devolucion: '23/02/2024' },
        { id: 3, game: 'Majjong', client: 'Pedro', prestamo_fecha_inicio: '25/02/2023', prestamo_fecha_devolucion: '23/12/2023' },
        { id: 4, game: 'Heroes', client: 'Paco', prestamo_fecha_inicio: '02/04/2023', prestamo_fecha_devolucion: '23/12/2024' },
        { id: 5, game: 'Ajedrez', client: 'Fran', prestamo_fecha_inicio: '14/12/2023', prestamo_fecha_devolucion: '23/08/2023' },
        { id: 6, game: 'Warhammer', client: 'Sergio', prestamo_fecha_inicio: '20/02/2023', prestamo_fecha_devolucion: '23/04/2023' },
        { id: 7, game: 'WaterResult', client: 'Ana', prestamo_fecha_inicio: '09/02/2023', prestamo_fecha_devolucion: '22/06/2023' },

    ],  
    pageable : {
        pageSize: 5,
        pageNumber: 0,
        sort: [
            {property: "id", direction: "ASC"}
        ]
    },
    totalElements: 7
}
