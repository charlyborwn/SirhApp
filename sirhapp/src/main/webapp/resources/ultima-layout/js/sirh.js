$(document).ajaxComplete(function (event, xhr, options) {
    if (typeof xhr.responseXML != 'undefined') { // It's undefined when plain $.ajax(), $.get(), etc is used instead of PrimeFaces ajax.
        fixViewState(xhr.responseXML);
    }
});

$(document).ready(function () {
    applyChangeHandler();
});

function applyChangeHandler() {
    var inputs = $(':input').keyup(function (e) {
        var nextInput = inputs.get(inputs.index(this) + 1);
        var id = this.getAttribute('id');
        if (e.which !== 13) {
            if (id.indexOf('email') === -1 && id.indexOf('password') === -1 && id.indexOf('multiline') === -1 && id.indexOf('capsdisabled') === -1) {
                var caretPosition = doGetCaretPosition(this);

                this.value = this.value.toLocaleUpperCase();
                // Reset caret position
                // (we ignore selection length, as typing deselects anyway)
                doSetCaretPosition(this, caretPosition);

            }
        } else {
            if (nextInput) {
                if (id.indexOf('multiline') === -1) {
                    nextInput.focus();
                }
            }
        }
    });
}
;


function fixViewState(responseXML) {
    var viewState = getViewState(responseXML);

    if (viewState) {
        for (var i = 0; i < document.forms.length; i++) {
            var form = document.forms[i];

            if (form.method == "post") {
                if (!hasViewState(form)) {
                    createViewState(form, viewState);
                }
            } else { // PrimeFaces also adds them to GET forms!
                removeViewState(form);
            }
        }
    }
}

function getViewState(responseXML) {
    var updates = responseXML.getElementsByTagName("update");

    for (var i = 0; i < updates.length; i++) {
        var update = updates[i];

        if (update.getAttribute("id").match(/^([\w]+:)?javax\.faces\.ViewState(:[0-9]+)?$/)) {
            return update.textContent || update.innerText;
        }
    }

    return null;
}

function hasViewState(form) {
    for (var i = 0; i < form.elements.length; i++) {
        if (form.elements[i].name == "javax.faces.ViewState") {
            return true;
        }
    }

    return false;
}

function createViewState(form, viewState) {
    var hidden;

    try {
        hidden = document.createElement("<input name='javax.faces.ViewState'>"); // IE6-8.
    } catch (e) {
        hidden = document.createElement("input");
        hidden.setAttribute("name", "javax.faces.ViewState");
    }

    hidden.setAttribute("type", "hidden");
    hidden.setAttribute("value", viewState);
    hidden.setAttribute("autocomplete", "off");
    form.appendChild(hidden);
}

function removeViewState(form) {
    for (var i = 0; i < form.elements.length; i++) {
        var element = form.elements[i];
        if (element.name == "javax.faces.ViewState") {
            element.parentNode.removeChild(element);
        }
    }
}

function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}

function submitenter(myfield, e)
{
    var keycode;
    if (window.event)
        keycode = window.event.keyCode;
    else if (e)
        keycode = e.which;
    else
        return true;

    if (keycode === 13)
    {
        ModifyEnterKeyPressAsTab();
        return false;
    } else
        return true;
}

function ModifyEnterKeyPressAsTab(event)
{
    if (event.keyCode === 13) {
        event.preventDefault();
    }
}

function ModifyEnterKeyPress(event)
{
    event.preventDefault();
    var caller;
    var key;
    if (window.event)
    {
        caller = window.event.srcElement; //Get the event caller in IE.
        key = window.event.keyCode; //Get the keycode in IE.
    } else
    {
        caller = event.target; //Get the event caller in Firefox.
        key = event.which; //Get the keycode in Firefox.
    }
    if (key === 13) //Enter key was pressed.
    {
        cTab = caller.tabIndex; //caller tabIndex.
        maxTab = 0; //highest tabIndex (start at 0 to change)
        minTab = cTab; //lowest tabIndex (this may change, but start at caller)
        allById = document.getElementsByTagName("*"); //Get input elements.
        allByIndex = []; //Storage for elements by index.
        c = 0; //index of the caller in allByIndex (start at 0 to change)
        i = 0; //generic indexer for allByIndex;
        for (id in allById) //Loop through all the input elements by id.
        {
            allByIndex[i] = allById[id]; //Set allByIndex.
            tab = allByIndex[i].tabIndex;
            if (caller === allByIndex[i])
                c = i; //Get the index of the caller.
            if (tab > maxTab)
                maxTab = tab; //Get the highest tabIndex on the page.
            if (tab < minTab && tab >= 0)
                minTab = tab; //Get the lowest positive tabIndex on the page.
            i++;
        }
        //Loop through tab indexes from caller to highest.
        for (tab = cTab; tab <= maxTab; tab++)
        {
            //Look for this tabIndex from the caller to the end of page.
            for (i = c + 1; i < allByIndex.length; i++)
            {
                if (allByIndex[i].tabIndex === tab)
                {
                    allByIndex[i].focus(); //Move to that element and stop.
                    return;
                }
            }
            //Look for the next tabIndex from the start of page to the caller.
            for (i = 0; i < c; i++)
            {
                if (allByIndex[i].tabIndex === tab + 1)
                {
                    allByIndex[i].focus(); //Move to that element and stop.
                    return;
                }
            }
            //Continue searching from the caller for the next tabIndex.
        }

        //The caller was the last element with the highest tabIndex,
        //so find the first element with the lowest tabIndex.
        for (i = 0; i < allByIndex.length; i++)
        {
            if (allByIndex[i].tabIndex === minTab)
            {
                allByIndex[i].focus(); //Move to that element and stop.
                return;
            }
        }
    }
}

function doGetCaretPosition(oField) {
    var iCaretPos = 0;
    if (document.selection) // IE Support
    {
        oField.focus();
        var oSel = document.selection.createRange();
        // Move selection start to 0 position
        oSel.moveStart('character', -oField.value.length);
        // The caret position is selection length
        iCaretPos = oSel.text.length;
    } else
    if (oField.selectionStart || oField.selectionStart == '0') // Firefox support
        iCaretPos = oField.selectionStart;
    return (iCaretPos);
}

function doSetCaretPosition(oField, iCaretPos)
{
    if (document.selection) {
        oField.focus();
        var oSel = document.selection.createRange();
        oSel.moveStart('character', -oField.value.length);
        oSel.moveStart('character', iCaretPos);
        oSel.moveEnd('character', 0);
        oSel.select();
    } else
    if (oField.selectionStart || oField.selectionStart == '0') {
        oField.selectionStart = iCaretPos;
        oField.selectionEnd = iCaretPos;
        oField.focus();
    }
}

function forceupper(o)
{
    var x = doGetCaretPosition(o);
    o.value = o.value.toUpperCase();
    doSetCaretPosition(o, x);
}

//<![CDATA[
if (PrimeFaces.widget.SelectOneMenu) {
    PrimeFaces.widget.SelectOneMenu = PrimeFaces.widget.SelectOneMenu.extend({
        init: function (cfg) {
            this._super(cfg);

            var $this = this;
            if (!this.disabled && this.jq.parent().hasClass('md-selectonemenu')) {
                if (this.input.val() != "") {
                    this.jq.addClass("ui-state-filled");
                }

                this.input.off('change').on('change', function () {
                    if ($(this).val() != " ") {
                        $this.jq.addClass("ui-state-filled");
                    } else {
                        $this.jq.removeClass("ui-state-filled");
                    }
                });
            }
        }
    });
}
//]]>

function showStatus() {
    PF('statusDialog').show();
}
function hideStatus() {
    PF('statusDialog').hide();
}