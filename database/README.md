# Banco de Dados - Universo Maker

## Execução dos Scripts

1. **Criar banco e estrutura:**
   ```sql
   sqlcmd -S localhost -i create_database.sql
   ```

2. **Inserir dados de exemplo:**
   ```sql
   sqlcmd -S localhost -i insert_sample_data.sql
   ```

## Estrutura das Tabelas

- **Usuarios**: Administradores, terapeutas, estagiários e famílias
- **Alunos**: Dados dos estudantes
- **Responsaveis**: Familiares vinculados aos alunos
- **Documentos**: Laudos e relatórios em PDF/imagem
- **Avaliacoes**: Observações dos terapeutas
- **PEI**: Planos Educacionais Individualizados
- **PEI_Metas**: Metas específicas de cada PEI
- **Materiais**: Biblioteca de atividades, vídeos e apostilas

## Usuário Padrão
- Email: admin@universomaker.com
- Senha: admin123