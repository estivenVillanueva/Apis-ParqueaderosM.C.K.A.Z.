package com.parking.service;

import com.parking.model.Administrator;
import com.parking.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator createAdministrator(Administrator administrator) {
        if (administratorRepository.existsByEmail(administrator.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }
        if (administratorRepository.existsByPhoneNumber(administrator.getPhoneNumber())) {
            throw new RuntimeException("El número de teléfono ya está registrado");
        }
        return administratorRepository.save(administrator);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(Long id) {
        return administratorRepository.findById(id);
    }

    public Optional<Administrator> getAdministratorByEmail(String email) {
        return administratorRepository.findByEmail(email);
    }

    @Transactional
    public Administrator updateAdministrator(Long id, Administrator administratorDetails) {
        Administrator administrator = administratorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        if (!administrator.getEmail().equals(administratorDetails.getEmail()) 
            && administratorRepository.existsByEmail(administratorDetails.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado");
        }

        if (!administrator.getPhoneNumber().equals(administratorDetails.getPhoneNumber()) 
            && administratorRepository.existsByPhoneNumber(administratorDetails.getPhoneNumber())) {
            throw new RuntimeException("El número de teléfono ya está registrado");
        }

        administrator.setName(administratorDetails.getName());
        administrator.setEmail(administratorDetails.getEmail());
        administrator.setPassword(administratorDetails.getPassword());
        administrator.setPhoneNumber(administratorDetails.getPhoneNumber());

        return administratorRepository.save(administrator);
    }

    public void deleteAdministrator(Long id) {
        if (!administratorRepository.existsById(id)) {
            throw new RuntimeException("Administrador no encontrado");
        }
        administratorRepository.deleteById(id);
    }
} 