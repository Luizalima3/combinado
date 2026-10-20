-- Rascunhos exigem apenas título. A publicação valida categoria e demais campos.
ALTER TABLE combinados ALTER COLUMN categoria DROP NOT NULL;
