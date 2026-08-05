CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    locale VARCHAR(16) NOT NULL DEFAULT 'en_US',
    disabled BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);

CREATE TABLE IF NOT EXISTS auth (
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    password_hash VARCHAR(64) NOT NULL,
    salt VARCHAR(24),
    last_login_at TIMESTAMPTZ DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS invites (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    code_hash VARCHAR(64) NOT NULL,
    invitee_id  BIGINT REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expired_at TIMESTAMPTZ NOT NULL,
    commited_at TIMESTAMPTZ,
    CONSTRAINT check_expiry_date CHECK (expired_at > created_at)
);

CREATE INDEX IF NOT EXISTS idx_invites_code ON invites(code_hash) WHERE code_hash IS NOT NULL;

CREATE INDEX IF NOT EXISTS idx_invites_user_id ON invites(user_id);

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

