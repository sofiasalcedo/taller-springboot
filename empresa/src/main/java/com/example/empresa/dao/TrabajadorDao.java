package com.example.empresa.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.empresa.entity.Trabajador;

public interface TrabajadorDao extends CrudRepository<Trabajador, Long> {

	
}