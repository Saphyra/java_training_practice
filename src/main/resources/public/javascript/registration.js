 function registration(){
    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const password2 = document.getElementById("password2").value;
    const name = document.getElementById("name").value;
    const phoneNumber = document.getElementById("phoneNumber").value;
    const birthDate = document.getElementById("birthDate").value;
    const currency = document.getElementById("currency").value;
    const country = document.getElementById("country").value;
    const zipcode = document.getElementById("zipcode").value;
    const city = document.getElementById("city").value;
    const street = document.getElementById("street").value;
    const houseNumber = document.getElementById("houseNumber").value;
    const floor = document.getElementById("floor").value;
    const door = document.getElementById("door").value;
    const tac = document.getElementById("tac").checked;

    if(tac == false){
        alert("Please accept the checkbox.");
        return;
    }

    if(username == ""){
        alert("Username missing.");
        return;
    }

    if(username.length < 6 || username.length > 32){
        alert("Username length is invalid");
        return;
    }

    if(email == ""){
        alert("Email missing.");
        return;
    }

    if(!isEmailValid(email)){
        alert("Email is invalid");
        return;
    }

    if(password == ""){
        alert("Password missing");
        return
    }

    if(password.length < 6 || password.length > 20){
        alert("Password length is invalid.");
        return
    }

    if(password2 == ""){
        alert("Password2 missing.");
        return;
    }

    if(password != password2){
        alert("Invalid confirm password.");
        return;
    }

    const data = {
        username: username,
        password: password,
        email: email,
        name: name,
        address: {
            country: country,
            zipCode: zipcode,
            city: city,
            street: street,
            houseNumber: houseNumber,
            floor: floor,
            door: door
        },
        phoneNumber: phoneNumber,
        birthDate: birthDate,
        currency: currency
    }

    const request = new XMLHttpRequest();
    request.open("POST", "/registration", 1);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function(){
        const responseStatus = request.status;
        const responseText = request.responseText;

        if(responseStatus == 200){
            alert("Registration successful");
            window.location.href = "/login"
        }else{
            const errorResponse = parseResponse(responseText);
            if(errorResponse.errorMessage){
                alert(errorResponse.errorMessage);
            }else{
                alert("Unknown error: " + responseStatus + " - " + responseText);
            }
        }

        function parseResponse(responseText){
            try{
                return JSON.parse(responseText);
            }catch(e){
                return {};
            }
        }
    }

    request.send(JSON.stringify(data));

    function isEmailValid(email){
        let result;
        if(email == null || email == undefined){
            result = false;
        }

        if(email.length < 6){
            return false;
        }

        return email.match("^\\s*[\\w\\-\\+_]+(\\.[\\w\\-\\+_]+)*\\@[\\w\\-\\+_]+\\.[\\w\\-\\+_]+(\\.[\\w\\-\\+_]+)*\\s*$");
    }
 }

