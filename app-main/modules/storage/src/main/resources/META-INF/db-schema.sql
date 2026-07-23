CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    email_hash TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    locale TEXT NOT NULL DEFAULT 'en',
    is_disabled INTEGER NOT NULL DEFAULT 0,
    last_login_at TEXT,
    created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ', 'now'))
);

-- default user
INSERT INTO users (username, email_hash, password_hash, locale, is_disabled, last_login_at)
VALUES (
    'admin',
    'fc44cf9659e377f0f670fe4736f731a52b8030ba6d519e426543b573a4b9ca0d', -- admin@example.com (SHA-256)
    '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', -- admin (SHA-256)
    'en_US',
    0,
    NULL
);

CREATE TABLE IF NOT EXISTS scripts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    uuid TEXT NOT NULL UNIQUE,
    title TEXT NOT NULL,
    annotation TEXT,
    authors TEXT,
    version INTEGER,
    created_at TEXT NOT NULL DEFAULT (strftime('%Y-%m-%dT%H:%M:%fZ', 'now'))
);

