var name;
var oldName;
var messageList = [];
var actionList = [];

function run() {
    var connectButton = document.getElementById('connect');
    connectButton.addEventListener('click', setName);
    var sendButton = document.getElementById('enter');
    sendButton.addEventListener('click', enterMessage);
    var deleteButton = document.getElementById('delete');
    deleteButton.addEventListener('click', deleteMessage);
    var editButton = document.getElementById('edit');
    editButton.addEventListener('click', editMessage);
    var showButton = document.getElementById('show');
    showButton.addEventListener('click', showHistory);
    var getNick = document.getElementById('getnick');
    getNick.value = getName();
    var history = document.getElementById('history');
    var item = localStorage.getItem('list');
    history.value = item;
};

function setName() {
    var nickname = document.getElementById('getnick');
    name = nickname.value;
    var action = "New name: " + name;
    actionList.push(action);
    localStorage.setItem('list', JSON.stringify(actionList, " ", 4));
};

function getName() {
    return name;
};

function restoreMessage() {
    if (typeof (Storage) == "undefined") {
        alert("Error!");
        return;
    }
    var message = localStorage.getItem('message');
    return message && JSON.parse(message);
};

function restoreAction() {
    if (typeof (Storage) == "undefined") {
        alert("Error!");
        return;
    }
    var action = localStorage.getItem('list');
    return action && JSON.parse(action);
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
        oldHistory = his.value;
        his.value += "\n" + name + ": " + enterMes;
        mes.value = "";
        var lastMessage = his.value;
        oldName = name;
        var action = name + " sent message " + enterMes;
        messageList.push(name + ": " + enterMes);
        localStorage.setItem('message', JSON.stringify(messageList, " ", 4));
        actionList.push(action);
        localStorage.setItem('list', JSON.stringify(actionList, " ", 4));
    }
};

function showHistory() {
    var show = document.getElementById('show');
    var history = document.getElementById('history');
    if (show.innerHTML == "Show history") {
        show.innerHTML = "Return";
        var item = localStorage.getItem('list');
        history.value = item;
    }
    else if (show.innerHTML == "Return") {
        show.innerHTML = "Show history";
        history.value = "";
    }
};

function deleteMessage() {
    his = document.getElementById('history');
    del = document.getElementById('delete');
    if (his.value != "")
        his.value = oldHistory;
    action = "Message" + " " + mes + " was deleted";
    actionList.push(action);
    localStorage.setItem('list', JSON.stringify(actionList, " ", 4));
};

function editMessage() {
    his = document.getElementById('history');
    his.value = oldHistory;
    mes = document.getElementById('mesarea');
    mes.value = enterMes;
    action = "Message " + enterMes + " was changed";
    actionList.push(action);
    localStorage.setItem('list', JSON.stringify(taskList, " ", 4));
};