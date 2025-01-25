package com.ebac.modulo65.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
@Data: Notación Lombok de generación de propiedades a clases Java
Es una combinación de las anotaciones @Getter, @Setter, @EqualsAndHashCode,
@NoArgsConstructor, @AllArgsConstructor y generando el método toString() por default.
 */
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "edad")
    private int edad;

    //@OneToMany: Especifica el tipo de relación siendo Llave Foranea (Uno ⮕ Muchos)
    //mappedBy: Mapeo de la Entity(tabla) a la tabla destino.
    //cascade: Afectación de su relación en otras tablas como Llave Foranea (update/delete)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)

    //@JsonManagedReference: Gestor de Referencia JSON
    //Extrae todas las relaciones correspondientes a la Llave Foranea (JOIN telefono + WHERE id_usuario)
    @JsonManagedReference
    private List<Telefono> telefonos = new ArrayList<>();

}
