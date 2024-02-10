package com.devsu.cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsu.cliente.model.Persona;
import com.devsu.cliente.repository.PersonaRepository;

/**
 * Servicio para la gestión de personas.
 */
@Service
@Transactional
public class PersonaService {
	private final PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	/**
	 * Obtiene todas las personas registradas.
	 * @return Lista de todas las personas.
	 */
	public List<Persona> findAll() {
		return personaRepository.findAll();
	}

	/**
	 * Guarda una nueva persona en la base de datos.
	 * @param persona Persona a guardar.
	 * @return Persona guardada.
	 */
	public Persona savePersona(Persona persona) {
		return personaRepository.save(persona);
	}

	/**
	 * Busca una persona por su identificador.
	 * @param id Identificador de la persona.
	 * @return Persona encontrada o un Optional vacío si no se encuentra.
	 */
	public Optional<Persona> findPersonaById(Long id) {
		return personaRepository.findById(id);
	}

	/**
	 * Actualiza una persona existente.
	 * @param id Identificador de la persona a actualizar.
	 * @param persona Datos actualizados de la persona.
	 * @return Persona actualizada.
	 */
	public Persona updatePersona(Long id, Persona persona) {
		persona.setIdentificacion(id);
		return personaRepository.save(persona);
	}

	/**
	 * Elimina una persona por su identificador.
	 * @param id Identificador de la persona a eliminar.
	 */
	public void deletePersona(Long id) {
		personaRepository.deleteById(id);
	}
}
