package validation;

import model.Reserva;
import java.util.List;

//Classe abstrata que implementa a lógica de encadeamento comum a todos os handlers.
public abstract class ValidacaoBase implements ValidacaoHandler {

    private ValidacaoHandler proximo;

    @Override
    public ValidacaoHandler setProximo(ValidacaoHandler proximo) {
        this.proximo = proximo;
        return proximo; //encadeamento fluente
    }

    //Delega ao próximo handler da cadeia, se existir (ou considera a validação aprovada).
    protected ResultadoValidacao validarProximo(Reserva novaReserva, List<Reserva> reservasExistentes) {
        if (proximo == null) {
            return ResultadoValidacao.sucesso();
        }
        return proximo.validar(novaReserva, reservasExistentes);
    }
}