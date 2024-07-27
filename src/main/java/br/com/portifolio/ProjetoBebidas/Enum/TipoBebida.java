package br.com.portifolio.ProjetoBebidas.Enum;


public enum TipoBebida {

    ALCOOLICA(1),
    SEM_ALCOOL(2);

    private int codigo;

    TipoBebida(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoBebida valueOf(int code){
        for (TipoBebida value : TipoBebida.values()){
            if (value.getCodigo() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código do status do pedido inválido");
    }
}
