function init() {
    loadTabela();
    document.querySelector('input[value="Salvar"]').addEventListener('click', salvarCampos);
    document.querySelector('input[value="Remover"]').addEventListener('click', removerCampos);
}

function loadTabela() {
    var URL = 'http://localhost:8080/ecommerce-web/cadastro-subclass';
    
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                tr = document.createElement('tr');
                tr.addEventListener('click', selecionaCampo);
                
                for (var x in obj[i]) {
                    td = document.createElement('td');
                    td.textContent = obj[i][x];
                    tr.appendChild(td);
                }
                
                document.querySelector('tbody').appendChild(tr);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(null);
}

function salvarCampos(e) {
    var oForm = e.target.form;
    var URL = 'http://localhost:8080/ecommerce-web/cadastro-subclass';
    
    var formData = {};
    formData['sub_classificacao'] = oForm.subclass.value;
    
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            parseJson(this.responseText);
            
            while (document.querySelector('tbody').rows.length > 0) {
                document.querySelector('tbody').deleteRow(0);
            }
            
            loadTabela();
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

function removerCampos() {
    var linhaSel = document.querySelector('.linha');
    console.log('pimentinha');
    if (linhaSel) {
        linhaSel.parentNode.removeChild(linhaSel);
        document.querySelector('form').reset();
    }
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById('result').innerHTML = '' + obj.message;
}

function selecionaCampo() {
    linha = document.querySelector('.linha');
    campos = this.querySelectorAll('td');
    
    if (linha) {
        linha.classList.remove('linha');
    }
    
    this.classList.add('linha');
}

init();