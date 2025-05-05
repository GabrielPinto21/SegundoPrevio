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

import com.example.demo.entidades.Manga;
import com.example.demo.repository.MangaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mangas")
@Tag(name = "Mangas", description = "Operaciones relacionadas con mangas")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    @GetMapping
    @Operation(summary = "Obtener todos los mangas", description = "Retorna una lista de todos los mangas")
    public List<Manga> getAllMangas() {
        return mangaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener manga por ID", description = "Retorna un manga específico según su ID")
    public ResponseEntity<Manga> getMangaById(@PathVariable Long id) {
        return mangaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo manga", description = "Crea un nuevo manga en la base de datos")
    public Manga createManga(@RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar manga", description = "Actualiza la información de un manga existente")
    public ResponseEntity<Manga> updateManga(@PathVariable Long id, @RequestBody Manga manga) {
        return mangaRepository.findById(id).map(existing -> {
            manga.setId(id);
            return ResponseEntity.ok(mangaRepository.save(manga));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar manga", description = "Elimina un manga específico por su ID")
    public ResponseEntity<Void> deleteManga(@PathVariable Long id) {
        if (mangaRepository.existsById(id)) {
            mangaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


