package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/producto")
public class CrudController {

	public static final Logger logger = LoggerFactory.getLogger(CrudController.class);
	
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/todos/")
	public ResponseEntity<String>  listProductos(){
		final String uri = "http://localhost:8765/producto/productos/";
		
		return restTemplate.getForEntity(uri, String.class);
	}
	
	@GetMapping("/prod/{id}")
	public ResponseEntity<?> getProd(@PathVariable("id") Long id){
		final String uri = "http://localhost:8765/producto/productos/" + id;
		
		return restTemplate.getForEntity(uri, String.class);
	}

	@PostMapping("/prod/")
	public ResponseEntity<?> createProd(@RequestBody Producto producto){
		final String uri = "http://localhost:8765/producto/productos/";
		return restTemplate.postForEntity(uri, producto, Producto.class);
	}
	
	@PutMapping("/prod/{id}")
	public void updateProd(@PathVariable("id") long id, @RequestBody Producto producto){
		final String uri = "http://localhost:8765/producto/productos/" + id;
		/*
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("idproducto", mensaje.getId().toString());		
		params.put("precioLista", mensaje.getPreLi().toString());
		params.put("descripcion", mensaje.getDes());
		params.put("stock", mensaje.getSt().toString());
		params.put("nombre", mensaje.getNo());
		*/
		logger.info("mensaje: {}", producto);	
		
		restTemplate.put(uri, producto);
		
	}
	
	@DeleteMapping("/prod/{id}")
	public void updateProd(@PathVariable("id") long id){
		final String uri = "http://localhost:8765/producto/productos/" + id;
		restTemplate.delete(uri);
	}
}



