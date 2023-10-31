CREATE TABLE noticias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    resumo TEXT,
    conteudo TEXT,
    data_publicacao DATETIME,
    nome_autor VARCHAR(255),
    categoria ENUM('ESPORTE', 'POLITICA', 'TECNOLOGIA', 'ENTRETENIMENTO')
);
