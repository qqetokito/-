async function choice() {
    if (localStorage.getItem("token") !== null) {
        const response = await fetch('http://localhost:8080/buy', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: localStorage.getItem("token")
        });
    
        if (response.status == 200) {
            document.getElementById("logined").style = 'display:flex !important';
            document.getElementById("not_logined").style = "display:none !important";
        } else {
            alert('Делать так не очень хорошо,ты знаешь о чем я говорю');
            localStorage.removeItem("token")
            window.location.href=window.location.origin+"/login.html"
        }
       

    } 
}
function exit(){
    localStorage.removeItem("token");
     window.location.href=window.location.origin+"/login.html"

}