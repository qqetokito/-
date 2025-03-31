document.addEventListener('DOMContentLoaded', function() {
    checkAuthorization();
});

function checkAuthorization() {
    if (localStorage.getItem('token') === null) {
        alert('Вам нужно сначала войти');
        window.location.href = 'login.html';
    } else {
      
        loadUserProfile();
    }
}

async function loadUserProfile() {
    
    let response= await fetch('http://localhost:8080/loadprofile', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${localStorage.getItem('token')}`
        }

});

if (response.status === 200) {
    const profile = await response.json();
     
    document.getElementById('name').value = profile.name;
    document.getElementById('surname').value = profile.surname;
    document.getElementById('addres').value = profile.addres;
    
} 
else if(response.status == 401){
    localStorage.removeItem('token');
    window.location.href = window.location.origin;
}
else {
    alert('Ошибка загрузки профиля');
}
}
function exit() {
    localStorage.removeItem('token');
    window.location.href = 'login.html';
}

async function saveUserProfile() {
    const profile = {
        name: document.getElementById('name').value,
        surname: document.getElementById('surname').value,
        addres: document.getElementById('addres').value,
        token: localStorage.getItem('token')
    };

    const response = await fetch('http://localhost:8080/profile', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(profile)
    });

    if (response.status === 200) {
        alert('Профиль обновлен');
    } else {
        alert('Ошибка обновления профиля');
    };

}
async function check_admin() {
    let response= await fetch('http://localhost:8080/admin', {
        method:'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: localStorage.getItem('token')

        
});
if (response.status == 200) {
    admin_form=document.getElementById('admin-form');
    admin_form.style.display='block';


}
else if(response.status == 403){
    admin_form=document.getElementById('admin-form');
    admin_form.style.display='none';

}
}
function addproduct(){
    window.location.href = 'addproduct.html';
}
function admin(){
    window.location.href = 'admin.html';
}


