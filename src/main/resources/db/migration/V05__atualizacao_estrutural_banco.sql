ALTER TABLE usuarios
    ALTER COLUMN senha TYPE VARCHAR(12);

ALTER TABLE administradores DROP CONSTRAINT IF EXISTS UK16vb32dwfq32vxgxd6k6awqrq;
ALTER TABLE administradores DROP CONSTRAINT IF EXISTS UKmims61qt1j972b3ggvn2dgl5c;


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

