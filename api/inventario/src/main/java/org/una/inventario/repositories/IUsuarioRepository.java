package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Departamento;
import org.una.inventario.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByCedulaAndPasswordEncriptado(String cedula, String passwordEncriptado);

    public Usuario findByCedula(String cedula);

    public List<Usuario> findByCedulaContaining(String cedula);

    public List<Usuario> findByNombreCompletoContainingIgnoreCase(String nombreCompleto);

    public List<Usuario> findByDepartamentoId(Long id);

    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    public Usuario findJefeByDepartamento(Long id);


    @Query("select u from Usuario u where UPPER(u.nombreCompleto) like CONCAT('%',UPPER(:nombreCompleto),'%')")
    public Usuario findNombreCompletoWithLikeSQL(@Param("nombreCompleto")String nombreCompleto);

}
