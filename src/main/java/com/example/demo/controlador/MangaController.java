package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.MangaDTO;
import com.example.demo.entidades.Manga;
import com.example.demo.service.MangaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/mangas")
public class MangaController {

	@Autowired
	private MangaService mServ;
	
	@GetMapping()
	public List<Manga> listarMangas() {
		return mServ.listMangas();
	}
	
	@GetMapping("/{id}")
	public MangaDTO getMangaById(@PathVariable int id) {
		return mServ.getMangaById(id);
	}
	
	@PostMapping
    public void createManga(@RequestBody MangaDTO dto) {
        mServ.addManga(dto);
    }

    // Editar un manga existente
    @PutMapping("/{id}")
    public void updateManga(@PathVariable Integer id, @RequestBody MangaDTO dto) {
        mServ.updateManga(id, dto);
    }

    // Eliminar un manga
    @DeleteMapping("/{id}")
    public void deleteManga(@PathVariable Integer id) {
        mServ.deleteManga(id);
    }
}