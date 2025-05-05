package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UsuarioDTO;
import com.example.demo.entidades.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO createUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            throw new RuntimeException("El username ya está en uso");
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(savedUsuario);
    }

    public UsuarioDTO updateUsuario(Integer id, Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        existingUsuario.setUsername(usuario.getUsername());
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setPassword(usuario.getPassword());
        Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
        return new UsuarioDTO(updatedUsuario);
    }

    public void deleteUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
