package com.grupo3.truequelibre.controllers;

import java.util.List;
import com.grupo3.truequelibre.entity.*;
import com.grupo3.truequelibre.interfaces.IGenericServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/estado")
public class EstadoController {

	@Autowired
	private IGenericServices<Estado,Integer> service;
	
	@GetMapping()
	public List<Estado> Get(){return service.getAll();}
	
	@GetMapping("/{id}")
	public Estado Get(@PathVariable Integer id){return service.getById(id);}
	
	@PostMapping()
	public void Create(@RequestBody Estado estado){service.save(estado);}
	
	@PutMapping()
	public void Update(@RequestBody Estado estado){service.save(estado);}
	
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Integer id){service.delete(id);}
}
