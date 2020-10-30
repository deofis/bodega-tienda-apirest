package com.deofis.tiendaapirest.productos.services;

import com.deofis.tiendaapirest.productos.domain.Categoria;
import com.deofis.tiendaapirest.productos.domain.Subcategoria;
import com.deofis.tiendaapirest.productos.exceptions.ProductoException;
import com.deofis.tiendaapirest.productos.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public Categoria crear(Categoria categoria) {
        Categoria categoriaNueva = Categoria.builder()
                .nombre(categoria.getNombre())
                .build();

        return this.categoriaRepository.save(categoriaNueva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> obtenerCategorias() {
        return this.categoriaRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria obtenerCategoria(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new ProductoException("Categoría no existente con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subcategoria> obtenerSubcategorias(Long categoriaId) {
        Categoria categoria = this.categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ProductoException("Categoria no existente con id: " + categoriaId));


        return categoria.getSubcategorias();
    }

    @Override
    @Transactional
    public Categoria actualizar(Categoria categoria, Long id) {
        Categoria categoriaActual = this.obtenerCategoria(id);

        categoriaActual.setNombre(categoria.getNombre());

        return this.categoriaRepository.save(categoriaActual);
    }
}
