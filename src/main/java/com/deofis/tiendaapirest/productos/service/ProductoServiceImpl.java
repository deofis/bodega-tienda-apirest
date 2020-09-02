package com.deofis.tiendaapirest.productos.service;

import com.deofis.tiendaapirest.productos.domain.Producto;
import com.deofis.tiendaapirest.productos.exception.ProductoException;
import com.deofis.tiendaapirest.productos.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto crear(Producto producto) {

        Producto nuevoProducto = Producto.builder()
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .activo(true)
                .precio(producto.getPrecio())
                .fechaCreacion(new Date())
                .foto(null)
                .destacado(producto.isDestacado())
                .categoria(producto.getCategoria())
                .marca(producto.getMarca())
                .build();

        return this.productoRepository.save(nuevoProducto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> obtenerProductos() {
        return this.productoRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto obtenerProducto(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Producto no existente con ID: " + id));
    }

    // Issue --> Si no pasas el objeto completo, los atributos que no pases se setean en null.
    // Fix --> @Valid en controller.
    @Override
    @Transactional
    public Producto actualizar(Producto producto, Long id) {

        Producto productoActual = this.obtenerProducto(id);

        productoActual.setNombre(producto.getNombre());
        productoActual.setDescripcion(producto.getDescripcion());
        productoActual.setActivo(producto.isActivo());
        productoActual.setPrecio(producto.getPrecio());
        productoActual.setFechaCreacion(producto.getFechaCreacion());
        productoActual.setDestacado(producto.isDestacado());
        productoActual.setCategoria(producto.getCategoria());
        productoActual.setMarca(producto.getMarca());

        return this.productoRepository.save(productoActual);
    }

    @Override
    @Transactional
    public void darDeBaja(Long id) {
        Producto producto = this.obtenerProducto(id);

        if (!producto.isActivo()) {
            throw new ProductoException("Este producto ya está dado de baja");
        }

        producto.setActivo(false);
        this.productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void darDeAlta(Long id) {
        Producto producto = this.obtenerProducto(id);

        if (producto.isActivo()) {
            throw new ProductoException("Este producto ya está activo");
        }

        producto.setActivo(true);
        this.productoRepository.save(producto);
    }
}
