package br.edu.ifsp.projetofinal.model.entities;

import java.util.Date;
public class Request {
    private Integer id;
    private String origem;
    private String destino;
    private Date dataViagem;
    private String dataViagem1;
    private String anexoNotaFiscal;
    private String anexoKmAntes;
    private String anexoKmDepois;

    private String status;

    public Request(Integer id, String origem, String destino, Date dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
        this.anexoNotaFiscal = anexoNotaFiscal;
        this.anexoKmAntes = anexoKmAntes;
        this.anexoKmDepois = anexoKmDepois;
        this.status = status;
    }
    public Request(String origem, String destino, String dataViagem, String anexoNotaFiscal, String anexoKmAntes, String anexoKmDepois, String status) {
        this.origem = origem;
        this.destino = destino;
        this.dataViagem1 = dataViagem;
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
