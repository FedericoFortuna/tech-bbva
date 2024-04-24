-- Crear la tabla bank_service
CREATE TABLE if not exists bank_service (
    bank_service_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    sector VARCHAR(255) NOT NULL,
    q_served INT,
    ex_att BOOLEAN,
    ex_tech_support BOOLEAN
);

-- Insertar valores para la enumeración BankService
INSERT INTO bank_service (name, sector, q_served, ex_att, ex_tech_support) VALUES
('PRESTAMO', 'Bancario', 100, TRUE, FALSE),
('PLAZO_FIJO', 'Bancario', 50, FALSE, TRUE),
('CHEQUE', 'Bancario', 200, TRUE, TRUE),
('TECNOLOGIA', 'Tecnología', NULL, TRUE, TRUE),
('RRHH', 'Recursos Humanos', NULL, FALSE, FALSE);