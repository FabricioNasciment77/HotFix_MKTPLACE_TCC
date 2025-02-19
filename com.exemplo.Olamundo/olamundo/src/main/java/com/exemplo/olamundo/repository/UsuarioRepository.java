package com.exemplo.olamundo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.exemplo.olamundo.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
