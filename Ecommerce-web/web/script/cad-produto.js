function init() {
    document.querySelector('input[value="Salvar"]').addEventListener('click', salvarCampos);
    document.querySelector('input[value="Remover"]').addEventListener('click', removerCampos);
    document.querySelector('input[type="file"]').addEventListener('change', carregaImagem);
    
    loadOptCategoria();
    loadTabela();
}

function carregaImagem(e) {
    var preview = document.querySelector('img');
    
    var reader = new FileReader();
    reader.onloadend = function () {
        preview.src = reader.result;
    };
    
    if (e.target.files[0]) {
        reader.readAsDataURL(e.target.files[0]);
    }
}

function loadOptCategoria() {
    var url = 'http://localhost:8080/ecommerce-web/produto?action=load-opt-categoria';
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                optionTag = document.createElement('option');
                optionTag.setAttribute('id', obj[i].categoriaId);
                optionTag.textContent = obj[i].categoria;
                document.querySelector('select[name="categoria"]').appendChild(optionTag);
            }
        }
    };
    
    http.open('GET', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

function loadTabela() {
    var url = 'http://localhost:8080/ecommerce-web/produto?action=load-produto';
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            
            for (var i in obj) {
                var dados = [obj[i].produtoId, obj[i].categoria.categoria, 
                    obj[i].produto, obj[i].valor, obj[i].desconto, obj[i].estoque];
                
                trTag = document.createElement('tr');
                trTag.addEventListener('click', selecionaCampo.bind(null, obj[i]));
                
                for (var x in dados) {
                    tdTag = document.createElement('td');
                    tdTag.textContent = dados[x];
                    trTag.appendChild(tdTag);
                }
                
                document.querySelector('tbody').appendChild(trTag);
            }
        }
    };
    
    http.open('GET', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

function salvarCampos(e) {
    var eForm = e.target.form;
    
    var formData = {};
    
    if (document.querySelector('.selected')) {
        formData['action'] = 'update';
        formData['produto_id'] = Number(eForm.produto_id.value);
    } else {
        formData['action'] = 'save';
    }
    
    formData['categoria_id'] = Number(eForm.categoria.options[eForm.categoria.selectedIndex].id);
    formData['produto'] = eForm.produto.value;
    formData['valor'] = Number(eForm.valor.value);
    formData['foto'] = document.querySelector('img').src;
    formData['desconto'] = Number(eForm.desconto.value);
    formData['estoque'] = Number(eForm.estoque.value);
    formData['descricao'] = eForm.descricao.value;
    
    var url = 'http://localhost:8080/ecommerce-web/produto';
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            openMessage(this.responseText);
        }
        if (this.readyState === 4 && this.status !== 200) {
            document.getElementById('result').innerHTML = 'Erro: ' + this.statusText;
        }
        if (this.readyState === 3) {
            document.getElementById('result').innerHTML = 'Aguarde...';
        }
    };
    
    http.open('POST', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send(JSON.stringify(formData));
}

function removerCampos(e) {
    if (confirm("Tem certeza que deseja excluir o registro selecionado?")) {
        var eForm = e.target.form;
        var formData = {};
        formData['action'] = 'delete';
        formData['produto_id'] = Number(eForm.produto_id.value);
        
        var url = "http://localhost:8080/ecommerce-web/produto";
        var http = new XMLHttpRequest();
        
        http.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                openMessage(this.responseText);
            }
            if (this.readyState === 4 && this.status !== 200) {
                document.getElementById('result').innerHTML = 'Erro: ' + this.statusText;
            }
            if (this.readyState === 3) {
                document.getElementById('result').innerHTML = 'Aguarde...';
            }
        };
        
        http.open('POST', url);
        http.setRequestHeader('Content-Type', 'application/json');
        http.send(JSON.stringify(formData));
    }
}

function selecionaCampo(objeto, e) {
    var selected = document.querySelector('.selected');
    var arrCampos = document.querySelectorAll('div > input, select, textarea');
    var arrDados = Object.values(objeto);
    
    if (selected) {
        selected.classList.remove('selected');
    }
    
    e.target.parentNode.classList.add('selected');
    
    for (var i in arrCampos) {
        if (arrCampos[i].tagName === "SELECT") {
            for (var x = 0, z = arrCampos[i].length; x < z; x++) {
                if (arrCampos[i].options[x].value === arrDados[i].categoria) {
                    arrCampos[i].options.selectedIndex = x;
                }
            }
        } else {
            if (arrCampos[i].type === "file") {
                document.querySelector('img').src = arrDados[i];
            } else {
                arrCampos[i].value = arrDados[i];
            }
        }
    }
}

function openMessage(json) {
    var data = JSON.parse(json);
    document.getElementById('result').innerHTML = data.message;
}

init();