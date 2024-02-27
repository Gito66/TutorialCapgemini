package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author ccsw
 *
 */
public class PrestamoSearchDto {

    private PageableRequest pageable;

    private Long game_id;
    private Long clients_id;
    private LocalDate date;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }

    public Long getClients_id() {
        return clients_id;
    }

    public void setClients_id(Long clients_id) {
        this.clients_id = clients_id;
    }

    public Long getGame_id() {
        return game_id;
    }

    public void getGame_id(Long game_id) {
        this.game_id = game_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
