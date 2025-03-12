package com.ebac.modulo65.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//@Data:Lombok Annotation
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

    /*
    @OneToMany: La anotación se usa para establecer una relación uno a muchos entre dos entidades.
    En este caso, se tiene una relación uno a muchos entre la entidad Usuario y la entidad Telefono,
    es decir, un usuario puede tener muchos teléfonos.

    -mappedBy: Este atributo indica que la relación es bidireccional y especifica cuál es el campo
    en la clase "relacionada" (en este caso Telefono) que maneja la relación. En este caso,
    el campo usuario en la entidad Telefono es el que maneja la relación. Esto es importante porque,
    cuando usas relaciones bidireccionales, es necesario especificar cuál de las dos entidades tiene la
    responsabilidad de manejar la relación.
    "Esto le dice a JPA que la relación OneToMany se mapea en la clase Telefono por el campo
    usuario, es decir, Telefono tiene un campo Usuario que actúa como "dueño" de la relación."

    -cascade: Esto especifica el comportamiento de propagación de las operaciones de persistencia.
    En este caso, CascadeType.ALL significa que todas las operaciones de persistencia (como agregar,
    actualizar, borrar, etc.) se propagarán desde la entidad Usuario hacia la entidad Telefono.
    "Las operaciones realizadas en un Usuario también se propagarán en los Telefono asociados a ese Usuario"
    */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)

    /*
    @JsonManagedReference: La anotación es parte de Jackson, una librería que se usa para serializar y
    deserializar objetos Java a JSON (y viceversa). Específicamente, se usa para manejar relaciones
    bidireccionales y evitar problemas de recursión infinita al serializar las entidades relacionadas.
    La anotación @JsonManagedReference se coloca en el lado "gestionado" de la relación (en este caso,
    en el Usuario), para que Jackson maneje la relación correctamente y no entre en un ciclo infinito.

    -Se usa @JsonManagedReference para indicar que la relación "activa" o "dueña" es la que está aquí.
    -En la entidad "dependiente", normalmente se usará la anotación complementaria @JsonBackReference
    para evitar que Jackson entre en un ciclo infinito cuando se deserialicen las entidades.
    */
    @JsonManagedReference
    private List<Telefono> telefonos = new ArrayList<>();

}
