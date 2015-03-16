var name;
var oldMessage;
var messageList = [];
var actionList = [];

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
    storeName(name);
    var action = "New name: " + name;
    localStorage.setItem('name', JSON.stringify(name));
    actionList.push(action);
    localStorage.setItem('list', JSON.stringify(actionList));
};

function getName() {
    return name;
};

function storeName(name) {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    localStorage.setItem('name', JSON.stringify(name));
};

function restoreName() {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    var item = localStorage.getItem('name');
    return item && JSON.parse(item);
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

function storeAction(action) {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    localStorage.setItem('list', JSON.stringify(action));
};

function restoreAction() {
    if (typeof(Storage) == "undefined") {
        alert("Error! LocalStorage is not accessible!");
        return;
    }
    var item = localStorage.getItem('list');
    return item && JSON.parse(item);
};

function enterMessage() {
    mes = document.getElementById('mesarea');
    his = document.getElementById('history');
    while (name == null || name.length === 0) {
        alert("Enter user name!");
        return;
    }
    if (mes.value != "") {
	    enterMes = mes.value;
        messageList.push(name + ": " + enterMes);
        reloadHistory();
        mes.value = "";
        storeMessage(messageList);
        var action = name + " sent message " + enterMes;
        storeAction(action);
    }
};

function reloadHistory() {
    var history = document.getElementById('history');
    history.value = "";
    for (var i = 0; i < messageList.length; i++)
        history.value += messageList[i] + "\n";
};

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
    action = "Message " + messageList[index - 1] + " was changed";
    storeAction(action);
    messageList[index - 1] = document.getElementById('mesarea').value;
    document.getElementById('mesarea').value = oldMessage;
    document.getElementById('enter').setAttribute("onclick", "enterMessage();");
    reloadHistory();
};