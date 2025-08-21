// HOISTING EXAMPLE
// Function declarations are hoisted (moved to top of scope), so they can be called before declaration
let AddTwonumbers = Add(3, 5); // This works because Add() is hoisted
console.log(AddTwonumbers); // Output: 8

// Function declaration (gets hoisted)
function Add(Num1, Num2) {
    return Num1 + Num2;
}

console.log(Add(1, 2)); // Output: 3

// FUNCTION EXPRESSIONS
// Function expressions are NOT hoisted - must be defined before use
const AddNumbers = function Adding(num1, num2) {
    return num1 + num2;
};
console.log(AddNumbers(1, 2)); // Output: 3

// ARGUMENTS OBJECT
// Functions have access to 'arguments' object containing all passed arguments
function AddManyNumbers() {
    let X = 0;
    for (const i of arguments) { // 'arguments' is array-like object
        X += i;
    }
    return X;
}

console.log(AddManyNumbers(1, 2, 3)); // Output: 6

// REST PARAMETERS (ES6)
// Better alternative to arguments object using spread syntax
function SumManyNumbers(...Num1) { // Num1 becomes a real array
    let X = 0;
    for (const i of Num1) {
        X += i;
    }
    return X;
}

console.log(SumManyNumbers(4, 5, 6)); // Output: 15

// DEFAULT PARAMETERS (ES6)
function Greet(name = 'Samir') { // Default parameter value
    console.log(`Hello ${name}`);
}

Greet(); // Output: Hello Samir
Greet('John'); // Output: Hello John

// GETTERS AND SETTERS
const Courses = {
    name: 'JavaScript',
    hours: 3,

    // Getter - accessed as property, not method
    get Data() {
        return this.name;
    },

    // Setter - set as property, not method
    set Value(Course) {
        this.name = Course;
    }
}

console.log(Courses.Data); // Output: JavaScript (no parentheses!)
Courses.Value = 'Java'; // Set like property, not method
console.log(Courses.Data); // Output: Java

// ERROR HANDLING WITH TRY-CATCH
function Adds(num1) {
    if (num1 < 0)
        throw new Error('This value is not a positive number'); // Throw custom error
    return num1;
}

try {
    console.log(Adds(-1)); // This will throw an error
} catch (e) {
    console.log(`Exception Handling Error: ${e.message}`); // Output: Exception Handling Error: This value is not a positive number
}

// VARIABLE SCOPE
let i = 1; // Global scope

function A() {
    let x = 0; // Function scope - only accessible inside this function
    // console.log(x); // This would work
}
// console.log(x); // This would throw ReferenceError: x is not defined
console.log(i); // Output: 1 (global variable accessible)

// VAR VS LET SCOPE
function Var(num) {
    {
        var z = 0; // var is function-scoped, not block-scoped
    }
    return z + num; // z is accessible here despite being in a block
}

console.log(Var(1)); // Output: 1

// THIS KEYWORD BEHAVIOR
function Log(name) {
    console.log(this); // 'this' depends on how function is called
}

console.log(Log('s')); // Output: undefined (in strict mode) or global object

// ARROW FUNCTIONS AND THIS
const Names = {
    name: 'Samir',
    Age: () => {
        // Arrow functions don't have their own 'this' - they inherit from parent scope
        console.log(this.Age + this.name); // 'this' refers to global object, not Names
    }
}

Names.Age(); // Output: undefinedundefined (or NaN depending on context)

// EXPLICIT THIS BINDING
function Introduce(Language) {
    console.log(this.name + ' Teaches ' + Language);
}

const Teacher = {
    name: "Samir"
};

// bind() creates new function with 'this' permanently bound to Teacher
const Introduction = Introduce.bind(Teacher);
Introduction("Javascript"); // Output: Samir Teaches Javascript

// call() and apply() can also be used for similar purposes
Introduce.call(Teacher, "Python"); // Output: Samir Teaches Python
Introduce.apply(Teacher, ["C++"]); // Output: Samir Teaches C++


