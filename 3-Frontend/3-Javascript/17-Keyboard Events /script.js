// Get the input element with ID "inputBox" and store it in inputBox variable
const inputBox = document.getElementById("inputBox");

// Add keydown event listener to inputBox - triggers when key is pressed down
inputBox.addEventListener("keydown", (e) => {
    // Log the actual character of the key that was pressed
    console.log(e.key);
});

// Add keyup event listener to inputBox - triggers when key is released
inputBox.addEventListener("keyup", (e) => {
    // Log the actual character of the key that was released
    console.log(e.key);
});

// Add keypress event listener to inputBox - triggers when key produces a character
inputBox.addEventListener("keypress", (e) => {
    // Log the actual character of the key that produced a character input
    console.log(e.key);
});

// Add another keydown event listener with additional functionality
inputBox.addEventListener("keydown", (e) => {
    // Check if the pressed key is the Enter key
    if (e.key === "Enter") {
        alert("You pressed Enter");
    }
    
    // Check if the pressed key has the physical code "Digit1" (the 1 key)
    if (e.code === "Digit1") {
        alert("You pressed 1");
    }
    
    // Check if the pressed key has the keyCode 65 (deprecated but still works)
    // Note: keyCode is deprecated and should be avoided in new code
    if (e.keyCode === 65) {
        alert("You pressed on letter a");
    }
    
    // Log three different ways to identify the pressed key:
    console.log(`You pressed ${e.key}`);      // The character value (e.g., "a", "1", "Enter")
    console.log(`You pressed ${e.code}`);     // The physical key code (e.g., "KeyA", "Digit1", "Enter")
    console.log(`You pressed ${e.keyCode}`);  // The deprecated numeric code (e.g., 65, 49, 13)
});


