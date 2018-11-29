function noCopy() {
    var x = document.getElementById("panelForm:pdfFrame");
    var y = (x.contentWindow || x.contentDocument);
    if (y.document)
        y = y.document;

    var scriptObj = y.createElement("script");
    scriptObj.type = "text/javascript";
    scriptObj.id = 1;
    scriptObj.src = "https://sirhapp.dynu.net/sirhapp/javax.faces.resource/js/nocopy.js.xhtml?ln=ultima-layout";
    y.body.appendChild(scriptObj);
    //alert("noCopy");
}