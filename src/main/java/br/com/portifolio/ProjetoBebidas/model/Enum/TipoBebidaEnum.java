package br.com.portifolio.ProjetoBebidas.model.Enum;


public enum TipoBebidaEnum {

    SEM_ALCOOL(0, "SEM ALCOOL"),
    ALCOOLICA(1, "ALCOOLICA"),
    REFRIGERANTE(2, "REFRIGERANTE"),
    SUCO(3, "SUCO"),
    CERVEJA(4, "CERVEJA"),
    VINHO(5, "VINHO"),
    ÁGUA(6, "AGUA");

    private final int codigo;
    private final String descricao;

    TipoBebidaEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter o enum pelo código
    public static TipoBebidaEnum getCodigo(int codigo) {
        for (TipoBebidaEnum tipo : TipoBebidaEnum.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código não encontrado: " + codigo);
    }
}
