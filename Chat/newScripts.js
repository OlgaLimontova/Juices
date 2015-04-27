var name;
var oldMessage;
var messageList = [];

function run() {
    var getNick = document.getElementById('getnick');
    var nickname = localStorage.getItem('name');
    name = nickname.toString().substring(1, nickname.toString().length - 1);
    document.getElementById('getnick').value = name;
    var messages = localStorage.getItem('message');
    var first = messages.toString().indexOf("\u0022");
    var last = messages.toString().lastIndexOf("\u0022");
    var i = 0;
    while (first != last) {
	    var bufIndex = messages.toString().indexOf("\u0022", first + 1);
	    var message = messages.toString().substring(first + 1, bufIndex);
	    if (i % 2 == 0)
	        messageList.push(message);
	    first = bufIndex;
	    i++;
    }
    reloadHistory();
};

var appState = {
    mainUrl: 'http://localhost:999/chat',
    token: 'TE11EN'
};

function isOnline() {
    stat = document.getElementById('status');
    if (stat.innerHTML == "Online")
        stat.innerHTML = "Offline";
    else if (stat.innerHTML == "Offline")
        stat.innerHTML = "Online";
};

function clean() {
    messageList.length = 0;
    reloadHistory();
};

function setName() {
    var nickname = document.getElementById('getnick');
    if (nickname.value == "") {
        alert("Input name!");
        return;
    }
    name = nickname.value;
};

function getName() {
    return name;
};

function restoreName() {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    var item = localStorage.getItem('name');
    return item && JSON.parse(item);
};

function updateMessage() {
    get(appState.mainUrl + "?token=" + appState.token,
        function(answer) {
            his = document.getElementById('history');
            var str = "";
            for (var i = 0; i < answer.messages.length; i++)
                str += answer.messages[i].user + ": " + answer.messages[i].message + "\n";
            his.value = str;
        });
};

function storeMessage(message) {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    localStorage.setItem('message', JSON.stringify(message));
};

function restoreMessage() {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    var item = localStorage.getItem('message');
    return item && JSON.parse(item);
};

function enterMessage() {
    mes = document.getElementById('mesarea');
    his = document.getElementById('history');
    while (name == null || name.length === 0) {
        alert("Enter user name!");
        return;
    }
    var postData = new Object();
    if (mes.value != "") {
        postData.message = mes.value;
        postData.id = Math.floor(Math.random() * 100000 + Math.random());
        messageList.push("[" + postData.id + "]   " + name + ": " + postData.message);
        reloadHistory();
        mes.value = "";
        storeMessage(messageList);
        postData.user = name;
        post(appState.mainUrl, JSON.stringify(postData),
        function() {
            updateMessage();
        });
    }
};

function reloadHistory() {
    var history = document.getElementById('history');
    history.value = "";
    for (var i = 0; i < messageList.length; i++)
        history.value += messageList[i] + "\n";
};

/*
function deleteMessage() {
    if (document.getElementById("getnumber").value != "") {
        var number = document.getElementById("getnumber").value;
        if (number > messageList.length) {
            alert("Incorrect index. Try again.");
            document.getElementById("getnumber").value = "";
            return;
        }
        if (isNaN(parseInt(number))) {
            alert("Incorrect input. Try again.");
            document.getElementById("getnumber").value = "";
            return;
        }
        var toDelete = messageList[number - 1];
        for (var i = number - 1; i < messageList.length - 1; i++)
            messageList[i] = messageList[i + 1];
        messageList.length--;
        alert("Message " + toDelete + " was deleted.");
        action = "Message " + toDelete + " was deleted";
        actionList.push(action);
        localStorage.setItem('list', JSON.stringify(actionList));
        document.getElementById('getnumber').value = "";
        reloadHistory();
    }
    else {
        alert("Input number!");
        return;
    }
};
*/


function deleteMessage() {
    deleteFunction(appState.mainUrl,
    function() {
        updateMessage();
    });
};

function editMessageFirst() {
    if (document.getElementById('getnumber').value != "") {
        index = document.getElementById('getnumber').value;
        if (index > messageList.length) {
            alert("Incorrect index. Try again.");
            document.getElementById('getnumber').value = "";
            return;
        }
        if (isNaN(parseInt(index))) {
            alert("Incorrect input. Try again.");
            document.getElementById('getnumber').value = "";
            return;
        }
        document.getElementById('getnumber').value = "";
        var toEdit = messageList[index - 1];
        oldMessage = document.getElementById('mesarea').value;
        var edit = document.getElementById('mesarea');
        edit.value = toEdit;
        document.getElementById('enter').setAttribute("onclick", "editMessageSecond();");
    }
};

function editMessageSecond() {
    alert("Message " + messageList[index - 1] + " was changed.");
    messageList[index - 1] = document.getElementById('mesarea').value;
    storeMessage(messageList);
    document.getElementById('mesarea').value = oldMessage;
    document.getElementById('enter').setAttribute("onclick", "enterMessage();");
    reloadHistory();
};

function get(url, continueWith) {
    ajax('GET', url, null, continueWith);
};

function post(url, data, continueWith) {
    ajax('POST', url, data, continueWith);
};

function deleteF(url, continueWith) {
    ajax('DELETE', url, null, continueWith);
};


function ajax(method, url, data, continueWith) {
    statusButton = document.getElementById('status');
    var xmlhttp = getXmlHttp();
    xmlhttp.open(method, url, true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
                if (xmlhttp.responseText != "")
                    continueWith(JSON.parse(xmlhttp.responseText));
                else
                    continueWith();
                statusButton.innerHTML = "Online";
            }
            if (xmlhttp.status == 0)
                statusButton.innerHTML = "Offline";
        }
    };
    xmlhttp.ontimeout = function() {
        statusButton.innerHTML = "Offline";
    };
    xmlhttp.send(data);
};

function getXmlHttp() {
    var xmlhttp;
    try {
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch(e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch(E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest != 'undefined')
        xmlhttp = new XMLHttpRequest();
    return xmlhttp;
};