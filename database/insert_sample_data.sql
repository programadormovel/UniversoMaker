-- Script de dados de exemplo para teste
USE UniversoMaker;
GO

-- Declarar variáveis para UUIDs
DECLARE @UserId1 UNIQUEIDENTIFIER = NEWID();
DECLARE @UserId2 UNIQUEIDENTIFIER = NEWID();
DECLARE @UserId3 UNIQUEIDENTIFIER = NEWID();
DECLARE @UserId4 UNIQUEIDENTIFIER = NEWID();
DECLARE @UserId5 UNIQUEIDENTIFIER = NEWID();
DECLARE @AlunoId1 UNIQUEIDENTIFIER = NEWID();
DECLARE @AlunoId2 UNIQUEIDENTIFIER = NEWID();

-- Inserir usuários de exemplo
INSERT INTO Usuarios (Id, Nome, Email, Senha, TipoUsuario) VALUES
(@UserId1, 'Dr. João Silva', 'joao.silva@universomaker.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Terapeuta'),
(@UserId2, 'Maria Santos', 'maria.santos@universomaker.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Terapeuta'),
(@UserId3, 'Ana Costa', 'ana.costa@universomaker.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Estagiario'),
(@UserId4, 'Carlos Oliveira', 'carlos@email.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Familia'),
(@UserId5, 'Lucia Ferreira', 'lucia@email.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Familia');

-- Inserir alunos de exemplo
INSERT INTO Alunos (Id, Nome, DataNascimento, CPF, HistoricoEscolar, HistoricoClinico) VALUES
(@AlunoId1, 'Pedro Oliveira', '2015-03-15', '123.456.789-01', 'Cursando 2º ano fundamental', 'Diagnóstico de TEA aos 4 anos'),
(@AlunoId2, 'Sofia Ferreira', '2016-07-22', '987.654.321-02', 'Cursando 1º ano fundamental', 'Acompanhamento fonoaudiológico');

-- Inserir responsáveis
INSERT INTO Responsaveis (AlunoId, UsuarioId, Nome, Parentesco, Telefone) VALUES
(@AlunoId1, @UserId4, 'Carlos Oliveira', 'Pai', '(11) 99999-1111'),
(@AlunoId2, @UserId5, 'Lucia Ferreira', 'Mãe', '(11) 99999-2222');

-- Inserir materiais de exemplo
INSERT INTO Materiais (Titulo, Descricao, TipoMaterial, CaminhoArquivo, CriadorId) VALUES
('Atividade de Coordenação Motora', 'Exercícios para desenvolvimento motor', 'Atividade', '/materiais/atividade_coordenacao.pdf', @UserId2),
('Vídeo Educativo - Cores', 'Vídeo para ensino de cores', 'Video', '/materiais/video_cores.mp4', @UserId2),
('Apostila de Alfabetização', 'Material de apoio para alfabetização', 'Apostila', '/materiais/apostila_alfabetizacao.pdf', @UserId1);

PRINT 'Dados de exemplo inseridos com sucesso!';