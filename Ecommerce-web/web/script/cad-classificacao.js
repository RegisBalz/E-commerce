function init() {
    loadCheckbox();
    loadTabela();
    
    btnNovo = document.querySelector('input[value="Novo"]');
    btnSalvar = document.querySelector('input[value="Salvar"]');
    btnRemover = document.querySelector('input[value="Remover"]');
    result = document.querySelector('#result');
    
    btnNovo.addEventListener('click', novoRegistro);
    btnSalvar.addEventListener('click', salvarCampos);
    btnRemover.addEventListener('click', removerCampos);
}

function loadCheckbox() {
    var URL = "http://localhost:8080/ecommerce-web/loadCheckboxClass";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                optionTag = document.createElement('option');
                optionTag.textContent = obj[i].subclassNome;
                document.querySelector('select').appendChild(optionTag);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function loadTabela() {
    var URL = "http://localhost:8080/ecommerce-web/loadTabelaClass";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                trTag = document.createElement('tr');
                trTag.addEventListener('click', selecionaCampo);
                
                for (var x in obj[i]) {
                    tdTag = document.createElement('td');
                    if (obj[i][x].constructor.name === "Object") {
                        tdTag.textContent = obj[i][x].subclassNome;
                    } else {
                        tdTag.textContent = obj[i][x];
                    }
                    trTag.appendChild(tdTag);
                }
                
                document.querySelector('tbody').appendChild(trTag);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function novoRegistro() {
    var selected = document.querySelector('.selected');
    if (selected) {
        selected.classList.remove('selected');
    }
    
    document.querySelector('form').reset();
    btnRemover.setAttribute('disabled', 'disabled');
}

function salvarCampos(e) {
    var oForm = e.target.form;
    var formData = {};
    formData['subclass'] = oForm.subclass.value;
    formData['class_nome'] = oForm.class_nome.value;
    
    if (document.querySelector('.selected')) {
        formData['class_id'] = Number(oForm.class_id.value);
        var metodo = 'PUT';
    } else {
        var metodo = 'POST';
    }
    
    var msg = [];
    if (/[A-Z]|[0-9]/i.test(formData.subclass) === false) {
        msg.push('- O campo Subclassificação é obrigatório!');
    }
    if (/[A-Z]|[0-9]/i.test(formData.class_nome) === false) {
        msg.push('- O campo Classificação é obrigatório!');
    }
    
    if (msg.length > 0) {
        alert('Preencha os campos corretamente:\n' + msg.join('\n'));
    } else {
        var URL = 'http://localhost:8080/ecommerce-web/saveClass';
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
        
        http.open(metodo, URL);
        http.setRequestHeader('Content-type', 'application/json');
        http.send(JSON.stringify(formData));
    }
}

function removerCampos() {
    
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
        var arrCampos = document.querySelectorAll('input[type="text"], select');
        
        for (i = 0, qtde1 = arrColunas.length; i < qtde1; i++) {
            arrCampos[i].value = arrColunas[i].textContent;
        }
        
        btnRemover.removeAttribute('disabled');
    }
}

function parseJson(jsonData) {
    var obj = JSON.parse(jsonData);
    document.getElementById('result').innerHTML = '' + obj.message;
    setTimeout(function(){result.classList.add('sumir');}, 4000);
    result.classList.remove('sumir');
}

init();