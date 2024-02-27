package com.ccsw.tutorial.prestamo.model;

import java.time.OffsetDateTime;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.game.model.Game;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "clients_id", nullable = false)
    private Clients clients;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "fechainicio", nullable = false)
    private OffsetDateTime fechainicio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(name = "fechadevolucion", nullable = false)
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
     * @return game
     */
    public Game getGame() {

        return game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) {

        this.game = game;
    }

    /**
     * @return clients
     */
    public Clients getClients() {

        return clients;
    }

    /**
     * @param clients new value of {@link #getClients}.
     */
    public void setClients(Clients clients) {

        this.clients = clients;
    }

    /**
     * @return title
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