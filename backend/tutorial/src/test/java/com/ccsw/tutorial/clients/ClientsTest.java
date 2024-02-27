package com.ccsw.tutorial.clients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

@ExtendWith(MockitoExtension.class)
public class ClientsTest {

    @Mock
    private ClientsRepository clientsRepository;

    @InjectMocks
    private ClientsServiceImpl clientsService;

    @Test
    public void findAllShouldReturnAllClientes() {

        List<Clients> list = new ArrayList<>();
        list.add(mock(Clients.class));

        when(clientsRepository.findAll()).thenReturn(list);

        List<Clients> clientes = clientsService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    public static final String CLIENTS_NAME = "CLI1";

    @Test
    public void saveNotExistsClientsIdShouldInsert() {

        ClientsDto clientsDto = new ClientsDto();
        clientsDto.setName(CLIENTS_NAME);

        ArgumentCaptor<Clients> clients = ArgumentCaptor.forClass(Clients.class);

        clientsService.save(null, clientsDto);

        verify(clientsRepository).save(clients.capture());

        assertEquals(CLIENTS_NAME, clients.getValue().getName());
    }

    public static final Long EXISTS_CLIENTS_ID = 1L;

    @Test
    public void saveExistsClientsIdShouldUpdate() {

        ClientsDto clientsDto = new ClientsDto();
        clientsDto.setName(CLIENTS_NAME);

        Clients clients = mock(Clients.class);
        when(clientsRepository.findById(EXISTS_CLIENTS_ID)).thenReturn(Optional.of(clients));

        clientsService.save(EXISTS_CLIENTS_ID, clientsDto);

        verify(clientsRepository).save(clients);
    }

    @Test
    public void deleteExistsClientsIdShouldDelete() throws Exception {

        Clients clients = mock(Clients.class);
        when(clientsRepository.findById(EXISTS_CLIENTS_ID)).thenReturn(Optional.of(clients));

        clientsService.delete(EXISTS_CLIENTS_ID);

        verify(clientsRepository).deleteById(EXISTS_CLIENTS_ID);
    }

    public static final Long NOT_EXISTS_CLIENTS_ID = 0L;
    /*
     * @Test public void getExistsClientsIdShouldReturnClients() {
     * 
     * Clients clients = mock(Clients.class);
     * when(clients.getId()).thenReturn(EXISTS_CLIENTS_ID);
     * when(clientsRepository.findById(EXISTS_CLIENTS_ID)).thenReturn(Optional.of(
     * clients));
     * 
     * Clients clientsResponse = clientsService.get(EXISTS_CLIENTS_ID);
     * 
     * assertNotNull(clientsResponse); assertEquals(EXISTS_CLIENTS_ID,
     * clients.getId()); }
     * 
     * @Test public void getNotExistsClientsIdShouldReturnNull() {
     * 
     * when(clientsRepository.findById(NOT_EXISTS_CLIENTS_ID)).thenReturn(Optional.
     * empty());
     * 
     * Clients clients = clientsService.get(NOT_EXISTS_CLIENTS_ID);
     * 
     * assertNull(clients); }
     */
}