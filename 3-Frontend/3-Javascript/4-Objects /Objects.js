// Object literal syntax - creating an object with properties and methods
const Course = {
    name: 'JavaScript101',
    hours: 3,
    Sections: 7,
    Teach: function() {
        console.log("Coding in javascript");
    }
};

// Accessing object properties and methods
console.log(Course); // Logs the entire object
console.log(Course.name); // Access property using dot notation
console.log(Course.hours); // Access property using dot notation
console.log(Course.Sections); // Access property using dot notation
Course.Teach(); // Call the method using dot notation

// Factory function pattern - creates objects without using 'new'
function Courses(name, hours, Section) {
    return {
        name, // Shorthand for name: name (ES6 feature)
        hours, // Property shorthand
        Section,
        TeachCode() { // Method shorthand (ES6 feature)
            console.log("Coding in " + name);
        }
    }
}

// Create object using factory function
const Teaching = Courses('Java', 49, 457); // No 'new' keyword needed
console.log(Teaching);
Teaching.TeachCode(); // Call the method

// Constructor function pattern - creates objects with 'new' keyword
function Cat(name, food, type) {
    // 'this' refers to the new object being created
    this.name = name;
    this.food = food;
    this.type = type;
    this.Sound = function() {
        console.log("Meow");
    }
}

// Create object using constructor function with 'new' keyword
const Cats = new Cat('Toto', 'Fish', 'Balady');
console.log(Cats);
Cats.Sound();

// Object manipulation - adding, modifying, and deleting properties
const CodingCourse = {
    name: 'Java',
    Price: 17
};

console.log(CodingCourse);

// Adding a new property using dot notation
CodingCourse.Place = 'Cairo';
console.log(CodingCourse);

// Adding a new property using bracket notation (useful for dynamic property names)
CodingCourse['NumberOfStudents'] = 29;
console.log(CodingCourse);

// Deleting a property using 'delete' operator
delete CodingCourse.Place;
console.log(CodingCourse);

// Object reference vs copy
let b = CodingCourse; // This creates a reference, not a copy
console.log(b);

// Modifying the original object affects the reference
CodingCourse.Price = 31;
console.log(b); // b also shows the updated price

// Creating a shallow copy using Object.assign()
let z = {};
Object.assign(z, CodingCourse); // Copies properties from CodingCourse to z
console.log(z);

// Modifying the original object does NOT affect the copy
CodingCourse.name = 'Java and SpringBoot';
console.log(z); // z remains unchanged

// Object static methods for working with objects
let Array1 = Object.keys(CodingCourse); // Returns array of property names
console.log(Array1);

let Array2 = Object.values(CodingCourse); // Returns array of property values
console.log(Array2);

let Array3 = Object.entries(CodingCourse); // Returns array of key-value pairs
console.log(Array3);

// Math object methods for mathematical operations
let Number1 = Math.floor(3.9); // Rounds down to nearest integer
console.log(Number1); // Output: 3

Number1 = Math.ceil(3.1); // Rounds up to nearest integer
console.log(Number1); // Output: 4

Number1 = Math.round(3.6); // Rounds to nearest integer
console.log(Number1); // Output: 4

Number1 = Math.sqrt(25); // Square root
console.log(Number1); // Output: 5

Number1 = Math.pow(2, 4); // Exponentiation (2^4)
console.log(Number1); // Output: 16

Number1 = Math.random() * 10; // Random number between 0 and 10
console.log(Number1);

// String methods for text manipulation
let Name = 'Samir loves Programming';
console.log(Name.toUpperCase()); // Converts to uppercase
console.log(Name.split(' ')); // Splits into array by space
console.log(Name.replace('loves', 'Likes')); // Replaces text
console.log(Name.toLowerCase()); // Converts to lowercase
console.log(Name.endsWith('Coding')); // Checks if string ends with 'Coding' (false)
console.log(Name.indexOf('L')); // Returns index of first 'L' (case-sensitive)
console.log(Name.includes('Programming')); // Checks if string contains 'Programming' (true)

//String Interpolation 
let FirstName = "Lola";
let LastName = "Gamal";
let Names = `${FirstName} ${LastName}`;
console.log(Names);

// Date object for working with dates and times
let DateTime = Date.now(); // Current timestamp in milliseconds since Unix epoch
console.log(DateTime);

DateTime = new Date(Date.now()); // Create Date object from timestamp
console.log(DateTime.toLocaleString()); // Localized date and time string

DateTime = new Date(); // Create Date object for current moment
console.log(DateTime.toTimeString()); // Time portion as string



