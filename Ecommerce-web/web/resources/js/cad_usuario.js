var URL = "http://localhost:8080/ecommerce-web/cadastro";

function cadastraUsuario(oForm) {
    var login = {};
    login["usuario"] = oForm.usuario.value;
    login["senha"] = oForm.senha.value;
    
    var uf = {};
    uf["nome_estado"] = oForm.nome_estado.value;
    
    var formData = {};
    formData["nome_completo"] = oForm.nome_completo.value;
    formData["email"] = oForm.email.value;
    formData["fone"] = oForm.fone.value;
    formData["usuario"] = oForm.usuario.value;
    formData["senha"] = oForm.senha.value;
    formData["nome_estado"] = oForm.nome_estado.value;
    formData["cidade"] = oForm.cidade.value;
    formData["bairro"] = oForm.bairro.value;
    formData["rua"] = oForm.rua.value;
    formData["cep"] = oForm.cep.value;
    formData["cpf"] = oForm.cpf.value;
    
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
    
    http.open("POST", URL);
    http.setRequestHeader("Content-type", "application/json");
    http.send(JSON.stringify(formData));
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById("result").innerHTML = "" + obj.message;
}