package br.com.portifolio.ProjetoBebidas.model.Enum;


public enum TipoBebida {

    SEM_ALCOOL(0L, "SEM ALCOOL"),
    ALCOOLICA(1L, "ALCOOLICA"),
    REFRIGERANTE(2L, "REFRIGERANTE"),
    SUCO(3L, "SUCO"),
    CERVEJA(4L, "CERVEJA"),
    VINHO(5L, "VINHO"),
    ÁGUA(6L, "AGUA");

    private final Long codigo;
    private final String descricao;

    TipoBebida(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter o enum pelo código
    public static TipoBebida getCodigo(Long codigo) {
        for (TipoBebida tipo : TipoBebida.values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código não encontrado: " + codigo);
    }
}
