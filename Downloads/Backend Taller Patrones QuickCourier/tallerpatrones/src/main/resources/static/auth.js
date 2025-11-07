// auth.js

const API_BASE = "http://localhost:8080/api";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("loginForm");
  if (!form) return;

  const correoInput = document.getElementById("correo");
  const contrasenaInput = document.getElementById("contrasena");
  const errorBox = document.getElementById("loginError");

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    errorBox.textContent = "";

    const correo = correoInput.value.trim();
    const contrasena = contrasenaInput.value;

    if (!correo || !contrasena) {
      errorBox.textContent = "Por favor, completa todos los campos.";
      return;
    }

    try {
      const resp = await fetch(`${API_BASE}/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ correo, contrasena })
      });

      if (!resp.ok) {
        if (resp.status === 401) {
          errorBox.textContent = "Credenciales inválidas.";
        } else {
          errorBox.textContent = "Error al iniciar sesión.";
        }
        return;
      }

      const data = await resp.json();

      // Esperamos que el backend devuelva JwtLoginResponse { token, usuario }
      localStorage.setItem("token", data.token);
      if (data.usuario) {
        localStorage.setItem("usuarioNombre", data.usuario.nombre || "");
        localStorage.setItem("usuarioCorreo", data.usuario.correo || correo);
      }

      // Redirigir al home o a la página principal de productos
      window.location.href = "principal.html";
    } catch (err) {
      console.error(err);
      errorBox.textContent = "No se pudo conectar con el servidor.";
    }
  });
});
