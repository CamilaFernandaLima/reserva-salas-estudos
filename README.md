# Extensão do Sistema de Reserva de Salas de Estudo

## Descrição
O projeto consiste em um sistema de Reserva de Salas de Estudo para um campus universitário, permitindo que usuários consultem salas disponíveis, criem reservas, alterem horários, cancelem reservas e gerem relatórios diários.

A aplicação foi desenvolvida com foco em Programação Orientada a Objetos e utilização de padrões de projeto:

Factory Method para criação dos diferentes tipos de sala
Strategy para as políticas de conflito de reservas
Observer para envio de notificações
Singleton para gerenciamento centralizado do repositório em memória
Chain of Responsability para validação em múltiplas etapas

O sistema possui uma interface simples em linha de comando e foi estruturado de forma modular, separando responsabilidades entre models, services, repository, strategies e observers.

## Funcionalidades
- Listar salas disponíveis
- Criar reserva
- Modificar reserva
- Cancelar reserva
- Relatório diário
- Notificações

## Padrões de Projeto Utilizados

### Factory Method
Utilizado na criação dos diferentes tipos de sala através da `SalaFactory`, evitando acoplamento direto com as subclasses de `Sala`.

### Strategy
Utilizado para definir diferentes políticas de reserva e detecção de conflitos de horário, permitindo troca dinâmica das regras de negócio.

### Observer
Utilizado para notificar usuários e serviços quando uma reserva é alterada ou cancelada.

### Singleton
Utilizado no `ReservaRepository`, garantindo uma única instância compartilhada para gerenciamento das reservas e salas.

### Chain of Responsability 
Utilizado em `ValidacaoHandler`, para implementar a validação das reservas em múltiplas etapas ([1] Usuário tem permissão para reservar essa sala? [2] O horário solicitado está disponível? [3] A sala tem capacidade suficiente para o grupo? [4] O usuário não tem reservas conflitantes em outras salas?).

## Estrutura do Projeto
src/
docs/

## Funcionalidade: Validação de Reservas

### Descrição
Esta PR adiciona a seguinte capacidade ao sistema de Reserva de Salas de Estudo:

1. **Validação encadeada** de reservas antes da criação, garantindo que cada reserva passe por múltiplas regras de negócio de forma modular e sequencial.

---

### Padrão de Projeto: Chain of Responsibility

**Justificativa:** O processo de validação de uma reserva envolve múltiplas regras independentes e sequenciais. Cada regra é encapsulada em um handler próprio, e se uma falhar, a cadeia é interrompida imediatamente com uma mensagem de erro clara — sem `if`s aninhados ou lógica centralizada.

A cadeia é montada no `Main` da seguinte forma:

```java
ValidacaoHandler cadeiaDeValidacao = new ValidacaoUsuario();
cadeiaDeValidacao
.setProximo(new ValidacaoHorario())
.setProximo(new ValidacaoSalaDisponivel())
.setProximo(new ValidacaoCapacidade());
```

Ordem de execução dos handlers:
1. **`ValidacaoUsuario`** — verifica se o usuário é não-nulo e possui nome.
2. **`ValidacaoHorario`** — verifica se início e fim são válidos e coerentes.
3. **`ValidacaoSalaDisponivel`** — detecta conflitos de horário com reservas existentes.
4. **`ValidacaoCapacidade`** — impede que estudantes reservem laboratórios.

Cada handler herda de `ValidacaoBase`, que implementa o encadeamento via `setProximo()` com retorno fluente. O resultado é encapsulado em `ResultadoValidacao`, com um booleano e, em caso de falha, a mensagem de erro.

---

### Como testar

1. Clone o repositório e acesse a branch desta PR.
2. Compile e execute a classe `Main`.

**Testar criação com validação (opção 2):**
- Executa a cadeia de validação antes de criar a reserva.
- O console exibirá a mensagem de erro do handler que falhou, ou confirmará a criação com o ID da reserva.
- Para testar falhas, edite o `Main` e tente:
- `Usuario` com nome vazio → falha no `ValidacaoUsuario`.
- `inicio` após `fim` → falha no `ValidacaoHorario`.
- Duas reservas no mesmo horário e sala → falha no `ValidacaoSalaDisponivel`.
- Reservar `Lab 01` com usuário `ESTUDANTE` → falha no `ValidacaoCapacidade`.


## Autores
Camila Fernanda e Silva Lima
Vitor ...
