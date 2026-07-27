-- Users dictionary
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    email_hash VARCHAR(64) NOT NULL UNIQUE,
    locale VARCHAR(16) NOT NULL DEFAULT 'en_US',
    disabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- default user
INSERT INTO users (id,username, email_hash)
VALUES (
    1,
    'admin',
    'fc44cf9659e377f0f670fe4736f731a52b8030ba6d519e426543b573a4b9ca0d' -- admin@example.com (SHA-256)
);

-- User authentication credentials for different auth providers
CREATE TABLE IF NOT EXISTS auth (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    password_hash VARCHAR(64) NOT NULL,
    last_login_at TIMESTAMPTZ DEFAULT NULL
);

CREATE INDEX idx_auth_user_id ON auth(user_id);

-- login/password auth
INSERT INTO auth (user_id, password_hash)
VALUES (
    1,
    '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' -- admin (SHA-256)
);


CREATE TABLE IF NOT EXISTS scripts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    uuid VARCHAR(32) NOT NULL UNIQUE,
    title VARCHAR(128) NOT NULL,
    annotation VARCHAR(1024),
    authors VARCHAR(64)[],
    version INTEGER,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

