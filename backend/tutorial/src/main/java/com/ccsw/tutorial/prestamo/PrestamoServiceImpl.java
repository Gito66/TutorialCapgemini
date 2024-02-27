package com.ccsw.tutorial.prestamo;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import jakarta.transaction.Transactional;
import com.ccsw.tutorial.clients.ClientsService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    ClientsService clientsService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Prestamo get(Long id) {

        return this.prestamoRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prestamo> findAll() {
        return (List<Prestamo>) this.prestamoRepository.findAll();
    }

    public Page<Prestamo> findPage(PrestamoSearchDto dto) {
        var gameSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", dto.getGame_id()));
        var clientSpec = new PrestamoSpecification(new SearchCriteria("clients.id", ":", dto.getClients_id()));
        var startSpec = new PrestamoSpecification(new SearchCriteria("fechainicio", "<=", dto.getDate()));
        var endSpec = new PrestamoSpecification(new SearchCriteria("fechadevolucion", ">=", dto.getDate()));

        var spec = Specification.where(gameSpec).and(clientSpec).and(startSpec).and(endSpec);
        return prestamoRepository.findAll(spec, dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void save(Long id, PrestamoDto dto) {

        Prestamo prestamo;

//        // Verificar si ya existe un préstamo asociado al cliente
//        if (dto.getClients() != null && prestamoRepository.existsByClientsId(dto.getClients().getId())) {
//            throw new RuntimeException("Ya existe un préstamo asociado a este cliente.");
//        }
//
//        // Verificar si ya existe un préstamo asociado al juego
//        if (dto.getGame() != null && prestamoRepository.existsByGameId(dto.getGame().getId())) {
//            throw new RuntimeException("Ya existe un préstamo asociado a este juego.");
//        }

        // Verificar si ya existe un préstamo que se superpone en el tiempo con las
        // fechas proporcionadas

        if (dto.getClients() != null && prestamoRepository.existsByClientsIdAndFechainicioBeforeAndFechadevolucionAfter(
                dto.getClients().getId(), dto.getFechaInicio(), dto.getFechaDevolucion())) {
            throw new RuntimeException(
                    "Ya existe un préstamo que involucra a este cliente dentro del período de tiempo especificado.");
        }

        if (dto.getGame() != null && prestamoRepository.existsByGameIdAndFechainicioBeforeAndFechadevolucionAfter(
                dto.getGame().getId(), dto.getFechaInicio(), dto.getFechaDevolucion())) {
            throw new RuntimeException(
                    "Ya existe un préstamo que involucra a este juego dentro del período de tiempo especificado.");
        }
        if (id == null) {
            prestamo = new Prestamo();
        } else {
            prestamo = this.get(id);
            prestamo = this.prestamoRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, prestamo, "id", "clients", "game");

        prestamo.setClients(clientsService.get(dto.getClients().getId()));
        prestamo.setGame(gameService.get(dto.getGame().getId()));

        this.prestamoRepository.save(prestamo);
    }

//    @Override
//    public void save(PrestamoDto dto) throws Exception {
//        var startDate = dto.getFechaInicio();
//        var endDate = dto.getFechaDevolucion();
//        var client = dto.getClients();
//        var game = dto.getGame();
//
//        System.err.println("==========================================");
//        System.err.println("-- VARIABLES DEL DTO --");
//        System.err.println("startDate = " + startDate.toString());
//        System.err.println("endDate = " + endDate.toString());
//        System.err.println("");
//
//        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
//
//        // COMPROBAR QUE EL PERIODO DE PRÉSTAMO ES DE 14 DÍAS
//        if (daysDiff > 14 || daysDiff < 0)
//            throw new Exception("The period of the loan has to be of 14 maximum and not negative");
//
//        // recorrer día a día desde la fecha incio hasta la final
//        for (LocalDate day = LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth()); day
//                .compareTo(endDate) <= 0; day = day.plusDays(1)) {
//
//            var clientSpec = new PrestamoSpecification(new SearchCriteria("client.id", ":", client.getId()));
//            var gameSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", game.getId()));
//            var startSpec = new PrestamoSpecification(new SearchCriteria("prestamo_fecha_inicio", "<=", day));
//            var endSpec = new PrestamoSpecification(new SearchCriteria("prestamo_fecha_devolucion", ">=", day));
//
//            System.err.println("-- DÍA = " + day.toString() + " --");
//
//            // COMPROBAR QUE EL CLIENTE NO TENGA MÁS DE UN JUEGO EL MISMO DÍA
//            var spec1 = Specification.where(clientSpec).and(startSpec).and(endSpec);
//            long ngames = prestamoRepository.count(spec1);
//            System.err.println("ngames = " + ngames);
//            if (ngames >= 2) {
//                throw new Exception("A client can't have more than 2 games the same day: " + day.toString());
//            }
//
//            // COMPROBAR QUE EL JUEGO NO APARECE PRESTADO OTRA VEZ A CUALQUIER PERSONA
//            var spec2 = Specification.where(gameSpec).and(startSpec).and(endSpec);
//            if (prestamoRepository.exists(spec2)) {
//                System.err.println("exist = true");
//                throw new Exception("The game can't appear in two times in the same day : " + day.toString());
//            }
//            System.err.println("exist = false");
//
//            System.err.println();
//        }
//
//        Prestamo prestamo = new Prestamo();
//        prestamo.setGame(gameService.get(dto.getGame().getId()));
//        prestamo.setClients(clientsService.get(dto.getClients().getId()));
//        prestamo.setFechaInicio(dto.getFechaInicio());
//        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
//        this.prestamoRepository.save(prestamo);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.prestamoRepository.deleteById(id);
    }
}