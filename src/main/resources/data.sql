CREATE TABLE IF NOT EXISTS identification_types (
    id SERIAL PRIMARY KEY,
    identification_type VARCHAR NOT NULL UNIQUE
);

INSERT INTO identification_types
    (identification_type)
VALUES
    ('RUC'),
    ('CEDULA')
ON CONFLICT (identification_type) DO NOTHING;