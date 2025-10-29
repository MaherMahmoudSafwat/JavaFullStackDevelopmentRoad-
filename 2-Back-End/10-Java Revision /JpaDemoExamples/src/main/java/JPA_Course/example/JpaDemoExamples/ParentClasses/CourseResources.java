package JPA_Course.example.JpaDemoExamples.ParentClasses;// ==================== INHERITANCE STRATEGY 1: SINGLE TABLE ====================

/*
🎯 SINGLE TABLE INHERITANCE STRATEGY

Imagine you have different types of course materials:
- Text documents (articles, PDFs)
- Video lectures  
- Image diagrams

All are "Course Resources" but have different specific properties.

SINGLE TABLE = All types stored in ONE big table with a "type" column
*/

import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import jakarta.persistence.*;

@Entity  // This creates a database table
@Table(name = "course_resources")  // Table will be named "course_resources"
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // All inheritance types in ONE table
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
/*
🎯 DISCRIMINATOR COLUMN EXPLANATION:
- discriminator = "distinguisher" = tells different types apart
- Column "resource_type" will contain: 'TEXT', 'VIDEO', 'IMAGE'
- JPA uses this to know which Java class each row represents

Expected Database Table Structure (ALL types in ONE table):
CREATE TABLE course_resources (
    -- From BaseEntity (inherited):
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    
    -- Discriminator column (tells us the type):
    resource_type VARCHAR(31) NOT NULL,  -- 'TEXT' or 'VIDEO' or 'IMAGE'
    
    -- Common fields (from CourseResource parent):
    title VARCHAR(255) NOT NULL,
    description TEXT,
    course_id INTEGER,
    
    -- Fields specific to TextResource (only used when resource_type = 'TEXT'):
    content LONGTEXT,      -- Only filled for TEXT resources, NULL for others
    word_count INTEGER,    -- Only filled for TEXT resources, NULL for others
    
    -- Fields specific to VideoResource (only used when resource_type = 'VIDEO'):
    video_url VARCHAR(500),    -- Only filled for VIDEO resources, NULL for others
    duration_minutes INTEGER,  -- Only filled for VIDEO resources, NULL for others
    
    -- Fields specific to ImageResource (only used when resource_type = 'IMAGE'):
    image_url VARCHAR(500),    -- Only filled for IMAGE resources, NULL for others
    alt_text VARCHAR(255),     -- Only filled for IMAGE resources, NULL for others
    file_size_bytes BIGINT     -- Only filled for IMAGE resources, NULL for others
);

PROS of Single Table:
✅ Fast queries (no joins needed)
✅ Simple to understand
✅ Easy polymorphic queries (get all resources regardless of type)

CONS of Single Table:
❌ Lots of NULL values (wasted space)
❌ Cannot have NOT NULL constraints on subclass fields
❌ Table can become very wide with many subtypes
*/
public abstract class CourseResources extends BaseEntity {  // Inherits id, createdAt, updatedAt

    @Column(nullable = false)  // Every resource must have a title
    /*
    Expected Output: title VARCHAR(255) NOT NULL
    */
    protected String title;

    @Column(columnDefinition = "TEXT")  // Allows longer text than default VARCHAR(255)
    /*
    🎯 COLUMN DEFINITION EXPLANATION:
    - Default @Column creates VARCHAR(255) - limited to 255 characters
    - columnDefinition = "TEXT" creates TEXT column - can store thousands of characters
    - Good for descriptions that might be long
    
    Expected Output: description TEXT
    */
    protected String description;

    @ManyToOne(fetch = FetchType.LAZY)  // Many resources can belong to one course
    @JoinColumn(name = "course_id")     // Creates foreign key column
    /*
    🎯 RELATIONSHIP EXPLANATION:
    - @ManyToOne: Many CourseResources belong to one Course
    - FetchType.LAZY: Don't load the course automatically, only when accessed
    - @JoinColumn: Creates "course_id" column that references courses table
    
    Expected Output: course_id INTEGER (foreign key to courses.id)
    
    Expected SQL when accessing resource.getCourse():
    SELECT * FROM courses WHERE id = ?  <-- Only executes when you actually use getCourse()
    */
    protected Courses course;

    // Constructors
    public CourseResources() {
        System.out.println("🏗️ Creating CourseResource (parent constructor)");
    }

    public CourseResources(String title, String description) {
        this.title = title;
        this.description = description;
        System.out.println("🏗️ Created CourseResource: " + title);
    }

    // Getters and setters (all child classes inherit these)
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Courses getCourse() { return course; }
    public void setCourse(Courses course) { this.course = course; }
}
// ==================== CHILD CLASS 1: TEXT RESOURCE ====================

// ==================== CHILD CLASS 2: VIDEO RESOURCE ====================

// ==================== CHILD CLASS 3: IMAGE RESOURCE ====================

// ==================== SAMPLE DATA IN SINGLE TABLE ====================

/*
🎯 HOW THE SINGLE TABLE LOOKS WITH ACTUAL DATA:

course_resources table:
+----+--------------+---------------------+---------------------+------------------+------------------------+----------+
| id | resource_type| created_at          | updated_at          | title            | description            | course_id|
+----+--------------+---------------------+---------------------+------------------+------------------------+----------+
| 1  | TEXT         | 2024-01-15 10:30:00 | NULL                | Java Basics      | Introduction to Java   | 1        |
| 2  | VIDEO        | 2024-01-15 10:31:00 | NULL                | Java Tutorial    | Video introduction     | 1        |
| 3  | IMAGE        | 2024-01-15 10:32:00 | NULL                | Java Diagram     | Architecture diagram   | 1        |
+----+--------------+---------------------+---------------------+------------------+------------------------+----------+

Additional columns (most will be NULL for each row):
+----+------------------+------------+---------------+-----------------+---------------+----------------+
| id | content          | word_count | video_url     | duration_minutes| image_url     | alt_text       |
+----+------------------+------------+---------------+-----------------+---------------+----------------+
| 1  | Java is a prog...| 150        | NULL          | NULL            | NULL          | NULL           |
| 2  | NULL             | NULL       | https://you...| 45              | NULL          | NULL           |
| 3  | NULL             | NULL       | NULL          | NULL            | https://ex... | JVM diagram    |
+----+------------------+------------+---------------+-----------------+---------------+----------------+

Notice how:
- Row 1 (TEXT): content and word_count are filled, others are NULL
- Row 2 (VIDEO): video_url and duration_minutes are filled, others are NULL
- Row 3 (IMAGE): image_url and alt_text are filled, others are NULL

This is the "waste" of Single Table strategy - lots of NULL values!
*/

// ==================== 2. OTHER INHERITANCE STRATEGIES ====================

/*
🎯 INHERITANCE STRATEGY 2: JOINED TABLES

Instead of one big table, each class gets its own table:

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CourseResource extends BaseEntity { ... }

Expected Database Structure:
-- Parent table (common fields):
CREATE TABLE course_resources (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    course_id INTEGER
);

-- Child table 1:
CREATE TABLE text_resource (
    id INTEGER PRIMARY KEY,  -- Foreign key to course_resources.id
    content LONGTEXT,
    word_count INTEGER,
    FOREIGN KEY (id) REFERENCES course_resources(id)
);

-- Child table 2:
CREATE TABLE video_resource (
    id INTEGER PRIMARY KEY,  -- Foreign key to course_resources.id
    video_url VARCHAR(500),
    duration_minutes INTEGER,
    FOREIGN KEY (id) REFERENCES course_resources(id)
);

-- Child table 3:
CREATE TABLE image_resource (
    id INTEGER PRIMARY KEY,  -- Foreign key to course_resources.id
    image_url VARCHAR(500),
    alt_text VARCHAR(255),
    file_size_bytes BIGINT,
    FOREIGN KEY (id) REFERENCES course_resources(id)
);

PROS of Joined Strategy:
✅ No NULL values - each table only has relevant columns
✅ Normalized database design
✅ Can have NOT NULL constraints on subclass fields

CONS of Joined Strategy:
❌ Slower queries (requires JOINs)
❌ More complex SQL
❌ More tables to manage

Example Query to get all TextResources:
SELECT cr.id, cr.title, cr.description, tr.content, tr.word_count
FROM course_resources cr
JOIN text_resource tr ON cr.id = tr.id;
*/

/*
🎯 INHERITANCE STRATEGY 3: TABLE_PER_CLASS

Each concrete (non-abstract) class gets its own complete table:

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CourseResource extends BaseEntity { ... }

Expected Database Structure:
-- No parent table!

-- Table 1 (has ALL fields):
CREATE TABLE text_resource (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,    -- Duplicated from BaseEntity
    updated_at TIMESTAMP,             -- Duplicated from BaseEntity
    title VARCHAR(255) NOT NULL,      -- Duplicated from CourseResource
    description TEXT,                 -- Duplicated from CourseResource
    course_id INTEGER,                -- Duplicated from CourseResource
    content LONGTEXT,                 -- Specific to TextResource
    word_count INTEGER                -- Specific to TextResource
);

-- Table 2 (has ALL fields):
CREATE TABLE video_resource (
    id INTEGER PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,    -- Duplicated
    updated_at TIMESTAMP,             -- Duplicated
    title VARCHAR(255) NOT NULL,      -- Duplicated
    description TEXT,                 -- Duplicated
    course_id INTEGER,                -- Duplicated
    video_url VARCHAR(500),           -- Specific to VideoResource
    duration_minutes INTEGER          -- Specific to VideoResource
);

-- And so on for each subclass...

PROS of Table Per Class:
✅ No NULL values
✅ Each table is self-contained
✅ Can have NOT NULL constraints

CONS of Table Per Class:
❌ Code/schema duplication
❌ Very slow polymorphic queries
❌ Complex to maintain

Example Polymorphic Query (SLOW!):
SELECT id, title FROM text_resource
UNION
SELECT id, title FROM video_resource
UNION
SELECT id, title FROM image_resource;
*/