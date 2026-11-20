# Relatório de execução — Teste de usuário no Mercado Livre

**Data:** 20/11/2026  
**Atividade:** SEMANA14 — TESTE DE USUÁRIO FINAL  
**Participante:** P01 (usuário final)  
**Avaliador:** responsável pela execução e registro  
**Amostra:** 1 participante

## 1. Finalidade

Avaliar facilidade de uso, eficiência e clareza da interface nas etapas de
busca, comparação e simulação de compra de um fone de ouvido Bluetooth no
Mercado Livre, conforme o cenário do enunciado.

O documento registra duas etapas:

1. um **piloto automatizado**, que levantou os limites técnicos do ambiente;
2. a **execução real com 1 participante**, com tempos, desvios e observações
   consolidados neste relatório.

## 2. Piloto automatizado (etapa preparatória)

- Ambiente: sessão OpenCode com ferramentas de leitura web, sem navegador
  interativo e sem conta autenticada.
- URLs consultadas e respostas obtidas:

| URL | Resultado |
|---|---|
| `https://www.mercadolivre.com.br/` | HTTP 200 — homepage carregada |
| `https://www.mercadolivre.com.br/jm/search?as_word=fone%20de%20ouvido%20bluetooth` | Redirecionamento para verificação de conta |
| `https://lista.mercadolivre.com.br/fone-de-ouvido-bluetooth` | Redirecionamento para verificação de conta |
| `https://www.mercadolivre.com.br/fone-de-ouvido-bluetooth` | HTTP 404 |
| `https://api.mercadolibre.com/sites/MLB/search?q=fone%20de%20ouvido%20bluetooth&limit=5` | HTTP 403 (Forbidden) |

- A página de verificação traz `isBot: true`, o frontend
  `suspicious-traffic-frontend` e a mensagem "Olá! Para continuar, acesse sua
  conta", acompanhada de `trace-id`.
- Conclusão: a automação serve para registrar a viabilidade e os bloqueios do
  ambiente, mas não produz dados de usabilidade. Não foi usada para preencher
  as métricas abaixo.

## 3. Execução com participante humano (P01)

| # | Tarefa | Tempo | Resultado | Observação do participante |
|---|---|---|---|---|
| 1 | Acessar o site | 3 s | S | Homepage carregada sem dificuldade |
| 2 | Pesquisar "fone de ouvido Bluetooth" | 5 s | S | Pesquisa realizada com o termo informado |
| 3 | Filtrar Full e ordenar por melhores avaliados | 3 s | **N** | Aplicou o filtro Full, mas não encontrou ordenação por "melhores avaliados"; só "mais relevantes" e "maior/menor valor" |
| 4 | Escolher produto com boa avaliação e abrir detalhes | 4 s | **N** | Abriu os detalhes, mas não leu as avaliações antes de escolher |
| 5 | Adicionar ao carrinho | 2 s | S | Botão localizado e item adicionado |
| 6 | Simular início da compra | 10 s | S | Conta já autenticada; iniciou a compra sem finalizar o pagamento |
| 7 | Retornar à tela inicial | 1 s | S | Clicou no logo do Mercado Livre |
| | **Total** | **28 s** | **5 S / 2 N** | 2 desvios registrados |

**Planilha de observação (consolidada):**

| Participante | Tarefa | Tempo (s) | Sucesso | Erros | Observação |
|---|---|---|---|---|---|
| P01 | 1 — Acessar site | 3 | S | 0 | Homepage carregada |
| P01 | 2 — Buscar produto | 5 | S | 0 | Termo informado digitado |
| P01 | 3 — Aplicar filtros | 3 | N | 1 | Ordenação por avaliação não encontrada |
| P01 | 4 — Abrir detalhes | 4 | N | 1 | Avaliações não consultadas |
| P01 | 5 — Adicionar ao carrinho | 2 | S | 0 | Item adicionado |
| P01 | 6 — Simular compra | 10 | S | 0 | Pagamento interrompido |
| P01 | 7 — Retornar ao início | 1 | S | 0 | Retorno pelo logo |
| P01 | **TOTAL** | **28** | **5 S / 2 N** | **2** | 0 min 28 s |

## 4. Métricas coletadas

| Métrica | Valor obtido | Critério | Situação |
|---|---|---|---|
| Tempo total | 28 s (0 min 28 s) | até 4 minutos | **Atendido** |
| Tempo médio por tarefa | 4 s | — | — |
| Taxa de conclusão das 7 tarefas | 5/7 = 71,4% | — | 2 tarefas com desvio |
| Adição ao carrinho | 1/1 = 100% (P01) | ≥ 90% dos usuários | Atendido para P01; não verificável estatisticamente com n = 1 |
| Erros/desvios | 2 | — | Ordenação por avaliação e leitura das avaliações |
| Escore SUS | não coletado | — | Formulário não aplicado ao participante |
| Pagamento finalizado | não | nunca finalizar | **Atendido** |

Os campos não coletados foram deixados em branco de propósito: preenchê-los
com valores estimados transformaria um piloto técnico em resultado fictício.

## 5. Análise dos resultados

- **Eficiência:** o fluxo completo levou 28 segundos, muito abaixo do limite de
  4 minutos. O participante já estava autenticado, o que reduziu o tempo da
  tarefa 6; em um usuário sem conta, esse valor tende a ser maior.
- **Principal achado de usabilidade (tarefa 3):** o participante não encontrou
  a opção de ordenar por "melhores avaliados" e relatou ver apenas "mais
  relevantes" e "maior/menor valor". Isso impediu o cumprimento literal da
  tarefa e é o desvio mais relevante do teste. Vale verificar se o critério
  existe em outro filtro ou se foi removido/renomeado no site.
- **Tarefa 4:** o participante abriu os detalhes de um produto em 4 segundos,
  mas não consultou as avaliações. Como a tarefa pedia um modelo com "boa
  avaliação", o critério não foi efetivamente verificado — pode indicar que a
  informação não estava salientada ou que o participante priorizou outro
  atributo.
- **Carrinho e compra:** as tarefas 5 e 6 foram concluídas sem ajuda, incluindo
  a interrupção antes do pagamento.
- **Retorno:** o uso do logo como caminho de retorno foi imediato (1 s),
  indicando affordance clara.

## 6. Questionário qualitativo e SUS

Os instrumentos abaixo estavam preparados, mas **não foram aplicados ao
participante**. Portanto, não há escore SUS nem respostas qualitativas. Essa é
uma limitação da execução: a avaliação cobre eficiência e conclusão das
tarefas, mas não a percepção subjetiva de usabilidade.

### Formulário SUS (não aplicado)

Itens, escala de 1 (discordo totalmente) a 5 (concordo totalmente):

1. Eu gostaria de usar este site com frequência.
2. Achei o site desnecessariamente complexo.
3. O site foi fácil de usar.
4. Acho que vou precisar de ajuda para usar este site.
5. As várias funções do site ficaram bem organizadas.
6. Achei que havia muita inconsistência no site.
7. Acho que a maioria das pessoas conseguiria usar este site rapidamente.
8. Achei o site confuso.
9. Senti segurança ao usar o site.
10. Preciso aprender muitas coisas antes de usar o site.

Cálculo: itens ímpares, contribuição = `(resposta − 1) × 2,5`; itens pares,
contribuição = `(5 − resposta) × 2,5`. Somar as 10 contribuições e dividir por
10 (resultado de 0 a 100). Referência: escore ≈ 68 é a média; acima de 80,
excelente; abaixo de 68, abaixo da média. Com 1 participante, o valor seria
individual, não uma média estatística.

### Questionário qualitativo (não aplicado)

1. Como você classificaria a facilidade de encontrar o fone que procurava?
2. Teve dificuldade para localizar os filtros "entrega Full" e "melhores avaliados"?
3. Entendeu o que significa "entrega Full"? Houve dúvida entre Full e frete grátis?
4. O processo de escolher um produto e abrir os detalhes foi intuitivo?
5. Encontrou facilmente o botão de adicionar ao carrinho?
6. A página trouxe as informações necessárias para decidir?
7. O produto e o preço no carrinho corresponderam ao escolhido?
8. Teve dúvida sobre como simular o início da compra sem finalizar o pagamento?
9. Em algum momento se perdeu ou voltou mais de uma vez?
10. Algum elemento atrapalhou a leitura ou o clique?
11. O que mudaria para tornar o fluxo mais simples?
12. Como avaliaria a experiência no geral?

## 7. Critérios de sucesso — balanço

| Critério do enunciado | Resultado |
|---|---|
| ≥ 90% dos usuários adicionam o produto ao carrinho | **Não verificável com 1 participante**; P01 concluiu (100% individual) |
| Tempo médio até 4 minutos | **Atendido** (28 s no total, média de 4 s por tarefa) |
| Filtros Full e melhores avaliados aplicados | **Parcial**: Full aplicado; ordenação por avaliação não encontrada |
| Evidências e explicação da execução | Presentes neste relatório |

## 8. Recomendações

1. Repetir o teste com pelo menos 10 participantes para avaliar o critério de
   90% de sucesso; com 1 participante o resultado é apenas individual.
2. Aplicar o formulário SUS e o questionário qualitativo na próxima execução,
   para complementar eficiência com percepção de usabilidade.
3. Investigar o ponto de atrito da tarefa 3: confirmar se a ordenação por
   "melhores avaliados" existe no site e, se existir, por que não foi encontrada.
4. Destacar as avaliações na página do produto ou torná-las mais visíveis, já
   que o participante escolheu sem consultá-las.
5. Repetir a medição com participante sem conta autenticada, para isolar o
   efeito do login já pronto sobre o tempo da tarefa 6.
