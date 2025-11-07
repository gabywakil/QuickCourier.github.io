// js/shoppingCart.js

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

function obtenerEmojiPorCategoria(cat) {
  switch (cat) {
    case "LIBRO": return "📚";
    case "SNACK": return "🍿";
    case "ACCESORIO": return "🎁";
    default: return "📦";
  }
}

function actualizarBadgeCarrito() {
  const cart = cargarCarrito();
  const totalItems = cart.reduce((sum, item) => sum + (item.quantity || 0), 0);
  const badge = document.getElementById("cartCount");
  if (badge) {
    badge.textContent = totalItems;
  }
}

function calcularTotales(cart) {
  const subtotal = cart.reduce(
    (sum, item) => sum + (item.precio * item.quantity),
    0
  );
  const iva = subtotal * 0.19; // 19%
  const total = subtotal + iva;
  return { subtotal, iva, total };
}

function renderizarResumen(cart) {
  const { subtotal, iva, total } = calcularTotales(cart);

  const subtotalEl = document.getElementById("summarySubtotal");
  const taxEl = document.getElementById("summaryTax");
  const totalEl = document.getElementById("summaryTotal");

  if (subtotalEl) subtotalEl.textContent = formatearPrecioCOP(subtotal);
  if (taxEl) taxEl.textContent = formatearPrecioCOP(iva);
  if (totalEl) totalEl.textContent = formatearPrecioCOP(total);
}

function renderizarCarrito() {
  const cartItemsDiv = document.getElementById("cartItems");
  const cart = cargarCarrito();

  cartItemsDiv.innerHTML = "";

  if (!cart.length) {
    cartItemsDiv.innerHTML = `
      <p class="empty-cart">
        Tu carrito está vacío. Agrega productos desde el catálogo.
      </p>
    `;
    renderizarResumen(cart);
    actualizarBadgeCarrito();
    return;
  }

  cart.forEach(item => {
    const row = document.createElement("div");
    row.className = "cart-item";
    row.dataset.id = item.id;

    const emoji = obtenerEmojiPorCategoria(item.categoria);
    const precioUnitario = formatearPrecioCOP(item.precio);
    const totalItem = formatearPrecioCOP(item.precio * item.quantity);

    row.innerHTML = `
      <div class="item-img">
        <span>${emoji}</span>
      </div>
      <div class="item-info">
        <h4>${item.nombre}</h4>
        <p class="price">${precioUnitario} c/u</p>
        <p style="font-size:0.8rem; color:#6b7280; margin-top:4px;">
          Total: <strong>${totalItem}</strong>
        </p>
      </div>
      <div class="qty-box">
        <button class="qty-btn qty-minus" type="button">-</button>
        <span class="qty-value">${item.quantity}</span>
        <button class="qty-btn qty-plus" type="button">+</button>
      </div>
      <span class="delete-btn" title="Eliminar">🗑️</span>
    `;

    cartItemsDiv.appendChild(row);
  });

  renderizarResumen(cart);
  actualizarBadgeCarrito();
}

function configurarEventosCarrito() {
  const cartItemsDiv = document.getElementById("cartItems");

  cartItemsDiv.addEventListener("click", (e) => {
    const cart = cargarCarrito();
    const row = e.target.closest(".cart-item");
    if (!row) return;

    const id = row.dataset.id;
    const item = cart.find(i => i.id === id);
    if (!item) return;

    // Botón eliminar
    if (e.target.classList.contains("delete-btn")) {
      const nuevoCart = cart.filter(i => i.id !== id);
      guardarCarrito(nuevoCart);
      renderizarCarrito();
      return;
    }

    // Botón + o -
    if (e.target.classList.contains("qty-plus")) {
      item.quantity += 1;
      guardarCarrito(cart);
      renderizarCarrito();
      return;
    }

    if (e.target.classList.contains("qty-minus")) {
      item.quantity -= 1;
      if (item.quantity <= 0) {
        const nuevoCart = cart.filter(i => i.id !== id);
        guardarCarrito(nuevoCart);
      } else {
        guardarCarrito(cart);
      }
      renderizarCarrito();
      return;
    }
  });
}

document.addEventListener("DOMContentLoaded", () => {
  actualizarBadgeCarrito();
  renderizarCarrito();
  configurarEventosCarrito();
});
