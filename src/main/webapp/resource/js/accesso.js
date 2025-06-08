document.addEventListener("DOMContentLoaded", function () {
  const loginText = document.querySelector(".title-text .login");
  const loginForm = document.querySelector("form.login");
  const loginBtn = document.querySelector("label.login");
  const signupBtn = document.querySelector("label.signup");
  const signupLink = document.querySelector("form .signup-link a");

  loginBtn.onclick = () => {
    loginForm.style.marginLeft = "0%";
    loginText.style.marginLeft = "0%";
  };

  signupBtn.onclick = () => {
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
  };

  if (signupLink) {
    signupLink.onclick = () => {
      signupBtn.click();
      return false;
    };
  }

  // Logica per mostrare il form registrazione se c'è un errore
  const selectedForm = document.body.dataset.formSelezionato;
  if (selectedForm === "signup") {
    document.getElementById("signup").checked = true;
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
  }
});