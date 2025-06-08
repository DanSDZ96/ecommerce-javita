function togglePassword() {
  const input = document.getElementById("myInput");
  const showIcon = document.querySelector(".show-icon");
  const hideIcon = document.querySelector(".hide-icon");

  if (input.type === "password") {
    input.type = "text";
    showIcon.style.display = "none";
    hideIcon.style.display = "inline";
  } else {
    input.type = "password";
    showIcon.style.display = "inline";
    hideIcon.style.display = "none";
  }
}

function togglePasswordRegister(toggleSpan) {
  const wrapper = toggleSpan.parentElement;
  const input = wrapper.querySelector("input[type='password'], input[type='text']");
  const showIcon = toggleSpan.querySelector(".show-icon");
  const hideIcon = toggleSpan.querySelector(".hide-icon");

  if (input.type === "password") {
    input.type = "text";
    showIcon.style.display = "none";
    hideIcon.style.display = "inline";
  } else {
    input.type = "password";
    showIcon.style.display = "inline";
    hideIcon.style.display = "none";
  }
}

