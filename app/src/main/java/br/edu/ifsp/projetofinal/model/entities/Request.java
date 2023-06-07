package br.edu.ifsp.projetofinal.model.entities;

import java.util.Date;

public class Request {
    private Integer id;
    private String origem;
    private String destino;
    private Date dataViagem;
    private String anexoNotaFiscal;
    private String anexoKmAntes;
    private String anexoKmDepois;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }

    public String getAnexoNotaFiscal() {
        return anexoNotaFiscal;
    }

    public void setAnexoNotaFiscal(String anexoNotaFiscal) {
        this.anexoNotaFiscal = anexoNotaFiscal;
    }

    public String getAnexoKmAntes() {
        return anexoKmAntes;
    }

    public void setAnexoKmAntes(String anexoKmAntes) {
        this.anexoKmAntes = anexoKmAntes;
    }

    public String getAnexoKmDepois() {
        return anexoKmDepois;
    }

    public void setAnexoKmDepois(String anexoKmDepois) {
        this.anexoKmDepois = anexoKmDepois;
    }
}
