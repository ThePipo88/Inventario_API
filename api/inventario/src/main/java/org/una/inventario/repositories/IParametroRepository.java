package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Marca;
import org.una.inventario.entities.Parametro;

import java.util.List;

@Repository
public interface IParametroRepository extends JpaRepository<Parametro, Long> {

    public List<Parametro> findByValor(String valor);

    public List<Parametro> findByNombre(String nombre);

    public List<Parametro> findByEstado(Boolean estado);

}
