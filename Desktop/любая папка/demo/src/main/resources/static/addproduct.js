const toBase64 = file => new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = reject;
});

async function date(){
    let name=document.getElementById('name').value;
    let price=document.getElementById('price').value;
    let description=document.getElementById('description').value;
    let image=document.getElementById('image').files[0];
    let str_img =  await toBase64(image)
    let token=localStorage.getItem('token');
    const resp=await fetch('http://localhost:8080/addproduct', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
          
        },
        body: JSON.stringify({
            name:name,
            price:price,
            description:description,
            image:str_img,
            token:token,
        })
    });
    if (resp.status === 200) {
        alert('Продукт добавлен');
    } else {
        alert('Ошибка добавления продукта');
    }
    
}

