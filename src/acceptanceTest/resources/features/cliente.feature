# language: pt
Funcionalidade: testar operações Crud (Create/Post, Read/Get, Update/Put e Delete) de Cliente
  Como usuário dessa API Rest, desejo cadastrar, consultar, atualizar e deletar Cliente
  Dessa forma, ter respostas padrão de sucesso ou de erro
  Para saber se meu sistema é capaz de executar um Crud de Cliente

  Contexto:
    Dado ambiente de teste ativado para Agrotis2025

  Cenario: Post para criar Cliente, com sucesso, pelo ClienteController
    Dado um ClienteDtoRequest, com nome "Robert Martin" e observações "Referência internacional em desenvolvimento de software"
    Quando a requisição Post for feita no ClienteController
    Entao receber resposta HTTP 201 do ClienteController
    E com body na resposta, com "Robert Martin" e observações "Referência internacional em desenvolvimento de software", do ClienteController
    E o Cliente corretamente cadastrado no banco de dados, com nome "Robert Martin" e observações "Referência internacional em desenvolvimento de software"

