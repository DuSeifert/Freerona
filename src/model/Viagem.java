package model;

public class Viagem {
    private int id;
    private String destino;
    private String data;
    private String descricao;
    private int idUsuarioCriador;

    public Viagem(int id, String destino, String data, String descricao, int idUsuarioCriador) {
        this.id = id;
        this.destino = destino;
        this.data = data;
        this.descricao = descricao;
        this.idUsuarioCriador = idUsuarioCriador;
    }

    public Viagem(String destino, String data, String descricao, int idUsuarioCriador) {
        this.destino = destino;
        this.data = data;
        this.descricao = descricao;
        this.idUsuarioCriador = idUsuarioCriador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuarioCriador() {
        return idUsuarioCriador;
    }

    public void setIdUsuarioCriador(int idUsuarioCriador) {
        this.idUsuarioCriador = idUsuarioCriador;
    }
} 