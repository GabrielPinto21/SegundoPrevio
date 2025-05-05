package com.example.demo.controlador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entidades.Manga;
import com.example.demo.entidades.Usuario;
import com.example.demo.repository.MangaRepository;
import com.example.demo.repository.UsuarioRepository;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios y sus mangas favoritos")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MangaRepository mangaRepository;

    // 6. Relación de mangas favoritos de un usuario
    @GetMapping("/{username}/favoritos")
    @Operation(
        summary = "Obtener favoritos del usuario",
        description = "Retorna una lista de mangas que están en los favoritos del usuario"
    )
    public ResponseEntity<Set<Manga>> getFavoritos(@PathVariable String username) {
        Optional<Usuario> usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        return usuario.map(value -> ResponseEntity.ok(value.getFavoritos()))
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Eliminar un manga de los favoritos de un usuario
    @DeleteMapping("/{username}/favoritos/{mangaId}")
    @Operation(
        summary = "Eliminar manga de favoritos",
        description = "Elimina un manga de la lista de favoritos del usuario"
    )
    public ResponseEntity<Void> deleteFavorito(@PathVariable String username, @PathVariable int mangaId) {
        Optional<Usuario> usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        Optional<Manga> manga = mangaRepository.findById(mangaId);

        if (usuario.isPresent() && manga.isPresent()) {
            usuario.get().getFavoritos().remove(manga.get());
            usuarioRepository.save(usuario.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 8. Agregar un manga a los favoritos de un usuario
    @PostMapping("/{username}/favoritos")
    @Operation(
        summary = "Agregar manga a favoritos",
        description = "Agrega un manga existente a la lista de favoritos del usuario"
    )
    public ResponseEntity<Void> addFavorito(@PathVariable String username, @RequestBody Manga manga) {
        Optional<Usuario> usuario = usuarioRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();

        Optional<Manga> mangaBD = mangaRepository.findById(manga.getId());

        if (usuario.isPresent() && mangaBD.isPresent()) {
            usuario.get().getFavoritos().add(mangaBD.get());
            usuarioRepository.save(usuario.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
