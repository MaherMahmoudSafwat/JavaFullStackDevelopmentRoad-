// Get references to various form elements by their IDs
const FormSubmit = document.getElementById("formSubmit"); // The form element
const inputSubmit = document.getElementById("inputSubmit"); // An input field
const formReset = document.getElementById("formReset"); // Reset button
const Change = document.getElementById("country"); // Country select/dropdown
const liveInput = document.getElementById("liveInput"); // Input for live updates
const output = document.getElementById("output"); // Output element to display text
const email = document.getElementById("email"); // Email input field

// Add submit event listener to the form
FormSubmit.addEventListener("submit", (e) => {
    e.preventDefault(); // Prevent the form from submitting and page refresh
    console.log("Submitted"); // Log confirmation message
    console.log(inputSubmit.value); // Log the value of the input field
});

// Add reset event listener to the form
FormSubmit.addEventListener("reset", (e) => {
    alert("Form has been cleared!"); // Show alert when form is reset
});

// Add change event listener to the country dropdown
Change.addEventListener("change", (e) => {
    // Log the selected country value when it changes
    console.log(`You selected ${e.currentTarget.value}`);
});

// Add input event listener for real-time updates
liveInput.addEventListener("input", (e) => {
    // Update the output element with the current input value in real-time
    output.innerText = e.currentTarget.value;
});

// Add focus event listener to email input
email.addEventListener("focus", (e) => {
    // Change background color when email input gains focus
    e.currentTarget.style.backgroundColor = "#e0f7fa";
});

// Add blur event listener to email input
email.addEventListener("blur", (e) => {
    // Reset background color when email input loses focus
    e.currentTarget.style.backgroundColor = "";
});



