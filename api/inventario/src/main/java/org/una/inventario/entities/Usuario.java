package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(length = 100, name = "password_encriptado")
    private String passwordEncriptado;

    @Column(length = 25, unique = true)
    private String cedula;

    @ManyToOne
    @JoinColumn(name="departamentos_id")
    private Departamento departamento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Transaccion> tranacciones = new ArrayList<>();

    @Column
    private boolean estado;

    @ManyToOne
    @JoinColumn(name="roles_id")
    private Rol rol;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaModificacion;

    @Column(name = "es_jefe")
    private boolean esJefe;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        estado=true;
        esJefe=false;
        fechaRegistro = new Date();
        fechaModificacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaModificacion = new Date();
    }

}
