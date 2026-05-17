package validation;

public class ResultadoValidacao {
 
    private final boolean valido;
    private final String mensagem;
 
    private ResultadoValidacao(boolean valido, String mensagem) {
        this.valido = valido;
        this.mensagem = mensagem;
    }
 
    public static ResultadoValidacao sucesso() {
        return new ResultadoValidacao(true, null);
    }
 
    public static ResultadoValidacao falha(String mensagem) {
        return new ResultadoValidacao(false, mensagem);
    }
 
    public boolean isValido() {
        return valido;
    }
 
    public String getMensagem() {
        return mensagem;
    }
}
 