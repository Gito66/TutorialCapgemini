package com.ccsw.tutorial.prestamo;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.clients.model.ClientsDto;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.Prestamo;

/**
 * @author ccsw
 *
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
    /**
     * Método para recuperar un listado paginado de {@link Prestamo} que cumplan con
     * specification
     *
     * @param pageable    {@link pageable}
     * @param spec{{@link Specification}
     * 
     * @return {@link Page} de {@link Prestamo}
     */
    @Override
    @EntityGraph(attributePaths = { "clients", "game" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

    /**
     * Método que devuelve numero de {@link Prestamos} que cumplen
     * {@link Specification}.
     * 
     * @param spec {@link Specification}
     * 
     * @return {@link long} número de {@link Prestamo}
     */
    @Override
    @EntityGraph(attributePaths = { "clients", "game" })
    long count(Specification<Prestamo> spec);

    Page<Prestamo> findAll(Pageable pageable);

//    Prestamo findByGame(GameDto gameDto);
//
//    Prestamo findByClients(ClientsDto clientsDto);
//    

    @EntityGraph(attributePaths = { "clients", "game" })
    Optional<Prestamo> findByClients(ClientsDto clientsDto);

    @EntityGraph(attributePaths = { "clients", "game" })
    Optional<Prestamo> findByGame(GameDto gameDto);

    // Nuevo método para verificar si existe un préstamo asociado a un cliente
    boolean existsByClientsId(Long clientId);

    // Nuevo método para verificar si existe un préstamo asociado a un juego
    boolean existsByGameId(Long gameId);

    // Nuevos métodos para verificar si existe un préstamo que se superpone en el
    // tiempo
    boolean existsByClientsIdAndFechainicioBeforeAndFechadevolucionAfter(Long clientId, OffsetDateTime fechainicio,
            OffsetDateTime fechadevolucion);

    boolean existsByGameIdAndFechainicioBeforeAndFechadevolucionAfter(Long gameId, OffsetDateTime fechainicio,
            OffsetDateTime fechadevolucion);

}
