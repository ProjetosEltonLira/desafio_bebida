package br.com.portifolio.ProjetoBebidas.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bebida")
public class BebidaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipobebida_id", nullable = false)
    private TipoBebidaEntity tipoBebidaEntity;

    //O id é a classe BebidaSecao, que tem a variável id, ".bebidaEntity" é uma variável dentro da BebidaSecaoKey
    @OneToMany(mappedBy = "id.bebidaEntity")
    private Set<BebidaSecaoEntity> bebidaSessoes;

    public BebidaEntity(){}
    public BebidaEntity(Long id, String nome, TipoBebidaEntity tipoBebidaEntity) {
        this.id = id;
        this.nome = nome;
        this.tipoBebidaEntity = tipoBebidaEntity;
        //this.bebidaSessoes = bebidaSessoes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBebidaEntity getTipoBebida() {
        return tipoBebidaEntity;
    }

    public void setTipoBebida(TipoBebidaEntity tipoBebidaEntity) {
        this.tipoBebidaEntity = tipoBebidaEntity;
    }

    public Set<BebidaSecaoEntity> getBebidaSessoes() {
        return bebidaSessoes;
    }

    public void setBebidaSessoes(Set<BebidaSecaoEntity> bebidaSessoes) {
        this.bebidaSessoes = bebidaSessoes;
    }


}
