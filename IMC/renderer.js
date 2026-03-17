'use strict';

// ── DOM ──────────────────────────────────────────────────────────
const inputNombre       = document.getElementById('nombre');
const inputPeso         = document.getElementById('peso');
const inputAltura       = document.getElementById('altura');
const btnCalcular       = document.getElementById('btnCalcular');
const btnBorrar         = document.getElementById('btnBorrar');
const btnHistorial      = document.getElementById('btnHistorial');
const btnHistorialTexto = document.getElementById('btnHistorialTexto');
const historialPanel    = document.getElementById('historialPanel');

const resultadoBox      = document.getElementById('resultadoBox');
const imcNumero         = document.getElementById('imcNumero');
const imcPill           = document.getElementById('imcPill');
const resultadoGuardado = document.getElementById('resultadoGuardado');

const historyList       = document.getElementById('historyList');
const historialCount    = document.getElementById('historialCount');

// ── Estado ───────────────────────────────────────────────────────
let historialVisible = false;  // oculto por defecto

// ── Clasificación IMC ────────────────────────────────────────────
function clasificarIMC(imc) {
  if (imc < 18.5) return { label: 'Bajo peso', clase: 'bajo'   };
  if (imc < 25.0) return { label: 'Normal',    clase: 'normal' };
  if (imc < 30.0) return { label: 'Sobrepeso', clase: 'sobre'  };
  return              { label: 'Obesidad',     clase: 'obeso'  };
}

btnCalcular.addEventListener('click', async () => {
  const peso   = parseFloat(inputPeso.value);
  const altura = parseFloat(inputAltura.value);
  const nombre = inputNombre.value.trim() || 'Sin nombre';

  if (!peso || !altura || peso <= 0 || altura <= 0) {
    shake(inputPeso);
    shake(inputAltura);
    return;
  }
  if (altura > 3) {
    mostrarResultado(null, null, '¿Altura en cm? Usa metros (ej. 1.75)');
    return;
  }

  const imc = peso / (altura * altura);
  const cat = clasificarIMC(imc);

  mostrarResultado(imc.toFixed(2), cat);

  const ahora = new Date();
  const registro = {
    id:        Date.now(),
    nombre,
    peso,
    altura,
    imc:       parseFloat(imc.toFixed(2)),
    categoria: cat.label,
    clase:     cat.clase,
    fecha:     ahora.toLocaleDateString('es-MX', { day:'2-digit', month:'short', year:'numeric' }),
    hora:      ahora.toLocaleTimeString('es-MX', { hour:'2-digit', minute:'2-digit' })
  };

  const res = await window.imcAPI.guardarRegistro(registro);
  resultadoGuardado.textContent = res.ok ? '✓ Guardado en el historial' : '⚠ No se pudo guardar';

  if (res.ok) {
    agregarTarjetaAlInicio(registro);
    actualizarConteo();
  }
});

function mostrarResultado(valorIMC, cat, errorMsg) {
  resultadoBox.hidden = false;
  if (errorMsg) {
    imcNumero.textContent = '—';
    imcPill.textContent   = errorMsg;
    imcPill.className     = 'imc-pill';
    resultadoGuardado.textContent = '';
    return;
  }
  imcNumero.textContent = valorIMC;
  imcPill.textContent   = cat.label;
  imcPill.className     = `imc-pill cat-${cat.clase}`;
}

btnHistorial.addEventListener('click', () => {
  historialVisible = !historialVisible;
  historialPanel.style.display    = historialVisible ? 'flex' : 'none';
  btnHistorialTexto.textContent   = historialVisible ? 'Ocultar historial' : 'Ver historial';
  btnHistorial.classList.toggle('activo', historialVisible);
});

async function cargarHistorial() {
  const lista = await window.imcAPI.leerHistorial();
  historyList.innerHTML = '';
  if (!lista.length) {
    historyList.innerHTML = '<p class="history-empty">No hay registros aún.</p>';
  } else {
    lista.forEach(r => agregarTarjetaAlFinal(r));
  }
  actualizarConteo(lista.length);
}

btnBorrar.addEventListener('click', async () => {
  if (!confirm('¿Borrar todo el historial? Esta acción no se puede deshacer.')) return;
  await window.imcAPI.borrarHistorial();
  historyList.innerHTML = '<p class="history-empty">No hay registros aún.</p>';
  actualizarConteo(0);
});

function agregarTarjetaAlInicio(r) {
  const empty = historyList.querySelector('.history-empty');
  if (empty) empty.remove();
  historyList.prepend(crearTarjeta(r));
}

function agregarTarjetaAlFinal(r) {
  historyList.appendChild(crearTarjeta(r));
}

function crearTarjeta(r) {
  const clase = r.clase || clasificarIMC(r.imc).clase;
  const div = document.createElement('div');
  div.className = `history-card cat-${clase}`;
  div.innerHTML = `
    <span class="hc-dot"></span>
    <div class="hc-body">
      <div class="hc-nombre">${escHTML(r.nombre)}</div>
      <div class="hc-meta">${r.fecha} · ${r.hora} · ${r.peso} kg / ${r.altura} m</div>
      <div class="hc-cat">${r.categoria}</div>
    </div>
    <div class="hc-imc">${r.imc}</div>
  `;
  return div;
}

function actualizarConteo(n) {
  if (n === undefined) {
    n = historyList.querySelectorAll('.history-card').length;
  }
  historialCount.textContent = n === 1 ? '1 registro' : `${n} registros`;
}

function shake(el) {
  el.style.animation = 'none';
  requestAnimationFrame(() => {
    el.style.animation = 'shake .35s ease';
    el.addEventListener('animationend', () => { el.style.animation = ''; }, { once: true });
  });
}

function escHTML(s) {
  return s.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
}

const style = document.createElement('style');
style.textContent = `@keyframes shake {
  0%,100%{transform:translateX(0)} 20%{transform:translateX(-5px)}
  40%{transform:translateX(5px)}   60%{transform:translateX(-3px)} 80%{transform:translateX(3px)}
}`;
document.head.appendChild(style);

cargarHistorial();