package SpringAi.example.SpringAi.AiController;

import SpringAi.example.SpringAi.Models.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
public class AiController {

    // ========== SPRING AI FRAMEWORK - ULTIMATE EXPLANATION ==========
    /*
    WHAT IS SPRING AI?
    ==================
    Spring AI is like a "universal translator" between your Java code and AI models.

    ANALOGY: Think of it as a driver who can operate ANY car (AI model) without you
    needing to learn each car's specific controls.

    WITHOUT Spring AI:
    - You'd need to learn each AI provider's unique API (OpenAI, Anthropic, Ollama, etc.)
    - Write different code for each AI service
    - Handle complex HTTP requests, authentication, error handling manually

    WITH Spring AI:
    - One consistent Java API for all AI models
    - Simple method calls like chatModel.call("Hello")
    - Spring handles all the complex communication behind the scenes

    REAL-WORLD COMPARISON:
    Traditional way: Like needing to speak 10 different languages to talk to people from 10 countries
    Spring AI way: Like having a universal translator - you speak one language, it handles the rest
    */

    // ========== CORE AI COMPONENTS - DEEP DIVE ==========

//    private OpenAiAudioTranscriptionModel AudioTranscriptionModel;
//    private final StabilityAiImageModel ImageModel;
//    private OpenAiAudioSpeechModel AudioSpeechModel;

    @Autowired
    private VectorStore vectorStore;

    /*
    EMBEDDING MODEL: THE "MEANING CONVERTER"
    =========================================
    WHAT IT DOES: Transforms text into mathematical representations (vectors/arrays of numbers)

    DEEPER EXPLANATION:
    - Humans understand words: "cat", "dog", "animal"
    - Computers understand numbers: [0.1, -0.2, 0.8], [0.15, -0.18, 0.75], [0.12, -0.15, 0.82]
    - Embeddings capture SEMANTIC MEANING in number patterns

    HOW IT WORKS BEHIND THE SCENES:
    1. You give it text: "I love programming"
    2. Model breaks it into tokens: ["I", "love", "programming"]
    3. Each token gets converted to numbers based on its meaning
    4. Final output: A large array (e.g., 1024 numbers) representing the entire text's meaning

    VISUAL EXAMPLE:
    Text: "cat"    → Embedding: [0.12, -0.45, 0.78, 0.23, -0.89, ...] (1024 numbers)
    Text: "kitten" → Embedding: [0.14, -0.42, 0.76, 0.25, -0.87, ...] (very similar pattern!)
    Text: "car"    → Embedding: [-0.56, 0.34, -0.12, 0.89, 0.45, ...] (very different pattern!)

    WHY THIS IS MAGICAL: The number patterns preserve relationships!
    - "cat" and "kitten" have similar number patterns (both felines)
    - "cat" and "car" have very different patterns (unrelated concepts)
    */
    @Autowired
    private EmbeddingModel embeddingModel;

    /*
    CHAT MODEL: THE "AI BRAIN"
    ==========================
    WHAT IT DOES: The actual intelligence that understands and generates human-like text

    DEEPER EXPLANATION:
    This is your Large Language Model (LLM) - using ZhiPuAI as configured in application.properties.

    HOW LLMS WORK (Simplified):
    1. TRAINING: Fed billions of text examples (books, websites, articles)
    2. PATTERN LEARNING: Learns grammar, facts, reasoning, writing styles
    3. PREDICTION: Given input, predicts the most likely next words

    ANALOGY: Like a super-smart autocomplete that understands context

    BEHIND THE SCENES FLOW:
    Your Input → Tokenization → Neural Network Processing → Probability Calculation → Text Generation
    */
    private ChatModel chatModel;  // Changed to generic ChatModel interface

    /*
    CHAT CLIENTS: "CONVERSATION MANAGERS"
    =====================================
    These are different "interfaces" for talking to the AI brain, each with special capabilities.

    ANALOGY: Think of different types of phone calls:
    - Regular call: Basic conversation
    - Conference call: Multiple features
    - Recorded call: Remembers everything said

    THREE TYPES EXPLAINED:
    */
    private ChatClient ChatClients;              // BASIC CALL: Simple, no-frills conversation
    private ChatClient ChatClientBuilder;        // ENHANCED CALL: Customizable with extra features
    private ChatClient ChatClientMemory;         // SMART CALL: Remembers conversation history

    /*
    CHAT MEMORY: "CONVERSATION NOTE-TAKER"
    ======================================
    WHAT IT DOES: Stores previous messages to maintain conversation context

    DEEPER EXPLANATION:
    Without memory, each AI interaction is like talking to someone with amnesia.
    With memory, the AI can reference previous exchanges.

    HOW IT WORKS TECHNICALLY:
    - Stores messages in a "window" (e.g., last 10 messages)
    - Automatically attaches relevant history to new prompts
    - Manages context length to avoid overloading the AI

    EXAMPLE FLOW:
    Message 1: "My name is Alice" → Memory: [user: "My name is Alice"]
    Message 2: "What's my name?" → Memory: [user: "My name is Alice", user: "What's my name?"]
    AI Response: "Your name is Alice!" ← Uses memory to answer correctly
    */
    private ChatMemory chatMemory = MessageWindowChatMemory.builder().build();

    // ========== CONSTRUCTOR: AI FACTORY SETUP ==========
    /*
    This constructor is like building an AI factory with different production lines.
    Each "chat client" is a different assembly line with specific capabilities.
    */
    public AiController
    (
            ChatModel chatModel,  // Changed to generic ChatModel interface
            ChatClient.Builder Builder
//            StabilityAiImageModel ImageModel,
//            OpenAiAudioTranscriptionModel AudioTranscriptionModel
    )
    {
        this.chatModel = chatModel;

        /*
        ASSEMBLY LINE 1: BASIC PRODUCTION
        ==================================
        Creates the simplest possible chat client.
        - No extra features
        - Direct connection to AI model
        - Good for: Simple Q&A, one-off requests
        */
        this.ChatClients = ChatClient.create(chatModel);  // Use generic chatModel

        /*
        ASSEMBLY LINE 2: CUSTOMIZED PRODUCTION
        ======================================
        Uses the Builder pattern for customizable clients.
        - Can add special settings
        - More control over behavior
        - Good for: Specialized tasks requiring configuration
        */
        this.ChatClientBuilder = Builder.build();

        /*
        ASSEMBLY LINE 3: SMART PRODUCTION WITH MEMORY
        =============================================
        This is the MOST sophisticated client with conversation memory.

        STEP-BY-STEP BREAKDOWN:
        1. Builder → Our factory base
        2. .defaultAdvisors() → Install "smart features" plugin system
        3. MessageChatMemoryAdvisor → The "memory plugin"
        4. .builder(chatMemory) → Configure memory plugin with our memory storage
        5. .build() → Finalize the memory plugin
        6. .build() → Finalize the entire chat client

        WHAT'S HAPPENING BEHIND THE SCENES:
        - Creates a client that automatically manages conversation history
        - Before sending your message, it attaches relevant previous messages
        - After getting response, it stores both your message and AI's response
        - Maintains context window (removes old messages when limit reached)

        REAL-WORLD USAGE:
        Perfect for chatbots, personal assistants, tutoring systems - anywhere
        where conversation continuity matters.
        */
        this.ChatClientMemory = Builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
//        this.ImageModel=ImageModel;
//        this.AudioTranscriptionModel = AudioTranscriptionModel;
    }

    // ========== API ENDPOINTS: COMPLETE BREAKDOWN ==========

    /*
    ENDPOINT 1: DIRECT MODEL ACCESS - "RAW AI BRAIN"
    =================================================
    PATH: GET /ChatModel/Hello
    USAGE: Most direct way to talk to AI

    TECHNICAL FLOW:
    HTTP Request → Spring MVC → This Method → chatModel.call() → AI Processing → Response

    METHOD BREAKDOWN:
    - @GetMapping: Makes this method accessible via HTTP GET requests
    - @PathVariable: Extracts "Message" from URL path
    - chatModel.call(): Direct method call to AI model
    - Returns: Raw string response from AI

    USE CASE: When you need maximum simplicity and don't need any advanced features.
    */
    @GetMapping("/ChatModel/{Message}")
    public String GetMessageAnswerOfChatModel(@PathVariable String Message) {
        // Direct pipeline to AI brain - no middleware, no features
        String Response = chatModel.call(Message);  // Use generic chatModel
        return Response;
    }

    /*
    ENDPOINT 2: CHAT CLIENT ACCESS - "ENHANCED INTERFACE"
    ======================================================
    PATH: GET /ChatClient/Hello
    USAGE: Better approach with method chaining capabilities

    METHOD CHAINING EXPLANATION:
    .prompt(Message) → Sets up the question/instruction
    .call() → Executes the AI request
    .content() → Extracts just the text content from response

    ADVANTAGES OVER DIRECT MODEL:
    - Cleaner, more readable code
    - Can easily add more operations in the chain
    - Consistent with other Spring AI patterns

    USE CASE: Most everyday AI interactions where you just want the text response.
    */
    @GetMapping("/ChatClient/{Message}")
    public String GetMessageAnswerOfChatClient(@PathVariable String Message) {
        String Response = ChatClients
                .prompt(Message)     // Prepare the message for AI
                .call()              // Send to AI and wait for response
                .content();          // Extract the actual text answer
        return Response;
    }

    /*
    ENDPOINT 3: FULL RESPONSE ACCESS - "DETAILED REPORT"
    =====================================================
    PATH: GET /ChatResponse/Hello
    USAGE: When you need complete information about the AI response

    WHAT IS ChatResponse?
    =====================
    It's a comprehensive object containing:
    - response.getResult().getOutput().getText(): The actual AI response text
    - response.getMetadata().getModel(): Which AI model was used (e.g., "glm-4")
    - response.getMetadata().getUsage(): Token usage statistics
    - response.getMetadata().getFinishReason(): Why generation stopped

    TECHNICAL DETAILS:
    - .chatResponse() returns full object instead of just text
    - Metadata helps with debugging and monitoring
    - Usage statistics help track costs (important for paid APIs)

    USE CASE: Debugging, monitoring, when you need performance metrics.
    */
    @GetMapping("/ChatResponse/{Message}")
    public String GetMessageAnswerOfChatResponse(@PathVariable String Message) {
        ChatResponse Response = ChatClients
                .prompt(Message)
                .call()
                .chatResponse();  // Get complete response package

        // Debug information - useful for development
        System.out.println("The Chat Response MetaData " + Response.getMetadata().getModel());

        return Response.getResult().getOutput().getText();
    }

    /*
    ENDPOINT 4: BUILDER CLIENT - "CUSTOM CONFIGURED"
    =================================================
    PATH: GET /ChatClientBuilder/Hello
    USAGE: Using the pre-configured builder client

    KEY DIFFERENCE:
    While this looks similar to Endpoint 3, this uses the ChatClientBuilder
    instance which could have different underlying configurations than the
    simple ChatClients instance.

    WHY HAVE MULTIPLE CLIENTS?
    ==========================
    Different clients can have different:
    - Temperature settings (creativity)
    - Max token limits
    - Timeout settings
    - Model configurations

    USE CASE: When you want different behavior for different types of requests.
    */
    @GetMapping("/ChatClientBuilder/{Message}")
    public String GetMessageAnswerOfChatClientBuilder(@PathVariable String Message) {
        ChatResponse Response = ChatClientBuilder
                .prompt(Message)
                .call()
                .chatResponse();
        System.out.println("The Chat Response MetaData " + Response.getMetadata().getModel());
        return Response.getResult().getOutput().getText();
    }

    /*
    ENDPOINT 5: MEMORY ENABLED CHAT - "CONTEXT-AWARE AI"
    =====================================================
    PATH: GET /ChatClientMemoryAdvisor/Hello
    USAGE: For conversations that require context and memory

    MAGIC BEHIND THE SCENES:
    ========================
    When you use ChatClientMemory, here's what happens automatically:

    1. BEFORE sending your message:
       - System checks chatMemory for previous messages
       - Builds a context window from recent conversation history
       - Appends this context to your current message

    2. AI PROCESSING:
       - AI receives: [Previous messages...] + [Your new message]
       - Generates response considering entire conversation context

    3. AFTER receiving response:
       - System stores your message + AI's response in chatMemory
       - Maintains the memory window (removes oldest if needed)

    CONVERSATION EXAMPLE:
    =====================
    Request 1: /ChatClientMemoryAdvisor/My%20name%20is%20John
    AI Response: "Hello John! Nice to meet you."
    Memory Updated: [User: "My name is John", AI: "Hello John! Nice to meet you."]

    Request 2: /ChatClientMemoryAdvisor/What%20is%20my%20name
    AI Actually Receives:
        "Previous conversation:
         User: My name is John
         AI: Hello John! Nice to meet you.

         Current message: What is my name"
    AI Response: "Your name is John!" ← Correct because of memory!

    USE CASE: Chatbots, personal assistants, multi-step conversations.
    */
    @GetMapping("/ChatClientMemoryAdvisor/{Message}")
    public String GetMessageAnswerOfChatClientMemoryAdvisor(@PathVariable String Message) {
        ChatResponse Response = ChatClientMemory
                .prompt(Message)
                .call()
                .chatResponse();
        System.out.println("The Chat Response MetaData " + Response.getMetadata().getModel());
        return Response.getResult().getOutput().getText();
    }

    /*
    ENDPOINT 6: PROMPT TEMPLATES - "DYNAMIC AI PROMPTS"
    ====================================================
    PATH: GET /PromptTemplate?Type=Action&Year=2023&Lang=English
    USAGE: For creating flexible, reusable AI prompts with variables

    WHAT ARE PROMPT TEMPLATES?
    ==========================
    Think of them as "mad libs" for AI instructions:
    - Template: "I want to watch a {Type} movie from {Year} in {Lang}"
    - Variables: Type="Action", Year="2023", Lang="English"
    - Result: "I want to watch a Action movie from 2023 in English"

    STEP-BY-STEP PROCESS:
    1. Define template with placeholders: {Type}, {Year}, {Lang}
    2. User provides actual values via request parameters
    3. PromptTemplate replaces placeholders with real values
    4. AI receives the completed, personalized prompt

    TECHNICAL BREAKDOWN:
    ===================
    PromptTemplate → A smart string processor that understands {variable} syntax
    .create(Map) → Replaces variables with values from the map
    Returns Prompt object → Ready-to-send AI instruction package

    BENEFITS:
    - Reusable prompts (same template, different values)
    - Cleaner code (no string concatenation)
    - Consistent prompt structure
    - Easy maintenance

    USE CASE: Any scenario where you need structured, parameterized AI requests.
    */
    @GetMapping("/PromptTemplate")
    public String GetMessageAnswerOfPromptTemplate(
            @RequestParam String Type,   // Movie genre: Action, Comedy, Drama, etc.
            @RequestParam String Year,   // Release year: 2023, 1990, 2015, etc.
            @RequestParam String Lang    // Language: English, Spanish, French, etc.
    ) {
        // Template with variables - reusable blueprint for AI requests
        String Template =
                """
                I want to watch a {Type} movie tonight with good rating,
                looking for movies around this year {Year}.
                The language I'm looking for is {Lang}.
                Suggest one specific movie and tell me the cast and length of the movie.
                
                Response format should be:
                1. Movie Name
                2. Basic Plot  
                3. Cast
                4. Length
                5. IMDB Rating
                """;

        // Create template processor
        PromptTemplate promptTemplate = new PromptTemplate(Template);

        // Replace variables with actual user input
        Prompt prompt = promptTemplate.create(
                Map.of(
                        "Type", Type,   // {Type} → "Action"
                        "Year", Year,   // {Year} → "2023"
                        "Lang", Lang    // {Lang} → "English"
                )
        );

        // Use memory-enabled client so follow-up questions work better
        String Response = ChatClientMemory
                .prompt(prompt)
                .call()
                .content();
        return Response;
    }

    /*
    ENDPOINT 7: TEXT TO EMBEDDINGS - "MEANING AS NUMBERS"
    ======================================================
    PATH: POST /Embeddings?Text=Hello%20world
    USAGE: Convert any text into its numerical representation

    DEEP DIVE INTO EMBEDDINGS:
    ==========================
    WHAT ARE EMBEDDINGS REALLY?
    - Numerical representations that capture semantic meaning
    - High-dimensional vectors (typically 256-4096 dimensions)
    - Similar meanings = similar vector patterns

    HOW ARE THEY CREATED?
    1. Tokenization: Split text into words/subwords
    2. Vector Lookup: Each token maps to a pre-learned vector
    3. Aggregation: Combine token vectors into text vector
    4. Normalization: Adjust to consistent scale

    PRACTICAL EXAMPLE:
    Input: "cat" → Output: [0.12, -0.45, 0.23, 0.67, -0.89, ...] (1024 numbers)
    Input: "dog" → Output: [0.14, -0.43, 0.25, 0.65, -0.87, ...] (similar!)
    Input: "car" → Output: [-0.56, 0.34, -0.78, 0.12, 0.45, ...] (different!)

    USE CASES:
    - Semantic search (find documents with similar meaning)
    - Recommendation systems
    - Text classification
    - Clustering similar content
    */
    @PostMapping("/Embeddings")
    public float[] GetMessageAnswersOfEmbeddings(@RequestParam String Text) {
        // Transform text into mathematical representation
        return embeddingModel.embed(Text);
    }

    /*
    ENDPOINT 8: SEMANTIC SIMILARITY - "MEANING COMPARISON"
    =======================================================
    PATH: POST /Similarity?Text1=cat&Text2=kitten
    USAGE: Measure how similar two texts are in meaning

    COSINE SIMILARITY DEEP EXPLANATION:
    ===================================
    WHAT IS IT? A mathematical way to compare two vectors by measuring the
    cosine of the angle between them.

    VISUAL ANALOGY:
    Imagine two arrows in space:
    - Same direction: 100% similar (cosine = 1)
    - 90° angle: 0% similar (cosine = 0)
    - Opposite direction: -100% similar (cosine = -1)

    MATHEMATICAL BREAKDOWN:
    Cosine Similarity = (A·B) / (||A|| × ||B||)
    Where:
    - A·B = Dot product (sum of element-wise multiplication)
    - ||A|| = Euclidean norm (square root of sum of squares)
    - ||B|| = Euclidean norm (square root of sum of squares)

    STEP-BY-STEP CALCULATION:
    1. Dot Product: Multiply corresponding elements, then sum
       [a1,a2,a3] · [b1,b2,b3] = (a1×b1) + (a2×b2) + (a3×b3)

    2. Norms: Measure of vector length
       ||A|| = sqrt(a1² + a2² + a3²)

    3. Final Calculation: Dot Product / (NormA × NormB)

    WHY COSINE SIMILARITY FOR EMBEDDINGS?
    - Focuses on direction rather than magnitude
    - Handles different text lengths well
    - Proven effective for semantic similarity

    REAL-WORLD RESULTS:
    "cat" vs "kitten" = 85-95% similarity
    "cat" vs "dog" = 70-80% similarity
    "cat" vs "car" = 5-15% similarity
    "programming" vs "coding" = 80-90% similarity
    */
    @PostMapping("/Similarity")
    public double GetMessageAnswersOfSimilarity(
            @RequestParam String Text1,
            @RequestParam String Text2
    ) {
        // Convert both texts to numerical embeddings
        float[] embeddings1 = embeddingModel.embed(Text1);
        float[] embeddings2 = embeddingModel.embed(Text2);

        // ========== COSINE SIMILARITY CALCULATION ==========
        double DotProduct = 0;    // Sum of element-wise multiplication
        double Norm1 = 0;         // Squared length of first vector
        double Norm2 = 0;         // Squared length of second vector

        // Iterate through each dimension of the vectors
        for(int i = 0; i < embeddings1.length; i++) {
            // Dot product: measure alignment between vectors
            DotProduct += embeddings1[i] * embeddings2[i];

            // Norm calculation: measure vector magnitudes
            Norm1 += Math.pow(embeddings1[i], 2);
            Norm2 += Math.pow(embeddings2[i], 2);
        }

        // Cosine Similarity Formula: (A·B) / (|A| * |B|)
        double similarity = DotProduct / (Math.sqrt(Norm1) * Math.sqrt(Norm2));

        // Convert to percentage for easier interpretation
        return similarity * 100;
    }

    @PostMapping("/SimpleVectorStore")
    public List<Document> GetMessageAnswersOfSimpleVectorStore(@RequestParam String Text)
    {
        // ========== AI-POWERED SEMANTIC SEARCH ENDPOINT ==========
        //
        // WHAT THIS DOES:
        // - Takes your search query (like "wireless headphones")
        // - Uses AI to find products by MEANING, not just keywords
        // - Returns semantically similar products from your vector database
        //
        // THE MAGIC OF VECTOR SEARCH:
        // - Converts text to NUMBER ARRAYS (vectors)
        // - Finds similar number patterns in Redis
        // - Returns products with similar meanings
        //

        return vectorStore.similaritySearch
                (
                        SearchRequest
                                .builder()
                                .query(Text)    // Your search query
                                .topK(2)        // Return top 2 most similar results
                                .build()
                );
    }

    @PostMapping("/RAG")
    public String GetMessageAnswersOfRAG(@RequestParam String Query)
    {
        return ChatClientMemory
                .prompt(Query)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
    }

//    @GetMapping("/Image")
//    public String GetMessageAnswersOfImage(@RequestParam String Query)
//    {
//        ImagePrompt Prompt = new ImagePrompt(Query);
//        ImageResponse Response = ImageModel.call(Prompt);
//        return Response.getResult().getOutput().getUrl();
//    }
//    @GetMapping("/ImageOptions")
//    public String GetMessageAnswersOfImageOptions
//            (
//                    @RequestParam String Query
//            )
//    {
//        ImageModel.getOptions();
//        ImagePrompt Prompt = new ImagePrompt
//                (
//                        Query,
//                        StabilityAiImageOptions
//                                .builder()
//                                .height(1024)
//                                .width(1024)
//                                .stylePreset("cinematic")
//                                .build()
//                );
//        ImageResponse Response = ImageModel.call(Prompt);
//        return Response.getResult().getOutput().getUrl();
//    }
//    @PostMapping("/DescribeImage")
//    public String GetMessageAnswersOfDescribeImage
//            (
//                    @RequestParam String Query,
//                    @RequestParam MultipartFile File
//            )
//    {
//        return ChatClientMemory
//                .prompt()
//                .user
//                        (
//                                promptUserSpec -> promptUserSpec.
//                                        media(MimeTypeUtils.IMAGE_JPEG,File.getResource())
//                        )
//                .call()
//                .content();
//    }
//    @PostMapping("/AudioTranscription")
//    public String GetMessageAnswersOfAudioTranscription(@RequestParam MultipartFile File)
//    {
//        return AudioTranscriptionModel.call(File.getResource());
//    }
//    @PostMapping("/AudioTranscriptionOptionsT")
//    public String GetMessageAnswersOfAudioTranscriptionOptions
//            (
//                    @RequestParam MultipartFile File
//            )
//    {
//        OpenAiAudioTranscriptionOptions Options =
//                OpenAiAudioTranscriptionOptions
//                        .builder()
//                        .language("es")
//                        .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.SRT)
//                        .build();
//        AudioTranscriptionPrompt Prompt = new AudioTranscriptionPrompt
//                (
//                        File.getResource(),
//                        Options
//                );
//        return AudioTranscriptionModel.call(Prompt).getResult().getOutput();
//    }
//    @PostMapping("/AudioSpeech")
//    public byte[] GetMessageAnswersOfAudioSpeech
//            (
//                    @RequestParam String Text
//            )
//    {
//        return AudioSpeechModel.call(Text);
//    }
//    @PostMapping("/AudioSpeechOptions")
//    public byte[] GetMessageAnswersOfAudioSpeechOptions
//            (
//                    @RequestParam String Text
//            )
//    {
//        OpenAiAudioSpeechOptions Options =
//                OpenAiAudioSpeechOptions
//                        .builder()
//                        .speed(1.5f)
//                        .voice
//                                (
//                                        OpenAiAudioApi
//                                        .SpeechRequest
//                                        .Voice
//                                        .NOVA
//                                )
//                        .build();
//        SpeechPrompt Prompt = new SpeechPrompt(Text,Options);
//        return AudioSpeechModel.call(Prompt).getResult().getOutput();
//    }
    @GetMapping("/ListOutputConverter")
    public List<String> GetMessageAnswersOfListOutputConverter(@RequestParam String Name)
    {
        List<String>Movies = ChatClientMemory
                .prompt()
                .user
                        (
                        u -> u.text("List top 5 movies of {Name}")
                        .param("Name",Name)
                        )
                .call()
                .entity(new ListOutputConverter(new DefaultConversionService()));
        return Movies;
    }
    @GetMapping("/BeanOutputConverter")
    public Movie GetMessageAnswersBeanOutputConverter(@RequestParam String Name)
    {
        String Message =
                """
                Get me the best movie of {Name}
                {format}
                """;
        BeanOutputConverter<Movie> BeanOutputConverter = new BeanOutputConverter<Movie>(Movie.class);

        Movie Movie = ChatClientMemory
                .prompt()
                .user(u->u.text("Get me the best movie of {Name}").param("Name",Name))
                .call()
                .entity(new BeanOutputConverter<Movie>(Movie.class));
        return Movie;
    }
    @GetMapping("/BeanOutputConverterLists")
    public List<Movie>GetMessageAnswers
            (
                    @RequestParam String Name
            )
    {
        BeanOutputConverter<Movie> BeanOutputConverter = new BeanOutputConverter<Movie>(Movie.class);

        List<Movie>Movies = ChatClientMemory
                .prompt()
                .user(u->u.text("Top 5 movies of {Name}").param("Name",Name))
                .call()
                .entity(new BeanOutputConverter<List<Movie>>(new ParameterizedTypeReference<List<Movie>>(){}));
        return Movies;
    }
}


