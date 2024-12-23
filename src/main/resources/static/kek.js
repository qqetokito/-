
async function qwe(){
    let login = document.getElementById('username').value;
    let password = document.getElementById('userpassword').value;
    const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ login: login, password: password })
    });
if (response.status == 403) {
    alert(response.status)
    alert("неверный пароль или логин");
    
} else if (response.status == 200) {
    
    alert("вход успешен");
	window.location.href = "/main";

    }
	else{
		alert("произошла ошибка");
	}
}