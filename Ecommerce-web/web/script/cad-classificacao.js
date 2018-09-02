function init() {
    loadSelect();
    document.querySelector('input[value="Salvar"]').addEventListener('click', salvarCampos);
}

function loadSelect() {
    var URL = "http://localhost:8080/ecommerce-web/cadastro-classificacao";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            
            for (var i in obj) {
                optionTag = document.createElement('option');
                optionTag.textContent = obj[i].subClassificacao;
                document.querySelector('select').appendChild(optionTag);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(null);
}

function loadTabela() {
    var URL = "http://localhost:8080/ecommerce-web/cadastro-classificacao";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            
            for (var i in obj) {
                trTag = document.createElement('tr');
                tr.addEventListener('click', selecionaCampo);
                
                for (var x in obj[i]) {
                    tdTag = document.createElement('td');
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

function salvarCampos() {
    
}

init();