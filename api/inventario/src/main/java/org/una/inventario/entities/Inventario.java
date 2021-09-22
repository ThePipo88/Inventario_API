package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Inventario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "responsable", nullable = false)
    private Long responsable;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column
    private boolean estado;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventario")
    private List<Valuacion> valuaciones = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
    }
}
