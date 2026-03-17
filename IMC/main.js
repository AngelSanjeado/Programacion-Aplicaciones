const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');
const fs = require('fs');

const dataPath = path.join(app.getPath('userData'), 'historial_imc.json');

function createWindow() {
  const win = new BrowserWindow({
    width: 900,
    height: 680,
    minWidth: 800,
    minHeight: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
      contextIsolation: true,
      nodeIntegration: false
    },
    titleBarStyle: 'hidden',
    backgroundColor: '#f4f6f9',
    show: false
  });

  win.loadFile('index.html');

  win.once('ready-to-show', () => {
    win.show();
  });
}

app.whenReady().then(() => {
  createWindow();

  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});

ipcMain.handle('leer-historial', () => {
  try {
    if (!fs.existsSync(dataPath)) {
      fs.writeFileSync(dataPath, JSON.stringify([], null, 2));
      return [];
    }
    const raw = fs.readFileSync(dataPath, 'utf-8');
    return JSON.parse(raw);
  } catch (err) {
    console.error('Error leyendo historial:', err);
    return [];
  }
});

ipcMain.handle('guardar-registro', (event, registro) => {
  try {
    let historial = [];
    if (fs.existsSync(dataPath)) {
      const raw = fs.readFileSync(dataPath, 'utf-8');
      historial = JSON.parse(raw);
    }
    historial.unshift(registro); // más reciente primero
    fs.writeFileSync(dataPath, JSON.stringify(historial, null, 2));
    return { ok: true };
  } catch (err) {
    console.error('Error guardando registro:', err);
    return { ok: false, error: err.message };
  }
});

// Borrar todo el historial
ipcMain.handle('borrar-historial', () => {
  try {
    fs.writeFileSync(dataPath, JSON.stringify([], null, 2));
    return { ok: true };
  } catch (err) {
    return { ok: false, error: err.message };
  }
});

ipcMain.handle('obtener-ruta', () => dataPath);