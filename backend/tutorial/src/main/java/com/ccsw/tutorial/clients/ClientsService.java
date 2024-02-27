package com.ccsw.tutorial.clients;

import java.util.List;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

/**
 * @author ccsw
 * 
 */
public interface ClientsService {

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link Clients}
     */
    List<Clients> findAll();

    /**
     * Método para crear o actualizar un cliente
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClientsDto dto);

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un {@link Clients} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Clients}
     */
    Clients get(Long id);

}