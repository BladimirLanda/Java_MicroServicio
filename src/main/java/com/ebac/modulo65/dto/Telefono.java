package com.ebac.modulo65.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_telefono;

    @Column(name = "tipo_telefono")
    private String tipo_telefono;

    @Column(name = "lada")
    private int lada;

    @Column(name = "numero")
    private String numero;

    /*
    @ManyToOne: Anotación que define una relación de muchos a uno entre Telefono y Usuario. Esto
    significa que cada Telefono pertenece a un único Usuario, pero un Usuario puede tener muchos Telefono.

    @JoinColumn(name = "id_usuario"): Especifica la columna de la tabla Telefono que será la clave
    foránea que hace referencia a la tabla Usuario. En este caso, la columna id_usuario de la tabla
    Telefono se relaciona con la clave primaria de la tabla Usuario.
    */
    @ManyToOne
    @JoinColumn(name = "id_usuario")

    /*
    @JsonBackReference: Es parte de Jackson, y se usa para controlar cómo se serializan las relaciones
    bidireccionales entre entidades cuando las estás convirtiendo a JSON. Se coloca en el lado
    "dependiente" de una relación bidireccional. En este caso, está en el Telefono, lo que
    significa que no se serializa el campo usuario cuando serializas un objeto Telefono.
    Este comportamiento evita una recursión infinita entre las entidades Usuario y Telefono
    cuando ambas se referencian mutuamente.

    Flujo de serialización:
    -Cuando serializas un Usuario, Jackson serializa la lista de Telefonos sin entrar en un ciclo infinito.
    -Cuando serializas un Telefono, Jackson no incluye el campo usuario dentro de cada Telefono,
    evitando que la relación se serialice recursivamente.

    Nota: La referencia a Usuario está representada como un objeto completo (en lugar de solo un id_usuario).
    Por lo tanto, un Post JSON, debes pasar un objeto Usuario completo con el campo id_usuario dentro.
    */
    @JsonBackReference
    private Usuario usuario;

}
