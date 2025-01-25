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

    //@ManyToOne: Especifica el tipo de relación con la Llave Foranea (Muchos ⮕ Uno)
    //@JoinColumn: Especifica la unión con la Llave Foranea
    @ManyToOne
    @JoinColumn(name = "id_usuario")

    //@JsonBackReference: Referencia de no retorno JSON
    //Cancela la extracción de la relación correspondiente a la Llave Foranea (JOIN usuario + WHERE id_usuario)
    @JsonBackReference
    private Usuario usuario;

}
