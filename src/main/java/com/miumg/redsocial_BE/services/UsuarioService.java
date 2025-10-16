package com.miumg.redsocial_BE.services;
import com.miumg.redsocial_BE.dtos.UsuarioAmigoDTO;
import com.miumg.redsocial_BE.dtos.UsuarioDTO;
import com.miumg.redsocial_BE.dtos.UsuarioResponseDTO;
import com.miumg.redsocial_BE.models.SolicitudAmistad;
import com.miumg.redsocial_BE.models.Usuario;
import com.miumg.redsocial_BE.repositories.SolicitudAmistadRepository;
import com.miumg.redsocial_BE.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SolicitudAmistadRepository solicitudAmistadRepository;

    private static final Integer STATUS_ACEPTADO = 2;

    public String authenticateUser(String username, String password) {
        Optional<Usuario> userOpt = usuarioRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return "USER_NOT_FOUND";
        }

        Usuario user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            return "INVALID_PASSWORD";
        }

        if (user.getStatus() == 0) {
            return "USER_DENIED";
        }

        return "AUTH_SUCCESS_" + user.getId();
    }

    public List<UsuarioResponseDTO> getUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getUsername(), u.getEmail(), u.getName(), u.getStatus()))
                .collect(Collectors.toList());
    }

    public Optional<Usuario> getById(Integer id){
        return usuarioRepository.findById(id);
    }

    public List<Usuario> searchUser(String searchText) {
        List<Usuario> result = new ArrayList<>();

        result.addAll(usuarioRepository.findByUsernameContainingIgnoreCase(searchText));
        result.addAll(usuarioRepository.findByNameContainingIgnoreCase(searchText));

        return result.stream()
                .distinct()
                .toList();
    }

    public Usuario saveUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setName(usuarioDTO.getName());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setStatus(1);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateByID(UsuarioDTO usuarioDTO, Integer id ){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setName(usuarioDTO.getName());
        usuario.setStatus(usuarioDTO.getStatus());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Boolean deleteUsuario(Integer id){
        try {
            usuarioRepository.deleteById(id);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

    public List<UsuarioAmigoDTO> getAmigos(Integer userId) {
        // Solicitudes donde soy remitente y ya aceptaron
        List<SolicitudAmistad> comoRemitente =
                solicitudAmistadRepository.findByUsuarioRemitenteIdAndStatusId(userId, STATUS_ACEPTADO);

        // Solicitudes donde soy destinatario y ya acepté
        List<SolicitudAmistad> comoDestinatario =
                solicitudAmistadRepository.findByUsuarioDestinatarioIdAndStatusId(userId, STATUS_ACEPTADO);

        return Stream.concat(
                        comoRemitente.stream().map(s -> new UsuarioAmigoDTO(
                                s.getUsuarioDestinatario().getId(),
                                s.getUsuarioDestinatario().getUsername(),
                                s.getUsuarioDestinatario().getEmail(),
                                s.getUsuarioDestinatario().getName()
                        )),
                        comoDestinatario.stream().map(s -> new UsuarioAmigoDTO(
                                s.getUsuarioRemitente().getId(),
                                s.getUsuarioRemitente().getUsername(),
                                s.getUsuarioRemitente().getEmail(),
                                s.getUsuarioRemitente().getName()
                        ))
                )
                .collect(Collectors.toList());
    }

    public void eliminarAmigo(Integer userId, Integer friendId) {
        Optional<SolicitudAmistad> solicitud =
                solicitudAmistadRepository.findByUsuarioRemitenteIdAndUsuarioDestinatarioIdAndStatusId(userId, friendId, STATUS_ACEPTADO)
                        .or(() -> solicitudAmistadRepository.findByUsuarioRemitenteIdAndUsuarioDestinatarioIdAndStatusId(friendId, userId, STATUS_ACEPTADO));

        if (solicitud.isEmpty()) {
            throw new RuntimeException("No existe amistad entre los usuarios");
        }

        solicitudAmistadRepository.delete(solicitud.get());
    }


}
