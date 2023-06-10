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
        if(data.length == 1)
            alert(`Your rank is ${data}`);
        else 
            alert(data);
    })
    .catch(error =>{
        alert("Something Went Wrong, Try Again");
    })
}