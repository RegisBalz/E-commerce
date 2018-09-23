function init() {
    loadProduto();
}

function loadProduto() {
    var url = 'http://localhost:8080/ecommerce-web/principal';
    var http = new XMLHttpRequest();
    
    var query = location.search.slice(1);
    var valor = query.split('=')[0];
    
    switch (valor) {
        case 'search':
            url += '?action=load-grid-pesquisa&' + query;
            break;
        case 'categoria':
            url += '?action=load-grid-categoria&' + query;
            break;
        case 'action':
            url += '?action=load-grid-desconto';
            break;
        default: 
            url += '?action=load-grid-produto';
    }
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            
            if (obj.length === 0) {
                document.querySelector('.catalogo-vazio').classList.remove('invisivel');
            } else {
                document.querySelector('.catalogo-vazio').classList.add('invisivel');
                
                for (var i in obj) {
                    blocoProd = document.createElement('div');
                    aTag = document.createElement('a');
                    h5Tag = document.createElement('h5');
                    divImage = document.createElement('div');
                    imgTag = document.createElement('img');
                    blocoValor = document.createElement('div');
                    pValor = document.createElement('p');
                    pDesc = document.createElement('p');
                    btnTag = document.createElement('button');
                    blocoProd.classList.add('product-item');
                    blocoProd.setAttribute('id', obj[i].produtoId);
                    aTag.setAttribute('href', 'pags/produto.html?id=' + blocoProd.id);
                    h5Tag.classList.add('product-name');
                    h5Tag.textContent = obj[i].descricao;
                    imgTag.classList.add('product-img');
                    imgTag.setAttribute('src', obj[i].foto);
                    blocoValor.classList.add('product-price');
                    pValor.classList.add('price');
                    pValor.textContent = 'R$ ' + obj[i].valor.toFixed(2);
                    pDesc.classList.add('promo');
                    
                    if (obj[i].desconto !== 0) {
                        var desconto = (obj[i].valor * obj[i].desconto) / 100;
                        var desconto = obj[i].valor - desconto;
                        pDesc.textContent = 'R$ ' + desconto.toFixed(2);
                        pValor.classList.add('lastPrice');
                    }
                    
                    btnTag.classList.add('btn-carrinho');
                    btnTag.textContent = 'ADD +';
                    btnTag.addEventListener('click', enviarCarrinho);
                    aTag.appendChild(h5Tag);
                    aTag.appendChild(divImage);
                    divImage.appendChild(imgTag);
                    blocoValor.appendChild(pValor);
                    blocoValor.appendChild(pDesc);
                    blocoProd.appendChild(aTag);
                    blocoProd.appendChild(blocoValor);
                    blocoProd.appendChild(btnTag);
                    document.querySelector('.catalogo').appendChild(blocoProd);
                }
            }
        }
    };
    
    http.open('GET', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

function enviarCarrinho() {
    var local = Number(this.parentNode.id);
    var url = 'http://localhost:8080/ecommerce-web/produtoInfo?id=' + this.parentNode.id;
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            var carrinho;
            
            if (localStorage.carrinho) {
                carrinho = JSON.parse(localStorage.carrinho);
            } else {
                carrinho = {produtos: [{produtoId: 0}]};
            }
            
            var dados = [];
            
            for (var i in carrinho.produtos) {
                dados[i] = carrinho.produtos[i].produtoId;
            }
            
            if (dados.some(verificaValor)) {
                alert('Este produto já foi adicionado ao carrinho!');
            } else {
                if (localStorage.length === 0) {
                    carrinho.produtos = [];
                }
                
                carrinho.produtos.push(obj);
                window.localStorage.setItem('carrinho', JSON.stringify(carrinho));
                
                alert('Adicionado ao carrinho!');
            }
            
            function verificaValor(dados) {
                return dados === local;
            }
            
            document.querySelector('#qtde-carrinho').textContent = JSON.parse(localStorage.carrinho).produtos.length;
        }
    };
    
    http.open('GET', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

init();