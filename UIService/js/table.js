let baseUrl = "http://localhost:8080";

init();
function init(){
    let url = `${baseUrl}/getAllUser`;

    fetch(url)
        .then(response => {
            return response.json();
        })
        .then(data =>{
            console.log(data);
            renderTable(data);
        })
        .catch(err =>{
            console.log(err);
        })
 }


function renderTable(data){
    const tbody = document.getElementById("table");
    tbody.innerHTML = null;
    rowListHtml = data.map((user) =>{
        return getRowData(
            user.name,
            user.address,
            user.city,
            user.country,
            user.pinCode,
            user.satScore,
            user.result
        )
    }).join("");

    tbody.innerHTML = rowListHtml;
    addDeleteHandler();
}

function getRowData(name,address,city,country,pinCode,satScore,result){
    let row = `
    <tr>
    <td>${name}</td>
    <td>${address}</td>
    <td>${city}</td>
    <td>${country}</td>
    <td>${pinCode}</td>
    <td>${satScore}</td>
    <td>${result}</td>
    <td>
        <a href="#" id="${name}" class="btn btn-danger deleteBtn"><i class="fas fa-trash" id="${name}"></i></a>
    </td>
    </tr>
    `
    return row;
}


function addDeleteHandler(){
    let anchorArray = document.getElementsByClassName("deleteBtn");
    if(anchorArray != null && anchorArray.length != 0){
        for(let i = 0; i < anchorArray.length; i ++){
            let anchorTag = anchorArray[i];
            let name = anchorTag.getAttribute("id");
            anchorTag.addEventListener("click",() => {
                deleteUser(name)
            });
        }
    }
}

function deleteUser(name){
    let url = `${baseUrl}/deleteUser/${name}`;
    fetch(url,{
        method : 'DELETE'
    })
    .then(response =>{
        return response.text(); 
    })
    .then(data => {
        alert(data);
        init();
    })
    .catch(err => {
        alert("Something Went Wrong, Please Try Again");
    });         
}