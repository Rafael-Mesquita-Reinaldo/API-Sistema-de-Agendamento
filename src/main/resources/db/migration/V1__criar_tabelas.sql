CREATE SCHEMA IF NOT EXISTS agendamento;

CREATE TABLE agendamento.usuarios(
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role  varchar(15) NOT NULL,
    CONSTRAINT chk_role CHECK ( role IN('ADMIN','PROFISSIONAL','CLIENTE'))
);

CREATE TABLE agendamento.clientes(
     id UUID PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     telefone VARCHAR(20) NOT NULL,
     usuario_id UUID NOT NULL unique,
     FOREIGN KEY (usuario_id) REFERENCES  agendamento.usuarios(id)
);
CREATE TABLE agendamento.profissionais (
    id UUID PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    usuario_id UUID NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES agendamento.usuarios(id)
);

CREATE TABLE agendamento.servicos (
    id UUID PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    duracao_minutos INTEGER NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);

CREATE TABLE agendamento.agendamentos (
    id UUID PRIMARY KEY,
    cliente_id UUID NOT NULL,
    profissional_id UUID NOT NULL,
    servico_id UUID NOT NULL,

    data DATE NOT NULL,
    hora TIME NOT NULL,
    status VARCHAR(15) NOT NULL,

    FOREIGN KEY (cliente_id) REFERENCES agendamento.clientes(id),
    FOREIGN KEY (profissional_id) REFERENCES agendamento.profissionais(id),
    FOREIGN KEY (servico_id) REFERENCES agendamento.servicos(id),

    CONSTRAINT chk_status CHECK ( status IN( 'AGENDADO','CONCLUIDO','CANCELADO') )
);



