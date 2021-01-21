package com.deofis.tiendaapirest.ecommerce.domain;

import com.deofis.tiendaapirest.productos.domain.Imagen;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "banners")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagen_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Imagen imagen;
    private String actionUrl;
    private Integer orden;
}
