# Sistema de Reserva de Salas de Estudo

## Descrição
O projeto consiste em um sistema de Reserva de Salas de Estudo para um campus universitário, permitindo que usuários consultem salas disponíveis, criem reservas, alterem horários, cancelem reservas e gerem relatórios diários.

A aplicação foi desenvolvida com foco em Programação Orientada a Objetos e utilização de padrões de projeto:

Factory Method para criação dos diferentes tipos de sala
Strategy para as políticas de conflito de reservas
Observer para envio de notificações
Singleton para gerenciamento centralizado do repositório em memória

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


## Estrutura do Projeto
src/
docs/

## Autores
