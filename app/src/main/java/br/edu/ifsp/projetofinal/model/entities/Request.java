package br.edu.ifsp.projetofinal.model.entities;

import java.io.Serializable;

public class Request implements Serializable {
    private int id;
    public String idUser;
    private String origem;
    private String destino;
    private String dataViagem;
    private String anexoNotaFiscal;
    private String anexoKmAntes;
    private String anexoKmDepois;
    private String status;
    public Request(){}

    public Request(int id, String idUser, String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {
        this.id = id;
        this.idUser = idUser;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.anexoNotaFiscal = anexoNotaFiscal;
        this.anexoKmAntes = anexoKmAntes;
        this.anexoKmDepois = anexoKmDepois;
        this.status = status;
    }
    public Request(int id,  String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.anexoNotaFiscal = anexoNotaFiscal;
        this.anexoKmAntes = anexoKmAntes;
        this.anexoKmDepois = anexoKmDepois;
        this.status = status;
    }

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
    public String getDataViagem() {
        return dataViagem;
    }
    public void setDataViagem(String dataViagem) {
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
