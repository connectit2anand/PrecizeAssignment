const baseUrl = "http://localhost:8080";

document.addEventListener("submit",(event)=>{
    event.preventDefault();
    handleSubmit(event);
});

function handleSubmit(event){
    let name = document.getElementById("name").value;
    let address = document.getElementById("address").value;
    let city = document.getElementById("city").value;
    let country = document.getElementById("country").value;
    let pinCode = document.getElementById("pin-code").value;
    let satScore = document.getElementById("sat-score").value;

    let requestObject = {
        name : name,
        address : address,
        city : city,
        country : country,
        pinCode : pinCode,
        satScore : satScore
    }

    let url = `${baseUrl}/addUser`;
    fetch(url, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestObject)
      })
      .then(response => response.text())
        .then(result => {alert(result);
          event.target.reset();
        })
        .catch(error => {
          alert("Something Went Wrong, Please Try Again");
        });
}