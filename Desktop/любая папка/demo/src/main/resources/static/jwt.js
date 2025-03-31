alert("qweasfwqrt	qwrt")
async function sendPostRequest() {
	
    const url = 'http://localhost:8080/login';
    
  
    let login_val = document.getElementById('username').value.trim();
    let password_val = document.getElementById('userpassword').value.trim();

  
    if (!login_val || !password_val) {
        alert("Заполните все поля");
  
    }

  
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ login: login_val, password: password_val }),
        });


        if (response.status === 200) {
            const responseData = await response.text();
            console.log('Response:', responseData);


            localStorage.setItem("token", responseData);
            alert("Вы успешно вошли!");
			window.location.href = "index.html";

        } else {
        
            const errorText = await response.text();
            alert(`Произошла ошибка: ${response.status}\n${errorText}`);
        }
    } 
  
