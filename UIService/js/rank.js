let baseUrl = "http://localhost:8080";

document.addEventListener("submit",(event)=>{
    event.preventDefault();
    let name = document.getElementById("name").value;
    userRank(name);
})


function userRank(name){
    let url = `${baseUrl}/getRank/${name}`;
    fetch(url,{
        method : 'GET'
    })

    .then(response =>{
        return response.text();
    })
    .then(data =>{
        if(data.substring(0,8) == 'Username')
            alert(data);
        else {
            alert(`Your rank is ${data}`);
        }
    })
    .catch(error =>{
        alert("Something Went Wrong, Try Again");
    })
}