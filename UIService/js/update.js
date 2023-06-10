let baseUrl = "http://localhost:8080";

document.addEventListener('submit',(event) =>{
    event.preventDefault();
    let name = document.getElementById("name").value;
    let satScore = document.getElementById("satScore").value;

    let url = `${baseUrl}/updateUser/${name}/${satScore}`;
    fetch(url,{
        method : 'PATCH'
    })
    .then(response => {
        return response.text();
    })
    .then(data =>{
        alert(data);
    })
    .catch(error =>{
        alert("Error while updating , please try again");
    })
})