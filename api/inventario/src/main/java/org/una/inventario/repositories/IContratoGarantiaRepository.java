package org.una.inventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.inventario.entities.Activo;
import org.una.inventario.entities.Categoria;
import org.una.inventario.entities.ContratoGarantia;

import java.util.List;

@Repository
public interface IContratoGarantiaRepository extends JpaRepository<ContratoGarantia, Long> {

    public List<ContratoGarantia> findByActivo(Long id);

    public List<ContratoGarantia> findByEstado(boolean estado);

    public List<ContratoGarantia> findByCosto(double costo);
}
