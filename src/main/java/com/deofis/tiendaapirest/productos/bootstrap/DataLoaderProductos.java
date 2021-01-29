package com.deofis.tiendaapirest.productos.bootstrap;

import com.deofis.tiendaapirest.productos.domain.*;
import com.deofis.tiendaapirest.productos.exceptions.ProductoException;
import com.deofis.tiendaapirest.productos.repositories.*;
import com.deofis.tiendaapirest.productos.services.SkuService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
@Profile({"devbodega", "qabodega"})
public class DataLoaderProductos implements CommandLineRunner {

    private final UnidadMedidaRepository unidadMedidaRepository;
    private final MarcaRepository marcaRepository;

    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;
    private final ProductoRepository productoRepository;

    private final SkuService skuService;


    @Override
    public void run(String... args) {

        if (this.unidadMedidaRepository.findByNombre("Unidad").isEmpty()) {
            UnidadMedida unidad = UnidadMedida.builder()
                    .nombre("Unidad")
                    .codigo("U").build();

            try {
                this.unidadMedidaRepository.save(unidad);
            } catch (DataIntegrityViolationException e) {
                throw new ProductoException(e.getMessage());
            }
        }

        if (this.marcaRepository.findAll().size() == 0) {
            List<Marca> marcas = new ArrayList<>();

            Marca terraCamiare = Marca.builder().nombre("Terra Camiare").build();
            marcas.add(terraCamiare);

            Marca indamaChardonnay = Marca.builder().nombre("Indama Chardonnay").build();
            marcas.add(indamaChardonnay);

            Marca alamos = Marca.builder().nombre("Alamos").build();
            marcas.add(alamos);

            this.marcaRepository.saveAll(marcas);
        }

        // Propiedades y valores.

        PropiedadProducto varietal = PropiedadProducto.builder()
                .nombre("Varietal").variable(true).valores(new ArrayList<>()).build();
        ValorPropiedadProducto malbec = ValorPropiedadProducto.builder()
                .valor("Malbec").build();
        ValorPropiedadProducto ancellotta = ValorPropiedadProducto.builder()
                .valor("Ancellotta").build();
        ValorPropiedadProducto blend = ValorPropiedadProducto.builder()
                .valor("Blend").build();
        ValorPropiedadProducto cabernet = ValorPropiedadProducto.builder()
                .valor("Cabernet Sauvignon").build();
        ValorPropiedadProducto syrah = ValorPropiedadProducto.builder()
                .valor("Syrah").build();
        ValorPropiedadProducto merlot = ValorPropiedadProducto.builder()
                .valor("Merlot").build();
        ValorPropiedadProducto chardonnay = ValorPropiedadProducto.builder()
                .valor("Chardonnay").build();
        ValorPropiedadProducto sauvignon = ValorPropiedadProducto.builder()
                .valor("Sauvignong Blanc").build();
        ValorPropiedadProducto torrontes = ValorPropiedadProducto.builder()
                .valor("Torrontes").build();
        ValorPropiedadProducto viognier = ValorPropiedadProducto.builder()
                .valor("Viognier").build();
        ValorPropiedadProducto malbecRose = ValorPropiedadProducto.builder()
                .valor("Malbec Rosé").build();
        ValorPropiedadProducto cabernetFranc = ValorPropiedadProducto.builder()
                .valor("Cabernet Franc").build();
        ValorPropiedadProducto pinot = ValorPropiedadProducto.builder()
                .valor("Pinot Noir").build();
        ValorPropiedadProducto tannat = ValorPropiedadProducto.builder()
                .valor("Tannat").build();
        ValorPropiedadProducto petit = ValorPropiedadProducto.builder()
                .valor("Petit Verdot").build();
        varietal.getValores().add(malbec);
        varietal.getValores().add(ancellotta);
        varietal.getValores().add(blend);
        varietal.getValores().add(cabernet);
        varietal.getValores().add(syrah);
        varietal.getValores().add(merlot);
        varietal.getValores().add(chardonnay);
        varietal.getValores().add(sauvignon);
        varietal.getValores().add(torrontes);
        varietal.getValores().add(viognier);
        varietal.getValores().add(malbecRose);
        varietal.getValores().add(cabernetFranc);
        varietal.getValores().add(pinot);
        varietal.getValores().add(tannat);
        varietal.getValores().add(petit);

        PropiedadProducto dulzor = PropiedadProducto.builder()
                .nombre("Dulzor").variable(true).valores(new ArrayList<>()).build();
        ValorPropiedadProducto brut = ValorPropiedadProducto.builder()
                .valor("Brut").build();
        ValorPropiedadProducto extraBrut = ValorPropiedadProducto.builder()
                .valor("Extra Brut").build();
        ValorPropiedadProducto brutNature = ValorPropiedadProducto.builder()
                .valor("Brut Nature").build();
        dulzor.getValores().add(brut);
        dulzor.getValores().add(extraBrut);
        dulzor.getValores().add(brutNature);

        // Categorías y subcategorías
        if (this.categoriaRepository.findAll().size() == 0) {
            List<Categoria> categorias = new ArrayList<>();
            // Vinos
            Subcategoria tinto = Subcategoria.builder()
                    .nombre("Tinto").codigo("TIN").propiedades(new ArrayList<>()).build();
            tinto.getPropiedades().add(varietal);

            Subcategoria blanco = Subcategoria.builder()
                    .nombre("Blanco").codigo("BLA").propiedades(new ArrayList<>()).build();
            blanco.getPropiedades().add(varietal);

            Subcategoria rosado = Subcategoria.builder()
                    .nombre("Rosado").codigo("ROS").propiedades(new ArrayList<>()).build();
            rosado.getPropiedades().add(varietal);

            Categoria vinos = Categoria.builder()
                    .nombre("Vinos").subcategorias(new ArrayList<>()).build();
            vinos.getSubcategorias().add(tinto);
            vinos.getSubcategorias().add(blanco);
            vinos.getSubcategorias().add(rosado);
            categorias.add(vinos);

            // Espumantes
            Subcategoria vinoEspumante = Subcategoria.builder()
                    .nombre("Vino espumante").codigo("ESP").propiedades(new ArrayList<>()).build();
            vinoEspumante.getPropiedades().add(dulzor);

            Subcategoria champagne = Subcategoria.builder()
                    .nombre("Champagne").codigo("CHA").propiedades(new ArrayList<>()).build();
            champagne.getPropiedades().add(dulzor);

            Subcategoria sidra = Subcategoria.builder()
                    .nombre("Sidra").codigo("SID").propiedades(new ArrayList<>()).build();
            sidra.getPropiedades().add(dulzor);

            Categoria espumantes = Categoria.builder()
                    .nombre("Espumantes").subcategorias(new ArrayList<>()).build();
            espumantes.getSubcategorias().add(vinoEspumante);
            espumantes.getSubcategorias().add(champagne);
            espumantes.getSubcategorias().add(sidra);
            categorias.add(espumantes);

            this.categoriaRepository.saveAll(categorias);
        }

        // Carga de productos
        if (this.productoRepository.findAll().size() == 0) {
            List<Producto> productos = new ArrayList<>();

            Producto naviraRose = Producto.builder()
                    .nombre("Navira Rosé 2017")
                    .descripcion("Cosecha: 2017.\n" +
                            "Linea: Navira.\n" +
                            "DATOS ANALÍTICOS:\n" +
                            "ALCOHOL: 13,50 %\n" +
                            "AZÚCAR RESIDUAL: 4,00 g/l\n" +
                            "PH: 3,45\n" +
                            "ACIDEZ TOTAL: 6,52 g/l\n" +
                            "PRODUCCIÓN: 5.000 Botellas\n" +
                            "SERVICIO: Entre 6-8°C")
                    .precio(500.00)
                    .disponibilidadGeneral(99)
                    .foto(null)
                    .imagenes(new ArrayList<>())
                    .activo(true)
                    .destacado(true)
                    .fechaCreacion(new Date())
                    .subcategoria(this.subcategoriaRepository.getOne(1L))
                    .marca(this.marcaRepository.getOne(1L))
                    .unidadMedida(this.unidadMedidaRepository.getOne(1L))
                    .propiedades(new ArrayList<>())
                    .skus(new ArrayList<>()).build();
            naviraRose.getPropiedades().add(varietal);
            naviraRose.setDefaultSku(Sku.builder()
                    .nombre(naviraRose.getNombre())
                    .descripcion(naviraRose.getDescripcion())
                    .fechaCreacion(naviraRose.getFechaCreacion())
                    .precio(naviraRose.getPrecio())
                    .disponibilidad(naviraRose.getDisponibilidadGeneral())
                    .defaultProducto(naviraRose).build());
            Sku naviraRoseSku = Sku.builder()
                    .nombre(naviraRose.getNombre())
                    .descripcion(naviraRose.getDescripcion())
                    //.variedades("95% Malbec - 5% Ancellotta.")
                    .fechaCreacion(naviraRose.getFechaCreacion())
                    .precio(naviraRose.getPrecio())
                    .disponibilidad(naviraRose.getDisponibilidadGeneral())
                    .valores(new ArrayList<>())
                    .producto(naviraRose).build();
            naviraRoseSku.getValores().add(malbec);
            naviraRoseSku.getValores().add(ancellotta);
            productos.add(naviraRose);

            Producto naviraBlend = Producto.builder()
                    .nombre("Navira Blend 2016")
                    .descripcion("Cosecha: 2016.\n" +
                            "Linea: Navira.\n" +
                            "DATOS ANALÍTICOS:\n" +
                            "ALCOHOL: 13,40 %\n" +
                            "AZÚCAR RESIDUAL: 3,50 g/l\n" +
                            "PH: 3,65\n" +
                            "ACIDEZ TOTAL: 6,00 g/l\n" +
                            "PRODUCCIÓN: 10.000 Botellas\n" +
                            "SERVICIO: Entre 14-16°C")
                    .precio(500.00)
                    .disponibilidadGeneral(99)
                    .foto(null)
                    .imagenes(new ArrayList<>())
                    .activo(true)
                    .destacado(true)
                    .fechaCreacion(new Date())
                    .subcategoria(this.subcategoriaRepository.getOne(1L))
                    .marca(this.marcaRepository.getOne(1L))
                    .unidadMedida(this.unidadMedidaRepository.getOne(1L))
                    .propiedades(new ArrayList<>())
                    .skus(new ArrayList<>()).build();

            naviraBlend.getPropiedades().add(varietal);
            naviraBlend.setDefaultSku(Sku.builder()
                    .nombre(naviraBlend.getNombre())
                    .descripcion(naviraBlend.getDescripcion())
                    .fechaCreacion(naviraBlend.getFechaCreacion())
                    .precio(naviraBlend.getPrecio())
                    .disponibilidad(naviraBlend.getDisponibilidadGeneral())
                    .defaultProducto(naviraBlend).build());
            Sku naviraBlendSku = Sku.builder()
                    .nombre(naviraBlend.getNombre())
                    .descripcion(naviraBlend.getDescripcion())
                    //.variedades("50% Merlot - 30% Tannat - 20% Petit Verdot.")
                    .fechaCreacion(naviraBlend.getFechaCreacion())
                    .precio(naviraBlend.getPrecio())
                    .disponibilidad(naviraBlend.getDisponibilidadGeneral())
                    .valores(new ArrayList<>())
                    .producto(naviraBlend).build();
            naviraBlendSku.getValores().add(merlot);
            naviraBlendSku.getValores().add(tannat);
            naviraBlendSku.getValores().add(petit);
            productos.add(naviraBlend);

            Producto indamaChardonnay = Producto.builder()
                    .nombre("Indama Chardonnay")
                    .descripcion("Cosecha: 2016.\n" +
                            "DATOS ANALÍTICOS:\n" +
                            "ALCOHOL: 13,80 %\n" +
                            "AZÚCAR RESIDUAL: 3,50 g/l\n" +
                            "PH: 3,36\n" +
                            "ACIDEZ TOTAL: 7 g/l\n" +
                            "PRODUCCIÓN: 15.000 Botellas\n" +
                            "SERVICIO: Entre 6-8°C")
                    .precio(700.00)
                    .disponibilidadGeneral(99)
                    .foto(null)
                    .imagenes(new ArrayList<>())
                    .activo(true)
                    .destacado(true)
                    .fechaCreacion(new Date())
                    .subcategoria(this.subcategoriaRepository.getOne(2L))
                    .marca(this.marcaRepository.getOne(2L))
                    .unidadMedida(this.unidadMedidaRepository.getOne(1L))
                    .propiedades(new ArrayList<>())
                    .skus(new ArrayList<>()).build();

            indamaChardonnay.getPropiedades().add(varietal);
            indamaChardonnay.setDefaultSku(Sku.builder()
                    .nombre(indamaChardonnay.getNombre())
                    .descripcion(indamaChardonnay.getDescripcion())
                    .fechaCreacion(indamaChardonnay.getFechaCreacion())
                    .precio(indamaChardonnay.getPrecio())
                    .disponibilidad(indamaChardonnay.getDisponibilidadGeneral())
                    .defaultProducto(indamaChardonnay).build());
            Sku indamaChardonnaySku = Sku.builder()
                    .nombre(indamaChardonnay.getNombre())
                    .descripcion(indamaChardonnay.getDescripcion())
                    //.variedades("95% Chardonnay - 5% Viognier.")
                    .fechaCreacion(indamaChardonnay.getFechaCreacion())
                    .precio(indamaChardonnay.getPrecio())
                    .disponibilidad(indamaChardonnay.getDisponibilidadGeneral())
                    .valores(new ArrayList<>())
                    .producto(indamaChardonnay).build();
            indamaChardonnaySku.getValores().add(chardonnay);
            indamaChardonnaySku.getValores().add(viognier);
            productos.add(indamaChardonnay);

            Producto bullaBrutNature = Producto.builder()
                    .nombre("Bulla Brut Nature")
                    .descripcion("Cosecha: 2020.\n" +
                            "ALCOHOL: 12,30 % v/v\n" +
                            "AZÚCAR RESIDUAL: 10.50 g/l\n" +
                            "PRODUCCIÓN: 3000 Botellas\n" +
                            "MARIDAJES: Ideal para acompañar mariscos, pescados y pastas.\n" +
                            "SERVICIO: Recomendamos servir entre 6-8°C de temperatura.")
                    .precio(800.00)
                    .disponibilidadGeneral(99)
                    .foto(null)
                    .imagenes(new ArrayList<>())
                    .activo(true)
                    .destacado(true)
                    .fechaCreacion(new Date())
                    .subcategoria(this.subcategoriaRepository.getOne(4L))
                    .marca(this.marcaRepository.getOne(1L))
                    .unidadMedida(this.unidadMedidaRepository.getOne(1L))
                    .propiedades(new ArrayList<>())
                    .skus(new ArrayList<>()).build();

            bullaBrutNature.getPropiedades().add(dulzor);
            bullaBrutNature.setDefaultSku(Sku.builder()
                    .nombre(bullaBrutNature.getNombre())
                    .descripcion(bullaBrutNature.getDescripcion())
                    .fechaCreacion(bullaBrutNature.getFechaCreacion())
                    .precio(bullaBrutNature.getPrecio())
                    .disponibilidad(bullaBrutNature.getDisponibilidadGeneral())
                    .defaultProducto(bullaBrutNature).build());
            Sku bullaBrutNatureSku = Sku.builder()
                    .nombre(bullaBrutNature.getNombre())
                    .descripcion(bullaBrutNature.getDescripcion())
                    //.variedades("Dulce.")
                    .fechaCreacion(bullaBrutNature.getFechaCreacion())
                    .precio(bullaBrutNature.getPrecio())
                    .disponibilidad(bullaBrutNature.getDisponibilidadGeneral())
                    .valores(new ArrayList<>())
                    .producto(bullaBrutNature).build();
            bullaBrutNatureSku.getValores().add(brutNature);
            productos.add(bullaBrutNature);

            this.productoRepository.saveAll(productos);
            // Cargamos los SKUS principales de cada producto.
            this.skuService.crearNuevoSku(naviraRoseSku, naviraRose);
            this.skuService.crearNuevoSku(naviraBlendSku, naviraBlend);
            this.skuService.crearNuevoSku(indamaChardonnaySku, indamaChardonnay);
            this.skuService.crearNuevoSku(bullaBrutNatureSku, bullaBrutNature);
        }
    }
}
