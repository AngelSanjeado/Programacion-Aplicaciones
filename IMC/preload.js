const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('imcAPI', {
  leerHistorial:    ()          => ipcRenderer.invoke('leer-historial'),
  guardarRegistro:  (registro)  => ipcRenderer.invoke('guardar-registro', registro),
  borrarHistorial:  ()          => ipcRenderer.invoke('borrar-historial')
});