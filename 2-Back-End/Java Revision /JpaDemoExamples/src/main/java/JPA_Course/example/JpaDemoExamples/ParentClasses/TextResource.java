package JPA_Course.example.JpaDemoExamples.ParentClasses;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;

@Entity  // This is also an entity, but stored in parent's table
@DiscriminatorValue("TEXT")  // This value goes in resource_type column
/*
🎯 INHERITANCE IN ACTION - TEXT RESOURCE:
- TextResource IS-A CourseResource 
- Inherits: id, createdAt, updatedAt (from BaseEntity)
- Inherits: title, description, course (from CourseResource)  
- Adds: content, wordCount (its own specific fields)
- Stored in course_resources table with resource_type = 'TEXT'

When you save a TextResource, this happens:
INSERT INTO course_resources (
    id, created_at, updated_at,           -- From BaseEntity
    resource_type,                        -- Set to 'TEXT'
    title, description, course_id,        -- From CourseResource
    content, word_count                   -- Specific to TextResource
    -- video_url, duration_minutes = NULL (not used for TEXT)
    -- image_url, alt_text = NULL (not used for TEXT)
) VALUES (
    1, '2024-01-15 10:30:00', NULL,      -- Auto-generated values
    'TEXT',                               -- Discriminator value
    'Java Basics', 'Intro to Java', 1,   -- Common fields
    'Java is a programming...', 150       -- TextResource specific fields
);
*/
public class TextResource extends CourseResources {  // INHERITANCE: TextResource IS-A CourseResource

    @Lob  // Large Object - for storing very long text content
    @Column(name = "content")
    /*
    🎯 @Lob EXPLANATION:
    - @Lob = Large Object annotation
    - Creates LONGTEXT column type (MySQL) or CLOB (Oracle)
    - Can store megabytes of text content
    - Perfect for articles, documents, long descriptions
    
    Expected Output: content LONGTEXT
    
    WITHOUT @Lob: content VARCHAR(255)  -- Limited to 255 characters, would truncate long content
    WITH @Lob:    content LONGTEXT      -- Can store up to 4GB of text
    */
    private String content;

    @Column(name = "word_count")
    /*
    Expected Output: word_count INTEGER
    */
    private Integer wordCount;

    // Constructors
    public TextResource() {
        super();  // Call parent (CourseResource) constructor first
        System.out.println("📝 Creating TextResource (child constructor)");
    }
    /*
    Constructor Chain Execution Order:
    1. BaseEntity constructor (implicit)
    2. CourseResource constructor (super() call)
    3. TextResource constructor
    
    Expected Console Output:
    🏗️ Creating CourseResource (parent constructor)
    📝 Creating TextResource (child constructor)
    */

    public TextResource(String title, String description, String content) {
        super(title, description);  // Call parent constructor with parameters
        this.content = content;
        this.wordCount = content != null ? content.split("\\s+").length : 0;  // Count words
        System.out.println("📝 Created TextResource: " + title + " (" + wordCount + " words)");
    }
    /*
    Expected Console Output:
    🏗️ Created CourseResource: Java Basics
    📝 Created TextResource: Java Basics (150 words)
    */

    // Custom method specific to TextResource (not available in parent class)
    public void updateWordCount() {
        this.wordCount = content != null ? content.split("\\s+").length : 0;
        System.out.println("📊 Updated word count to: " + wordCount);
    }
    /*
    🎯 POLYMORPHISM EXAMPLE:
    CourseResource resource = new TextResource("Title", "Desc", "Content");  // Upcast to parent type
    resource.getTitle();        // ✅ Works - inherited from parent
    resource.updateWordCount(); // ❌ Compile error - method only exists in TextResource
    
    // To use specific methods, you need to cast:
    if (resource instanceof TextResource) {
        TextResource textResource = (TextResource) resource;  // Downcast to specific type
        textResource.updateWordCount(); // ✅ Now it works
    }
    */

    // Getters and setters
    public String getContent() { return content; }
    public void setContent(String content) {
        this.content = content;
        updateWordCount();  // Automatically recalculate word count when content changes
    }

    public Integer getWordCount() { return wordCount; }
    public void setWordCount(Integer wordCount) { this.wordCount = wordCount; }
}
