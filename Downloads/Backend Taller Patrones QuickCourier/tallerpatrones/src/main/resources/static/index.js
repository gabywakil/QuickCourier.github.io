
document.addEventListener("DOMContentLoaded", () => {
  const navbar = document.querySelector(".navbar");
  window.addEventListener("scroll", () => {
    if (window.scrollY > 50) {
      navbar.classList.add("scrolled");
    } else {
      navbar.classList.remove("scrolled");
    }
  });

  const getStartedBtn = document.querySelector(".btn-primary");
  const learnMoreBtn = document.querySelector(".btn-secondary");

  getStartedBtn.addEventListener("click", () => {
    document.querySelector(".categories-section").scrollIntoView({
      behavior: "smooth",
    });
  });

  learnMoreBtn.addEventListener("click", () => {
    document.querySelector(".why-choose-section").scrollIntoView({
      behavior: "smooth",
    });
  });

  const categoryCards = document.querySelectorAll(".category-card");
  categoryCards.forEach((card) => {
    card.addEventListener("mouseenter", () => {
      card.classList.add("hovered");
    });
    card.addEventListener("mouseleave", () => {
      card.classList.remove("hovered");
    });
  });

  const animatedElements = document.querySelectorAll(
    ".hero-content, .category-card, .feature-card"
  );

  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("visible");
        }
      });
    },
    { threshold: 0.2 }
  );

  animatedElements.forEach((el) => observer.observe(el));

  const logo = document.querySelector(".logo-img");
  logo.addEventListener("click", (e) => {
    e.preventDefault();
    logo.classList.add("blink");
    setTimeout(() => logo.classList.remove("blink"), 600);
    setTimeout(() => (window.location.href = "index.html"), 400);
  });

  const footerText = document.querySelector(".footer-bottom p");
  const year = new Date().getFullYear();
  footerText.innerHTML = `© ${year} QuickCourier. All rights reserved.`;

  const loginBtn = document.querySelector(".btn-login");
  if (loginBtn) {
    loginBtn.addEventListener("click", (e) => {
      e.preventDefault();
      loginBtn.classList.add("pressed");
      setTimeout(() => {
        window.location.href = "login.html";
      }, 300);
    });
  }
});
