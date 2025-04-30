/*
 * This is INVALID syntax - wildcard characters ('?') cannot be used
 * in generic class declarations. They can only be used:
 * 1. In method parameters
 * 2. In variable declarations
 *
 * The compiler would reject this with an error like:
 * "Cannot use wildcard in generic class declaration"
 *
 * Correct alternatives:
 */
/*
public class Printer <?>{
}
*/
