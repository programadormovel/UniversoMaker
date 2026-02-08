-- DROP DATABASE db_universo_marker_v1
-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS db_universo_marker_v1
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Seleciona o banco de dados para uso
USE db_universo_marker_v1;

-- Criação da tabela de cidades
CREATE TABLE city (
    id 				BIGINT			AUTO_INCREMENT,
    city_name 		VARCHAR(50) 	NOT NULL UNIQUE,
    uf 				VARCHAR(2)  	NOT NULL,
    ibge_nr			VARCHAR(10) 	NOT NULL,
	city_image_url	VARCHAR(255) 		NULL,
	city_file_url	VARCHAR(255) 		NULL,
	is_active 		BOOLEAN 		DEFAULT TRUE,
    created_at 		DATETIME 		NOT NULL,
    
    PRIMARY KEY(id)
);

-- Criação da tabela de mensagens
CREATE TABLE message_to_admin (
    id 				BIGINT			AUTO_INCREMENT,
    sender 			VARCHAR(50) 	NOT NULL,
    email 			VARCHAR(100)  	NOT NULL,
    subject			VARCHAR(50) 	NOT NULL, -- Solicitação de acesso, Inativação de conta ou Outro assunto
	message	    	VARCHAR(255) 		NULL,
	is_active 		BOOLEAN 		DEFAULT TRUE,
    created_at 		DATETIME 		NOT NULL,
    
    PRIMARY KEY(id)
);

/***** --! DOMÍNIO USER !-- *****/
/*--! QUALIFICAÇÃO DO USUÁRIO !--*/
-- ----------------------------- --
-- Criação da tabela de usuários
CREATE TABLE users (
    id 					BIGINT		 AUTO_INCREMENT,
    fullname			VARCHAR(100) NOT NULL,
    username 			VARCHAR(100) NOT NULL UNIQUE, -- email
    password 			VARCHAR(255) NOT NULL,
    profile_image_url 	VARCHAR(255) NULL,
    is_active 			BOOLEAN 	 DEFAULT TRUE,
    created_at 			DATETIME 	 NOT NULL,
    updated_at 			DATETIME 	 	 NULL,
    status_user			VARCHAR(255) NOT NULL, -- ENUM (ACTIVE, INACTIVE, CHANGE_PASSWORD, BLOCKED)
    
    PRIMARY KEY(id)
);

-- SELECT * FROM users;

-- Criação da tabela de papéis (roles)
CREATE TABLE roles (
    id 			BIGINT		AUTO_INCREMENT,
    role_name 	VARCHAR(50) NOT NULL UNIQUE,
    description TEXT 			NULL,
	is_active 	BOOLEAN 	DEFAULT TRUE,
    created_at 	DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);
-- SELECT * FROM roles;

-- Tabela de relacionamento N:N entre usuários e papéis
CREATE TABLE user_role (
    user_id BIGINT,
    role_id BIGINT,
    
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);
-- SELECT * FROM user_role;

-- Criação da tabela de tokens
CREATE TABLE user_token (
    id 			BIGINT		AUTO_INCREMENT,
    user_id 	BIGINT	 	NOT NULL,
    token 		TEXT 		NOT NULL,
    token_type 	VARCHAR(20) NOT NULL,  -- ENUM ('ACCESS', 'REFRESH')
    is_revoked 	BOOLEAN 	DEFAULT FALSE,
    expires_at 	DATETIME 	NOT NULL,
    created_at 	DATETIME 	NOT NULL,
    
	PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
-- SELECT * FROM user_token;

-- Criação da tabela de logs dos logins
CREATE TABLE login_log (
    id 			BIGINT		AUTO_INCREMENT,
    user_id 	BIGINT	 		NULL,
    ip_address 	VARCHAR(45) 	NULL,
    user_agent 	TEXT 			NULL,
    success 	BOOLEAN 		NULL,
	created_at 	DATETIME 	NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
-- SELECT * FROM login_log;

-- ** Para usos futuros (prevenção de ataques DDoS) ** --
/*
CREATE TABLE access_log (
    id 			BIGINT		 AUTO_INCREMENT,
    user_id 	BIGINT 			NULL, -- Pode ser NULL se o usuário não estiver autenticado
    ip_address 	VARCHAR(45) 	NOT NULL, -- IP de origem da requisição
    endpoint 	VARCHAR(255) 	NOT NULL, -- Caminho da API ou página acessada
    method 		VARCHAR(10) 	NOT NULL, -- Método HTTP (GET, POST, etc.)
    status_code INT 			NOT NULL, -- Código de resposta HTTP (ex: 200, 401, 403, 500)
    user_agent 	TEXT 				NULL, -- Informações do navegador/dispositivo.
	created_at 	DATETIME 		NOT NULL, -- Data e hora da requisição.
    
	PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_city (
    id 			BIGINT		 AUTO_INCREMENT,
    user_id 	BIGINT	 	 NOT NULL,
    city_id 	BIGINT	 	 NOT NULL,
    notes 		TEXT 		 	 NULL,
    created_at 	DATETIME 	 NOT NULL,
    updated_at 	DATETIME 	 	 NULL,
    status_user	VARCHAR(255) NOT NULL, -- ENUM (ACTIVE, INACTIVE, BLOCKED)
    
	PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (city_id) REFERENCES city(id)
);
*/

/***** --! DOMÍNIO PESSOA !-- *****/
/*--! QUALIFICAÇÃO DA PESSOA !--*/
-- ----------------------------- --
-- Criação da tabela de tipo de pessoa
CREATE TABLE person_type (
    id 			BIGINT		AUTO_INCREMENT,
    type_name 	VARCHAR(50) NOT NULL, -- Professor, Responsável, Aluno ou Paciente, Profissional, Administrador, Gestor...
    description TEXT 			NULL,
	is_active 	BOOLEAN 	DEFAULT TRUE,
    created_at 	DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);
-- SELECT * FROM person_type;

-- Criação da tabela de pessoa, caso seja algo mais específico, criar uma tabela própria para a necessidade
CREATE TABLE person (
    id 					BIGINT		 AUTO_INCREMENT,
    user_id 			BIGINT 	 	 NOT NULL,
    type_id				BIGINT 	 	 NOT NULL,
    name 				VARCHAR(100) NOT NULL,
    birth_date			DATE 		 NOT NULL,
    gender				CHAR(2) 	 NOT NULL,
    document			VARCHAR(20)  NOT NULL,
	person_image_url	VARCHAR(255) 	 NULL,
	is_active 			BOOLEAN 	 DEFAULT TRUE,
    created_at 			DATETIME 	 NOT NULL,
    updated_at 			DATETIME 	 	 NULL,
        
	PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (type_id) REFERENCES person_type(id)
);

-- Tabela de tipo de relacionamento
CREATE TABLE relationship_type (
    id 			BIGINT		AUTO_INCREMENT,
    type_name 	VARCHAR(50) NOT NULL, -- Pais ou Avós, Tios, Responsável Legal
    description TEXT 			NULL,
	is_active 	BOOLEAN 	DEFAULT TRUE,
    created_at 	DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Tabela de relacionamento N:N entre Responsável e Aluno
CREATE TABLE guardian_student (
    id				BIGINT		 AUTO_INCREMENT,
    guardian_id 	BIGINT		 NOT NULL,
    student_id 		BIGINT 		 NOT NULL,
    relationship_id BIGINT 		 NOT NULL,
	notes		    VARCHAR(400) 	 NULL,
	is_active 		BOOLEAN 	 DEFAULT TRUE,
	created_at 		DATETIME 	 NOT NULL,
	updated_at 		DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (guardian_id) 	  REFERENCES person(id),
    FOREIGN KEY (student_id) 	  REFERENCES person(id),
    FOREIGN KEY (relationship_id) REFERENCES relationship_type(id)
);

-- Tabela para armazenar os arquivos do aluno
CREATE TABLE student_file (
    id 				BIGINT		 AUTO_INCREMENT,
    student_id		BIGINT	 	 NOT NULL,
    file_url 		VARCHAR(255) NOT NULL,
    description 	TEXT 		     NULL,
	is_active 		BOOLEAN  	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
	updated_at 		DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES person(id)
);

-- Criação da tabela de tipo de contato
CREATE TABLE contact_type (
    id 				BIGINT		AUTO_INCREMENT,
    type_name 		VARCHAR(50) NOT NULL, -- Celular, Telefone Fixo, Email, Website...
    description 	TEXT 			NULL,
	is_active 		BOOLEAN  	DEFAULT TRUE,
	created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Tabela de relacionamento N:N entre Contato e Pessoa
CREATE TABLE person_contact (
    id 			BIGINT		 AUTO_INCREMENT,
    person_id 	BIGINT	 	 NOT NULL,
	type_id		BIGINT	 	 NOT NULL,
	contact		VARCHAR(100) NOT NULL, -- indica o contato da pessoa
	notes		VARCHAR(100)	 NULL,
	is_active 	BOOLEAN 	 DEFAULT TRUE,
    created_at 	DATETIME 	 NOT NULL,
    updated_at 	DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (type_id) 	REFERENCES contact_type(id)
);

/***** --! DOMÍNIO PROFISSIONAL !-- *****/
-- Criação da tabela Conselho de Classe
CREATE TABLE class_council (
    id 				BIGINT		AUTO_INCREMENT,
    council_name 	VARCHAR(50) NOT NULL,
    description 	TEXT 			NULL,
	is_active 		BOOLEAN 	DEFAULT TRUE,
    created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Criação da tabela de Especialidade
CREATE TABLE specialty (
    id 				BIGINT		AUTO_INCREMENT,
    specialty_name 	VARCHAR(50) NOT NULL, -- Enfermeiro, Psicopedagogo, Psicólogo, Terapeuta Ocupacional...
    description 	TEXT 			NULL,
    council_id 		BIGINT	 	NOT NULL, -- Colocar id 1 pra todos
	is_active 		BOOLEAN  	DEFAULT TRUE,
    created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (council_id) REFERENCES class_council(id)
);

-- Tabela de relacionamento N:N entre Profissional e Especialidade
CREATE TABLE professional_specialty (
    id 				BIGINT		 AUTO_INCREMENT,
    person_id 		BIGINT	 	 NOT NULL,
    specialty_id 	BIGINT	 	 NOT NULL,
	document_nr		VARCHAR(20)  NOT NULL, -- indica o registro do profissional no conselho de classe correspondente
	is_active 		BOOLEAN  	 DEFAULT TRUE,
	created_at 		DATETIME 	 NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (specialty_id) REFERENCES specialty(id) 
);


/***** --! DOMÍNIO INSTITUIÇÃO !-- *****/
/*--! QUALIFICAÇÃO DA INSTITUIÇÃO !--*/
-- --------------------------------- --
-- Criação da tabela de tipo de Instituição
CREATE TABLE institution_type (
    id 			BIGINT		AUTO_INCREMENT,
    type_name 	VARCHAR(50) NOT NULL, -- Escola, Clínica, Consultório...
    description TEXT 			NULL,
	is_active 	BOOLEAN 	DEFAULT TRUE,
    created_at 	DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Criação da tabela de Instituição, caso seja algo mais específico, criar uma tabela própria para a necessidade
-- Escola, Clínica, Consultório...
CREATE TABLE institution (
    id 				BIGINT		 AUTO_INCREMENT,
    user_id 		BIGINT	 	 NOT NULL, -- Usuário responsável pela instituição no sistema
    type_id		 	BIGINT	 	 NOT NULL,
    name 			VARCHAR(100) NOT NULL,
    description		VARCHAR(400) 	 NULL,
    zip_code		VARCHAR(10)  NOT NULL,
    address_nr		VARCHAR(20)  	 NULL,
    address_info	VARCHAR(100)  	 NULL,
	is_active 		BOOLEAN 	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
    updated_at 		DATETIME 	 	 NULL,
        
	PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (type_id) REFERENCES institution_type(id)
);

-- Tabela de relacionamento N:N entre Contato e Inbstituição
CREATE TABLE institution_contact (
    id 				BIGINT		 AUTO_INCREMENT,
    institution_id 	BIGINT	 	 NOT NULL,
	type_id			BIGINT	 	 NOT NULL,
	contact			VARCHAR(20)  NOT NULL, -- indica o contato da instituição
    notes			VARCHAR(100)	 NULL,
	is_active 		BOOLEAN 	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
    updated_at 		DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (institution_id) REFERENCES institution(id),
    FOREIGN KEY (type_id) REFERENCES contact_type(id) 
);

-- Tabela de Turma
CREATE TABLE institution_class (
    id 				BIGINT		 AUTO_INCREMENT,
    name 			VARCHAR(100) NOT NULL,
    notes			VARCHAR(300)     NULL,
    academic_year 	CHAR(4) 	 NOT NULL,
	institution_id 	BIGINT	 	 NOT NULL,
	is_active 		BOOLEAN 	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
    updated_at 		DATETIME 	 	 NULL,
    
	PRIMARY KEY (id),
    FOREIGN KEY (institution_id) REFERENCES institution(id)
);

-- Tabela N:N de Aluno e Turma
CREATE TABLE student_class (
    id 			BIGINT		 AUTO_INCREMENT,
    student_id 	BIGINT	 	 NOT NULL,
	class_id	BIGINT	 	 NOT NULL,
	notes		VARCHAR(300) 	 NULL,
	is_active 	BOOLEAN 	 DEFAULT TRUE,
    created_at 	DATETIME 	 NOT NULL,
    updated_at 	DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES person(id),
    FOREIGN KEY (class_id)   REFERENCES institution_class(id)
);

-- Tabela de cargo
CREATE TABLE professional_role (
    id 						BIGINT		AUTO_INCREMENT,
    professional_role_name 	VARCHAR(50) NOT NULL, -- Professor, Coordenador, Diretor...
    description 			TEXT 			NULL,
	is_active 				BOOLEAN 	DEFAULT TRUE,
    created_at 				DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Tabela N:N de Profissional e Turma
CREATE TABLE professional_class (
    id 					 BIGINT		  AUTO_INCREMENT,
    professional_id 	 BIGINT	 	  NOT NULL,
    professional_role_id BIGINT	 	  NOT NULL,
	class_id			 BIGINT	 	  NOT NULL,
	notes				 VARCHAR(300) 	  NULL,
	is_active 			 BOOLEAN 	  DEFAULT TRUE,
    created_at 			 DATETIME 	  NOT NULL,
    updated_at 			 DATETIME 	 	  NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (professional_id) 		REFERENCES person(id),
    FOREIGN KEY (professional_role_id) 	REFERENCES professional_role(id),
    FOREIGN KEY (class_id)   	  		REFERENCES institution_class(id)
);

-- Tabela N:N de Profissional e Instituição
CREATE TABLE professional_institution (
    id 					 BIGINT		  AUTO_INCREMENT,
    professional_id 	 BIGINT	 	  NOT NULL,
    professional_role_id BIGINT	 	  NOT NULL,
	institution_id		 BIGINT	 	  NOT NULL,
	notes				 VARCHAR(300) 	  NULL,
	is_active 			 BOOLEAN 	  DEFAULT TRUE,
    created_at 			 DATETIME 	  NOT NULL,
    updated_at 			 DATETIME 	 	  NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (professional_id) 		REFERENCES person(id),
    FOREIGN KEY (professional_role_id) 	REFERENCES professional_role(id),
    FOREIGN KEY (institution_id)   	  	REFERENCES institution(id)
);

/***** --! DOMÍNIO AVALIAÇÃO !-- *****/
/*--! QUALIFICAÇÃO DA AVALIAÇÃO DIAGNÓSTICA !--*/
-- ------------------------------- --
-- Tipo de avaliação
CREATE TABLE evaluation_type (
    id 				BIGINT		AUTO_INCREMENT,
    type_name 		VARCHAR(50) NOT NULL, -- Anamnese ou Avaliação Diagnóstica ou...
    description 	TEXT 			NULL, 
	is_active 		BOOLEAN 	DEFAULT TRUE,
	created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Avaliação
CREATE TABLE evaluation (
    id 				BIGINT		AUTO_INCREMENT,
	type_id			BIGINT	 	NOT NULL, -- indica se é uma Anamnese ou uma Avaliação Diagnóstica ou ...
    student_id 		BIGINT	 	NOT NULL, -- em quem foi realizada a avaliação
    professional_id BIGINT	 	NOT NULL, -- quem realizou a avaliação
    notes 			TEXT 		NOT NULL,
	rate			INT				NULL,
	is_active 		BOOLEAN 	DEFAULT TRUE,
    created_at 		DATETIME 	NOT NULL,
    updated_at 		DATETIME 		NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) 	  REFERENCES person(id),
    FOREIGN KEY (professional_id) REFERENCES person(id),
    FOREIGN KEY (type_id) 		  REFERENCES evaluation_type(id) 
);

-- Item de avaliação
CREATE TABLE evaluation_item (
    id 				BIGINT		AUTO_INCREMENT,
    item_name 		VARCHAR(50) NOT NULL, -- Peso, Altura, Pressão Arterial...
    description 	TEXT 			NULL, 
	is_active 		BOOLEAN 	DEFAULT TRUE,
	created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- histórico clínico
CREATE TABLE clinical_history (
    id 				BIGINT		AUTO_INCREMENT,
	item_id			BIGINT	 	NOT NULL, -- Peso, Altura, Pressão Arterial...
    student_id 		BIGINT	 	NOT NULL, -- em quem foi realizada a avaliação
    professional_id BIGINT	 	NOT NULL, -- quem realizou a avaliação
    notes 			TEXT 		NOT NULL,
	is_active 		BOOLEAN 	DEFAULT TRUE,
    created_at 		DATETIME 	NOT NULL,
    updated_at 		DATETIME 		NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) 	  REFERENCES person(id),
    FOREIGN KEY (professional_id) REFERENCES person(id),
    FOREIGN KEY (item_id) 		  REFERENCES evaluation_item(id) 
);

-- Categoria da Questão
CREATE TABLE question_category (
    id 				BIGINT		AUTO_INCREMENT,
	type_id			BIGINT	 	NOT NULL, -- indica se é uma Anamnese ou uma Avaliação Diagnóstica ou ...
    category_name 	VARCHAR(50) NOT NULL, -- ex: "Histórico Familiar", "Hábitos", "Sintomas" (Anamnese) ou "Cognitivo", "Motor", "Comportamental" (Avaliação Diagnóstica)
    description 	TEXT 			NULL, 
	is_active 		BOOLEAN 	DEFAULT TRUE,
	created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY (type_id) REFERENCES evaluation_type(id) 
);

-- Questões da Avaliação
CREATE TABLE evaluation_question (
    id 		 	BIGINT	 AUTO_INCREMENT,
    question 	TEXT 	 NOT NULL,
    category_id BIGINT	 NOT NULL, 
    is_active 	BOOLEAN  DEFAULT TRUE,
    created_at	DATETIME NOT NULL,
    updated_at 	DATETIME 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES question_category(id)
);

-- Respostas das Questões da Avaliação
CREATE TABLE evaluation_answer (
    id 				BIGINT	 AUTO_INCREMENT,
    evaluation_id 	BIGINT	 NOT NULL,
    question_id 	BIGINT	 NOT NULL,
    answer_text 	TEXT 	 NOT NULL,
	score 			INT 	 	 NULL, -- opcional, se houver pontuação
	is_active 		BOOLEAN  DEFAULT TRUE,
    created_at		DATETIME NOT NULL,
    updated_at 		DATETIME 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (evaluation_id) REFERENCES evaluation(id),
    FOREIGN KEY (question_id)	REFERENCES evaluation_question(id)
);

-- Arquivos Gerados pela Avaliação
CREATE TABLE evaluation_file (
    id 				BIGINT		 AUTO_INCREMENT,
    evaluation_id	BIGINT	 	 NOT NULL,
    file_url 		VARCHAR(255) NOT NULL,
    description 	TEXT 		     NULL,
	is_active 		BOOLEAN  	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
	updated_at		DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (evaluation_id) REFERENCES evaluation(id)
);

-- Tipo de avaliação
CREATE TABLE classification (
    id 					BIGINT		AUTO_INCREMENT,
    classification_name VARCHAR(50) NOT NULL, -- "Dentro da média", "Abaixo do esperado"...
    description 		TEXT 			NULL, 
	is_active 			BOOLEAN  	 DEFAULT TRUE,
	created_at 			DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

-- Informações sobre o Diagnóstico
CREATE TABLE diagnostic_result (
    id 					BIGINT		AUTO_INCREMENT,
    evaluation_id 		BIGINT	 	NOT NULL,
	professional_id 	BIGINT	 	NOT NULL, -- quem será o responsável pela avaliação
    classification_id	BIGINT	 	NOT NULL,
    recommendations		TEXT 		NOT NULL,
    is_active 			BOOLEAN  	DEFAULT TRUE,
    created_at			DATETIME 	NOT NULL,
    updated_at 			DATETIME 		NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (evaluation_id) 	REFERENCES evaluation(id),
    FOREIGN KEY (professional_id) 	REFERENCES person(id),
    FOREIGN KEY (classification_id) REFERENCES classification(id)
);

/***** --! DOMÍNIO PEI !-- *****/
/*--! QUALIFICAÇÃO DO PEI !--*/
-- ------------------------- --
-- Tipo de função avaliada
CREATE TABLE function_type (
    id 				BIGINT		AUTO_INCREMENT,
    function_name 	VARCHAR(50) NOT NULL, -- "Cognitiva - Percepção", "Cognitiva - Atenção", "Cognitiva - Memória", "Cognitiva - Raciocínio Lógico", "Motora", "Social", "Interacao Social", "Linguagem e Comunicação", "Desempenho Escolar - Comportamento", "Desempenho Escolar - Foco e Interesse", "Língua Portuguesa", "Matemática"
    description 	TEXT 			NULL, 
	is_active 		BOOLEAN  	DEFAULT TRUE,
	created_at 		DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE educational_need (
    id 						BIGINT		AUTO_INCREMENT,
    educational_need_name 	VARCHAR(50) NOT NULL, -- Atendimento Clínico, Acompanhamento Médico, Uso de Medicamento...
    description 			TEXT 			NULL, 
	is_active 				BOOLEAN  	DEFAULT TRUE,
	created_at 				DATETIME 	NOT NULL,
    
    PRIMARY KEY(id)
);

CREATE TABLE pei (
    id 				BIGINT		AUTO_INCREMENT,
    student_id 		BIGINT	 	NOT NULL, -- em quem foi realizada a avaliação
    professional_id BIGINT	 	NOT NULL, -- quem realizou a avaliação
    notes 			TEXT 		NOT NULL,
	diagnostic 		TEXT 			NULL,
	is_active 		BOOLEAN 	DEFAULT TRUE,
    created_at 		DATETIME 	NOT NULL,
    updated_at 		DATETIME 		NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) 	  REFERENCES person(id),
    FOREIGN KEY (professional_id) REFERENCES person(id)
);

CREATE TABLE evaluation_function_pei (
    id 				BIGINT	 AUTO_INCREMENT,
    pei_id 			BIGINT	 NOT NULL,
    function_id 	BIGINT	 NOT NULL,
    notes 			TEXT 	 NOT NULL,
	score 			INT 	 	 NULL, -- opcional, se houver pontuação
	is_active 		BOOLEAN  DEFAULT TRUE,
    created_at		DATETIME NOT NULL,
    updated_at 		DATETIME 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (pei_id) 	  REFERENCES pei(id),
    FOREIGN KEY (function_id) REFERENCES function_type(id)
);

CREATE TABLE educational_need_pei (
    id 					BIGINT	 AUTO_INCREMENT,
    pei_id 				BIGINT	 NOT NULL,
    educational_need_id BIGINT	 NOT NULL,
	professional_id 	BIGINT	 NOT NULL,
    notes 				TEXT 	 NOT NULL,
	is_active 			BOOLEAN  DEFAULT TRUE,
    created_at			DATETIME NOT NULL,
    updated_at 			DATETIME 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (pei_id) 			  REFERENCES pei(id),
    FOREIGN KEY (educational_need_id) REFERENCES educational_need(id),
    FOREIGN KEY (professional_id) 	  REFERENCES person(id)
);

CREATE TABLE pei_monitoring (
    id 					BIGINT	 AUTO_INCREMENT,
    pei_id 				BIGINT	 NOT NULL,
	professional_id 	BIGINT	 NOT NULL,
	notes 				TEXT 	 NOT NULL,
	is_active 			BOOLEAN  DEFAULT TRUE,
    created_at			DATETIME NOT NULL,
    updated_at 			DATETIME 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (pei_id) 		  REFERENCES pei(id),
    FOREIGN KEY (professional_id) REFERENCES person(id)
);

-- Arquivos Gerados pela Avaliação
CREATE TABLE pei_file (
    id 				BIGINT		 AUTO_INCREMENT,
    pei_id			BIGINT	 	 NOT NULL,
    file_url 		VARCHAR(255) NOT NULL,
    description 	TEXT 		     NULL,
	is_active 		BOOLEAN  	 DEFAULT TRUE,
    created_at 		DATETIME 	 NOT NULL,
	updated_at		DATETIME 	 	 NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (pei_id) REFERENCES pei(id)
);

-- SHOW TABLES;
-- CRIAR A TABELA test - Diferente de Avaliação
-- CRIAR OS HISTÓRICO DO ALUNO COM OS DADOS (CIDs, PESO, ALTURA...)







