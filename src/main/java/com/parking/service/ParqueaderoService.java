package com.parking.service;

import com.parking.model.Parqueadero;
import com.parking.repository.ParqueaderoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParqueaderoService {

    @Autowired
    private ParqueaderoRepository parqueaderoRepository;

    public List<Parqueadero> getAllParqueaderos() {
        return parqueaderoRepository.findAll();
    }

    public Optional<Parqueadero> getParqueaderoById(Long id) {
        return parqueaderoRepository.findById(id);
    }

    public Parqueadero createParqueadero(Parqueadero parqueadero) {
        return parqueaderoRepository.save(parqueadero);
    }

    public Parqueadero updateParqueadero(Long id, Parqueadero parqueaderoDetails) {
        Optional<Parqueadero> optionalParqueadero = parqueaderoRepository.findById(id);
        if (optionalParqueadero.isPresent()) {
            Parqueadero parqueadero = optionalParqueadero.get();
            parqueadero.setNombre(parqueaderoDetails.getNombre());
            parqueadero.setDireccion(parqueaderoDetails.getDireccion());
            parqueadero.setCapacidad(parqueaderoDetails.getCapacidad());
            parqueadero.setHoraApertura(parqueaderoDetails.getHoraApertura());
            parqueadero.setHoraCierre(parqueaderoDetails.getHoraCierre());
            parqueadero.setTiposServicios(parqueaderoDetails.getTiposServicios());
            parqueadero.setLogoUrl(parqueaderoDetails.getLogoUrl());
            return parqueaderoRepository.save(parqueadero);
        }
        return null;
    }

    public void deleteParqueadero(Long id) {
        parqueaderoRepository.deleteById(id);
    }
} 