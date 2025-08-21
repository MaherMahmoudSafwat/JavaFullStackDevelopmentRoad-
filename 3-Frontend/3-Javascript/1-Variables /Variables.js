// Declare a variable 'x' and assign it the string value "Maher"
let x = "Maher";
// Print the value of 'x' to the console
console.log(x);  // Output: Maher
// Print the type of 'x' (string)
console.log(typeof x);  // Output: string

// Reassign 'x' to the number 5
x = 5;
// Print the new value of 'x'
console.log(x);  // Output: 5
// Print the new type of 'x' (number)
console.log(typeof x);  // Output: number

// Declare a variable 'y' and assign it the boolean value true
let y = true;
// Print the value of 'y'
console.log(y);  // Output: true
// Print the type of 'y' (boolean)
console.log(typeof y);  // Output: boolean

// Declare a variable 'z' without assigning a value
let z;
// Print the value of 'z' (undefined)
console.log(z);  // Output: undefined
// Print the type of 'z' (undefined)
console.log(typeof z);  // Output: undefined

// Reassign 'y' to null
y = null;
// Print the value of 'y' (null)
console.log(y);  // Output: null
// Print the type of 'y' (object - this is a known JavaScript quirk)
console.log(typeof y);  // Output: object

// Declare an object with two properties
let Object = {
    name: "Javascript101",  // string property
    hours: 5               // number property
};

// Print the entire object
console.log(Object);  // Output: { name: 'Javascript101', hours: 5 }

// Access and print the 'name' property of the object
console.log(Object.name);  // Output: Javascript101

// Declare an array of strings
let Courses = ['Java', 'Javascript', 'C++', 'SpringBoot', 'Angular'];

// Access and print the first element of the array (index 0)
console.log(Courses[0]);  // Output: Java

// Declare a variable with a property name
let Property = 'hours';
// Access the object property using bracket notation with a variable
console.log(Object[Property]);  // Output: 5 (same as Object.hours)

// Define a function that logs "Hello World" to the console
function Function1() {
    console.log("Hello World");
}

// Call the function
Function1();  // Output: Hello World

// Define a function that takes a parameter and returns a greeting string
function SayHello(name) {
    return 'Hello ' + name;
}

// Call the function with an argument and print the returned value
console.log(SayHello('Mostafa'));  // Output: Hello Mostafa


