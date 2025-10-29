package JPA_Course.example.JpaDemoExamples.ParentClasses;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")  // This value goes in resource_type column
/*
When you save a VideoResource:
INSERT INTO course_resources (
    id, created_at, resource_type, title, description, course_id,
    video_url, duration_minutes
    -- content, word_count = NULL (not used for VIDEO)
    -- image_url, alt_text = NULL (not used for VIDEO)
) VALUES (
    2, '2024-01-15 10:31:00', 'VIDEO', 'Java Tutorial', 'Learn Java', 1,
    'https://youtube.com/watch?v=123', 45
);
*/
public class VideoResource extends CourseResources {  // INHERITANCE: VideoResource IS-A CourseResource

    @Column(name = "video_url", length = 500)  // URLs can be very long
    /*
    🎯 COLUMN LENGTH EXPLANATION:
    - Default VARCHAR is usually 255 characters
    - URLs can be much longer than 255 characters
    - length = 500 creates VARCHAR(500) to accommodate long URLs

    Expected Output: video_url VARCHAR(500)
    */
    private String videoUrl;

    @Column(name = "duration_minutes")
    /*
    Expected Output: duration_minutes INTEGER
    */
    private Integer durationMinutes;

    // Constructors
    public VideoResource() {
        super();
        System.out.println("🎥 Creating VideoResource (child constructor)");
    }

    public VideoResource(String title, String description, String videoUrl, Integer durationMinutes) {
        super(title, description);
        this.videoUrl = videoUrl;
        this.durationMinutes = durationMinutes;
        System.out.println("🎥 Created VideoResource: " + title + " (" + durationMinutes + " minutes)");
    }

    // Custom method specific to VideoResource
    public String getFormattedDuration() {
        if (durationMinutes == null) return "Unknown duration";

        int hours = durationMinutes / 60;      // Integer division: 90 / 60 = 1 hour
        int mins = durationMinutes % 60;       // Remainder: 90 % 60 = 30 minutes

        if (hours > 0) {
            return String.format("%d hours %d minutes", hours, mins);  // "1 hours 30 minutes"
        } else {
            return String.format("%d minutes", mins);                   // "45 minutes"
        }
    }
    /*
    Example Usage:
    VideoResource video = new VideoResource("Tutorial", "Desc", "url", 90);
    System.out.println(video.getFormattedDuration()); // Output: "1 hours 30 minutes"
    */

    // Getters and setters
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
