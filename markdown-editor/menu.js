const { app, Menu, shell, ipcMain, BrowserWindow, globalShortcut, dialog } = require('electron');
const fs = require('fs');

function saveFile() {
    console.log('Saving the file');
    const window = BrowserWindow.getFocusedWindow();
    window.webContents.send('editor-event', 'save');
}

function loadFile() {
    const window = BrowserWindow.getFocusedWindow();
    const options = {
        title: 'Pick a markdown file',
        filters: [
            { name: 'Markdown files', extensions: ['md'] },
            { name: 'Text files', extensions: ['txt'] }
        ]
    };
    dialog.showOpenDialog(window, options, paths => {
        if (paths && paths.length > 0) {
            const content = fs.readFileSync(paths[0]).toString();
            window.webContents.send('load', content);
        }
    });
}

const template = [
    {
        label: 'File',
        submenu: [
            {
                label: 'Open',
                accelerator: 'CommandOrControl+O',
                click() {
                    loadFile();
                }
            },
            {
                label: 'Save',
                accelerator: 'CommandOrControl+S',
                click() {
                    saveFile();
                }
            }
        ]
    },
    {
        label: 'Format',
        submenu: [
            {
                label: 'Toggle Bold',
                click() {
                    const window = BrowserWindow.getFocusedWindow();
                    window.webContents.send(
                        'editor-event',
                        'toggle-bold'
                    );
                }
            }
        ]
    },
    {
        role: 'help',
        submenu: [
            {
                label: 'About Editor Component',
                click() {
                    shell.openExternal('https://simplemde.com/');
                }
            }
        ]
    }
];

// Menú de depuración: solo si la variable de entorno DEBUG está definida
// En Windows se define con: set DEBUG=true && electron .
// En PowerShell: $env:DEBUG="true"; electron .
if (process.env.DEBUG) {
    template.push({
        label: 'Debugging',
        submenu: [
            {
                label: 'Dev Tools',
                role: 'toggleDevTools'
            },
            { type: 'separator' },
            {
                role: 'reload',
                accelerator: 'Alt+R'
            }
        ]
    });
}

// NOTA: El bloque de menú específico de macOS (darwin) del libro se omite
// porque en Windows no existe el menú de aplicación con el nombre del app.
// Windows ya gestiona el cierre de la aplicación a través del botón X
// y del menú de la barra de tareas.

const menu = Menu.buildFromTemplate(template);

app.on('ready', () => {
    globalShortcut.register('CommandOrControl+S', () => {
        saveFile();
    });

    globalShortcut.register('CommandOrControl+O', () => {
        loadFile();
    });
});

ipcMain.on('editor-reply', (event, arg) => {
    console.log(`Received reply from web page: ${arg}`);
});

ipcMain.on('save', (event, arg) => {
    console.log(`Saving content of the file`);
    console.log(arg);
    const window = BrowserWindow.getFocusedWindow();
    const options = {
        title: 'Save markdown file',
        filters: [
            {
                name: 'MyFile',
                extensions: ['md']
            }
        ]
    };
    dialog.showSaveDialog(window, options, filename => {
        if (filename) {
            console.log(`Saving content to the file: ${filename}`);
            fs.writeFileSync(filename, arg);
        }
    });
});

module.exports = menu;