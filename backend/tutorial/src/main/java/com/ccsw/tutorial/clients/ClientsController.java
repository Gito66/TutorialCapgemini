package com.ccsw.tutorial.clients;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author ccsw
 * 
 */
@Tag(name = "Clients", description = "API of Clients")
@RequestMapping(value = "/clients")
@RestController
@CrossOrigin(origins = "*")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link ClientsDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Clients")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClientsDto> findAll() {

        List<Clients> clientes = this.clientsService.findAll();

        return clientes.stream().map(e -> mapper.map(e, ClientsDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un cliente
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Client")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClientsDto dto) {

        this.clientsService.save(id, dto);
    }

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Client")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.clientsService.delete(id);
    }
}