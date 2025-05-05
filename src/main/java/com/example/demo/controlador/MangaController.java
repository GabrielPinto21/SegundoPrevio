package com.example.demo.controlador;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entidades.Manga;
import com.example.demo.service.MangaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/mangas")
public class MangaController {

	@Autowired
	private MangaService mServ;
	
	@GetMapping("/listar")
	public List<Manga> listarMangas() {
		return mServ.listMangas();
	}
	
	@GetMapping("/{id}")
	public Manga getMangaById(@PathVariable int id) {
		return mServ.getMangaById(id);
	}
	
	@PostMapping("/crear")
	public Manga crearManga(@RequestBody Manga manga) {
	    return mServ.crearManga(manga);
	}

	@PutMapping("/actualizar/{id}")
	public Manga actualizarManga(@PathVariable int id, @RequestBody Manga manga) {
	    return mServ.actualizarManga(id, manga);
	}

	@DeleteMapping("/eliminar/{id}")
	public void eliminarManga(@PathVariable int id) {
	    mServ.eliminarManga(id);
	}

}