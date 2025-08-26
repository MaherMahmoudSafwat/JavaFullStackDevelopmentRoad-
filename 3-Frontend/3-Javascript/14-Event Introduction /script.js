//Inline
// Define a function that logs a message when called
function clicked() {
    console.log("Hello from javascript");
}

//DOM Property 
// Select the element with class "btn2"
const btn = document.querySelector(".btn2");

// Assign a click handler function to btn - this will be overwritten
btn.onclick = function() {
    console.log("first Click");
}

// Overwrite the previous click handler - only this one will execute
btn.onclick = function() {
    alert("Hello");
}

// Select the element with class "btn3"
const Button = document.querySelector(".btn3");

// Add first click event listener using anonymous function
Button.addEventListener("click", function() {
    console.log("First Click");
})

// Add second click event listener using arrow function
Button.addEventListener("click", () => {
    console.log("Second Click");
})

// Define a named function for click handling
function CLicked() {
    console.log("Third Click");
}

// Add third click event listener using the named function
Button.addEventListener("click", CLicked);


