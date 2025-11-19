# Lab11 – Programação Concorrente

Este repositório inclui duas implementações em Java desenvolvidas para o laboratório 11 da disciplina de Programação Concorrente.

## Arquivos

1. MyPool.java  
   Implementa um pool de threads construído manualmente, utilizando objetos Runnable. O programa cria várias tarefas de impressão ("Hello") e também verifica se números são primos, distribuindo as tarefas entre as threads criadas.

2. FutureHello.java  
   Exemplo de uso de Callable e Future, utilizando um ExecutorService com número fixo de threads. Cada tarefa retorna um resultado, permitindo somar valores e checar primalidade de forma assíncrona. Os resultados são acessados usando future.get().

## Como compilar

Para compilar os programas, use:

javac MyPool.java
javac FutureHello.java

## Como executar

Após compilar, execute com:

java MyPool
java FutureHello
