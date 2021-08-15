/*nav links */
ActivatLink();

//User From
const UserForm = document.getElementById("UserForm");
const UsernameNode = document.getElementById("Username");
const emailNode = document.getElementById("email");
const dobNode = document.getElementById("dob");
const PassNode = document.getElementById("Pass");
//---------------------------------------------------
UserForm.addEventListener("submit", ValidateUserForm);

//Shoes Form
const ShoesForm = document.getElementById("ShoesForm");
const ProductNameNode = document.getElementById("ProductName");
const descriptionNode = document.getElementById("description");
const categoryNode = document.getElementById("category");
const inStockNode = document.getElementById("inStock");
const priceNode = document.getElementById("price");
//-------------------------------------------------
ShoesForm.addEventListener("submit", ValidateShoesForm);

//change the active links base on URL
function ActivatLink() {
  let currentLocation = location.href;

  const NavLinks = document.querySelectorAll(".nav-link");
  let lengthOfLinks = NavLinks.length;
  for (let i = 0; i < lengthOfLinks; i++) {
    if (NavLinks[i].href === currentLocation) {
      NavLinks[i].classList.add("active");
    }
  }

  const SideLinks = document.querySelectorAll(".list-group-item-action");
  let lengthOfSideLinks = SideLinks.length;

  for (let i = 0; i < lengthOfSideLinks; i++) {
    if (SideLinks[i].href === currentLocation) {
      SideLinks[i].classList.add("active");
    }
  }
}

function ValidateShoesForm(e) {
  let isNotValid = false;

  let ProductNameValue = ProductNameNode.value.trim();

  let descriptionValue = descriptionNode.value.trim();

  let categoryValue = categoryNode.value.trim();

  let inStock = inStockNode.value;

  let priceValue = priceNode.value;

  if (ProductNameValue === "") {
    setErrorFor(ProductNameNode);
    isNotValid = true;
  } else {
    setSuccessFor(ProductNameNode);
  }

  if (descriptionValue === "") {
    setErrorFor(descriptionNode);
    isNotValid = true;
  } else {
    setSuccessFor(descriptionNode);
  }

  if (categoryValue === "") {
    setErrorFor(categoryNode);
    isNotValid = true;
  } else {
    setSuccessFor(categoryNode);
  }

  if (inStock < 0 || inStock === "") {
    setErrorFor(inStockNode);
    isNotValid = true;
  } else {
    setSuccessFor(inStockNode);
  }

  if (priceValue <= 0) {
    setErrorFor(priceNode);

    isNotValid = true;
  } else {
    setSuccessFor(priceNode);
  }

  if (isNotValid) {
    e.preventDefault();
  }
}

function ValidateUserForm(e) {
  let isNotValid = false;
  let UsernameValue = UsernameNode.value.trim();
  let emailValue = emailNode.value.trim();
  let dobValue = dobNode.value.trim();
  let PassValue = PassNode.value.trim();

  console.log("enter the user validation");

  if (UsernameValue === "") {
    setErrorFor(UsernameNode);
    isNotValid = true;
  } else {
    setSuccessFor(UsernameNode);
  }

  if (emailValue === "" || String(emailValue).includes("@") === false) {
    setErrorFor(emailNode);
    isNotValid = true;
  } else {
    setSuccessFor(emailNode);
  }

  if (dobValue === "") {
    setErrorFor(dobNode);
    isNotValid = true;
  } else {
    setSuccessFor(dobNode);
  }

  if (PassValue === "") {
    setErrorFor(PassNode);
    isNotValid = true;
  } else {
    setSuccessFor(PassNode);
  }

  if (isNotValid) {
    e.preventDefault();
  }
}

function setErrorFor(inputNode) {
  inputNode.classList.remove("is-valid");
  inputNode.classList.add("is-invalid");
}
function setSuccessFor(inputNode) {
  inputNode.classList.remove("is-invalid");
  inputNode.classList.add("is-valid");
}
