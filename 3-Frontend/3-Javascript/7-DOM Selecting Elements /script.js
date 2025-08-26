// Log the global window object (the entire browser window)
console.log(window);

// Log the document object (the HTML document) through the window
console.log(window.document);

// Log the document object directly (same as window.document)
console.log(document);

// Get the element with ID "main" and store it in the Main variable
const Main = document.getElementById("main");
console.log(Main);

// Replace the HTML content inside the Main element with an h1 tag
Main.innerHTML = "<h1>Mohammed Elkasef</h1>";

// Get all elements with class "list-item" and store them in ListItem
const ListItem = document.getElementsByClassName("list-item");
console.log(ListItem);

// Log the third element (index 2) from the ListItem collection
console.log(ListItem[2]);

// Change the HTML content of the third list item to "Products"
ListItem[2].innerHTML = "Products";

// Log the updated third element to confirm the change
console.log(ListItem[2]); 

// Get all paragraph elements (<p>) and store them in TagName
const TagName = document.getElementsByTagName("p");
console.log(TagName);

// Use querySelector to get the first element matching the CSS selector #main
console.log(document.querySelector("#main"));

// Use querySelector to get the first element with class "list-item"
console.log(document.querySelector(".list-item"));

// Use querySelector to get the first paragraph element
console.log(document.querySelector("p"));

// Use querySelectorAll to get all elements with class "list-item"
console.log(document.querySelectorAll(".list-item"));


