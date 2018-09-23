function init() {
    loadCbUF();
    //loadProdutoId();
    //document.querySelector('fieldset input[type="button"]').addEventListener('click', gerarPedido);
}

function loadCbUF() {
    var URL = "http://localhost:8080/ecommerce-web/pedido?condicao=carregarCb";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            for (var i in obj) {
                optionTag = document.createElement('option');
                optionTag.textContent = obj[i].nomeEstado;
                optionTag.setAttribute('class', obj[i].nomeEstado);
                optionTag.setAttribute('id', obj[i].ufId);
                document.querySelector('select[name="estado"]').appendChild(optionTag);
            }
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function loadProdutoId() {
    var URL = "http://localhost:8080/ecommerce-web/pedido?id=" + location.search.split('&')[0];
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
                var as = document.querySelector('body');
                div1 = document.createElement('span');
                as.appendChild(div1);
                div2 = document.createElement('span');
                as.appendChild(div2);
                div3 = document.createElement('span');
                as.appendChild(div3);
                div4 = document.createElement('span');
                as.appendChild(div4);
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function gerarPedido(e) {
    var oForm = e.target.form;
    var formData = {};
    
    formData['cliente'] = oForm.cliente.value;
    formData['cpf_cnpj'] = oForm.cpf_cnpj.value;
    formData['estado'] = Number(oForm.estado.options[oForm.estado.selectedIndex].id);
    formData['cep'] = Number(oForm.cep.value);
    formData['cidade'] = oForm.cidade.value;
    formData['bairro'] = oForm.bairro.value;
    formData['rua'] = oForm.rua.value;
    formData['numero'] = oForm.numero.value;
    formData['complemento'] = oForm.complemento.value;
    formData['fone'] = oForm.fone.value;
    formData['email'] = oForm.email.value;
    formData['parcela'] = Number(oForm.parcela.value);
    
    var URL = "http://localhost:8080/ecommerce-web/pedido";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //gerarItensPedido();
            
        }
    };
    
    http.open('POST', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(JSON.stringify(formData));
    alert('Pedido gerado com sucesso!');
}

function gerarItensPedido() {
    var URL = "http://localhost:8080/ecommerce-web/pedido?condicao=itempedido";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
        }
    };
    
    http.open('POST', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

init();