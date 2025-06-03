# language: pt
Funcionalidade: testar operações Crud (Create/Post, Read/Get, Update/Put e Delete) de Cliente
  Como usuário dessa API Rest, desejo cadastrar, consultar, atualizar e deletar Cliente
  Dessa forma, ter respostas padrão de sucesso ou de erro
  Para saber se meu sistema é capaz de executar um Crud de Cliente

  Contexto:
    Dado ambiente de teste ativado para Agrotis2025
    Dado cadastros de Propriedades disponíveis no banco de dados
    |        nome        |
    |    Fazenda XT2     |
    |    Fazenda HR5     |
    Dado cadastros de Laboratórios disponíveis no banco de dados
    |        nome        |
    |  Laboratorio XT2   |
    |  Laboratorio HR5   |

  Cenario: Post para criar Cliente, com sucesso, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e ProprietarioDtoRequest, com nome "Fazenda XT2", e LaboratorioDtoRequest, com nome "Laboratorio XT2"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 201 do ClienteController
    E com Cliente, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e Proprietario, com nome "Fazenda XT2", e LaboratorioDtoRequest, com nome "Laboratorio XT2", no body da resposta do ClienteController
    E o Cliente corretamente cadastrado no banco de dados, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste"

  Cenario: Post para criar Cliente, com erro 404 not found de Propriedade, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e ProprietarioDtoRequest inexistente e LaboratorioDtoRequest, com nome "Laboratorio XT2"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 404 do ClienteController

  Cenario: Post para criar Cliente, com erro 404 not found de Laboratório, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e Proprietario, com nome "Fazenda XT2", e LaboratorioDtoRequest inexistente
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 404 do ClienteController
