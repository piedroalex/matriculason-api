ALTER TABLE usuarios
    ALTER COLUMN senha TYPE VARCHAR(12);

ALTER TABLE administradores DROP CONSTRAINT IF EXISTS unique_cargo;
ALTER TABLE administradores DROP CONSTRAINT IF EXISTS unique_departamento;

ALTER TABLE alunos
    ADD CONSTRAINT fk_aluno_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE administradores
    ADD CONSTRAINT fk_admin_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

