// Log the root element of the document (the <html> element)
console.log(document.documentElement);

// Log the <head> element of the document
console.log(document.head);

// Log the title text of the document (content of the <title> tag)
console.log(document.title);

// Log the <body> element of the document
console.log(document.body);

// Log all child elements of the <head> element (returns an HTMLCollection)
console.log(document.head.children);

// Log the second child element of the <head> (index 1)
console.log(document.head.children[1]);

// Log all child elements of the <body> element (returns an HTMLCollection)
console.log(document.body.children);

// Log the fourth child element of the <body> (index 3)
console.log(document.body.children[3]);

// Log all link elements (<a> and <area> with href attribute) in the document
console.log(document.links);

// Log the first link element in the document
console.log(document.links[0]);

// Log the href attribute value of the first link element
console.log(document.links[0].href);

// Change the href attribute of the first link element to point to GitHub
document.links[0].href = "https://github.com";

// Log all form elements in the document (returns an HTMLCollection)
console.log(document.forms);

// Log the first form element in the document
console.log(document.forms[0]);

// Log the form control (input) with name="Name" from the first form
console.log(document.forms[0].Name);

// Log the form control (input) with name="Email" from the first form
console.log(document.forms[0].Email);

// Log all image elements (<img>) in the document (returns an HTMLCollection)
console.log(document.images);

// Log the first image element in the document
console.log(document.images[0]);

// Log the alt attribute value of the first image element
console.log(document.images[0].alt);

// Log the src attribute value of the first image element
console.log(document.images[0].src);


