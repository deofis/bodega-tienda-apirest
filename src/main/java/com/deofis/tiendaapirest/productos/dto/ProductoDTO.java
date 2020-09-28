package com.deofis.tiendaapirest.productos.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    @CsvBindByName
    private String nombre;

    @CsvBindByName
    private String descripcion;

    @CsvBindByName
    private Double precio;

    @CsvBindByName
    private Long categoriaId;

    @CsvBindByName
    private Long marcaId;

    @CsvBindByName
    private Long unidadMedidaId;

    @CsvBindByName
    private Integer stock;

    @CsvBindByName
    private String color;

    @CsvBindByName
    private String talle;

    @CsvBindByName
    private String peso;
}
