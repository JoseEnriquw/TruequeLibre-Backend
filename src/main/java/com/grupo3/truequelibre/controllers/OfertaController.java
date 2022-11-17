package com.grupo3.truequelibre.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo3.truequelibre.interfaces.IOfertaServices;
import com.grupo3.truequelibre.responses.Oferta.CalificarTruequeRequest;
import com.grupo3.truequelibre.services.OfertaService.CreateOfertaRequest;
import com.grupo3.truequelibre.services.OfertaService.FiltrarOfertaRequest;
import com.grupo3.truequelibre.services.OfertaService.UpdateComentarioRequest;
import com.grupo3.truequelibre.services.OfertaService.UpdateFinalizarRequest;
import com.grupo3.truequelibre.services.OfertaService.UpdateOfertaRequest;
import com.grupo3.truequelibre.viewmodels.UpdateComentarVM;
import com.grupo3.truequelibre.viewmodels.UpdateFinalizarVM;
import com.grupo3.truequelibre.viewmodels.UpdateOfertaVM;

@RestController
@RequestMapping(path="api/v1/oferta")
public class OfertaController extends ControllerBase {

	@Autowired
	IOfertaServices service;
	
	@GetMapping
	public ResponseEntity<?> Get(){return Result(service.getAll());}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> Get(@PathVariable Integer id) {return Result( service.getById(id));}
	
	@PostMapping()
	public ResponseEntity<?> Create(@RequestBody CreateOfertaRequest oferta){return Result(service.create(oferta));}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> Update(@PathVariable Integer id,@RequestBody UpdateOfertaVM oferta){return Result(service.update(new UpdateOfertaRequest(id,oferta.id_estado())));}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> Delete(@PathVariable Integer id){return Result(service.delete(id));}
	
	@PostMapping("/filtrar")
	public ResponseEntity<?> Filtrar(@RequestBody FiltrarOfertaRequest filtros) {return Result(service.filtrar(filtros));}
	
	@GetMapping("/estado/{id}")
	public ResponseEntity<?> GetBy(@PathVariable Integer id_oferta){return Result(service.GetByIdEstadoOferta(id_oferta));}
	
	@PutMapping("/estado/{id}")
	public ResponseEntity<?> Update(@PathVariable Integer id,@RequestBody UpdateFinalizarVM request){
		return Result(service.updateFinalizarTrueque(new UpdateFinalizarRequest(id, request.usuario_principal_acepto(),request.usuario_ofertante_acepto())));}
	
	@PutMapping("/comentario/{id}")
	public ResponseEntity<?> UpdateCalifico(@PathVariable Integer id,@RequestBody UpdateComentarVM request){
		return Result(service.updateComentarioTrueque(new UpdateComentarioRequest(id, request.usuario_principal_califico(),request.usuario_ofertante_califico())));}
}
