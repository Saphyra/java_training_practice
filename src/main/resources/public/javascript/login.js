function login(){
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const remember = document.getElementById("remember").checked;

    const data = {
        email: email,
        password: password,
        remember: remember
    }

    const request = new XMLHttpRequest();
    request.open("POST", "/login", 1);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function(){
        const responseStatus = request.status;
        const responseText = request.responseText;

        if(responseStatus == 200){
            window.location.href = "/index";
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
}

$(document).ready(init);

function init(){
    document.getElementById("email").onkeyup = function(e){
        if(e.which == 13){
            login();
        }
    }
    document.getElementById("password").onkeyup = function(e){
        if(e.which == 13){
            login();
        }
    }
}