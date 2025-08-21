// FizzBuzz Program
// Checks divisibility of a number and prints specific strings
let x = 5;

// Check if divisible by both 3 and 5 first (most specific condition)
if (x % 3 === 0 && x % 5 === 0) {
    console.log('FizzBuzz');
} 
// Then check if divisible by 3
else if (x % 3 === 0) {
    console.log('Fizz');
} 
// Then check if divisible by 5
else if (x % 5 === 0) {
    console.log('Buzz');
} 
// Default case - print the number itself
else {
    console.log(x);
}
// Output for x=5: "Buzz"

// Switch Statement Example
let str = 'ABC'; // Changed variable name from 'String' to avoid shadowing the String object

switch (str) {
    case 'A':
        console.log('A');
        break;
    case 'B':
        console.log('B');
        break;
    case 'C':
        console.log('C');
        break;
    default:
        console.log(str); // Will execute if no cases match
}
// Output: "ABC" (since none of the cases match)

// Array Iteration with For Loop
let system = [0, 1, 2, 3, 4, 5, 6, 7, 9, 10]; // Changed variable name to lowercase (convention)

for (let i = 0; i < system.length; i++) { // Changed 'x' to 'i' for clarity
    if (system[i] % 2 === 0) {
        console.log(system[i] + ' is Even');
    } else {
        console.log(system[i] + ' is Odd');
    }
}
/* Output:
0 is Even
1 is Odd
2 is Even
...
10 is Even
*/

// While Loop with Break and Continue
let sum = -1; // Changed 'Sum' to 'sum' (camelCase convention)

while (true) {
    sum++;
    
    // Skip iteration when sum equals 5
    if (sum === 5) {
        continue; // This creates an infinite loop - fixed below
    }
    
    console.log(sum);
    
    // Exit loop when sum reaches 10
    if (sum === 10) {
        break;
    }
}
/* Original issue: When sum=5, it would continue without incrementing, causing infinite loop
   Fixed by moving the increment before the continue check */

// Do-While Loop Example
sum = 0;
do {
    console.log(sum);   
} while (sum != 0);
// Output: 0 (executes once before checking condition)

// Object Iteration - Fixed Version
// Original had duplicate properties which would be overwritten
const courses = [ // Changed to array of objects for proper data structure
    { name: "Javascript101", hours: 3, sections: 7 },
    { name: "Java", hours: 12, sections: 73 },
    { name: "C++", hours: 31, sections: 137 },
    { name: "SpringBoot", hours: 51, sections: 459 }
];

// Iterate through array of course objects
for (let course of courses) {
    console.log(course);
    // Alternatively access specific properties:
    // console.log(`Course: ${course.name}, Hours: ${course.hours}`);
}

// For-Of Loop with Array
let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]; // Better variable name than 'Array'

for (let num of numbers) { // 'num' is clearer than 'x'
    console.log(num);
}
// Output: 1 2 3 ... 12