// Select the first element with class "main-link" and store it in myLink
const myLink = document.querySelector(".main-link");
console.log(myLink); // Log the element to the console

// Get the value of the "class" attribute of myLink and log it
console.log(myLink.getAttribute("class"));

// Get the value of the "href" attribute of myLink and log it
console.log(myLink.getAttribute("href"));

// Set the "target" attribute of myLink to "_blank" (opens link in new tab)
myLink.setAttribute("target", "_blank");
console.log(myLink); // Log the element to show the updated attribute

// Set a custom "title" attribute (tooltip text) for myLink
myLink.setAttribute("title", "ELkashef site");
console.log(myLink); // Log the element to show the updated attribute

// Remove the "title" attribute from myLink
myLink.removeAttribute("title");
console.log(myLink); // Log the element to show the attribute was removed

// Check if myLink has a "target" attribute and log the result (true/false)
console.log(myLink.hasAttribute("target"));

// Check if myLink has a "title" attribute and log the result (true/false)
console.log(myLink.hasAttribute("title"));

// Select the first paragraph element in the document
const FirstParagraph = document.querySelector("p");

// Add a click event listener to the paragraph
FirstParagraph.addEventListener("click", () => {
    // When clicked, set a custom data attribute "data-green" with empty value
    FirstParagraph.setAttribute("data-green", "");
    console.log(FirstParagraph); // Log the element to show the new attribute
    
    // Check if the paragraph has the "data-green" attribute
    if (FirstParagraph.hasAttribute("data-green")) {
        // If it does, change the text color to green
        FirstParagraph.style.color = "green";
    }
});



