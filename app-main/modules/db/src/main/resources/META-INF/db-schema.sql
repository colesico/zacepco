CREATE TABLE IF NOT EXISTS scripts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    uuid TEXT NOT NULL UNIQUE,
    title TEXT NOT NULL,
    annotation TEXT,
    authors TEXT,
    version INTEGER,
    created TEXT NOT NULL
);
