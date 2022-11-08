package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo3.truequelibre.interfaces.ICategoriaServices;
import com.grupo3.truequelibre.services.CategoriaService.CreateCategoriaRequest;

@RestController
@RequestMapping(path="/categoria")
public class CategoriaController extends ControllerBase {
	@Autowired
	private ICategoriaServices service;
	
	@GetMapping()
	public ResponseEntity<?> Get(){return Result(service.getAll());}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> Get(@PathVariable Integer id) {return Result( service.getById(id));}
	
	@PostMapping()
	public ResponseEntity<?> Create(@RequestBody CreateCategoriaRequest estado){return Result(service.create(estado));}
	
	@GetMapping("/cargarImagenes")
	public ResponseEntity<?> cargarImagenes(){return Result(service.cargarImagenes());}
}
