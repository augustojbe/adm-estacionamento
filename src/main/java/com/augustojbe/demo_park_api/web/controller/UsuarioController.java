package com.augustojbe.demo_park_api.web.controller;


import com.augustojbe.demo_park_api.entity.Usuario;
import com.augustojbe.demo_park_api.service.UsuarioService;
import com.augustojbe.demo_park_api.web.dto.UsuarioCreateDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioResponseDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioSenhaDto;
import com.augustojbe.demo_park_api.web.dto.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto creteDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(creteDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable("id") Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(user));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id, @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id,
                dto.getSenhaAtual(),
                dto.getNovaSenha(),
                dto.getConfirmaSenha());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
