-- Seleciona o banco de dados para uso
USE db_universo_marker_v1;

/** TABELA: roles **/
INSERT INTO roles (role_name, description, is_active, created_at) VALUES 
('ROLE_ADMIN', 'Administra o sistema', true, now()),
('ROLE_GUARDIAN', 'Responsável', true, now()),
('ROLE_PROFESSIONAL', 'Profissional', true, now()),
('ROLE_TEACHER', 'Professor', true, now()),
('ROLE_USER', 'Role padrão', true, now());

/** TABELA: users - APENAS PARA TESTES **/
INSERT INTO users (fullname, username, password, profile_image_url, is_active, created_at, updated_at, status_user) VALUES 
('Ordnael Zurc', 'ordnaelzurc@email.com.br', '$2a$10$AVtCu6QKdf6UNuyEDfUBDO4FUBpRYjB/JioiF7n4f7q7syeT/lPcy', null, 1, now(), null, 'ACTIVE');

/** TABELA: message_to_admin - APENAS PARA TESTES **/
INSERT INTO message_to_admin (sender, email, subject, message, is_active, created_at) VALUES
('João Silva', 'joao.silva@email.com', 'Solicitação de acesso', 
 'Gostaria de solicitar acesso ao sistema de relatórios.', TRUE, '2025-11-20 09:15:00'),
('Maria Oliveira', 'maria.oliveira@email.com', 'Inativação de conta', 
 'Favor inativar minha conta pois não utilizarei mais o serviço.', TRUE, '2025-11-21 14:30:00'),
('Carlos Souza', 'carlos.souza@email.com', 'Outro assunto', 
 'Tenho dúvidas sobre a política de privacidade.', TRUE, '2025-11-22 11:45:00'),
('Ana Pereira', 'ana.pereira@email.com', 'Solicitação de acesso', 
 'Preciso de acesso ao módulo financeiro para análise de dados.', TRUE, '2025-11-23 08:20:00'),
('Fernanda Lima', 'fernanda.lima@email.com', 'Outro assunto', 
 'Gostaria de atualizar meu endereço de e-mail cadastrado.', FALSE, '2025-11-24 16:10:00');

/** TABELA: user_role - APENAS PARA TESTES **/
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

/** TABELA: person_type **/
INSERT INTO person_type (type_name, description, is_active, created_at) VALUES
('Professor', 'Responsável por ministrar aulas e orientar alunos', true, now()),
('Responsável', 'Pessoa encarregada por um aluno ou paciente', true, now()),
('Aluno', 'Pessoa matriculada em cursos ou disciplinas', true, now()),
('Paciente', 'Pessoa que recebe atendimento médico ou terapêutico', true, now()),
('Profissional', 'Pessoa que atua em área técnica ou especializada', true, now()),
('Administrador', 'Responsável pela gestão administrativa do sistema', true, now()),
('Gestor', 'Responsável por coordenar equipes e processos', true, now());

/** TABELA: relationship_type **/
INSERT INTO relationship_type (type_name, description, is_active, created_at) VALUES
('Pai/Mãe', 'Responsável direto pela criança', TRUE, now()),
('Avô/Avó', 'Ascendentes diretos dos pais', TRUE, now()),
('Tio/Tia', 'Irmãos dos pais', TRUE, now()),
('Responsável Legal', 'Pessoa com guarda legal', TRUE, now());

/** TABELA: contact_type **/
INSERT INTO contact_type (type_name, description, is_active, created_at) VALUES
('Celular', 'Telefone móvel', TRUE, now()),
('Telefone Fixo', 'Telefone residencial ou comercial', TRUE, now()),
('Email', 'Endereço eletrônico', TRUE, now()),
('Website', 'Página institucional ou pessoal', TRUE, now());

/** TABELA: class_council **/
INSERT INTO class_council (council_name, description, is_active, created_at) VALUES
('SEM CONSELHO', 'Não possui conselho de clsse', TRUE, now()),
('CFM/CRM', 'Conselho Federal de Medicina (CFM) / Conselho Regional de Medicina (CRM): Para médicos.', TRUE, now()),
('CFO/CRO', 'Conselho Federal de Odontologia (CFO) / Conselho Regional de Odontologia (CRO): Para cirurgiões-dentistas.', TRUE, now()),
('COFEN/COREN', 'Conselho Federal de Enfermagem (COFEN) / Conselho Regional de Enfermagem (COREN): Para enfermeiros, técnicos e auxiliares de enfermagem.', TRUE, now()),
('COFFITO/CREFIT', 'Conselho Federal de Fisioterapia e Terapia Ocupacional (COFFITO) / Conselho Regional de Fisioterapia e Terapia Ocupacional (CREFIT): Para fisioterapeutas e terapeutas ocupacionais.', TRUE, now()),
('CFM/CRF', 'Conselho Federal de Farmácia (CFM) / Conselho Regional de Farmácia (CRF): Para farmacêuticos.', TRUE, now()),
('CFN/CRN', 'Conselho Federal de Nutrição (CFN) / Conselho Regional de Nutricionistas (CRN): Para nutricionistas.', TRUE, now()),
('CFP/CRP', 'Conselho Federal de Psicologia (CFP) / Conselho Regional de Psicologia (CRP): Para psicólogos.', TRUE, now()),
('CFMV/CRMV', 'Conselho Federal de Medicina Veterinária (CFMV) / Conselho Regional de Medicina Veterinária (CRMV): Para médicos veterinários e zootecnistas.', TRUE, now());

/** TABELA: specialty **/
INSERT INTO specialty (specialty_name, description, council_id, is_active, created_at) VALUES
('Enfermeiro', 'Profissional da área da saúde', 4, TRUE, now()),
('Psicopedagogo', 'Especialista em dificuldades de aprendizagem', 8, TRUE, now()),
('Psicólogo', 'Profissional da saúde mental', 8, TRUE, now()),
('Terapeuta Ocupacional', 'Foco em reabilitação funcional', 1, TRUE, now());

/** TABELA: institution_type **/
INSERT INTO institution_type (type_name, description, is_active, created_at) VALUES
('Escola', 'Instituição de ensino', TRUE, now()),
('Clínica', 'Estabelecimento de saúde', TRUE, now()),
('Consultório', 'Local de atendimento profissional', TRUE, now());

/** TABELA: professional_role **/
INSERT INTO professional_role (professional_role_name, description, is_active, created_at) VALUES
('Coordenador', 'Responsável pela coordenação pedagógica', TRUE, now()),
('Diretor', 'Gestor da instituição', TRUE, now()),
('Orientador Educacional', 'Apoio ao desenvolvimento escolar', TRUE, now()),
('Professor', 'Responsável pelo ensino de disciplinas', TRUE, now());

/** TABELA: evaluation_type **/
INSERT INTO evaluation_type (type_name, description, is_active, created_at) VALUES
('Acompanhamento', 'Monitoramento contínuo do desenvolvimento', TRUE, now()),
('Anamnese', 'Levantamento inicial de informações', TRUE, now()),
('Avaliação Diagnóstica', 'Identificação de dificuldades específicas', TRUE, now());

/** TABELA: evaluation_item **/
INSERT INTO evaluation_item (item_name, description, is_active, created_at)
VALUES 
('Pressão arterial (PA)', 'Medida da força do sangue contra as paredes das artérias, geralmente aferida com um esfigmomanômetro.', TRUE, NOW()),
('Frequência cardíaca (FC)', 'O número de batimentos cardíacos por minuto (BPM), verificado através do pulso (palpação) ou estetoscópio.', TRUE, NOW()),
('Frequência respiratória (FR)', 'O número de respirações por minuto.', TRUE, NOW()),
('Temperatura corporal', 'A temperatura interna do corpo, medida com um termômetro.', TRUE, NOW()),
('Saturação de oxigênio (SpO2)', 'A quantidade de oxigênio no sangue, medida com um oxímetro de pulso (considerado o quinto sinal vital).', TRUE, NOW()),
('Peso', 'Medida do peso do indivíduo', TRUE, NOW()),
('Altura', 'Medida da altura do indivíduo', TRUE, NOW()),
('Circunferência abdominal', 'Medida da circunferência abdominal do individuo', TRUE, NOW());

/** TABELA: question_category (assumindo os IDs 1=Acompanhamento, 2=Anamnese, 3=Diagnóstica) **/
INSERT INTO question_category (type_id, category_name, description, is_active, created_at) VALUES
(2, 'Histórico Familiar', 'Informações sobre antecedentes familiares', TRUE, now()),
(2, 'Hábitos', 'Rotinas e comportamentos diários', TRUE, now()),
(2, 'Sintomas', 'Sinais observados pela família', TRUE, now()),
(3, 'Cognitivo', 'Capacidades mentais e intelectuais', TRUE, now()),
(3, 'Motor', 'Habilidades físicas e coordenação', TRUE, now()),
(3, 'Comportamental', 'Aspectos emocionais e sociais', TRUE, now());

/** TABELA: classification **/
INSERT INTO classification (classification_name, description, is_active, created_at) VALUES
('Dentro da média', 'Desempenho esperado para a faixa etária', TRUE, now()),
('Abaixo do esperado', 'Desempenho inferior ao esperado', TRUE, now()),
('Acima da média', 'Desempenho superior ao esperado', TRUE, now());

/** TABELA: function_type **/
INSERT INTO function_type (function_name, description, is_active, created_at) VALUES
('Cognitiva - Percepção', 'Capacidade de perceber estímulos', TRUE, now()),
('Cognitiva - Atenção', 'Foco e concentração', TRUE, now()),
('Cognitiva - Memória', 'Retenção e evocação de informações', TRUE, now()),
('Cognitiva - Raciocínio Lógico', 'Pensamento estruturado e lógico', TRUE, now()),
('Motora', 'Coordenação e movimento', TRUE, now()),
('Social', 'Relacionamento interpessoal', TRUE, now()),
('Interação Social', 'Capacidade de se comunicar com outros', TRUE, now()),
('Linguagem e Comunicação', 'Expressão verbal e não verbal', TRUE, now()),
('Desempenho Escolar - Comportamento', 'Conduta em ambiente escolar', TRUE, now()),
('Desempenho Escolar - Foco e Interesse', 'Engajamento nas atividades escolares', TRUE, now()),
('Língua Portuguesa', 'Domínio da linguagem escrita e falada', TRUE, now()),
('Matemática', 'Raciocínio numérico e lógico', TRUE, now());

/** TABELA: educational_need **/
INSERT INTO educational_need (educational_need_name, description, is_active, created_at) VALUES
('Atendimento Clínico', 'Necessidade de acompanhamento terapêutico', TRUE, now()),
('Acompanhamento Médico', 'Consultas regulares com especialistas', TRUE, now()),
('Uso de Medicamento', 'Tratamento farmacológico indicado', TRUE, now());




