//signup.js

const API_BASE = "http://localhost:8080/api";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("signupForm");
  if (!form) return;

  const nombreInput = document.getElementById("nombre");
  const correoInput = document.getElementById("correo");
  const telefonoInput = document.getElementById("telefono");
  const contrasenaInput = document.getElementById("contrasena");
  const confirmarInput = document.getElementById("confirmarContrasena");
  const strengthBar = document.getElementById("strengthBar");
  const errorBox = document.getElementById("signupError");
  const successBox = document.getElementById("signupSuccess");

  // Indicador de fuerza de contraseña
  contrasenaInput.addEventListener("input", () => {
    const value = contrasenaInput.value;
    let strength = 0;

    if (value.length >= 8) strength++;
    if (value.match(/[a-z]/) && value.match(/[A-Z]/)) strength++;
    if (value.match(/\d/)) strength++;
    if (value.match(/[^a-zA-Z\d]/)) strength++;

    strengthBar.className = "strength-bar";
    if (strength === 1) strengthBar.classList.add("weak");
    else if (strength === 2) strengthBar.classList.add("medium");
    else if (strength === 3) strengthBar.classList.add("good");
    else if (strength === 4) strengthBar.classList.add("strong");
  });

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    errorBox.textContent = "";
    successBox.textContent = "";

    const nombre = nombreInput.value.trim();
    const correo = correoInput.value.trim();
    const telefono = telefonoInput.value.trim(); // solo front
    const contrasena = contrasenaInput.value;
    const confirmar = confirmarInput.value;

    if (!nombre || !correo || !telefono || !contrasena || !confirmar) {
      errorBox.textContent = "Por favor, completa todos los campos.";
      return;
    }

    if (contrasena !== confirmar) {
      errorBox.textContent = "Las contraseñas no coinciden.";
      return;
    }

    try {
      const resp = await fetch(`${API_BASE}/auth/registro`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          nombre,
          correo,
          contrasena
          // el backend ignora telefono si no existe en el DTO
        })
      });

      if (!resp.ok) {
        if (resp.status === 400) {
          errorBox.textContent = "No se pudo registrar. ¿El correo ya está en uso?";
        } else {
          errorBox.textContent = "Error al crear la cuenta.";
        }
        return;
      }

      // Opcional: leer respuesta del usuario creado
      await resp.json();

      successBox.textContent = "Cuenta creada correctamente. Redirigiendo al login...";
      setTimeout(() => {
        window.location.href = "login.html";
      }, 1500);
    } catch (err) {
      console.error(err);
      errorBox.textContent = "No se pudo conectar con el servidor.";
    }
  });
});
