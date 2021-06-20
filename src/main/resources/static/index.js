const LoginHTML = '<form class="frm">\n' +
    '        <label for="login"></label><div id="vallog" class="val-log"><input id="login" placeholder="Логин" class="inpt"></div>\n' +
    '        <label for="password"></label><div id="valpas" class="val-pas"><input id="password" type="password" placeholder="Пароль" class="inpt"></div>\n' +
    '        <div class="cntr"><button onclick="singIn()" class="btn green" type="button">Войти</button>\n' +
    '        <button onClick="inner(SignUpHTML)" class="btn blue" type="button">Регистрация</button></div>\n' +
    '</form>';

const SignUpHTML = '<form class="frm">\n' +
    '        <label for="email"></label><div id="valemail" class="val-email"><input id="email" placeholder="Email" class="inpt"></div>\n' +
    '        <label for="login"></label> <div id="vallog" class="val-log"><input id="login" placeholder="Логин" class="inpt"></div>\n' +
    '        <label for="password"></label> <div id="valpas" class="val-pas"><input id="password" type="password" placeholder="Пароль" class="inpt"> </div>\n' +
    '        <button onclick="regist()" class="btn-max blue" type="button">Регистрация</button>\n' +
    '</form>';

const SucHTML = '<span class="suc">Успешная авторизация!</span>';

const mainPage = async (user, productList) => {
    let allLots = '';
    let allUsersLotsList = '';

    await allUsersLots(user).then(async lotsList => {
        if (lotsList) {
            lotsList = lotsList.reverse();
            allUsersLotsList += '<div class="lotsList">\n';
            lotsList.forEach( lot => {
                allUsersLotsList += '<div>Название: '+lot.name+'</div>\n' +
                    '<div>Описание: '+lot.description+'</div>\n' +
                    '<div>Цена: '+lot.cost+'</div>\n' +
                    '<button type="button" class="btn-lot val-text" id="delLot" data-id="'+lot.id+'">Удалить</button>'
            });
            allUsersLotsList += '</div>';
        }
    });

    productList = productList.reverse();
    allLots += '<div class="lotsList">\n';
    await productList.forEach( lot => {

        console.log(lot);
        console.log(productList);

        let btn = '<button type="button" class="btn-lot blue" id="delLot" data-id="'+lot.id+'">Купить</button>';

        allLots += '<div>Название: '+lot.name+'</div>' +
                    '<div>Цена: '+lot.cost+'</div>' +
            '<div>Описание: '+lot.description+'</div>\n' +
            btn;
        });
    allLots += '</div>';

    return(
        '<div class="mainContainer"> ' +
        `<div class="form-c"><span class="loginView">`+user.login+`</span>\n` +
        `<form class="frm">\n` +
        `        <label for="name"></label> <div id="vallog" class="val-log"><input id="name" placeholder="Название" class="inpt-lot"></div>\n` +
        `        <label for="description"></label> <div id="valpas" class="val-pas"><input id="description" placeholder="Описание" class="inpt-lot"> </div>\n` +
        `        <label for="cost"></label> <div id="valcost" class="val-cost"><input id="cost" placeholder="Цена" class="inpt-lot"> </div>\n` +
        `        <button id="addLot" class="inpt-lot blue" type="button">Добавить лот</button>\n` +
        `</form></div>\n` +
        `<div class="listsCont">
            <div class="preListCont">
                Ваши лоты:` + allUsersLotsList + `
            </div>
            <div class="preListCont">
                Все лоты:` + allLots + `
            </div>
        </div>\n` +
        '</div>'
    );
}

async function getUserByLogin(login) {
    let res = await fetch("http://localhost:8080/users/findByLogin?login=" + login);
    return res.text();
}

function shortPas() {
    inner(SignUpHTML);
    document.getElementById("vallog").innerHTML = '<span class="val-text">Поле пароля пустое.</span>' +
        '<input id="login" placeholder="Логин" class="inpt">';
    document.getElementById("valpas").innerHTML = '<input id="password" placeholder="Пароль" class="inpt val" type="password">';
    regist();
}

function noLogin() {
    inner(LoginHTML);
    document.getElementById("vallog").innerHTML = '<span class="val-text">Логин не найден.</span>' +
        '<input id="login" placeholder="Логин" class="inpt val">';
}

function noPas() {
    inner(LoginHTML);
    document.getElementById("vallog").innerHTML = '<span class="val-text">Пароль неверен.</span>' +
        '<input id="login" placeholder="Логин" class="inpt">';
    document.getElementById("valpas").innerHTML = '<input id="password" placeholder="Пароль" class="inpt val" type="password">';
}

async function regist() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;

    if (!password) {
        return shortPas();
    }

    getUserByLogin(login).then(async user => {
        if (user) {
            document.getElementById("vallog").innerHTML = '<span class="val-text">Логин занят.</span>' +
                '<input id="login" placeholder="Логин" class="inpt val">';
        } else {

            let user = JSON.stringify({
                "email": email,
                "login": login,
                "password": password
            })

            await fetch('http://localhost:8080/users/save', {
                method: 'POST',
                headers: {
                    "Content-type": "application/json;charset=utf-8"
                },
                body: user
            });

            inner(SucHTML);
            setTimeout(() => {
                inner(LoginHTML);
            }, 1500);
        }
    });
}

async function singIn() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;

    getUserByLogin(login).then(async u => {
        let user;

        if (u) {
            user = JSON.parse(u);
        } else return noLogin();

        if (user.password === password) {
            await inner(await mainPage(user, await allLots()), user);
        } else return noPas();
    });
}

async function newLot(user) {
    let name = document.getElementById("name").value;
    let cost = parseInt(document.getElementById("cost").value);
    let description = document.getElementById("description").value;

    let lot = {
        name: name,
        description: description,
        cost: cost,
        user_id: user.id
    }

    await fetch('http://localhost:8080/lots/save', {
        method: 'POST',
        headers: {
            "Content-type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(lot)
    });

    await inner(await mainPage(user, await allLots()), user);
}

async function allUsersLots(user) {
    let res = await fetch('http://localhost:8080/lots/findAllByUserId/'+user.id);
    return await res.json();
}

async function allLots() {
    let res = await fetch('http://localhost:8080/lots/findAll');
    return await res.json();
}

async function deleteLot(id, user) {
    await fetch('http://localhost:8080/lots/delete/' + id, {
        method: 'DELETE'
    })

    await inner(await mainPage(user, await allLots()), user);
}

async function inner(HTML, user) {

    document.getElementById('container').innerHTML = HTML;

    try {
        let delLot = document.getElementById("delLot");
        let lotId = delLot.dataset.id;

        delLot.onclick = async () => {
            await deleteLot(lotId, user);
        }
    } catch (e) {}

    try {
        document.getElementById("addLot").onclick = () => {
            newLot(user);
        };
    } catch (e) {}
}

inner(LoginHTML);

function inArray(arr, obj) {
    let flag = false;

    arr.forEach(o => {
        if (JSON.stringify(o) === JSON.stringify(obj))
            return flag = true;
    })

    return flag;
}