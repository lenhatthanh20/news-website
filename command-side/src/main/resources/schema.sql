-- Creating `users` table
DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    mobile_phone VARCHAR(15) NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    last_login TIMESTAMP,
    is_active BOOLEAN,
    CONSTRAINT users_unique_email UNIQUE (email)
);

-- Creating `roles` table
DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE IF NOT EXISTS roles (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT roles_unique_name UNIQUE (name)
);

-- Creating `users_roles` association table
DROP TABLE IF EXISTS users_roles CASCADE;
CREATE TABLE IF NOT EXISTS users_roles (
    user_id VARCHAR(100) REFERENCES users(id),
    role_id VARCHAR(100) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user_id
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_users_roles_role_id
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

-- Creating `permissions` table
DROP TABLE IF EXISTS permissions CASCADE;
CREATE TABLE IF NOT EXISTS permissions (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    CONSTRAINT permissions_unique_name UNIQUE (name)
);

-- Creating `roles-permissions` association table
DROP TABLE IF EXISTS roles_permissions CASCADE;
CREATE TABLE IF NOT EXISTS roles_permissions (
    role_id VARCHAR(100) REFERENCES roles(id),
    permission_id VARCHAR(100) REFERENCES permissions(id),
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_roles_permissions_role_id
            FOREIGN KEY (role_id)
            REFERENCES roles(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT fk_roles_permissions_permission_id
            FOREIGN KEY (permission_id)
            REFERENCES permissions(id)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

-- Creating `audit_logs` table
DROP TABLE IF EXISTS audit_logs CASCADE;
CREATE TABLE IF NOT EXISTS audit_logs (
    id VARCHAR(100) PRIMARY KEY,
    user_id VARCHAR(100),
    activity_type VARCHAR(255),
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);

-- Creating `posts` table
CREATE TABLE posts (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    author_id VARCHAR(100) NOT NULL,
    parent_id VARCHAR(100) DEFAULT NULL,
    title VARCHAR(100) NOT NULL,
    meta_title VARCHAR(100) DEFAULT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    summary TEXT DEFAULT NULL,
    published BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL,
    published_at TIMESTAMP DEFAULT NULL,
    content TEXT DEFAULT NULL,
    CONSTRAINT fk_post_user
        FOREIGN KEY (author_id)
        REFERENCES users(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE UNIQUE INDEX uq_slug ON posts (slug);
CREATE INDEX idx_post_user ON posts (author_id);
CREATE INDEX idx_post_parent ON posts (parent_id);

ALTER TABLE posts
    ADD CONSTRAINT fk_post_parent
    FOREIGN KEY (parent_id)
    REFERENCES posts(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- Creating `post_meta` table
CREATE TABLE post_meta (
    id VARCHAR(100) PRIMARY KEY,
    post_id VARCHAR(100) NOT NULL,
    meta_key VARCHAR(50) NOT NULL,
    content TEXT DEFAULT NULL,
    CONSTRAINT fk_meta_post
        FOREIGN KEY (post_id)
        REFERENCES posts(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX idx_meta_post ON post_meta (post_id);
CREATE UNIQUE INDEX uq_post_meta ON post_meta (post_id, meta_key);

-- Creating `comments` table
CREATE TABLE comments (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    post_id VARCHAR(100) NOT NULL,
    parent_id VARCHAR(100) DEFAULT NULL,
    published BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    published_at TIMESTAMP DEFAULT NULL,
    content TEXT DEFAULT NULL,
    CONSTRAINT fk_comment_post
        FOREIGN KEY (post_id)
        REFERENCES posts(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX idx_comment_post ON comments (post_id);
CREATE INDEX idx_comment_parent ON comments (parent_id);

ALTER TABLE comments
    ADD CONSTRAINT fk_comment_parent
    FOREIGN KEY (parent_id)
    REFERENCES comments (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- Creating `categories` table
CREATE TABLE categories (
    id VARCHAR(100) PRIMARY KEY,
    version BIGINT NOT NULL,
    parent_id VARCHAR(100) DEFAULT NULL,
    meta_title VARCHAR(100) DEFAULT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    content TEXT DEFAULT NULL
);

CREATE INDEX idx_category_parent ON categories (parent_id);

ALTER TABLE categories
    ADD CONSTRAINT fk_category_parent
    FOREIGN KEY (parent_id)
    REFERENCES categories (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

-- Creating `post_category` table
CREATE TABLE posts_categories (
    post_id VARCHAR(100) NOT NULL,
    category_id VARCHAR(100) NOT NULL,
    PRIMARY KEY (post_id, category_id),
    CONSTRAINT fk_posts_categories_post_id
        FOREIGN KEY (post_id)
        REFERENCES posts (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_posts_categories_category_id
        FOREIGN KEY (category_id)
        REFERENCES categories (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX idx_posts_categories_category ON posts_categories (category_id);
CREATE INDEX idx_posts_categories_post ON posts_categories (post_id);

-- Creating `tags` table
CREATE TABLE tags (
    id VARCHAR(100) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    meta_title varchar(100) DEFAULT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL,
    content text
);

CREATE TABLE posts_tags (
    post_id VARCHAR(100) NOT NULL,
    tag_id VARCHAR(100) NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk_posts_tags_post_id
        FOREIGN KEY (post_id)
        REFERENCES posts (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_posts_tags_tag_id
        FOREIGN KEY (tag_id)
        REFERENCES tags (id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE INDEX idx_posts_tags_tag ON posts_tags (tag_id);
CREATE INDEX idx_posts_tags_post ON posts_tags (post_id);
