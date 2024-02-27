package com.ccsw.tutorial.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;

import jakarta.transaction.Transactional;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    ClientsRepository clientsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Clients> findAll() {
        return (List<Clients>) this.clientsRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClientsDto dto) {

        Clients clients;
        Clients existClient = clientsRepository.findByName(dto.getName());
        if (id == null && existClient == null) {
            clients = new Clients();
        } else {
            clients = this.clientsRepository.findById(id).orElse(null);
            clients = this.get(id);
        }

        clients.setName(dto.getName());

        this.clientsRepository.save(clients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clientsRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.clientsRepository.deleteById(id);
    }

    @Override
    public Clients get(Long id) {
        // TODO Auto-generated method stub
        return this.clientsRepository.findById(id).orElse(null);
    }

}