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
    |    Fazenda CCC     |
    |    Fazenda BBN     |
    Dado cadastros de Laboratórios disponíveis no banco de dados
    |        nome        |
    |  Laboratorio XT2   |
    |  Laboratorio HR5   |
    |  Laboratorio CCC   |
    |  Laboratorio BBN   |
    Dado cadastros de Clientes disponíveis no banco de dados
    |        nome        |      dataInicial      |       dataFinal       |     observacoes      |     nomePropriedade     |     nomeLaboratorio     |
    |   Jeff Sutherland  | 2025-01-01T09:30:00Z  | 2025-04-08T09:10:00Z  |        Teste1        |       Fazenda HR5       |     Laboratorio HR5     |
    |     Kent Beck      | 2025-02-07T04:50:10Z  | 2025-08-09T02:12:05Z  |        Teste2        |       Fazenda BBN       |     Laboratorio BBN     |

  Cenario: Post para criar Cliente, com sucesso, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e PropriedadeDtoRequest, com nome "Fazenda XT2", e LaboratorioDtoRequest, com nome "Laboratorio XT2"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 201 do ClienteController
    E com Cliente, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e Propriedade, com nome "Fazenda XT2", e Laboratorio, com nome "Laboratorio XT2", no body da resposta do ClienteController
    E o Cliente corretamente cadastrado no banco de dados, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste"

  Cenario: Post para criar Cliente, com erro 404 not found de Propriedade, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e ProprietarioDtoRequest inexistente e LaboratorioDtoRequest, com nome "Laboratorio XT2"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 404 do ClienteController

  Cenario: Post para criar Cliente, com erro 404 not found de Laboratório, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "2025-06-05T09:00:00Z" e dataFinal "2025-07-05T09:00:00Z" e observações "Observação de teste", e Proprietario, com nome "Fazenda XT2", e LaboratorioDtoRequest inexistente
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 404 do ClienteController


  Cenario: Get para consultar Cliente, com sucesso, pelo ClienteController
    Dado um identificador ID de um cliente existente, com nome "Jeff Sutherland"
    Quando uma requisição Get for feita no método findById do ClienteController
    Entao receber resposta HTTP 200 do ClienteController
    E com Cliente, com nome "Jeff Sutherland" e dataInicial "2025-01-01T09:30:00Z" e dataFinal "2025-04-08T09:10:00Z" e observações "Teste1", e Propriedade, com nome "Fazenda HR5", e Laboratorio, com nome "Laboratorio HR5", no body da resposta do ClienteController

  Cenario: Get para consultar Cliente, com erro 404 not found, pelo ClienteController
    Dado um identificador ID de um cliente inexistente
    Quando uma requisição Get for feita no método findById do ClienteController
    Entao receber resposta HTTP 404 do ClienteController


  Cenario: Delete para apagar Cliente, com sucesso, pelo ClienteController
    Dado um identificador ID de um cliente existente, com nome "Jeff Sutherland"
    Quando uma requisição Delete for feita no método deleteById do ClienteController
    Entao receber resposta HTTP 204 do ClienteController
    E o Cliente foi apagado do banco de dados pelo ClienteController

  Cenario: Delete para apagar Cliente, com erro 404 not found, pelo ClienteController
    Dado um identificador ID de um cliente inexistente
    Quando uma requisição Delete for feita no método deleteById do ClienteController
    Entao receber resposta HTTP 404 do ClienteController


  Cenario: Put para atualizar Cliente, com sucesso, pelo ClienteController
    Dado um identificador ID de um cliente existente, com nome "Kent Beck"
    Dado um ClienteDtoRequest, com nome "Kentt Beck" e dataInicial "2025-06-06T09:00:03Z" e dataFinal "2025-08-05T09:01:02Z" e observações "Teste3", e PropriedadeDtoRequest, com nome "Fazenda CCC", e LaboratorioDtoRequest, com nome "Laboratorio CCC"
    Quando uma requisição Put for feita no método update do ClienteController
    Entao receber resposta HTTP 200 do ClienteController
    E com Cliente, com nome "Kentt Beck" e dataInicial "2025-06-06T09:00:03Z" e dataFinal "2025-08-05T09:01:02Z" e observações "Teste3", e Propriedade, com nome "Fazenda CCC", e Laboratorio, com nome "Laboratorio CCC", no body da resposta do ClienteController
    E o Cliente corretamente cadastrado no banco de dados, com nome "Kentt Beck" e dataInicial "2025-06-06T09:00:03Z" e dataFinal "2025-08-05T09:01:02Z" e observações "Teste3"

  Cenario: Put para atualizar Cliente, com erro 404 not found, pelo ClienteController
    Dado um identificador ID de um cliente inexistente
    E um ClienteDtoRequest, com nome "Kentt Beck" e dataInicial "2025-06-06T09:00:03Z" e dataFinal "2025-08-05T09:01:02Z" e observações "Teste3", e PropriedadeDtoRequest, com nome "Fazenda CCC", e LaboratorioDtoRequest, com nome "Laboratorio CCC"
    Quando uma requisição Put for feita no método update do ClienteController
    Entao receber resposta HTTP 404 do ClienteController

