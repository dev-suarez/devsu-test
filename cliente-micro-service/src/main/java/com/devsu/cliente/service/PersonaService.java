package com.devsu.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cliente.model.Persona;
import com.devsu.cliente.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {
	private final PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	public List<Persona> findAll() {
		return personaRepository.findAll();
	}

	public Persona savePersona(Persona persona) {
		return personaRepository.save(persona);
	}

	public Optional<Persona> findPersonaById(Long id) {
		return personaRepository.findById(id);
	}

	public Persona updatePersona(Long id, Persona persona) {
		persona.setIdentificacion(id);
		return personaRepository.save(persona);
	}

	public void deletePersona(Long id) {
		personaRepository.deleteById(id);
	}
}
