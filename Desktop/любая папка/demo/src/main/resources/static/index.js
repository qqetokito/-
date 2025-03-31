async function required(){
 	
    let login = document.getElementById('username').value;
    let password = document.getElementById('userpassword').value;
 const response = await fetch('http://localhost:8080/register', {
     method: 'POST',
     headers: {
         'Content-Type': 'application/json'
     },
     body: JSON.stringify({ login: login, password: password })
 });

 if (response.status == 403) {
     alert("Пароль слишком прост");
 } else if (response.status == 200) {
  
     alert("Вы успешно зарегистрировались");
	 window.location.href=window.location.origin;	 
 }
 else if(response.status == 410){
    alert("Такой логин не зарегистрирован")
 }

 else {
     alert("Произошла ошибка. Попробуйте снова.");
 }
 if (!login_val || !password_val) {
 		    alert("Заполните все поля");
 		    
 		}
 
}
