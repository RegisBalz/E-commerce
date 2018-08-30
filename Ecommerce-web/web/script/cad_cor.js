function init() {
    loadTabela();
    document.querySelector('input[type="button"]').addEventListener('click', registrarCampos);
}

function loadTabela() {
    var URL = 'http://localhost:8080/ecommerce-web/cadastro-cor';
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                console.log(obj[i]);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(null);
}

function registrarCampos(e) {
    var oForm = e.target.form;
    var URL = 'http://localhost:8080/ecommerce-web/cadastro-cor';
    
    var formData = {};
    formData['cor'] = oForm.cor.value;
    
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            parseJson(this.responseText);
        }
        
        if (this.readyState === 4 && this.status !== 200) {
            document.getElementById('result').innerHTML = "Erro " + this.statusText;
        }
        
        if (this.readyState === 3) {
            document.getElementById('result').innerHTML = "Aguarde...";
        }
    };
    
    http.open('POST', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(JSON.stringify(formData));
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById('result').innerHTML = '' + obj.message;
}

init();