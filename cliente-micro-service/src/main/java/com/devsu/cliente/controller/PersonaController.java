package com.devsu.cliente.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.cliente.model.Persona;
import com.devsu.cliente.service.PersonaService;

/**
 * Controlador para la gestión de personas.
 */
@RestController
@RequestMapping("/personas")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    /**
     * Obtiene todas las personas registradas.
     * 
     * @return Lista de todas las personas.
     */
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.findAll();
    }

    /**
     * Crea una nueva persona.
     * 
     * @param persona Datos de la persona a crear.
     * @return Persona creada.
     */
    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }

    /**
     * Obtiene una persona específica por su identificador.
     * 
     * @param id Identificador de la persona.
     * @return ResponseEntity con la persona encontrada o un estado de no
     *         encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        return personaService.findPersonaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una persona existente.
     * 
     * @param id      Identificador de la persona a actualizar.
     * @param persona Datos actualizados de la persona.
     * @return Persona actualizada.
     */
    @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        return personaService.updatePersona(id, persona);
    }

    /**
     * Elimina una persona por su identificador.
     * 
     * @param id Identificador de la persona a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
    }
}