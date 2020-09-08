package com.deofis.tiendaapirest.autenticacion.service;

import com.deofis.tiendaapirest.autenticacion.domain.RefreshToken;
import com.deofis.tiendaapirest.autenticacion.exception.AutenticacionException;
import com.deofis.tiendaapirest.autenticacion.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generarRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setFechaCreacion(new Date());

        return this.refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validarRefreshToken(String token) {
        this.refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new AutenticacionException("Refresh Token Inválido"));
    }

    @Override
    public void eliminarRefreshToken(String token) {
        this.refreshTokenRepository.deleteByToken(token);
    }
}
