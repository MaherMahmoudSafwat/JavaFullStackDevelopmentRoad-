// Get references to DOM elements
const btn = document.getElementById("btn");          // Button with ID "btn"
const text = document.querySelector(".text");        // Element with class "text"
const Box = document.querySelector(".box");          // Element with class "box"
const PressBtn = document.querySelector(".press");   // Element with class "press"
const boxImg = document.querySelector(".box-img");   // Element with class "box-img"

// Add click event listener to the button
btn.addEventListener("click", function() {
    console.log("Clicked");  // Log message when button is clicked
});

// Add double-click event listener to the text element
text.addEventListener("dblclick", function() {
    text.style.color = "blue";  // Change text color to blue on double-click
});

// Add mouseover event listener to the box
Box.addEventListener("mouseover", function() {
    Box.style.backgroundColor = "red";  // Change box background to red when mouse enters
});

// Add mouseout event listener to the box
Box.addEventListener("mouseout", function() {
    Box.style.backgroundColor = "lightgray";  // Revert box background when mouse leaves
    console.log("Mouse left from inside.");   // Log message when mouse leaves
});

// Add mousedown event listener to the press button
PressBtn.addEventListener("mousedown", function() {
    PressBtn.style.backgroundColor = "red";  // Change button color to red when pressed
    PressBtn.textContent = "Holding";        // Change button text to "Holding"
});

// Add mouseup event listener to the press button
PressBtn.addEventListener("mouseup", function() {
    PressBtn.style.backgroundColor = "green";  // Change button color to green when released
    PressBtn.textContent = "released";         // Change button text to "released"
});

// Add mouseleave event listener to the box
Box.addEventListener("mouseleave", function() {
    Box.style.backgroundColor = "lightgray";  // Revert box background when mouse leaves boundaries
    console.log("Mouse left from boundaries"); // Log message when mouse leaves boundaries
});

// Add contextmenu event listener to the box (right-click)
Box.addEventListener("contextmenu", function() {
    console.log("Right click");  // Log message when right-clicked
});

// Add dragstart event listener to the image
boxImg.addEventListener("dragstart", function() {
    console.log("drag start...");  // Log message when drag starts
});

// Add drag event listener to the image (fires continuously during dragging)
boxImg.addEventListener("drag", function() {
    console.log("dragging Middle...");  // Log message during dragging
});

// Add dragend event listener to the image
boxImg.addEventListener("dragend", function() {
    console.log("drag end...");  // Log message when drag ends
});


