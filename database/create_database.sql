-- Script de criação do banco de dados Universo Maker
-- SQL Server 2019

USE master;
GO

-- Criar banco de dados
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'UniversoMaker')
BEGIN
    CREATE DATABASE UniversoMaker;
END
GO

USE UniversoMaker;
GO

-- Tabela de Usuários (Administradores, Terapeutas, Estagiários, Famílias)
CREATE TABLE Usuarios (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    Nome NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    Senha NVARCHAR(255) NOT NULL,
    TipoUsuario NVARCHAR(20) NOT NULL CHECK (TipoUsuario IN ('Administrador', 'Terapeuta', 'Estagiario', 'Familia')),
    Ativo BIT DEFAULT 1,
    DataCriacao DATETIME2 DEFAULT GETDATE(),
    DataAtualizacao DATETIME2 DEFAULT GETDATE()
);

-- Tabela de Alunos
CREATE TABLE Alunos (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    Nome NVARCHAR(100) NOT NULL,
    DataNascimento DATE NOT NULL,
    CPF NVARCHAR(14) UNIQUE,
    Foto NVARCHAR(255),
    HistoricoEscolar NVARCHAR(MAX),
    HistoricoClinico NVARCHAR(MAX),
    Ativo BIT DEFAULT 1,
    DataCriacao DATETIME2 DEFAULT GETDATE(),
    DataAtualizacao DATETIME2 DEFAULT GETDATE()
);

-- Tabela de Responsáveis
CREATE TABLE Responsaveis (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    AlunoId UNIQUEIDENTIFIER NOT NULL,
    UsuarioId UNIQUEIDENTIFIER NOT NULL,
    Nome NVARCHAR(100) NOT NULL,
    Parentesco NVARCHAR(50) NOT NULL,
    Telefone NVARCHAR(20),
    FOREIGN KEY (AlunoId) REFERENCES Alunos(Id),
    FOREIGN KEY (UsuarioId) REFERENCES Usuarios(Id)
);

-- Tabela de Documentos/Laudos
CREATE TABLE Documentos (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    AlunoId UNIQUEIDENTIFIER NOT NULL,
    UsuarioId UNIQUEIDENTIFIER NOT NULL,
    TipoDocumento NVARCHAR(50) NOT NULL,
    NomeArquivo NVARCHAR(255) NOT NULL,
    CaminhoArquivo NVARCHAR(500) NOT NULL,
    Descricao NVARCHAR(500),
    DataUpload DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (AlunoId) REFERENCES Alunos(Id),
    FOREIGN KEY (UsuarioId) REFERENCES Usuarios(Id)
);

-- Tabela de Avaliações
CREATE TABLE Avaliacoes (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    AlunoId UNIQUEIDENTIFIER NOT NULL,
    TerapeutaId UNIQUEIDENTIFIER NOT NULL,
    TipoAvaliacao NVARCHAR(50) NOT NULL,
    Observacoes NVARCHAR(MAX) NOT NULL,
    Progresso NVARCHAR(MAX),
    DataAvaliacao DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (AlunoId) REFERENCES Alunos(Id),
    FOREIGN KEY (TerapeutaId) REFERENCES Usuarios(Id)
);

-- Tabela de PEI (Plano Educacional Individualizado)
CREATE TABLE PEI (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    AlunoId UNIQUEIDENTIFIER NOT NULL,
    CriadorId UNIQUEIDENTIFIER NOT NULL,
    Versao INT NOT NULL DEFAULT 1,
    Status NVARCHAR(20) DEFAULT 'Rascunho' CHECK (Status IN ('Rascunho', 'Aprovado', 'Arquivado')),
    DataCriacao DATETIME2 DEFAULT GETDATE(),
    DataAprovacao DATETIME2,
    FOREIGN KEY (AlunoId) REFERENCES Alunos(Id),
    FOREIGN KEY (CriadorId) REFERENCES Usuarios(Id)
);

-- Tabela de Metas do PEI
CREATE TABLE PEI_Metas (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    PEIId UNIQUEIDENTIFIER NOT NULL,
    Meta NVARCHAR(500) NOT NULL,
    Estrategia NVARCHAR(MAX),
    ResponsavelId UNIQUEIDENTIFIER,
    DataInicio DATE,
    DataFim DATE,
    Status NVARCHAR(20) DEFAULT 'Pendente' CHECK (Status IN ('Pendente', 'Em Andamento', 'Concluida')),
    FOREIGN KEY (PEIId) REFERENCES PEI(Id),
    FOREIGN KEY (ResponsavelId) REFERENCES Usuarios(Id)
);

-- Tabela de Materiais
CREATE TABLE Materiais (
    Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    Titulo NVARCHAR(200) NOT NULL,
    Descricao NVARCHAR(500),
    TipoMaterial NVARCHAR(50) NOT NULL CHECK (TipoMaterial IN ('Atividade', 'Video', 'Apostila')),
    CaminhoArquivo NVARCHAR(500) NOT NULL,
    CriadorId UNIQUEIDENTIFIER NOT NULL,
    Publico BIT DEFAULT 1,
    DataCriacao DATETIME2 DEFAULT GETDATE(),
    FOREIGN KEY (CriadorId) REFERENCES Usuarios(Id)
);

-- Índices para performance
CREATE INDEX IX_Usuarios_Email ON Usuarios(Email);
CREATE INDEX IX_Alunos_Nome ON Alunos(Nome);
CREATE INDEX IX_Documentos_AlunoId ON Documentos(AlunoId);
CREATE INDEX IX_Avaliacoes_AlunoId ON Avaliacoes(AlunoId);
CREATE INDEX IX_PEI_AlunoId ON PEI(AlunoId);

-- Inserir usuário administrador padrão
INSERT INTO Usuarios (Nome, Email, Senha, TipoUsuario) 
VALUES ('Administrador', 'admin@universomaker.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Administrador');

PRINT 'Banco de dados UniversoMaker criado com sucesso!';