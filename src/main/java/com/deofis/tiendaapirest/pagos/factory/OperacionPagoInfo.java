package com.deofis.tiendaapirest.pagos.factory;

import com.deofis.tiendaapirest.pagos.dto.AmountPayload;
import com.deofis.tiendaapirest.pagos.dto.PayerPayload;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Map;

public abstract class OperacionPagoInfo {

    @JsonIgnore
    protected Map<String, Object> atributos;

    public OperacionPagoInfo(Map<String, Object> atributos) {
        this.atributos = atributos;
    }

    public Map<String, Object> getAtributos() {
        return this.atributos;
    }

    public abstract String getId();

    public abstract String getStatus();

    public abstract String getApproveUrl();

    public abstract AmountPayload getAmount();

    public abstract PayerPayload getPayer();
}