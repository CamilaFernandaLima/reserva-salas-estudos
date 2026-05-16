import factory.SalaFactory;
import factory.TipoSala;
import model.*;
import service.RelatorioService;
import service.ReservaService;
import strategy.PoliticaPrimeiroAReservar;
import validation.ResultadoValidacao;
import validation.ValidacaoCapacidade;
import validation.ValidacaoHandler;
import validation.ValidacaoHorarioCoerente;
import validation.ValidacaoSalaDisponivel;
import validation.ValidacaoUsuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ReservaService reservaService =
                new ReservaService(new PoliticaPrimeiroAReservar());
        RelatorioService relatorioService =
                new RelatorioService();

        Sala sala101 = SalaFactory.criarSala(TipoSala.INDIVIDUAL, "Sala 101");
        Sala sala202 = SalaFactory.criarSala(TipoSala.GRUPO, "Sala 202");
        Sala lab01  = SalaFactory.criarSala(TipoSala.LABORATORIO, "Lab 01");

        reservaService.adicionarSala(sala101);
        reservaService.adicionarSala(sala202);
        reservaService.adicionarSala(lab01);

        // Adição da cadeia de validação (Chain of Responsibility)
        // Cada handler valida uma regra e, se aprovada, passa ao próximo.
        ValidacaoHandler cadeiaDeValidacao = new ValidacaoUsuario();
        cadeiaDeValidacao
                .setProximo(new ValidacaoHorarioCoerente())
                .setProximo(new ValidacaoSalaDisponivel())
                .setProximo(new ValidacaoCapacidade());

        boolean executando = true;
        while (executando) {
            System.out.println("\n=== SISTEMA DE RESERVA ===");
            System.out.println("1 - Listar salas disponíveis");
            System.out.println("2 - Criar reserva");
            System.out.println("3 - Gerar relatório diário");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    List<Sala> salasDisponiveis =
                            reservaService.listarSalasDisponiveis(
                                    LocalDateTime.of(2026, 5, 20, 10, 0),
                                    LocalDateTime.of(2026, 5, 20, 11, 0)
                            );
                    System.out.println("\nSalas disponíveis:");
                    for (Sala sala : salasDisponiveis) {
                        System.out.println("- " + sala.getNome());
                    }
                    break;

                case 2:
                    Usuario usuario = new Usuario("Breno", TipoUsuario.ESTUDANTE);
                    LocalDateTime inicio = LocalDateTime.of(2026, 5, 20, 10, 0);
                    LocalDateTime fim    = LocalDateTime.of(2026, 5, 20, 11, 0);

                    // Cria uma reserva temporária apenas para rodar a cadeia de validação
                    Reserva reservaParaValidar = new Reserva(usuario, sala202, inicio, fim);

                    ResultadoValidacao resultado = cadeiaDeValidacao.validar(
                            reservaParaValidar,
                            reservaService.listarTodasReservas()
                    );

                    if (!resultado.isValido()) {
                        System.out.println(resultado.getMensagem());
                    } else {
                        // Cadeia aprovada — delega ao serviço (que aplica a strategy)
                        reservaService.criarReserva(usuario, sala202, inicio, fim);
                    }
                    break;

                case 3:
                    relatorioService.gerarRelatorioDiario(
                            LocalDate.of(2026, 5, 20)
                    );
                    break;

                case 4:
                    executando = false;
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}