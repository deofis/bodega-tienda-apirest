package com.deofis.tiendaapirest.perfiles.dto;

import com.deofis.tiendaapirest.clientes.domain.Cliente;
import com.deofis.tiendaapirest.perfiles.domain.Carrito;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerfilDTO implements Serializable {

    private final static long serialVersionUID = 1L;

    private String usuario;
    private Cliente cliente;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Carrito carrito;
}
