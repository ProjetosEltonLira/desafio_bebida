package br.com.portifolio.ProjetoBebidas.Model.entities;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

    public int quantidadeMaximaSessao = 5;
    public List<Sessao> listSessao = new ArrayList<>();

    public Estoque(){}
    public Estoque(List<Sessao> listSessao) {
        this.listSessao = listSessao;
    }

    public int getQuantidadeMaximaSessao() {
        return quantidadeMaximaSessao;
    }

    public List<Sessao> getListSessao() {
        return listSessao;
    }
    
    public boolean removerSessao(int id){
        for(int i = 0; i < listSessao.size();i++){
            Sessao sessao = listSessao.get(i);
            if(sessao.getId() == id){
                listSessao.remove(id);
                return true;
            }
        }
        return false;
    }

    public boolean adicionarSessao(Sessao sessao) {
        if(checarDisponibilidadeSessao()){
            listSessao.add(sessao);
            return true;
        }
        return false;
    }



    public boolean checarDisponibilidadeSessao (){
        return listSessao.size() < quantidadeMaximaSessao;
    }

    @Override
    public String toString() {
        return "Estoque" + listSessao +"}";
    }
}


