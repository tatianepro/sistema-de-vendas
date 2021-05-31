INSERT INTO USUARIO (ID, LOGIN, SENHA, ADMIN) VALUES (NULL, 'user', '$2a$10$8dujIx/DK/R4I5rm7Svi5ek.JP3NSUB95cY4MLc/.pS.Dqa4G8Vta', false);

INSERT INTO CLIENTE (ID, NOME, CPF) VALUES (NULL, 'Mario de Andrade', '22455877912');
INSERT INTO CLIENTE (ID, NOME, CPF) VALUES (NULL, 'Tatiane Takeuti', '27708548870');

INSERT INTO PRODUTO (ID, DESCRICAO, PRECO_UNITARIO) VALUES (NULL, 'Notebook', '2975.00');
INSERT INTO PRODUTO (ID, DESCRICAO, PRECO_UNITARIO) VALUES (NULL, 'Impressora', '1000.00');

INSERT INTO PEDIDO (ID, DATA_PEDIDO, TOTAL, STATUS, CLIENTE_ID) VALUES (NULL, CURRENT_DATE, '3000.00', 'REALIZADO' , 1);

INSERT INTO ITEM_PEDIDO (ID, PEDIDO_ID, PRODUTO_ID, QUANTIDADE) VALUES (NULL, 1, 2, 3);