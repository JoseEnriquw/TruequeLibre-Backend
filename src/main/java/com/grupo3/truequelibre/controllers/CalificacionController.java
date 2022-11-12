package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo3.truequelibre.interfaces.ICalificacionServices;
import com.grupo3.truequelibre.services.CalificacionService.CreateCalificacionRequest;
import com.grupo3.truequelibre.services.CalificacionService.FiltroCalificacionRequest;

@RestController
@RequestMapping(path="api/v1/calificacion")
public class CalificacionController extends ControllerBase{
	
	@Autowired
	ICalificacionServices service;
	
	@GetMapping("/{usuario}")
	public ResponseEntity<?> Get(@PathVariable Integer usuario){return Result(service.getAll(usuario));}
	
	@PostMapping
	public ResponseEntity<?> Create(@RequestBody CreateCalificacionRequest request){return Result(service.create(request));}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> GetByTipo(@RequestBody FiltroCalificacionRequest tipoCalificacion){return Result(service.getByTipo(tipoCalificacion));}
}

