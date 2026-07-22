/*
 * Copyright (c) 2026 Vladlen V. Larionov.
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may obtain a copy of the License at http://apache.org
 */

CREATE SEQUENCE IF NOT EXISTS script_seq START WITH 1;

CREATE TABLE IF NOT EXISTS scripts (
    id BIGINT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    uuid UUID NOT NULL UNIQUE,
    title VARCHAR(128) NOT NULL,
    annotation VARCHAR(800),
    authors TEXT[],
    version INT,
    created TIMESTAMP WITH TIME ZONE NOT NULL
);
