package br.com.portifolio.ProjetoBebidas.Model.entities;

import br.com.portifolio.ProjetoBebidas.Enum.TipoBebida;
import br.com.portifolio.ProjetoBebidas.exception.ExceptionError;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sessao {

    private int id;
    private double capacidade;
    private double volumeAtual;
    private TipoBebida tipo;
    private List<Bebida> bebida = new ArrayList<>();

    public Sessao(){}

    public Sessao(int id, double capacidade, double volumeAtual, TipoBebida tipo) {
        this.id = id;
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
        this.tipo = tipo;
    }
    public Sessao(int id, double capacidade, double volumeAtual, TipoBebida tipo,Bebida bebida) throws ExceptionError {
        this.id = id;
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
        this.tipo = tipo;
        adicionarBebida(bebida);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public double getVolumeAtual() {
        return volumeAtual;
    }

    public void setVolumeAtual(double volumeAtual) {
        this.volumeAtual = volumeAtual;
    }

    public TipoBebida getTipo() {
        return tipo;
    }

    public void setTipo(TipoBebida tipo) {
        this.tipo = tipo;
    }

    public List<Bebida> getBebida() {
        return bebida;
    }

    public void setBebida(List<Bebida> bebida) {
        this.bebida = bebida;
    }

    private double consultarVolumeDisponivel (){
        return capacidade - volumeAtual;
    }

    public void adicionarBebida(Bebida bebida) throws ExceptionError {
        if(!Objects.equals(bebida.getTipo(), this.tipo)){
            throw new ExceptionError("A sessão " + tipo + " só pode receber bebidas do mesmo tipo");
        }
        if(consultarVolumeDisponivel() == 0){
            throw new ExceptionError("A sessao" + id + " atingiu o limite de bebidas, não é possível adicionar mais bebidas a essa sessão");

        }if(bebida.getQuantidade() <= 0){
            throw new ExceptionError("Não é possível inserir um valor igual ou inferior a 0 litros de bebida na sessao");
        }
        if(bebida.getQuantidade() > consultarVolumeDisponivel()){
            throw new ExceptionError("A sessao possui " + consultarVolumeDisponivel() + " litros disponíveis, não é possível inserir " + bebida.getQuantidade() + " litros da bebida");
        }
        volumeAtual += bebida.getQuantidade();
        this.bebida.add(bebida);

    }

    @Override
    public String toString() {
        return "Sessao{"+
                "id:" + id +
                " Capacidade:" + capacidade +
                " VolumeAtual:" + volumeAtual +
                " Tipo:" + tipo +
                " ListaBebida:" + bebida + "}";

    }
}
