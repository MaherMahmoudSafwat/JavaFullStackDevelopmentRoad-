const NavList = document.querySelector(".nav-list");
const SecondListItem = document.querySelector("ul li");

console.log(NavList);
console.log(SecondListItem);

console.log(NavList.children);
console.log(NavList.children[1].innerText);

console.log(NavList.firstElementChild);
console.log(NavList.lastElementChild);

const Second = document.querySelector("ul li:nth-child(2)");
console.log(Second);
console.log(Second.previousElementSibling);
console.log(Second.nextElementSibling);
console.log(Second.parentElement);
console.log(Second.parentElement.parentElement);