// Select the element with class "nav-list" (typically a <ul> or <ol>)
const navList = document.querySelector(".nav-list");

// Select the second list item (<li>) within any unordered list
const secondListItem = document.querySelector("ul li:nth-child(2)");

// Log all direct child ELEMENTS (HTMLCollection) of navList - excludes text nodes, comments, etc.
console.log(navList.children);

// Log all direct child NODES (NodeList) of navList - includes elements, text nodes, comments, etc.
console.log(navList.childNodes);

// Log the text content of the second child NODE (often a text node containing whitespace)
console.log(navList.childNodes[1].textContent);

// Log the first child NODE (often a text node with whitespace, not an element)
console.log(navList.firstChild);

// Log the first child ELEMENT (ignores text nodes, comments, etc.)
console.log(navList.firstElementChild);

// Log the last child ELEMENT (ignores text nodes, comments, etc.)
console.log(navList.lastElementChild);

// Log the last child NODE (often a text node with whitespace, not an element)
console.log(navList.lastChild);

// Log the previous sibling NODE (often a text node with whitespace)
console.log(secondListItem.previousSibling);

// Log the next sibling NODE (often a text node with whitespace)
console.log(secondListItem.nextSibling);

// Log the parent ELEMENT (always an element node)
console.log(secondListItem.parentElement);

// Log the parent NODE (could be an element, document, or document fragment)
console.log(secondListItem.parentNode);
