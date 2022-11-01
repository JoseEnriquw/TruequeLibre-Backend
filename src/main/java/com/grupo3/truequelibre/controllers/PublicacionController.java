package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo3.truequelibre.interfaces.IPublicacionServices;
import com.grupo3.truequelibre.services.EstadoService.CreateEstado;
import com.grupo3.truequelibre.services.PublicacionService.CreatePublicacionRequest;

@RestController
@RequestMapping(path="api/v1/publicacion")
public class PublicacionController extends ControllerBase {
	@Autowired
	IPublicacionServices service;
	
	@GetMapping
	public ResponseEntity Get(){return Result(service.getAll());}
	
	@GetMapping("/{id}")
	public ResponseEntity Get(@PathVariable Integer id) {return Result( service.getById(id));}
	
	@PostMapping()
	public ResponseEntity Create(@RequestBody CreatePublicacionRequest request){return Result(service.create(request));}
}
