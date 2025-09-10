document.addEventListener("keydown", (e) => {
    if(e.key === "Enter") {
        document.getElementById("submitBtn").click();
    };
});

const FirstName = document.getElementById("firstName");
const LastName = document.getElementById("lastName");
const Email = document.getElementById("email");
const Phone = document.getElementById("phone");
const Password = document.getElementById("password");

FirstName.addEventListener("focus",(e)=>
{
    e.currentTarget.style.backgroundColor = "lightblue";
});

FirstName.addEventListener("blur",(e)=>
{
    e.currentTarget.style.backgroundColor = "white";
});

LastName.addEventListener("focus",(e)=>
{
    e.currentTarget.style.backgroundColor = "lightblue";
});

LastName.addEventListener("blur",(e)=>
{
    e.currentTarget.style.backgroundColor = "white";
});

Email.addEventListener("focus",(e)=>
{
    e.currentTarget.style.backgroundColor = "lightblue";
});

Email.addEventListener("blur",(e)=>
{
    e.currentTarget.style.backgroundColor = "white";
});

Phone.addEventListener("focus",(e)=>
{
    e.currentTarget.style.backgroundColor = "lightblue";
});

Phone.addEventListener("blur",(e)=>
{
    e.currentTarget.style.backgroundColor = "white";
});

Password.addEventListener("focus",(e)=>
{
    e.currentTarget.style.backgroundColor = "lightblue";
});

Password.addEventListener("blur",(e)=>
{
    e.currentTarget.style.backgroundColor = "white";
});