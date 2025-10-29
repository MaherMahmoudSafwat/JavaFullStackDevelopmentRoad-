/**
 * COMPREHENSIVE DATA GENERATION
 * Creates a complete fake education system
 */
// ==================== COMPREHENSIVE REGEX GUIDE ====================

/*
WHAT IS REGEX (REGULAR EXPRESSIONS)?
Regular expressions are PATTERNS that describe text matching rules.
Think of regex like a very precise description of what text should look like.

WHAT ARE LITERALS IN REGEX?
LITERALS are characters that match themselves exactly.
- The letter 'a' matches the letter 'a'
- The number '5' matches the number '5'
- Most letters and numbers are literals

WHAT ARE SPECIAL CHARACTERS (METACHARACTERS)?
Some characters have SPECIAL MEANINGS in regex:
. ^ $ * + ? { } [ ] \ | ( )

To use these characters literally, you must ESCAPE them with backslash (\)

WHY DOUBLE BACKSLASH (\\) IN JAVA?
Java strings use backslash (\) as escape character
To get one backslash in regex, you need two backslashes in Java string
Java String: "\\d" becomes Actual Regex: \d
*/

// ==================== COMPLETE REGEX CHARACTER REFERENCE ====================

package JPA_Course.example.JpaDemoExamples.Examples;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * COMPREHENSIVE REGEX EXAMPLES
 * This class demonstrates EVERY common regex pattern with detailed explanations
 */
@Entity
@Table(name = "regex_examples")
@Data
public class RegexExamples {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // ==================== BASIC LITERALS ====================

    /**
     * LITERAL CHARACTERS - Match themselves exactly
     *
     * Pattern: "hello"
     * Matches: "hello" exactly
     * Doesn't match: "Hello", "HELLO", "helo"
     */
    @Pattern(regexp = "hello", message = "Must be exactly 'hello'")
    private String literalExample;

    // ==================== CHARACTER CLASSES ====================

    /**
     * [abc] - CHARACTER CLASS
     * Matches ANY ONE character from the list inside brackets
     *
     * Pattern: [abc]
     * Matches: "a" OR "b" OR "c"
     * Doesn't match: "d", "ab", "ABC"
     */
    @Pattern(regexp = "[abc]", message = "Must be a, b, or c")
    private String characterClassBasic;

    /**
     * [a-z] - CHARACTER RANGE
     * Matches any lowercase letter from a to z
     *
     * Pattern: [a-z]
     * Matches: "a", "m", "z"
     * Doesn't match: "A", "1", "!"
     */
    @Pattern(regexp = "[a-z]", message = "Must be a lowercase letter")
    private String lowercaseRange;

    /**
     * [A-Z] - UPPERCASE RANGE
     * Matches any uppercase letter from A to Z
     */
    @Pattern(regexp = "[A-Z]", message = "Must be an uppercase letter")
    private String uppercaseRange;

    /**
     * [0-9] - DIGIT RANGE
     * Matches any digit from 0 to 9
     */
    @Pattern(regexp = "[0-9]", message = "Must be a digit")
    private String digitRange;

    /**
     * [a-zA-Z0-9] - COMBINED RANGES
     * Matches any letter (upper or lower) or digit
     *
     * Pattern: [a-zA-Z0-9]
     * Matches: "a", "Z", "5"
     * Doesn't match: "!", "@", " "
     */
    @Pattern(regexp = "[a-zA-Z0-9]", message = "Must be alphanumeric")
    private String alphanumeric;

    /**
     * [^abc] - NEGATED CHARACTER CLASS
     * ^ at START of character class means NOT
     * Matches any character EXCEPT a, b, or c
     *
     * Pattern: [^abc]
     * Matches: "d", "1", "!"
     * Doesn't match: "a", "b", "c"
     */
    @Pattern(regexp = "[^abc]", message = "Must NOT be a, b, or c")
    private String negatedClass;

    // ==================== PREDEFINED CHARACTER CLASSES ====================

    /**
     * \\d - DIGIT SHORTHAND
     * Same as [0-9]
     * Matches any digit
     *
     * WHY DOUBLE BACKSLASH?
     * Java String: "\\d" becomes Regex: \d
     */
    @Pattern(regexp = "\\d", message = "Must be a digit")
    private String digitShorthand;

    /**
     * \\D - NON-DIGIT SHORTHAND
     * Same as [^0-9]
     * Matches any character that is NOT a digit
     */
    @Pattern(regexp = "\\D", message = "Must NOT be a digit")
    private String nonDigit;

    /**
     * \\w - WORD CHARACTER
     * Same as [a-zA-Z0-9_]
     * Matches letters, digits, and underscore
     *
     * Pattern: \\w
     * Matches: "a", "Z", "5", "_"
     * Doesn't match: "!", "@", " ", "-"
     */
    @Pattern(regexp = "\\w", message = "Must be a word character")
    private String wordCharacter;

    /**
     * \\W - NON-WORD CHARACTER
     * Same as [^a-zA-Z0-9_]
     * Matches anything that is NOT a word character
     */
    @Pattern(regexp = "\\W", message = "Must NOT be a word character")
    private String nonWordCharacter;

    /**
     * \\s - WHITESPACE
     * Matches space, tab, newline, carriage return
     *
     * Pattern: \\s
     * Matches: " " (space), "\\t" (tab), "\\n" (newline)
     */
    @Pattern(regexp = "\\s", message = "Must be whitespace")
    private String whitespace;

    /**
     * \\S - NON-WHITESPACE
     * Matches any character that is NOT whitespace
     */
    @Pattern(regexp = "\\S", message = "Must NOT be whitespace")
    private String nonWhitespace;

    /**
     * . - DOT (ANY CHARACTER)
     * Matches ANY character except newline
     *
     * Pattern: .
     * Matches: "a", "1", "!", " "
     * Doesn't match: newline character
     */
    @Pattern(regexp = ".", message = "Must be any single character")
    private String anyCharacter;

    // ==================== ESCAPED SPECIAL CHARACTERS (LITERALS) ====================

    /**
     * \\. - LITERAL DOT
     * Matches actual dot character, not "any character"
     *
     * WITHOUT ESCAPE: . means "any character"
     * WITH ESCAPE: \\. means literal dot "."
     */
    @Pattern(regexp = "\\.", message = "Must be a literal dot")
    private String literalDot;

    /**
     * \\( - LITERAL OPENING PARENTHESIS
     * WITHOUT ESCAPE: ( starts a group
     * WITH ESCAPE: \\( means literal "("
     */
    @Pattern(regexp = "\\(", message = "Must be opening parenthesis")
    private String literalOpenParen;

    /**
     * \\) - LITERAL CLOSING PARENTHESIS
     */
    @Pattern(regexp = "\\)", message = "Must be closing parenthesis")
    private String literalCloseParen;

    /**
     * \\+ - LITERAL PLUS SIGN
     * WITHOUT ESCAPE: + means "one or more"
     * WITH ESCAPE: \\+ means literal "+"
     */
    @Pattern(regexp = "\\+", message = "Must be plus sign")
    private String literalPlus;

    /**
     * \\* - LITERAL ASTERISK
     * WITHOUT ESCAPE: * means "zero or more"
     * WITH ESCAPE: \\* means literal "*"
     */
    @Pattern(regexp = "\\*", message = "Must be asterisk")
    private String literalAsterisk;

    /**
     * \\? - LITERAL QUESTION MARK
     * WITHOUT ESCAPE: ? means "zero or one"
     * WITH ESCAPE: \\? means literal "?"
     */
    @Pattern(regexp = "\\?", message = "Must be question mark")
    private String literalQuestion;

    /**
     * \\[ - LITERAL OPENING BRACKET
     * WITHOUT ESCAPE: [ starts character class
     * WITH ESCAPE: \\[ means literal "["
     */
    @Pattern(regexp = "\\[", message = "Must be opening bracket")
    private String literalOpenBracket;

    /**
     * \\] - LITERAL CLOSING BRACKET
     */
    @Pattern(regexp = "\\]", message = "Must be closing bracket")
    private String literalCloseBracket;

    /**
     * \\{ - LITERAL OPENING BRACE
     * WITHOUT ESCAPE: { starts quantifier
     * WITH ESCAPE: \\{ means literal "{"
     */
    @Pattern(regexp = "\\{", message = "Must be opening brace")
    private String literalOpenBrace;

    /**
     * \\} - LITERAL CLOSING BRACE
     */
    @Pattern(regexp = "\\}", message = "Must be closing brace")
    private String literalCloseBrace;

    /**
     * \\^ - LITERAL CARET
     * WITHOUT ESCAPE: ^ means "start of string"
     * WITH ESCAPE: \\^ means literal "^"
     */
    @Pattern(regexp = "\\^", message = "Must be caret")
    private String literalCaret;

    /**
     * \\$ - LITERAL DOLLAR SIGN
     * WITHOUT ESCAPE: $ means "end of string"
     * WITH ESCAPE: \\$ means literal "$"
     */
    @Pattern(regexp = "\\$", message = "Must be dollar sign")
    private String literalDollar;

    /**
     * \\| - LITERAL PIPE
     * WITHOUT ESCAPE: | means "OR"
     * WITH ESCAPE: \\| means literal "|"
     */
    @Pattern(regexp = "\\|", message = "Must be pipe character")
    private String literalPipe;

    /**
     * \\\\ - LITERAL BACKSLASH
     * To match one backslash, you need FOUR backslashes in Java!
     * Java String: "\\\\" becomes Regex: \\ becomes Match: \
     */
    @Pattern(regexp = "\\\\", message = "Must be backslash")
    private String literalBackslash;

    // ==================== QUANTIFIERS ====================

    /**
     * * - ZERO OR MORE
     * Matches the preceding element zero or more times
     *
     * Pattern: a*
     * Matches: "", "a", "aa", "aaa", "aaaa"...
     * Doesn't match: "b", "ab"
     */
    @Pattern(regexp = "a*", message = "Must be zero or more 'a' characters")
    private String zeroOrMore;

    /**
     * + - ONE OR MORE
     * Matches the preceding element one or more times
     *
     * Pattern: a+
     * Matches: "a", "aa", "aaa", "aaaa"...
     * Doesn't match: "", "b", "ab"
     */
    @Pattern(regexp = "a+", message = "Must be one or more 'a' characters")
    private String oneOrMore;

    /**
     * ? - ZERO OR ONE (OPTIONAL)
     * Matches the preceding element zero or one time
     *
     * Pattern: a?
     * Matches: "", "a"
     * Doesn't match: "aa", "b"
     */
    @Pattern(regexp = "a?", message = "Must be zero or one 'a' character")
    private String zeroOrOne;

    /**
     * {n} - EXACTLY N TIMES
     * Matches the preceding element exactly n times
     *
     * Pattern: a{3}
     * Matches: "aaa"
     * Doesn't match: "", "a", "aa", "aaaa"
     */
    @Pattern(regexp = "a{3}", message = "Must be exactly 3 'a' characters")
    private String exactlyN;

    /**
     * {n,} - N OR MORE TIMES
     * Matches the preceding element n or more times
     *
     * Pattern: a{2,}
     * Matches: "aa", "aaa", "aaaa"...
     * Doesn't match: "", "a"
     */
    @Pattern(regexp = "a{2,}", message = "Must be 2 or more 'a' characters")
    private String nOrMore;

    /**
     * {n,m} - BETWEEN N AND M TIMES
     * Matches the preceding element between n and m times (inclusive)
     *
     * Pattern: a{2,4}
     * Matches: "aa", "aaa", "aaaa"
     * Doesn't match: "", "a", "aaaaa"
     */
    @Pattern(regexp = "a{2,4}", message = "Must be between 2 and 4 'a' characters")
    private String betweenNAndM;

    // ==================== ANCHORS ====================

    /**
     * ^ - START OF STRING ANCHOR
     * Matches the position at the start of the string
     *
     * Pattern: ^hello
     * Matches: "hello world" (starts with hello)
     * Doesn't match: "say hello" (hello not at start)
     */
    @Pattern(regexp = "^hello", message = "Must start with 'hello'")
    private String startAnchor;

    /**
     * $ - END OF STRING ANCHOR
     * Matches the position at the end of the string
     *
     * Pattern: world$
     * Matches: "hello world" (ends with world)
     * Doesn't match: "world peace" (world not at end)
     */
    @Pattern(regexp = "world$", message = "Must end with 'world'")
    private String endAnchor;

    /**
     * ^...$ - COMPLETE STRING MATCH
     * String must match from start to end exactly
     *
     * Pattern: ^hello$
     * Matches: "hello" (exactly)
     * Doesn't match: "hello world", "say hello"
     */
    @Pattern(regexp = "^hello$", message = "Must be exactly 'hello'")
    private String completeMatch;

    // ==================== GROUPING AND ALTERNATION ====================

    /**
     * | - ALTERNATION (OR)
     * Matches either the pattern before OR the pattern after
     *
     * Pattern: cat|dog
     * Matches: "cat" OR "dog"
     * Doesn't match: "bird", "catdog"
     */
    @Pattern(regexp = "cat|dog", message = "Must be 'cat' or 'dog'")
    private String alternation;

    /**
     * () - GROUPING
     * Groups patterns together, useful with quantifiers and alternation
     *
     * Pattern: (cat|dog)s
     * Matches: "cats" OR "dogs"
     * Doesn't match: "cat", "dog"
     */
    @Pattern(regexp = "(cat|dog)s", message = "Must be 'cats' or 'dogs'")
    private String grouping;

    /**
     * (...)+ - GROUP WITH QUANTIFIER
     * The entire group must appear one or more times
     *
     * Pattern: (ab)+
     * Matches: "ab", "abab", "ababab"
     * Doesn't match: "a", "b", "aba"
     */
    @Pattern(regexp = "(ab)+", message = "Must be one or more 'ab' sequences")
    private String groupQuantifier;

    // ==================== REAL-WORLD EXAMPLES ====================

    /**
     * COMPREHENSIVE EMAIL PATTERN
     * ^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$
     *
     * Breaking it down:
     * ^ = Start of string
     * [a-zA-Z0-9._%+-]+ = Username: letters, digits, dot, underscore, percent, plus, hyphen (one or more)
     * @ = Literal @ symbol
     * [a-zA-Z0-9.-]+ = Domain: letters, digits, dot, hyphen (one or more)
     * \\. = Literal dot (escaped)
     * [a-zA-Z]{2,} = Extension: letters only, 2 or more characters
     * $ = End of string
     */
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Invalid email format")
    private String email;

    /**
     * US PHONE NUMBER PATTERN
     * ^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$
     *
     * Breaking it down:
     * ^ = Start of string
     * \\( = Literal opening parenthesis (escaped)
     * \\d{3} = Exactly 3 digits
     * \\) = Literal closing parenthesis (escaped)
     * \\s = One whitespace character
     * \\d{3} = Exactly 3 digits
     * - = Literal hyphen
     * \\d{4} = Exactly 4 digits
     * $ = End of string
     *
     * Matches: "(123) 456-7890"
     * Doesn't match: "123-456-7890", "(12) 456-7890"
     */
    @Pattern(regexp = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$",
            message = "Phone format must be (123) 456-7890")
    private String phoneNumber;

    /**
     * STRONG PASSWORD PATTERN
     * ^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$
     *
     * This uses POSITIVE LOOKAHEADS (?=...)
     * Lookaheads check conditions without consuming characters
     *
     * Breaking it down:
     * ^ = Start of string
     * (?=.*[a-z]) = Must contain at least one lowercase letter (lookahead)
     * (?=.*[A-Z]) = Must contain at least one uppercase letter (lookahead)
     * (?=.*\\d) = Must contain at least one digit (lookahead)
     * (?=.*[@$!%*?&]) = Must contain at least one special character (lookahead)
     * [A-Za-z\\d@$!%*?&] = Only these characters are allowed
     * {8,} = Must be at least 8 characters long
     * $ = End of string
     */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be 8+ characters with uppercase, lowercase, digit, and special character")
    private String strongPassword;

    /**
     * USERNAME PATTERN
     * ^[a-zA-Z][a-zA-Z0-9_-]{2,19}$
     *
     * Breaking it down:
     * ^ = Start of string
     * [a-zA-Z] = Must start with a letter
     * [a-zA-Z0-9_-] = Then letters, digits, underscore, or hyphen
     * {2,19} = 2 to 19 more characters (total length 3-20)
     * $ = End of string
     *
     * Valid: "user123", "john_doe", "mary-jane"
     * Invalid: "123user" (starts with digit), "a" (too short)
     */
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_-]{2,19}$",
            message = "Username must start with letter, be 3-20 characters, and contain only letters, digits, underscore, hyphen")
    private String username;

    /**
     * DATE PATTERN (YYYY-MM-DD)
     * ^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$
     *
     * Breaking it down:
     * ^ = Start of string
     * \\d{4} = Exactly 4 digits for year
     * - = Literal hyphen
     * (0[1-9]|1[0-2]) = Month: 01-09 OR 10-12
     *   0[1-9] = 0 followed by 1-9 = 01, 02, ..., 09
     *   1[0-2] = 1 followed by 0-2 = 10, 11, 12
     * - = Literal hyphen
     * (0[1-9]|[12]\\d|3[01]) = Day: 01-09 OR 10-29 OR 30-31
     *   0[1-9] = 01, 02, ..., 09
     *   [12]\\d = 10-19, 20-29
     *   3[01] = 30, 31
     * $ = End of string
     */
    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
            message = "Date format must be YYYY-MM-DD")
    private String dateFormat;
}