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
    // variables are not decalred correctly. use 'var mes = ...'
    // use userfriendly and verbose variable names instead of short ones. e.g. 'newMessage' instaead of 'mes'
    while (name == null || name.length === 0) {
        // try this: while (!name)
        alert("Enter user name!");
        return;
    }
    if (mes.value != "") {
        // try this: if (name)
        enterMes = mes.value;
        oldHistory = his.value;
        his.value += name + ": " + enterMes + "\n";
        mes.value = "";
        // variable  names and declaration/definition again :(
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
