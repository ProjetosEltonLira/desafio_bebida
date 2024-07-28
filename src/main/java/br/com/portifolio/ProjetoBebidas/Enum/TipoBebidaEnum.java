package br.com.portifolio.ProjetoBebidas.Enum;


public enum TipoBebidaEnum {

    SEM_ALCOOL(1),
    ALCOOLICA(2);

    private int codigo;

    TipoBebidaEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoBebidaEnum valueOf(int code){
        for (TipoBebidaEnum value : TipoBebidaEnum.values()){
            if (value.getCodigo() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código do status do pedido inválido");
    }
}
