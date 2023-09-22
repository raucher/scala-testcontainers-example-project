CREATE TABLE IF NOT EXISTS records (
    id serial PRIMARY KEY,
    data VARCHAR UNIQUE NOT NULL
);

INSERT INTO records(data)
    VALUES ('record-1'), ('record-2'), ('record-3');
