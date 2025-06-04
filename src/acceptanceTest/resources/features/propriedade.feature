# language: pt
Funcionalidade: testar operações Crud (Create/Post, Read/Get, Update/Put e Delete) de Propriedade
  Como usuário dessa API Rest, desejo cadastrar, consultar, atualizar e deletar Propriedade
  Dessa forma, ter respostas padrão de sucesso ou de erro
  Para saber se meu sistema é capaz de executar um Crud de Propriedade

  Contexto:
    Dado ambiente de teste ativado para Agrotis2025 de Propriedade
    Dado cadastros de Propriedades disponíveis no banco de dados de Propriedade
      |        nome        |
      |    Fazenda AAA     |
      |    Fazenda BBB     |
      |    Fazenda TTT     |
      |    Fazenda EEE     |

  Cenario: Get para consultar Propriedade, com sucesso, pelo PropriedadeController
    Quando uma requisição Get for feita, com nome "Fazenda BBB" npo filtro, no método search do PropriedadeController
    Entao receber resposta HTTP 200 do PropriedadeController

