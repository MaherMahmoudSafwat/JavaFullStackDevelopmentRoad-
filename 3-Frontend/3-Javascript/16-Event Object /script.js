const container = document.querySelector(".container");
const Title = document.querySelector(".title");
document.addEventListener("click",(event)=>
{
    console.log(event.type);
});

document.addEventListener("click",(e)=>
{
    console.log(e.target);
});

container.addEventListener("click",(e)=>
{
    console.log(e.currentTarget);
});

document.addEventListener("click",(e)=>
{
    console.log(e.timeStamp);
});

document.addEventListener("click",(e)=>
{
    console.log(`Client X : ${e.clientX}`);
});

document.addEventListener("click",(e)=>
{
    console.log(`Client Y : ${e.clientY}`);
});

document.addEventListener("click",(e)=>
{
    console.log(`Page X : ${e.pageX}`);
});

document.addEventListener("click",(e)=>
{
    console.log(`Page Y : ${e.pageY}`);
});

Title.addEventListener("click",(e)=>
{
    console.log(`Offset X ${e.offsetX}`);
});

Title.addEventListener("click",(e)=>
{
    console.log(`Offset Y ${e.offsetY}`);
});

document.addEventListener("click",(e)=>
{
    console.log(`Screen X : ${e.screenX}`);
});

document.addEventListener("click",(e)=>
{
    console.log(`Screen Y : ${e.screenY}`);
});
// Select the element with class "container" and store it in container variable
const container = document.querySelector(".container");

// Select the element with class "title" and store it in Title variable
const Title = document.querySelector(".title");

// Add click event listener to the entire document
document.addEventListener("click", (event) => {
    // Log the type of event that occurred (should be "click")
    console.log(event.type);
});

// Add another click event listener to the document using different parameter name
document.addEventListener("click", (e) => {
    // Log the element that was actually clicked (the target of the event)
    console.log(e.target);
});

// Add click event listener to the container element
container.addEventListener("click", (e) => {
    // Log the element that the event listener is attached to (container)
    console.log(e.currentTarget);
});

// Add click event listener to document to track timing
document.addEventListener("click", (e) => {
    // Log the timestamp (in milliseconds) of when the event occurred
    console.log(e.timeStamp);
});

// Add click event listener to document to track X coordinate relative to viewport
document.addEventListener("click", (e) => {
    // Log the X coordinate of the click relative to the browser's viewport
    console.log(`Client X : ${e.clientX}`);
});

// Add click event listener to document to track Y coordinate relative to viewport
document.addEventListener("click", (e) => {
    // Log the Y coordinate of the click relative to the browser's viewport
    console.log(`Client Y : ${e.clientY}`);
});

// Add click event listener to document to track X coordinate relative to whole document
document.addEventListener("click", (e) => {
    // Log the X coordinate of the click relative to the entire document
    console.log(`Page X : ${e.pageX}`);
});

// Add click event listener to document to track Y coordinate relative to whole document
document.addEventListener("click", (e) => {
    // Log the Y coordinate of the click relative to the entire document
    console.log(`Page Y : ${e.pageY}`);
});

// Add click event listener to the Title element to track X coordinate relative to the element
Title.addEventListener("click", (e) => {
    // Log the X coordinate of the click relative to the Title element itself
    console.log(`Offset X ${e.offsetX}`);
});

// Add click event listener to the Title element to track Y coordinate relative to the element
Title.addEventListener("click", (e) => {
    // Log the Y coordinate of the click relative to the Title element itself
    console.log(`Offset Y ${e.offsetY}`);
});

// Add click event listener to document to track X coordinate relative to the physical screen
document.addEventListener("click", (e) => {
    // Log the X coordinate of the click relative to the entire screen/monitor
    console.log(`Screen X : ${e.screenX}`);
});

// Add click event listener to document to track Y coordinate relative to the physical screen
document.addEventListener("click", (e) => {
    // Log the Y coordinate of the click relative to the entire screen/monitor
    console.log(`Screen Y : ${e.screenY}`);
});



