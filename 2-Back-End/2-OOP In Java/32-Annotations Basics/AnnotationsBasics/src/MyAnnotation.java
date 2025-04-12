import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Available at runtime
@Target(ElementType.METHOD)         // Can be used on methods
public @interface MyAnnotation
{
     /* @interface → Declares a custom annotation.
     Value() and Number() → Elements (like methods, but they define parameters).
     default → Optional default values.
     */
    String Value() default "Str";
    int Number() default 1;
}
