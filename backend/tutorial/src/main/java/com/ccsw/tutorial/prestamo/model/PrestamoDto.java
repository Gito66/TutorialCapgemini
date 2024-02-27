package com.ccsw.tutorial.prestamo.model;

import java.time.OffsetDateTime;

import com.ccsw.tutorial.clients.model.ClientsDto;
import com.ccsw.tutorial.game.model.GameDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ccsw
 *
 */
public class PrestamoDto {

    private Long id;

    private ClientsDto clients;

    private GameDto game;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private OffsetDateTime fechainicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private OffsetDateTime fechadevolucion;

    /**
     * @return id
     */
    public Long getId() {

        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return name
     */
    public ClientsDto getClients() {

        return clients;
    }

    /**
     * @param name new value of {@link #getName}.
     */
    public void setClients(ClientsDto clients) {

        this.clients = clients;
    }

    /**
     * @return title
     */
    public GameDto getGame() {

        return game;
    }

    /**
     * @param title new value of {@link #getTitle}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }

    /**
     * @return prestamo_fecha_inicio
     */
    public OffsetDateTime getFechaInicio() {

        return fechainicio;
    }

    /**
     * @param prestamo_fecha_inicio new value of {@link #getFechaInicio}.
     */
    public void setFechaInicio(OffsetDateTime fechainicio) {

        this.fechainicio = fechainicio;
    }

    /**
     * @return prestamo_fecha_devolucion
     */
    public OffsetDateTime getFechaDevolucion() {

        return fechadevolucion;
    }

    /**
     * @param prestamo_fecha_devolucion new value of {@link #getFechaDevolucion}.
     */
    public void setFechaDevolucion(OffsetDateTime fechadevolucion) {

        this.fechadevolucion = fechadevolucion;
    }

}