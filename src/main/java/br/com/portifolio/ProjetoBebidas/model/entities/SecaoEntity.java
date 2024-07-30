package br.com.portifolio.ProjetoBebidas.model.entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "secao")
public class SecaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "capacidade")
    private Double capacidade;

    @Column (name = "volumeatual")
    private Double volumeAtual;

    @ManyToMany
    @JoinTable(
            name = "secao_bebida",
            joinColumns = @JoinColumn(name = "secao_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id"))
    private Set<BebidaEntity> bebidas;

    @OneToMany(mappedBy = "secao")
    Set<SecaoBebidaEntity> secaoBebidas;

    public SecaoEntity(){}
    public SecaoEntity(Double capacidade, Double volumeAtual) {
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
    }
    public SecaoEntity(Double capacidade, Double volumeAtual, Set<BebidaEntity> bebidas) {
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
        this.bebidas = bebidas;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }
    public Double getVolumeAtual() {
        return volumeAtual;
    }
    public void setVolumeAtual(Double volumeAtual) {
        this.volumeAtual = volumeAtual;
    }
    public Set<BebidaEntity> getBebidas() {
        return bebidas;
    }
    public void setBebidas(Set<BebidaEntity> bebidas) {
        this.bebidas = bebidas;
    }

    public double consultarVolumeDisponivel (){
        return capacidade - volumeAtual;
    }
    /*public void adicionarBebida(BebidaEntity bebidaEntity) throws ExceptionError {
        if(!Objects.equals(bebidaEntity.getTipoBebida().getId(), this.tipoBebidaEntity.getId())){
            throw new ExceptionError("A sessão " + tipoBebidaEntity + " só pode receber bebidas do mesmo tipo");
        }
        if(consultarVolumeDisponivel() == 0.0){
            throw new ExceptionError("A sessao" + id + " atingiu o limite de bebidas, não é possível adicionar mais bebidas a essa sessão");

        }if(bebidaEntity.getQuantidade() <= 0.0){
            throw new ExceptionError("Não é possível inserir um valor igual ou inferior a 0 litros de bebida na sessao");
        }
        if(bebidaEntity.getQuantidade() > consultarVolumeDisponivel()){
            throw new ExceptionError("A sessao possui " + consultarVolumeDisponivel() + " litros disponíveis, não é possível inserir " + bebidaEntity.getQuantidade() + " litros da bebida");
        }
        volumeAtual += bebidaEntity.getQuantidade();


    }*/

    @Override
    public String toString() {
        return "Sessao{"+
                "id:" + id +
                " Capacidade:" + capacidade +
                " VolumeAtual:" + volumeAtual +
                " Tipo:" + getBebidas() +
                " ListaBebida:" + bebidas.toString() + "}";

    }
}
