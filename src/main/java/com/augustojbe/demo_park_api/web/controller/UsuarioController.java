package com.augustojbe.demo_park_api.web.controller;


import com.augustojbe.demo_park_api.entity.Usuario;
import com.augustojbe.demo_park_api.service.UsuarioService;
import com.augustojbe.demo_park_api.web.dto.UsuarioCreateDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioResponseDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioSenhaDto;
import com.augustojbe.demo_park_api.web.dto.mapper.UsuarioMapper;
import com.augustojbe.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, eição e leitura de um usuário.")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
    responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class ))),
            @ApiResponse(responseCode = "409", description = "Usuário e-mail já cadastrado no sistema",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class ))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))

    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto creteDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(creteDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));

    }

    @Operation(summary = "Lista todos os usuarios", description = "Lista todos os usuarios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios listados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class ))),
            })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(usuarios));
    }

    @Operation(summary = "Recuperar um usuário pelo Id", description = "Recurso para recuperar o usuário pelo Id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDto.class ))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))

            })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable("id") Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.toDto(user));

    }

    @Operation(summary = "Atualizar Senha", description = "Atualizar Senha",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class ))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class ))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))

            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable("id") Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id,
                dto.getSenhaAtual(),
                dto.getNovaSenha(),
                dto.getConfirmaSenha());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
