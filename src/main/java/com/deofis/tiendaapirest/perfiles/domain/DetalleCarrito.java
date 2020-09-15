package com.deofis.tiendaapirest.perfiles.domain;

import com.deofis.tiendaapirest.productos.domain.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "carrito_detalles")
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCarrito implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto producto;
    private Integer cantidad;

    public Double getSubtotal() {
        return this.cantidad.doubleValue() * this.producto.getPrecio();
    }
}
