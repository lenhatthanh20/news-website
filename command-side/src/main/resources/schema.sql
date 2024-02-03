DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS articles CASCADE;
DROP TABLE IF EXISTS comments CASCADE;

-- Creating 'roles' table
CREATE TABLE IF NOT EXISTS roles (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    version BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT roles_unique_name UNIQUE (name)
);

-- Creating 'users' table
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role_id VARCHAR(100),
    version BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT users_unique_email UNIQUE (email),
    CONSTRAINT users_fk_role FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE SET NULL
);

-- Creating 'articles' table
CREATE TABLE IF NOT EXISTS articles (
    id VARCHAR(100) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    user_id VARCHAR(100),
    summary TEXT,
    thumbnail VARCHAR(255) UNIQUE NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    published_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT articles_fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
);

-- Creating 'comments' table
CREATE TABLE IF NOT EXISTS comments (
    id VARCHAR(100) PRIMARY KEY,
    user_id VARCHAR(100),
    content TEXT,
    article_id VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT comments_fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
    CONSTRAINT comments_fk_article FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE
);
