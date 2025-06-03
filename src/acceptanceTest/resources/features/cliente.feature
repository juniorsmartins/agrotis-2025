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

  Cenario: Post para criar Cliente, com sucesso, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e dataInicial "03/06/2025" e dataFinal "03/07/2025" e observações "Observação de teste", e ProprietarioDtoRequest, com nome "Fazenda XT2"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 201 do ClienteController
    E com Cliente, com nome "Robert Martin" e dataInicial "03/06/2025" e dataFinal "03/07/2025" e observações "Observação de teste", e Proprietario, com nome "Fazenda XT2", no body da resposta do ClienteController
    E o Cliente corretamente cadastrado no banco de dados, com nome "Robert Martin" e dataInicial "03/06/2025" e dataFinal "03/07/2025" e observações "Observação de teste"



