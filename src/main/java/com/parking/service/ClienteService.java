package com.parking.service;

import com.parking.model.Cliente;
import com.parking.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNombre(clienteDetails.getNombre());
            cliente.setCorreo(clienteDetails.getCorreo());
            cliente.setContrasena(clienteDetails.getContrasena());
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
} 