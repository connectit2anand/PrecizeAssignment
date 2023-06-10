let baseUrl = "http://localhost:8080";

document.addEventListener("submit",(event)=>{
    event.preventDefault();
    let name = document.getElementById("name").value;
    deleteUser(name);
})


function deleteUser(name){
    let url = `${baseUrl}/deleteUser/${name}`;
    fetch(url,{
        method : 'DELETE'
    })

    .then(response =>{
        return response.text();
    })
    .then(data =>{
        alert(data);
    })
    .catch(error =>{
        alert("Something Went Wrong, Try Again");
    })
}