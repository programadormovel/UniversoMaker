(() => {
  'use strict';

  const header = document.querySelector('.site-header');
  const menuButton = document.querySelector('.menu-toggle');
  const nav = document.querySelector('.main-nav');
  const reducedMotion = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  const updateHeader = () => header?.classList.toggle('is-scrolled', window.scrollY > 24);
  updateHeader();
  window.addEventListener('scroll', updateHeader, { passive: true });

  menuButton?.addEventListener('click', () => {
    const open = menuButton.getAttribute('aria-expanded') === 'true';
    menuButton.setAttribute('aria-expanded', String(!open));
    menuButton.setAttribute('aria-label', open ? 'Abrir menu' : 'Fechar menu');
    nav?.classList.toggle('is-open', !open);
  });

  nav?.querySelectorAll('a').forEach(link => link.addEventListener('click', () => {
    menuButton?.setAttribute('aria-expanded', 'false');
    menuButton?.setAttribute('aria-label', 'Abrir menu');
    nav.classList.remove('is-open');
  }));

  const revealElements = document.querySelectorAll('.reveal');
  if (!reducedMotion && 'IntersectionObserver' in window) {
    const observer = new IntersectionObserver(entries => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add('is-visible');
          observer.unobserve(entry.target);
        }
      });
    }, { threshold: 0.12, rootMargin: '0px 0px -40px' });
    revealElements.forEach(el => observer.observe(el));
  } else {
    revealElements.forEach(el => el.classList.add('is-visible'));
  }

  const carousel = document.querySelector('.carousel');
  if (carousel) {
    const track = carousel.querySelector('.carousel-track');
    const slides = [...carousel.querySelectorAll('.carousel-slide')];
    const dotsContainer = carousel.querySelector('.carousel-dots');
    const status = carousel.querySelector('.carousel-status');
    let index = 0;
    let autoplayId = null;
    let startX = 0;
    let pointerDelta = 0;

    const dots = slides.map((slide, i) => {
      const dot = document.createElement('button');
      dot.type = 'button';
      dot.className = 'carousel-dot';
      dot.setAttribute('role', 'tab');
      dot.setAttribute('aria-label', `Mostrar imagem ${i + 1}`);
      dot.addEventListener('click', () => goTo(i, true));
      dotsContainer.appendChild(dot);
      return dot;
    });

    function goTo(nextIndex, announce = false) {
      index = (nextIndex + slides.length) % slides.length;
      track.style.transform = `translateX(-${index * 100}%)`;
      slides.forEach((slide, i) => {
        slide.classList.toggle('is-active', i === index);
        slide.setAttribute('aria-hidden', String(i !== index));
      });
      dots.forEach((dot, i) => dot.setAttribute('aria-selected', String(i === index)));
      if (announce) status.textContent = `Imagem ${index + 1} de ${slides.length}: ${slides[index].dataset.caption}`;
    }

    function startAutoplay() {
      if (reducedMotion) return;
      stopAutoplay();
      autoplayId = window.setInterval(() => goTo(index + 1), 5000);
    }

    function stopAutoplay() {
      if (autoplayId) window.clearInterval(autoplayId);
      autoplayId = null;
    }

    carousel.querySelector('.prev')?.addEventListener('click', () => { goTo(index - 1, true); startAutoplay(); });
    carousel.querySelector('.next')?.addEventListener('click', () => { goTo(index + 1, true); startAutoplay(); });
    carousel.addEventListener('mouseenter', stopAutoplay);
    carousel.addEventListener('mouseleave', startAutoplay);
    carousel.addEventListener('focusin', stopAutoplay);
    carousel.addEventListener('focusout', event => { if (!carousel.contains(event.relatedTarget)) startAutoplay(); });
    carousel.addEventListener('keydown', event => {
      if (event.key === 'ArrowLeft') { event.preventDefault(); goTo(index - 1, true); }
      if (event.key === 'ArrowRight') { event.preventDefault(); goTo(index + 1, true); }
      if (event.key === 'Home') { event.preventDefault(); goTo(0, true); }
      if (event.key === 'End') { event.preventDefault(); goTo(slides.length - 1, true); }
    });

    carousel.addEventListener('pointerdown', event => { startX = event.clientX; pointerDelta = 0; });
    carousel.addEventListener('pointermove', event => { if (startX) pointerDelta = event.clientX - startX; });
    carousel.addEventListener('pointerup', () => {
      if (Math.abs(pointerDelta) > 55) goTo(index + (pointerDelta < 0 ? 1 : -1), true);
      startX = 0;
      pointerDelta = 0;
      startAutoplay();
    });

    goTo(0);
    startAutoplay();

    const lightbox = document.getElementById('lightbox');
    const lightboxImage = lightbox?.querySelector('img');
    const lightboxCaption = lightbox?.querySelector('figcaption');
    let lightboxIndex = 0;

    const updateLightbox = () => {
      const image = slides[lightboxIndex].querySelector('img');
      lightboxImage.src = image.currentSrc || image.src;
      lightboxImage.alt = image.alt;
      lightboxCaption.textContent = slides[lightboxIndex].dataset.caption;
    };

    slides.forEach((slide, i) => slide.querySelector('.slide-image-button')?.addEventListener('click', () => {
      lightboxIndex = i;
      updateLightbox();
      stopAutoplay();
      lightbox?.showModal();
    }));
    lightbox?.querySelector('.lightbox-close')?.addEventListener('click', () => lightbox.close());
    lightbox?.querySelector('.lightbox-prev')?.addEventListener('click', () => { lightboxIndex = (lightboxIndex - 1 + slides.length) % slides.length; updateLightbox(); });
    lightbox?.querySelector('.lightbox-next')?.addEventListener('click', () => { lightboxIndex = (lightboxIndex + 1) % slides.length; updateLightbox(); });
    lightbox?.addEventListener('click', event => { if (event.target === lightbox) lightbox.close(); });
    lightbox?.addEventListener('close', startAutoplay);
  }

  const phoneInput = document.querySelector('input[name="telefone"]');
  phoneInput?.addEventListener('input', () => {
    const digits = phoneInput.value.replace(/\D/g, '').slice(0, 11);
    if (digits.length <= 2) phoneInput.value = digits;
    else if (digits.length <= 6) phoneInput.value = `(${digits.slice(0,2)}) ${digits.slice(2)}`;
    else if (digits.length <= 10) phoneInput.value = `(${digits.slice(0,2)}) ${digits.slice(2,6)}-${digits.slice(6)}`;
    else phoneInput.value = `(${digits.slice(0,2)}) ${digits.slice(2,7)}-${digits.slice(7)}`;
  });

  const form = document.getElementById('contact-form');
  let submitting = false;
  form?.addEventListener('submit', async event => {
    event.preventDefault();
    if (submitting) return;

    const status = form.querySelector('.form-status');
    const submitButton = form.querySelector('button[type="submit"]');
    const requiredFields = [...form.querySelectorAll('[required]')];
    requiredFields.forEach(field => field.setAttribute('aria-invalid', String(!field.checkValidity())));
    const firstInvalid = requiredFields.find(field => !field.checkValidity());
    if (firstInvalid) {
      status.textContent = 'Revise os campos obrigatórios antes de enviar.';
      status.style.color = '#b12c2c';
      firstInvalid.focus();
      return;
    }

    submitting = true;
    submitButton.disabled = true;
    submitButton.textContent = submitButton.dataset.loadingText || 'Enviando...';
    status.textContent = 'Enviando sua mensagem...';
    status.style.color = '#1768e5';

    try {
      const response = await fetch(form.action, {
        method: 'POST',
        body: new FormData(form),
        headers: { 'Accept': 'application/json' }
      });
      if (!response.ok) throw new Error('Falha no envio');
      status.textContent = 'Mensagem enviada com sucesso. Nossa equipe entrará em contato em breve.';
      status.style.color = '#168556';
      form.reset();
      requiredFields.forEach(field => field.removeAttribute('aria-invalid'));
    } catch (error) {
      status.textContent = 'Não foi possível enviar neste momento. Entre em contato pelo WhatsApp (11) 98656-5779 ou pelo e-mail diretor@tntsolution.com.br.';
      status.style.color = '#b12c2c';
    } finally {
      submitting = false;
      submitButton.disabled = false;
      submitButton.textContent = submitButton.dataset.defaultText || 'Enviar solicitação';
    }
  });

  form?.querySelectorAll('input, select, textarea').forEach(field => {
    field.addEventListener('input', () => field.removeAttribute('aria-invalid'));
  });

  const year = document.getElementById('current-year');
  if (year) year.textContent = new Date().getFullYear();
})();
