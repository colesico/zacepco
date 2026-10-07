PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    locale TEXT NOT NULL DEFAULT 'en_US',
    disabled INTEGER NOT NULL DEFAULT 0 CHECK (disabled IN (0, 1)),
    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP
) STRICT;

CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);

CREATE TABLE IF NOT EXISTS auth (
    user_id INTEGER PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    password_hash TEXT NOT NULL,
    salt TEXT,
    last_login_at TEXT DEFAULT NULL
) STRICT;

CREATE TABLE IF NOT EXISTS invites (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
    code_hash TEXT NOT NULL,
    invitee_id INTEGER REFERENCES users(id) ON DELETE SET NULL,
    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired_at TEXT NOT NULL,
    commited_at TEXT,
    CHECK (expired_at > created_at)
) STRICT;

CREATE INDEX IF NOT EXISTS idx_invites_code
    ON invites(code_hash)
    WHERE code_hash IS NOT NULL;

CREATE INDEX IF NOT EXISTS idx_invites_user_id ON invites(user_id);

CREATE TABLE IF NOT EXISTS cases (
    id INTEGER PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    access TEXT NOT NULL CHECK (access IN ('PRIVATE', 'PROTECTED', 'PUBLIC')),
    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    script_id TEXT NOT NULL,
    script_title TEXT NOT NULL,
    script_annotation TEXT NOT NULL,
    script_authors TEXT NOT NULL,
    script_version INTEGER NOT NULL,
    script_creation_date TEXT NOT NULL
) STRICT;

CREATE UNIQUE INDEX IF NOT EXISTS idx_cases_author_unique
    ON cases (user_id, script_id)
    WHERE access IN ('PRIVATE', 'PROTECTED');

CREATE UNIQUE INDEX IF NOT EXISTS idx_cases_public_unique
    ON cases (script_id)
    WHERE access = 'PUBLIC';

CREATE TABLE IF NOT EXISTS cases_id_sequence (
    id INTEGER PRIMARY KEY CHECK (id = 1),
    value INTEGER NOT NULL
);

INSERT OR IGNORE INTO cases_id_sequence (id, value) VALUES (1, 0);