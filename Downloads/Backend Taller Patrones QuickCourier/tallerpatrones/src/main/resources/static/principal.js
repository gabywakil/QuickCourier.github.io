// js/principal.js

const API_BASE = "http://localhost:8080/api";

let productos = [];
let categoriaActiva = "TODOS";
let terminoBusqueda = "";

// ========= Utilidades =========

function obtenerToken() {
  return localStorage.getItem("token");
}

function formatearPrecioCOP(valor) {
  if (typeof valor !== "number") {
    valor = Number(valor);
  }
  return new Intl.NumberFormat("es-CO", {
    style: "currency",
    currency: "COP",
    maximumFractionDigits: 0
  }).format(valor || 0);
}

function obtenerEmojiYCOLORPorCategoria(cat) {
  switch (cat) {
    case "LIBRO":
      return { emoji: "📚", bg: "linear-gradient(135deg, #3B82F6 0%, #1D4ED8 100%)" };
    case "SNACK":
      return { emoji: "🍿", bg: "linear-gradient(135deg, #F97316 0%, #EA580C 100%)" };
    case "ACCESORIO":
      return { emoji: "🎁", bg: "linear-gradient(135deg, #10B981 0%, #059669 100%)" };
    default:
      return { emoji: "📦", bg: "linear-gradient(135deg, #64748B 0%, #475569 100%)" };
  }
}

// --- Carrito en localStorage ---

function cargarCarrito() {
  try {
    const raw = localStorage.getItem("cartItems");
    if (!raw) return [];
    const parsed = JSON.parse(raw);
    return Array.isArray(parsed) ? parsed : [];
  } catch (e) {
    console.error("Error leyendo cartItems:", e);
    return [];
  }
}

function guardarCarrito(cart) {
  localStorage.setItem("cartItems", JSON.stringify(cart));
}

function actualizarBadgeCarrito() {
  const cart = cargarCarrito();
  const totalItems = cart.reduce((sum, item) => sum + (item.quantity || 0), 0);
  const badge = document.getElementById("cartCount");
  if (badge) {
    badge.textContent = totalItems;
  }
}

// ========= Renderizado =========

function actualizarContadores() {
  const total = productos.length;
  const countLibros = productos.filter(p => p.categoria === "LIBRO").length;
  const countSnacks = productos.filter(p => p.categoria === "SNACK").length;
  const countAccesorios = productos.filter(p => p.categoria === "ACCESORIO").length;

  document.getElementById("countTodos").textContent = total;
  document.getElementById("countLibros").textContent = countLibros;
  document.getElementById("countSnacks").textContent = countSnacks;
  document.getElementById("countAccesorios").textContent = countAccesorios;
}

function actualizarTituloYConteo(listaFiltrada) {
  const titleEl = document.getElementById("productsTitle");
  const countEl = document.getElementById("productsCount");

  let textoCategoria;
  switch (categoriaActiva) {
    case "LIBRO":      textoCategoria = "Libros"; break;
    case "SNACK":      textoCategoria = "Snacks"; break;
    case "ACCESORIO":  textoCategoria = "Accesorios"; break;
    default:           textoCategoria = "Todos los productos"; break;
  }

  titleEl.textContent = textoCategoria;
  countEl.textContent = `${listaFiltrada.length} producto${listaFiltrada.length !== 1 ? "s" : ""} encontrados`;
}

function renderizarProductos(lista) {
  const grid = document.getElementById("productsGrid");
  grid.innerHTML = "";

  if (!lista.length) {
    grid.innerHTML = `
      <p style="grid-column: 1/-1; text-align:center; color:#64748B;">
        No se encontraron productos para los filtros aplicados.
      </p>
    `;
    actualizarTituloYConteo(lista);
    return;
  }

  lista.forEach(prod => {
    const { emoji, bg } = obtenerEmojiYCOLORPorCategoria(prod.categoria);
    const precio = formatearPrecioCOP(prod.precio);

    const card = document.createElement("div");
    card.className = "product-card";
    card.dataset.category = prod.categoria;

    card.innerHTML = `
      <div class="product-image" style="background: ${bg};">
        <span style="font-size:3rem;">${emoji}</span>
      </div>
      <div class="product-info">
        <h3 class="product-name">${prod.nombre}</h3>
        <p class="product-description" style="font-size:0.9rem; color:#64748B; margin-bottom:0.75rem;">
          ${prod.descripcion || ""}
        </p>
        <div class="product-footer">
          <span class="product-price">${precio}</span>
          <button
            class="btn-add-cart"
            type="button"
            data-id="${prod.id}"
            data-nombre="${prod.nombre}"
            data-precio="${prod.precio}"
            data-descripcion="${prod.descripcion || ""}"
            data-categoria="${prod.categoria}"
          >
            Agregar
          </button>
        </div>
      </div>
    `;

    grid.appendChild(card);
  });

  actualizarTituloYConteo(lista);
}

// ========= Filtros =========

function aplicarFiltros() {
  let lista = [...productos];

  if (categoriaActiva !== "TODOS") {
    lista = lista.filter(p => p.categoria === categoriaActiva);
  }

  if (terminoBusqueda.trim().length > 0) {
    const t = terminoBusqueda.toLowerCase();
    lista = lista.filter(p =>
      (p.nombre && p.nombre.toLowerCase().includes(t)) ||
      (p.descripcion && p.descripcion.toLowerCase().includes(t)) ||
      (p.sku && p.sku.toLowerCase().includes(t))
    );
  }

  renderizarProductos(lista);
}

// ========= Eventos UI =========

function configurarEventos() {
  // Categorías
  document.querySelectorAll(".category-item").forEach(item => {
    item.addEventListener("click", () => {
      document.querySelectorAll(".category-item").forEach(i => i.classList.remove("active"));
      item.classList.add("active");
      categoriaActiva = item.dataset.category || "TODOS";
      aplicarFiltros();
    });
  });

  // Búsqueda
  const searchInput = document.getElementById("searchInput");
  if (searchInput) {
    searchInput.addEventListener("input", () => {
      terminoBusqueda = searchInput.value;
      aplicarFiltros();
    });
  }

  // Perfil (opcional)
  const profileBtn = document.getElementById("profileBtn");
  if (profileBtn) {
    profileBtn.addEventListener("click", () => {
      const nombre = localStorage.getItem("usuarioNombre") || "(sin nombre)";
      const correo = localStorage.getItem("usuarioCorreo") || "(sin correo)";
      alert(`Usuario actual:\n\nNombre: ${nombre}\nCorreo: ${correo}`);
    });
  }

  // Ir al carrito al hacer clic en ícono (si existe)
  const cartBtn = document.getElementById("cartBtn");
  if (cartBtn) {
    cartBtn.addEventListener("click", () => {
      window.location.href = "shoppingCard.html";
    });
  }

  // Manejo de clicks en "Agregar" al carrito
  const grid = document.getElementById("productsGrid");
  const cartCountEl = document.getElementById("cartCount");

  grid.addEventListener("click", (e) => {
    const btn = e.target.closest(".btn-add-cart");
    if (!btn) return;

    const id = btn.dataset.id;
    const nombre = btn.dataset.nombre;
    const precio = Number(btn.dataset.precio);
    const descripcion = btn.dataset.descripcion || "";
    const categoria = btn.dataset.categoria;

    let cart = cargarCarrito();
    const existente = cart.find(item => item.id === id);
    if (existente) {
      existente.quantity += 1;
    } else {
      cart.push({
        id,
        nombre,
        precio,
        descripcion,
        categoria,
        quantity: 1
      });
    }
    guardarCarrito(cart);

    // actualizar badge
    if (cartCountEl) {
      const totalItems = cart.reduce((sum, item) => sum + (item.quantity || 0), 0);
      cartCountEl.textContent = totalItems;
    }
  });
}

// ========= Carga inicial =========

async function cargarProductos() {
  const token = obtenerToken();
  if (!token) {
    window.location.href = "login.html";
    return;
  }

  try {
    const resp = await fetch(`${API_BASE}/productos`, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });

    if (resp.status === 401 || resp.status === 403) {
      localStorage.removeItem("token");
      window.location.href = "login.html";
      return;
    }

    if (!resp.ok) {
      console.error("Error al obtener productos:", resp.status);
      alert("No se pudieron cargar los productos.");
      return;
    }

    productos = await resp.json();
    actualizarContadores();
    aplicarFiltros();
    actualizarBadgeCarrito();
  } catch (err) {
    console.error("Error de red al obtener productos:", err);
    alert("Error de conexión con el servidor.");
  }
}

document.addEventListener("DOMContentLoaded", () => {
  configurarEventos();
  cargarProductos();
});
