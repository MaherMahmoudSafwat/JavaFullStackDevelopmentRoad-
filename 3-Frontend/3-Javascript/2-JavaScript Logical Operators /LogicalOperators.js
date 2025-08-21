// Conditional (Ternary) Operator Example
// The ternary operator (?:) is a shorthand for if-else statements
// Syntax: condition ? value_if_true : value_if_false
let HasLicense = true; // Boolean variable indicating if someone has a license

// If HasLicense is true, assign 1, otherwise assign 0
let IsEligibleForDrive = HasLicense ? 1 : 0;
console.log(IsEligibleForDrive); // Output: 1 (because HasLicense is true)

// Comparison Operators Examples
let x = 1;    // Number
let y = '1';  // String containing "1"

// Loose equality (==) compares values after type conversion
console.log(x == y);  // Output: true (because '1' is converted to number 1)

// Strict equality (===) compares both value AND type
console.log(x === y); // Output: false (number 1 !== string '1')

// Relational Operators
console.log(1 > 2);   // Output: false (1 is not greater than 2)
console.log(1 >= 2);  // Output: false (1 is not greater than or equal to 2)
console.log(1 < 2);   // Output: true (1 is less than 2)
console.log(1 <= 2);  // Output: true (1 is less than or equal to 2)

// Logical OR Operator (||)
// Returns the first truthy value or the last value if all are falsy
console.log(true || true);   // Output: true
console.log(true || false);  // Output: true
console.log(false || true);  // Output: true
console.log(false || false); // Output: false

// Logical AND Operator (&&)
// Returns the first falsy value or the last value if all are truthy
console.log(true && true);   // Output: true
console.log(true && false);  // Output: false
console.log(false && true);  // Output: false
console.log(false && false); // Output: false

// Logical NOT Operator (!)
// Inverts the boolean value
console.log(!true);  // Output: false
console.log(!false); // Output: true

/* Falsy Values in JavaScript:
   0, NaN, null, undefined, false, "" (empty string)
   All other values are truthy */

// Nullish Coalescing Operator (??)
// Returns the right-hand operand when the left-hand is null or undefined
// Unlike ||, it doesn't consider other falsy values like 0 or ""
x = NaN; // Not a Number (falsy value)
let z = x ?? true; // Since x is not null/undefined, returns x (NaN)
console.log(z); // Output: NaN

// Short-circuit Evaluation with OR (||)
// Returns the first truthy value in the chain
let Name = false || 'N1' || 'N2' || 'N3';
// Evaluation:
// 1. false is falsy → move to next
// 2. 'N1' is truthy → return 'N1' (stops evaluation)
console.log(Name); // Output: 'N1'

/* Arithmetic Operators Explained:
   +  Addition (also string concatenation)
   -  Subtraction
   *  Multiplication
   /  Division
   %  Modulus (remainder after division)
   ** Exponentiation (power operator, ES2016+)
   
   Example:
   console.log(2 + 3);   // 5
   console.log(2 - 3);   // -1
   console.log(2 * 3);   // 6
   console.log(6 / 3);   // 2
   console.log(7 % 3);   // 1 (remainder of 7 divided by 3)
   console.log(2 ** 3);  // 8 (2 to the power of 3)
*/


