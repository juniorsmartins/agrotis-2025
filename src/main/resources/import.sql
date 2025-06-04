INSERT INTO propriedades(propriedade_id, nome) VALUES('f8f2cdf3-377c-4bc6-87bf-4bd4b9fbdd98', 'Fazenda DFF');
INSERT INTO propriedades(propriedade_id, nome) VALUES('03261788-fdf2-4b67-b8b2-bde7a24be1b6', 'Fazenda ABC');
INSERT INTO propriedades(propriedade_id, nome) VALUES('35a0b808-ae05-460c-b62d-4f52492e4e15', 'Fazenda EEE');
INSERT INTO propriedades(propriedade_id, nome) VALUES('775de8eb-4aa3-48c0-80dc-1c7185e44217', 'Fazenda QQQ');

INSERT INTO laboratorios(laboratorio_id, nome) VALUES('86daa8c0-6992-4112-81c4-7f7bd5ba9229', 'Laboratório DFF');
INSERT INTO laboratorios(laboratorio_id, nome) VALUES('b7369d07-9885-4f33-9b63-d72e4c01a210', 'Laboratório ABC');
INSERT INTO laboratorios(laboratorio_id, nome) VALUES('1dd4d5dd-6648-4b8d-828d-f85552ac621d', 'Laboratório WWW');
INSERT INTO laboratorios(laboratorio_id, nome) VALUES('1c01627d-0ba0-4b15-a7fb-14ba5c0b6444', 'Laboratório YYY');

INSERT INTO clientes(cliente_id, nome, data_inicial, data_final, propriedade_id, laboratorio_id, observacoes) VALUES('eb957f38-90c4-4ef2-850c-229fb1658fcd', 'Linus Pauling', TO_TIMESTAMP('2023-10-01 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-03 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), '03261788-fdf2-4b67-b8b2-bde7a24be1b6', 'b7369d07-9885-4f33-9b63-d72e4c01a210', 'TESTE2');

