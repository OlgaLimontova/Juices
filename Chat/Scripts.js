var name = "";

function run() {
    var connectButton = document.getElementById('connect');
    connectButton.addEventListener('click', saveName);
    var sendButton = document.getElementById('enter');
    sendButton.addEventListener('click', enterMessage);
    var deleteButton = document.getElementById('delete');
    deleteButton.addEventListener('click', deleteMessage);
    var editButton = document.getElementById('edit');
    editButton.addEventListener('click', editMessage);
};

function saveName() {
    var nickname = document.getElementById('getnick');
    name = nickname.value;
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
        his.value += name + ": " + enterMes + "\n";
        mes.value = "";
    }
};

function deleteMessage() {
    his = document.getElementById('history');
    del = document.getElementById('delete');
    if (his.value != "")
        his.value = oldHistory;
};

function editMessage() {
    his = document.getElementById('history');
    mes = document.getElementById('mesarea');
    ed = document.getElementById('edit');
    his.value = oldHistory;
    mes.value = enterMes;
};