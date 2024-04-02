-- Inserir alguns leilões fictícios
INSERT INTO Leilao (descricao, valor_minimo, status_aberto) VALUES 
('Leilão de arte', 100.00, true),
('Leilão de joias', 500.00, false),
('Leilão de carros', 10000.00, true);

-- Inserir alguns concorrentes fictícios
INSERT INTO Concorrente (nome, cpf) VALUES 
('João Silva', '123.456.789-00'),
('Maria Oliveira', '987.654.321-00'),
('Carlos Santos', '111.222.333-44');

-- Inserir alguns lances fictícios
INSERT INTO Lance (leilao, concorrente, valor) VALUES
(1, 1, 120.00),
(1, 2, 150.00),
(2, 2, 550.00),
(3, 3, 10500.00);
