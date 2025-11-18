package SpringAi.example.SpringAi.DataInitializer;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private VectorStore vectorStore;  // This connects to REDIS vector database

    /*
    ========== DATA INITIALIZATION - AI SEARCH SETUP ==========

    WHAT THIS DOES:
    - Reads your product_details.txt file
    - Converts products to VECTORS (number arrays)
    - Stores them in Redis for AI-powered semantic search

    AUTOMATICALLY RUNS when Spring Boot starts up!
    */

    @PostConstruct
    public void InitData() {
        try {
            logger.info("Starting data initialization...");

            // ========== STEP 1: READ PRODUCT FILE ==========
            // Reads product_details.txt from src/main/resources
            TextReader textReader = new TextReader(new ClassPathResource("product_details.txt"));

            // ========== STEP 2: SPLIT INTO CHUNKS ==========
            //
            // TOKEN TEXT SPLITTER EXPLAINED:
            // - Breaks large text into smaller pieces for better AI processing
            // - Parameters: max tokens per chunk, overlap, etc.
            // - Like cutting a long article into paragraphs for easier reading
            //
            TokenTextSplitter splitter = new TokenTextSplitter(500, 30, 20, 500, false);

            // Split products into manageable chunks
            List<Document> documents = splitter.split(textReader.get());
            logger.info("Split documents into {} chunks", documents.size());

            // ========== STEP 3: CONVERT TO VECTORS & STORE IN REDIS ==========
            //
            // VECTOR STORAGE MAGIC HAPPENS HERE:
            // - Each product becomes a 384-dimensional NUMBER ARRAY
            // - Example: "Bluetooth Earbuds" → [0.12, -0.45, 0.78, ...]
            // - Stored in Redis for super-fast semantic search
            //
            // AI EMBEDDING PROCESS:
            // 1. Product text → AI model analyzes meaning
            // 2. Generates 384 numbers capturing semantic features
            // 3. Stores number pattern in Redis vector database
            // 4. Enables "search by meaning" not just keywords
            //
            vectorStore.add(documents);
            logger.info("Successfully added {} documents to vector store", documents.size());

            // ========== NOW READY FOR AI-POWERED SEARCH! ==========
            //
            // Your products are now stored as VECTORS in Redis
            // When you search, the system:
            // 1. Converts your query to vectors
            // 2. Finds similar vector patterns in Redis
            // 3. Returns semantically similar products
            //
            // EXAMPLE:
            // Search: "portable audio"
            // Finds: "Bluetooth Wireless Earbuds" (similar vector pattern)
            // Even though the words don't match exactly!
            //

        } catch (Exception e) {
            logger.error("Failed to initialize data: {}", e.getMessage(), e);
            throw new RuntimeException("Data initialization failed", e);
        }
    }
}
