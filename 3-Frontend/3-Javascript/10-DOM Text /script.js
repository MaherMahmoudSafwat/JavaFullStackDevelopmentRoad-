// Select the first <p> element in the document and store it in FirstParagraph
const FirstParagraph = document.querySelector("p");

// Log the entire paragraph element to the console
console.log(FirstParagraph);

// Log the HTML content inside the paragraph (including any HTML tags)
console.log(FirstParagraph.innerHTML);

// This line is commented out but would replace the paragraph's content with an H3 heading
// FirstParagraph.innerHTML = "<h3>Heading 3</h3>";

// Log only the visible text content of the paragraph (excluding HTML tags)
console.log(FirstParagraph.innerText);

// Log all text content including hidden text and preserving whitespace formatting
console.log(FirstParagraph.textContent);


