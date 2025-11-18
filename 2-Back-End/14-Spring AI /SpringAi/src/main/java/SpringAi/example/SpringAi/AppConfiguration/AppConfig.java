package SpringAi.example.SpringAi.AppConfiguration;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.VectorStore;
//import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.ai.vectorstore.redis.RedisVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.JedisPooled;

@Configuration
public class AppConfig
{
    /*
    ========== VECTOR STORE CONFIGURATION ==========

    THIS CLASS SETS UP WHERE YOUR AI DATA IS STORED

    VECTOR STORES EXPLAINED:
    - Store text as NUMBER ARRAYS (vectors) for AI search
    - Enable "search by meaning" instead of just keywords
    - Example: "wireless audio" finds "Bluetooth Earbuds"
    */

    // ========== SIMPLE VECTOR STORE (COMMENTED OUT) ==========
    // In-memory storage - loses data when app restarts
    // Not suitable for production
    /*
    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel)
    {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
    */

    // ========== POSTGRESQL VECTOR STORE (COMMENTED OUT) ==========
    //
    // PGVECTOR: PostgreSQL database with vector extensions
    // - Stores vectors in SQL database tables
    // - Good for complex queries + vector search together
    // - We're NOT using this - switched to Redis instead
    //
    /*
    @Bean
    @Primary
    public VectorStore vectorStore(JdbcTemplate jdbcTemplate, EmbeddingModel embeddingModel) {
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .dimensions(384)  // all-minilm:latest uses 384 dimensions
                .distanceType(PgVectorStore.PgDistanceType.COSINE_DISTANCE)
                .initializeSchema(true)
                .indexType(PgVectorStore.PgIndexType.HNSW)
                .build();
    }
    */

    // ========== REDIS CONNECTION SETUP ==========
    //
    // Creates connection to Redis running in Docker
    // Redis is our VECTOR DATABASE - stores product embeddings
    //
    @Bean
    public JedisPooled jedisPooled()
    {
        // Connects to Redis on localhost port 6380
        // Port 6380 = Your Docker Redis container
        // (Not 6379 - that's already used by another Redis)
        return new JedisPooled("localhost", 6380);  // CHANGED to 6380
    }

    // ========== REDIS VECTOR STORE (PRIMARY - CURRENTLY USING) ==========
    //
    // REDIS VECTOR STORE: In-memory database for AI embeddings
    // - Super fast for vector similarity searches
    // - Perfect for product semantic search
    // - Stores everything as NUMBER PATTERNS
    //
    // HOW IT WORKS:
    // 1. Products → Converted to 384-number arrays (vectors)
    // 2. Stored in Redis with semantic meaning patterns
    // 3. When you search → Query becomes vectors too
    // 4. Finds products with similar number patterns
    //
    @Bean
    @Primary  // This tells Spring: "Use Redis as the main vector store"
    public VectorStore VectorRedisStore
    (
            JedisPooled jedisPooled,      // Redis connection
            EmbeddingModel embeddingModel // AI model that creates vectors
    )
    {
        return RedisVectorStore.builder(jedisPooled, embeddingModel)
                .indexName("product-index")    // Name for product search index
                .initializeSchema(true)        // Auto-create Redis data structures
                .build();

        // ========== WHAT THIS BUILDS ==========
        //
        // In Redis, your products are stored as:
        // KEY: product:123
        // VALUE: [0.12, -0.45, 0.78, ...] (384 numbers)
        //
        // SEARCH EXAMPLE:
        // You: "portable audio device"
        // → Vector: [0.11, -0.44, 0.77, ...]
        // → Redis finds similar: "Bluetooth Earbuds" [0.12, -0.45, 0.78, ...]
        // → Returns: Products that MEAN the same thing!
        //
    }
}
