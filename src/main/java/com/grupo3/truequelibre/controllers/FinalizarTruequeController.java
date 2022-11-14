package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.grupo3.truequelibre.interfaces.IFinalizarTruequeServices;
import com.grupo3.truequelibre.services.FinalizarTruequeService.CreateFinalizarTruequeRequest;
import com.grupo3.truequelibre.services.FinalizarTruequeService.UpdateFinalizarRequest;

@RestController
@RequestMapping(path="api/v1/finalizartrueque")
public class FinalizarTruequeController extends ControllerBase {
	@Autowired
	IFinalizarTruequeServices service;
	
	@GetMapping
	public ResponseEntity<?> Get(@PathVariable Integer id_oferta){return Result(service.GetByIdOferta(id_oferta));}
	
	@PostMapping()
	public ResponseEntity<?> Create(@RequestBody CreateFinalizarTruequeRequest request){return Result(service.createfinalizarTrueque(request));}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> Update(@PathVariable UpdateFinalizarRequest request){return Result(service.update(request));}
	

}
