package org.una.inventario.entities;

import lombok.*;
import org.una.inventario.dto.CategoriaDTO;
import org.una.inventario.dto.MarcaDTO;
import org.una.inventario.dto.ProveedorDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Activo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "continente")
    private Long continente;

    @Column
    private boolean estado;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @ManyToOne
    @JoinColumn(name="categorias_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="marcas_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name="proveedores_id")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name="activos_id")
    private Activo activoId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<Valuacion> valuaciones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activoId")
    private List<Activo> activos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<ActivoAsignado> activosAsignados = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<ContratoGarantia> contratosGarantias = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activo")
    private List<Alerta> alertas = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }

}
