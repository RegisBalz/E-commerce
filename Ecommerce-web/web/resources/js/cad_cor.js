var url = "http://localhost:8080/ecommerce-web/cor";

function cadastrarCor(oForm) {
    var formData = {};
    formData["corId"] = Number(oForm.cor_id.value);
    formData["cor"] = oForm.cor.value;
    
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            parseJson(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            document.getElementById("result").innerHTML = "Erro: " + this.statusText;
        }
        if (this.readyState === 3) {
            document.getElementById("result").innerHTML = "Aguarde...";
        }
    };
    
    http.open("POST", url);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(formData));
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById("result").innerHTML = "" + obj.message;
}