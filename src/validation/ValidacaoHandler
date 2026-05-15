package validation;

import model.Reserva;
import java.util.List;

//Interface do padrão Chain of Responsibility para validação de reservas.
public interface ValidacaoHandler {

    //Encadeamento fluente: cadeia.setProximo(A).setProximo(B).setProximo(C)
    ValidacaoHandler setProximo(ValidacaoHandler proximo);

  // Se passar, delega ao próximo handler; 
  // se falhar, retorna ResultadoValidacao.falha() imediatamente, interrompendo a cadeia.
    ResultadoValidacao validar(Reserva novaReserva, List<Reserva> reservasExistentes);
}