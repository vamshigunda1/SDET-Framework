-- Create dedicated schema for Healenium and set search_path
CREATE SCHEMA IF NOT EXISTS healenium AUTHORIZATION healenium_user;
ALTER ROLE healenium_user SET search_path TO healenium, public;
GRANT ALL ON SCHEMA healenium TO healenium_user;
