document.addEventListener('copy', function (e) {
    e.clipboardData.setData('text/plain', 'No se puede copiar el texto!');
    e.clipboardData.setData('text/html', '<b>No se puede copiar el texto!</b>');
    e.preventDefault(); // We want our data, not data from any selection, to be written to the clipboard
});

function disableselect(e) {
    return false;
}

function reEnable() {
    return true;
}

document.onselectstart = new Function("return false;")

if (window.sidebar) {
    document.onmousedown = disableselect;
    document.onClick = reEnable;
}

function myFunctionon() {
    alert("Function no-copy");
}