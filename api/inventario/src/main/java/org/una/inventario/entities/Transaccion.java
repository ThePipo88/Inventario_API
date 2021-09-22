package org.una.inventario.entities;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transacciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaccion implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolId;

    @Column(name = "objeto", length = 100)
    private String objeto;

    @ManyToOne
    @JoinColumn(name="usuarios_id")
    private Usuario usuario;

    @Column(name = "accion", length = 100)
    private String accion;

    @Column(name = "fecha_creacion", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaCreacion;

    @Column(name = "fecha_finalizado")
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizado;

    private static final long serialVersionUID = 1L;

    @PrePersist
    public void prePersist() {
        fechaCreacion = new Date();
        fechaFinalizado = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        fechaFinalizado = new Date();
    }
}
