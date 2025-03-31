async function buy() {
    if (!localStorage.getItem("token")==null) {
        alert('Необходимо авторизоваться');
    }
    
    const response = await fetch('http://localhost:8080/buy', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: localStorage.getItem("token")
    });

    if (response.status == 200) {
        alert('Товар куплен');
    } else {
        alert('Ошибка');
		localStorage.removeItem("token")
		window.location.href=window.location.origin+"/login.html"
    }
}
