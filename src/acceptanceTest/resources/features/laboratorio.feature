# language: pt
Funcionalidade: testar operações Crud (Create/Post, Read/Get, Update/Put e Delete) de Laboratorio
  Como usuário dessa API Rest, desejo cadastrar, consultar, atualizar e deletar Laboratorio
  Dessa forma, ter respostas padrão de sucesso ou de erro
  Para saber se meu sistema é capaz de executar um Crud de Laboratorio

  Contexto:
    Dado ambiente de teste ativado para Agrotis2025 de Laboratorio
    Dado cadastros de Propriedades disponíveis no banco de dados de Laboratorio
      |        nome        |
      |  Laboratorio PPP   |
      |  Laboratorio OOO   |
      |  Laboratorio LLL   |
      |  Laboratorio KKK   |

  Cenario: Get para consultar Laboratorio, com sucesso, pelo LaboratorioController
    Quando uma requisição Get for feita, com nome "Laboratorio LLL" npo filtro, no método search do LaboratorioController
    Entao receber resposta HTTP 200 do LaboratorioController
    E a resposta contém apenas laboratorios com o nome "Laboratorio LLL" do LaboratorioController

