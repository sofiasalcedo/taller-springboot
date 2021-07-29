package com.example.empresa.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.empresa.dao.TrabajadorDao;
import com.example.empresa.entity.Trabajador;

@RestController
public class Controller {
	
	@Autowired
	private TrabajadorDao trabajadorDao;
	
	@GetMapping("/trabajadores")
	public Iterable<Trabajador> getTrabajadores(){
		return trabajadorDao.findAll();
	}
	
	@GetMapping("/ver/{id}")
	public Optional<Trabajador> detalle(@PathVariable Long id) {
		return trabajadorDao.findById(id);
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public Trabajador guardar(Trabajador trabajador) {
		if (trabajador != null) {
			return trabajadorDao.save(trabajador);
		}
		return new Trabajador();
	}
	
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.PUT)
	public String editar(Trabajador trabajador, @PathVariable Long id) {
		if (trabajadorDao.findById(id).isPresent()) {
			Trabajador trabajadorActual = new Trabajador();
			trabajadorActual.setId(trabajador.getId());
			trabajadorActual.setNombre(trabajador.getNombre());
			trabajadorActual.setApellido(trabajador.getApellido());
			trabajadorActual.setTelefono(trabajador.getTelefono());
			trabajadorActual.setEmail(trabajador.getEmail());
			trabajadorDao.save(trabajadorActual);
			return "Información actualizada";
		}
		return "Error al actualizar información";
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
	public String eliminar(@PathVariable Long id) {
		if (trabajadorDao.findById(id).isPresent()) {
			trabajadorDao.deleteById(id);
			return "Información de trabajador eliminada correctamente";
		}
		return "Error! La información del trabajador no existe";
	}
}