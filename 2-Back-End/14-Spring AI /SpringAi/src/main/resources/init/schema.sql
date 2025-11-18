-- ========== POSTGRESQL VECTOR STORE SCHEMA (COMMENTED OUT - NOT USING POSTGRESQL) ==========
--
-- THIS FILE IS FOR POSTGRESQL + PGVECTOR SETUP
-- We're NOT using this anymore since we switched to Redis
-- Keeping for reference if we need PostgreSQL later
--

-- Enable PostgreSQL vector extensions for AI embeddings
-- VECTOR: Adds support for storing number arrays (vectors)
-- HSTORE: Adds key-value store capabilities
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;

-- ========== VECTOR STORAGE TABLE ==========
--
-- This table stores text as NUMBER ARRAYS (vectors) for AI semantic search
-- Each row represents a piece of text converted to mathematical form
--
-- EXAMPLE:
-- Product: "Bluetooth Wireless Earbuds" 
-- Becomes: [0.12, -0.45, 0.78, 0.23, -0.89, ...] (384 numbers)
-- This enables searching by MEANING instead of just keywords
--
CREATE TABLE IF NOT EXISTS vector_store (
    id TEXT PRIMARY KEY,                    -- Unique identifier for each vector
    content TEXT,                           -- Original text (e.g., product description)
    metadata JSONB,                         -- Additional info as JSON (category, price, etc.)
    embedding VECTOR(384)                   -- 384-dimensional number array
    -- ^^ 384 dimensions = 384 different meaning features
    -- Each number represents some semantic aspect (electronics, portable, audio, etc.)
);

-- ========== AI SEARCH INDEX ==========
--
-- HNSW Index: High-performance index for vector similarity search
-- Makes semantic search FAST - finds similar vectors quickly
--
-- COSINE SIMILARITY EXPLAINED:
-- - Measures how similar two vectors are by their "direction"
-- - Same direction = similar meaning (cosine = 1.0)
-- - Opposite direction = opposite meaning (cosine = -1.0)
-- - Example: "cat" and "kitten" have high cosine similarity
--
CREATE INDEX IF NOT EXISTS vector_store_embedding_idx
ON vector_store
USING HNSW (embedding vector_cosine_ops);
-- ^^ This index enables: "Find products similar to 'wireless headphones'"
--    by comparing their 384-dimensional vector patterns

-- ========== HOW THIS WOULD WORK (IF WE WERE USING POSTGRESQL) ==========
--
-- 1. Your products get converted to 384-number arrays
--    "Bluetooth Earbuds" → [0.12, -0.45, 0.78, ...]
--    "Noise-canceling Headphones" → [0.14, -0.43, 0.76, ...]
--
-- 2. When you search "wireless audio", it becomes: [0.13, -0.44, 0.77, ...]
--
-- 3. PostgreSQL finds the closest matching vectors using cosine similarity
--
-- 4. Returns: "Bluetooth Earbuds" and "Noise-canceling Headphones"
--    Because their vector patterns are similar to your search query
--

-- ========== CURRENT REALITY ==========
--
-- We're using REDIS instead of PostgreSQL for vector storage because:
-- - Redis is faster for simple vector searches
-- - Easier to set up and manage
-- - Our product search doesn't need complex SQL queries
--
-- Your actual vector storage happens in Redis, not PostgreSQL!
--
