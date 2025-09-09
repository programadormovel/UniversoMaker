-- Inserir usuário administrador padrão
USE universomaker;
GO

-- Verificar se o usuário já existe antes de inserir
IF NOT EXISTS (SELECT 1 FROM Usuarios WHERE Email = 'admin@universomaker.com')
BEGIN
    INSERT INTO Usuarios (Id, Nome, Email, Senha, TipoUsuario, Ativo, DataCriacao, DataAtualizacao) 
    VALUES (
        NEWID(),
        'Administrador', 
        'admin@universomaker.com', 
        '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', -- senha: admin123
        'Administrador', 
        1, 
        GETDATE(), 
        GETDATE()
    );
    PRINT 'Usuário administrador criado com sucesso!';
END
ELSE
BEGIN
    PRINT 'Usuário administrador já existe.';
END