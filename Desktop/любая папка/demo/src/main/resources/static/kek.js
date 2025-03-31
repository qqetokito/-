


async function asd(){
	
	let email=document.getElementById('email').value;
	let name=document.getElementById('name').value;
	let message=document.getElementById('message').value;
	const response = await fetch('http://localhost:8080/message', {
	       method: 'POST',
	       headers: {
	           'Content-Type': 'application/json'
	       },
	       body: JSON.stringify({ email:email,name:name,message:message })
	   });
	   
}