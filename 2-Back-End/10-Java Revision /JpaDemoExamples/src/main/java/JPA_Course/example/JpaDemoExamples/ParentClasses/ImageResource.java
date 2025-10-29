package JPA_Course.example.JpaDemoExamples.ParentClasses;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("IMAGE")  // This value goes in resource_type column
public class ImageResource extends CourseResources {  // INHERITANCE: ImageResource IS-A CourseResource

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "alt_text")  // Alternative text for accessibility (screen readers)
    /*
    🎯 ACCESSIBILITY EXPLANATION:
    - Alt text describes images for visually impaired users
    - Screen readers read this text aloud
    - Good practice for web accessibility

    Expected Output: alt_text VARCHAR(255)
    */
    private String altText;

    @Column(name = "file_size_bytes")
    /*
    🎯 DATA TYPE EXPLANATION:
    - Integer: Can store up to ~2 billion (2GB)
    - Long: Can store much larger numbers (good for file sizes)
    - File sizes can be very large, so Long is safer choice

    Expected Output: file_size_bytes BIGINT
    */
    private Long fileSizeBytes;

    // Constructors
    public ImageResource() {
        super();
        System.out.println("🖼️ Creating ImageResource (child constructor)");
    }

    public ImageResource(String title, String description, String imageUrl, String altText) {
        super(title, description);
        this.imageUrl = imageUrl;
        this.altText = altText;
        System.out.println("🖼️ Created ImageResource: " + title);
    }

    // Custom method specific to ImageResource
    public String getFormattedFileSize() {
        if (fileSizeBytes == null) return "Unknown size";

        if (fileSizeBytes < 1024) {                                    // Less than 1 KB
            return fileSizeBytes + " bytes";                           // "523 bytes"
        } else if (fileSizeBytes < 1024 * 1024) {                     // Less than 1 MB
            return String.format("%.1f KB", fileSizeBytes / 1024.0);   // "15.7 KB"
        } else {                                                       // 1 MB or larger
            return String.format("%.1f MB", fileSizeBytes / (1024.0 * 1024.0)); // "2.3 MB"
        }
    }
    /*
    Example Usage:
    ImageResource image = new ImageResource("Diagram", "Desc", "url", "Alt text");
    image.setFileSizeBytes(1500000L);  // 1.5 MB
    System.out.println(image.getFormattedFileSize()); // Output: "1.4 MB"
    */

    // Getters and setters
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getAltText() { return altText; }
    public void setAltText(String altText) { this.altText = altText; }

    public Long getFileSizeBytes() { return fileSizeBytes; }
    public void setFileSizeBytes(Long fileSizeBytes) { this.fileSizeBytes = fileSizeBytes; }
}
