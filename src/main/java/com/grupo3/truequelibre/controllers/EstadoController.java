package com.grupo3.truequelibre.controllers;

import com.grupo3.truequelibre.interfaces.IEstadoServices;
import com.grupo3.truequelibre.services.EstadoService.CreateEstado;
import com.grupo3.truequelibre.services.EstadoService.UpdateEstado;
import com.grupo3.truequelibre.viewmodels.UpdateEstadoVM;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class EstadoController extends ControllerBase {

	@Autowired
	private IEstadoServices service;
	
	@GetMapping()
	public ResponseEntity Get(){return Result(service.getAll());}
	
	@GetMapping("/{id}")
	public ResponseEntity Get(@PathVariable Integer id) {return Result( service.getById(id));}
	
	@PostMapping()
	public ResponseEntity Create(@RequestBody CreateEstado estado){return Result(service.create(estado));}
	
	@PutMapping("/{id}")
	public ResponseEntity Update(@PathVariable Integer id,@RequestBody @Valid UpdateEstadoVM estado){return Result( service.update( new  UpdateEstado(id,estado.descripcion())));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity Delete(@PathVariable Integer id){return Result(service.delete(id));}
}
