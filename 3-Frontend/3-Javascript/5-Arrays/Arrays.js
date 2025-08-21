// Create initial array
const Array1 = [1, 2, 3, 4, 5];
console.log("Initial array:", Array1);  // Output: [1, 2, 3, 4, 5]

// pop() removes and returns the last element from an array
Array1.pop();
console.log("After pop():", Array1);    // Output: [1, 2, 3, 4]

// shift() removes and returns the first element from an array  
Array1.shift();
console.log("After shift():", Array1);  // Output: [2, 3, 4]

// splice() - modifies array by removing/replacing elements
// splice(startIndex, deleteCount, itemsToAdd)
// ⚠️ WARNING: Trying to remove 4 elements but only 1 element exists from index 2
Array1.splice(2, 4, 0); // At index 2, remove 4 elements (only 1 exists), add 0
console.log("After splice(2,4,0):", Array1);  // Output: [2, 3, 0]

// Setting length to 0 empties the array completely
Array1.length = 0;
console.log("After length=0:", Array1); // Output: []

// Rebuilding the array with various methods
Array1.push(6);     // Add to end: [6]
Array1.unshift(1);  // Add to beginning: [1, 6]
Array1.splice(1, 1, 4); // At index 1, remove 1 element, add 4: [1, 4]
console.log("After rebuilding:", Array1); // Output: [1, 4]

// splice() to insert multiple elements
Array1.splice(2, 0, 3, 4, 5); // At index 2, remove 0 elements, add 3,4,5
console.log("After inserting elements:", Array1); // Output: [1, 4, 3, 4, 5]

// filter() creates new array with elements that pass test
let EvenArray1 = Array1.filter(number => number % 2 === 0);
console.log("Even numbers:", EvenArray1); // Output: [4, 4]

// forEach() executes a function for each array element
console.log("forEach output:");
Array1.forEach(element => console.log(element)); // Logs each element

// map() creates new array with results of calling function on every element
let NewArray1 = Array1.map((number, index) => number * index);
console.log("Mapped array (element * index):", NewArray1);

// reduce() reduces array to single value using accumulator
let SumArray1 = Array1.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
console.log("Sum of array:", SumArray1);

// join() creates string from array elements with specified separator
let JoinArray1 = Array1.join(' ');
console.log("Joined array:", JoinArray1);

// concat() merges two or more arrays
let A1 = [1, 2, 3];
let A2 = [4, 5, 6];
let A3 = A1.concat(A2);
console.log("Concatenated arrays:", A3); // Output: [1, 2, 3, 4, 5, 6]

// Spread operator (...) expands array into individual elements
A3 = [...A1, 5, ...A2]; // Insert 5 between the arrays
console.log("Using spread operator:", A3); // Output: [1, 2, 3, 5, 4, 5, 6]

// sort() sorts array elements (default is string conversion)
Array1.sort();
console.log("Default sort (lexicographical):", Array1);

// Custom sort function for numerical descending order
Array1.sort((number1, number2) => {
    if (number1 > number2)
        return -1;      // number1 comes first
    else if (number1 < number2)
        return 1;       // number2 comes first
    else
        return 0;       // they're equal
});
console.log("Custom descending sort:", Array1);

// Array of objects
const Courses = [
    {
        name: 'Javascript',
        hours: 3
    },
    {
        name: 'C++',
        hours: 15
    },
    {
        name: 'Java',
        hours: 53
    }
]

// find() returns first element that satisfies condition
const Course = Courses.find((e) => e.name === 'Java');
console.log("Found course:", Course);

// Arrow function syntax
let Add = (Num1, Num2) => Num1 + Num2;
console.log("Arrow function result:", Add(3, 4)); // Output: 7

// Working with new array
const Arrays = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// slice() returns shallow copy of portion of array
let A = Arrays.slice(1, 5); // Elements from index 1 to 4 (5 not included)
console.log("Slice(1,5):", A); // Output: [2, 3, 4, 5]

// every() tests if ALL elements pass the condition
const Even = Arrays.every((Number) => Number % 2 === 0);
console.log("All even?", Even); // Output: false

// some() tests if AT LEAST ONE element passes the condition
const Odd = Arrays.some((Number) => Number % 2 !== 0);
console.log("Any odd?", Odd); // Output: true

// indexOf() returns first index of element
A = Arrays.indexOf(1);
console.log("Index of 1:", A); // Output: 0

// lastIndexOf() returns last index of element  
A = Arrays.lastIndexOf(3);
console.log("Last index of 3:", A); // Output: 2

// includes() checks if array contains element
A = Arrays.includes(9);
console.log("Includes 9?", A); // Output: true

// Array access
console.log("Element at index 1:", Arrays[1]); // Output: 2



