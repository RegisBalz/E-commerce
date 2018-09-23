function init() {
    loadTabela();
    
    btnNovo = document.querySelector('input[value="Novo"]');
    btnSalvar = document.querySelector('input[value="Salvar"]');
    btnRemover = document.querySelector('input[value="Remover"]');
    result = document.querySelector('#result');
    
    btnNovo.addEventListener('click', novoRegistro);
    btnSalvar.addEventListener('click', salvarCampos);
    btnRemover.addEventListener('click', removerCampos);
}

function loadTabela() {
    var URL = 'http://localhost:8080/ecommerce-web/categoria';
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                trTag = document.createElement('tr');
                trTag.addEventListener('click', selecionaCampo);
                
                for (var x in obj[i]) {
                    tdTag = document.createElement('td');
                    tdTag.textContent = obj[i][x];
                    trTag.appendChild(tdTag);
                }
                
                document.querySelector('tbody').appendChild(trTag);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

function novoRegistro() {
    var selected = document.querySelector('.selected');
    if (selected) {
        selected.classList.remove('selected');
    }
    
    document.querySelector('form').reset();
    document.querySelector('#categoria').focus();
    btnRemover.setAttribute('disabled', 'disabled');
}

function salvarCampos(e) {
    var oForm = e.target.form;
    var formData = {};
    
    if (document.querySelector('.selected')) {
        formData['action'] = 'update';
        formData['categoria_id'] = Number(oForm.categoria_id.value);
    } else {
        formData['action'] = 'save';
    }
    
    formData['categoria'] = oForm.categoria.value;
    
    var msg = [];
    if (/[A-Z]|[0-9]/i.test(formData.marca) === false) {
        msg.push('- O campo Categoria é obrigatório!');
    }
    
    if (msg.length > 0) {
        alert('Preencha os campos corretamente:\n' + msg.join('\n'));
    } else {
        var URL = 'http://localhost:8080/ecommerce-web/categoria';
        var http = new XMLHttpRequest();
        
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                parseJson(this.responseText);
                
                while (document.querySelector('tbody').rows.length > 0) {
                    document.querySelector('tbody').deleteRow(0);
                }
                
                novoRegistro();
                loadTabela();
            }
            
            if (this.readyState === 4 && this.status !== 200) {
                result.innerHTML = "Erro " + this.statusText;
            }
            
            if (this.readyState === 3) {
                result.innerHTML = "Aguarde...";
            }
        };
        
        http.open("POST", URL);
        http.setRequestHeader('Content-Type', 'application/json');
        http.send(JSON.stringify(formData));
    }
}

function removerCampos(e) {
    if (confirm("Tem certeza que deseja excluir o registro selecionado?")) {
        var oForm = e.target.form;
        var formData = {};
        formData['action'] = 'delete';
        formData['categoria_id'] = Number(oForm.categoria_id.value);
        
        var URL = 'http://localhost:8080/ecommerce-web/categoria';
        var http = new XMLHttpRequest();
        
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                parseJson(this.responseText);
                
                while (document.querySelector('tbody').rows.length > 0) {
                    document.querySelector('tbody').deleteRow(0);
                }
                
                loadTabela();
                novoRegistro();
            }
            
            if (this.readyState === 4 && this.status !== 200) {
                result.innerHTML = "Erro " + this.statusText;
            }
            
            if (this.readyState === 3) {
                result.innerHTML = "Aguarde...";
            }
        };
        
        http.open('POST', URL);
        http.setRequestHeader('Content-Type', 'application/json');
        http.send(JSON.stringify(formData));
    }
}

function selecionaCampo() {
    var selected = document.querySelector('.selected');
    
    if (selected) {
        selected.classList.remove('selected');
        document.querySelector('form').reset();
        btnRemover.setAttribute('disabled', 'disabled');
    }
    
    if (selected !== this) {
        this.classList.add('selected');
        var arrColunas = this.querySelectorAll('td');
        var arrCampos = document.querySelectorAll('input[type="text"]');
        
        for (i = 0, qtde = arrColunas.length; i < qtde; i++) {
            arrCampos[i].value = arrColunas[i].textContent;
        }
        
        btnRemover.removeAttribute('disabled');
    }
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    result.innerHTML = '' + obj.message;
    setTimeout(function(){result.classList.add('sumir');}, 4000);
    result.classList.remove('sumir');
}

init();