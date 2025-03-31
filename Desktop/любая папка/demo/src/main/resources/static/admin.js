async function check_admin() {
    let response= await fetch('http://localhost:8080/admin', {
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: localStorage.getItem('token')

        
});
    if (response.status === 200) {
         console.log('OK');
    } else if(response.status == 401){
        alert('Вы не администратор');
        window.location.href = 'profile.html';
    } else {
        alert('Ошибка проверки прав администратора');
        window.location.href = 'profile.html';
    };
}
async function loading_data(){
      let response= await fetch('http://localhost:8080/admin', {
        method:'GET',
        headers: {
            'Content-Type': 'application/json'
        },
      

});

}
