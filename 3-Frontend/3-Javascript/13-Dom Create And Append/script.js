// Create a new unordered list element
const ul = document.createElement("ul");

// Set the class attribute of the ul to "nav-list"
ul.setAttribute("class", "nav-list");

// Function to create and add list items to the ul
function CreateListItem(text) {
    // Create a new list item element
    const li = document.createElement("li");
    
    // Create a text node with the provided text
    const listText = document.createTextNode(text);
    
    // Append the li to the ul
    ul.appendChild(li);
    
    // Append the text node to the li
    li.appendChild(listText);
}

// Append the ul to the body of the document
document.body.appendChild(ul);

// Create list items using the function
CreateListItem("Home");
CreateListItem("About");
CreateListItem("Services");
CreateListItem("Contact");

// Create additional list item elements
const li1 = document.createElement("li");
const li2 = document.createElement("li");
const li3 = document.createElement("li");
const li4 = document.createElement("li");

// Create text nodes for the new list items
const Text2 = document.createTextNode("About");
const Text3 = document.createTextNode("Services");
const Text4 = document.createTextNode("contact");

// Append the first new li to the ul
ul.appendChild(li1);

// Append multiple li elements at once using append()
ul.append(li2, li3, li4);

// Add text nodes to the li elements
li2.appendChild(Text2);
li3.appendChild(Text3);
li4.appendChild(Text4);

// Set the innerHTML of li1 to "Home" (overwriting any existing content)
li1.innerHTML = "Home";

// Log the ul element to the console to see its structure
console.log(ul);

// Append the ul to the body again (redundant since it was already appended earlier)
document.body.appendChild(ul);


