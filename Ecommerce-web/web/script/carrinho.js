function init() {
    montaCarrinho();
    document.querySelector('button[class="limpar-carrinho"]').addEventListener('click', limparCarrinho);
    document.querySelector('button[class="comprar-tudo"]').addEventListener('click', comprarTudo);
}

function montaCarrinho() {
    while (document.querySelectorAll('.product-item').length > 0) {
        document.querySelectorAll('.product-item')[0].remove();
    }
    
    var obj = JSON.parse(localStorage.getItem('carrinho'));
    for (var i in obj) {
        for (var x in obj[i]) {
            productItem = document.createElement('div');
            productItem.classList.add('product-item');
            productItem.setAttribute('id', obj[i][x].produtoId);
            productImg = document.createElement('div');
            productImg.classList.add('product-img');
            productItem.appendChild(productImg);
            aTag = document.createElement('a');
            aTag.setAttribute('href', '../pags/produto.html?id=' + obj[i][x].produtoId);
            productImg.appendChild(aTag);
            imgTag = document.createElement('img');
            imgTag.src = obj[i][x].foto;
            aTag.appendChild(imgTag);
            compraData = document.createElement('div');
            compraData.classList.add('compra-data');
            productItem.appendChild(compraData);
            productName = document.createElement('h5');
            productName.classList.add('product-name');
            productName.textContent = obj[i][x].descricao;
            compraData.appendChild(productName);
            compraPrice = document.createElement('div');
            compraPrice.classList.add('compra-price');
            compraData.appendChild(compraPrice);
            cp1 = document.createElement('span');
            cp1.classList.add('cp1');
            compraPrice.appendChild(cp1);
            cp2 = document.createElement('span');
            cp2.classList.add('cp2');
            compraPrice.appendChild(cp2);
            compraValor = document.createElement('p');
            compraValor.classList.add('compra-valor');
            
            if (obj[i][x].desconto > 0) {
                var desconto = (obj[i][x].valor * obj[i][x].desconto) / 100;
                desconto = obj[i][x].valor - desconto;
                cp1.textContent = 'R$ ' + obj[i][x].valor;
                cp2.textContent = ' (' + obj[i][x].desconto + '% de desconto)';
                compraValor.textContent = 'R$ ' + desconto.toFixed();
            } else {
                compraValor.textContent = 'R$ ' + obj[i][x].valor;
            }
            
            compraData.appendChild(compraValor);
            compraParcela = document.createElement('p');
            compraParcela.classList.add('compra-parcela');
            compraParcela.textContent = 'Parcelas até 4x sem juros';
            compraData.appendChild(compraParcela);
            productOption = document.createElement('div');
            productOption.classList.add('product-option');
            productItem.appendChild(productOption);
            comprar = document.createElement('a');
            comprar.classList.add('comprar');
            comprar.href = '../pags/pedido.html?id=' + obj[i][x].produtoId;
            comprar.onclick = comprarProduto;
            comprar.textContent = 'COMPRAR';
            productOption.appendChild(comprar);
            deletar = document.createElement('button');
            deletar.classList.add('deletar');
            deletar.type = 'button';
            deletar.textContent = 'REMOVER';
            deletar.onclick = deletarProduto;
            productOption.appendChild(deletar);
            spanTag = document.createElement('span');
            productOption.appendChild(spanTag);
            labelTag = document.createElement('label');
            labelTag.textContent = 'Quantidade';
            spanTag.appendChild(labelTag);
            qtde = document.createElement('input');
            qtde.classList.add('qtde');
            qtde.value = 1;
            qtde.type = 'number';
            qtde.min = 1;
            qtde.max = obj[i][x].estoque;
            spanTag.appendChild(qtde);
            estoque = document.createElement('span');
            estoque.classList.add('estoque');
            estoque.textContent = 'Restantes: ' + obj[i][x].estoque;
            productItem.appendChild(estoque);
            
            document.querySelector('.cart-items').appendChild(productItem);
        }
    }
    
    if (document.querySelectorAll('.product-item').length > 0) {
        document.querySelector('#qtde-carrinho').textContent = document.querySelectorAll('.product-item').length;
    } else {
        document.querySelector('#qtde-carrinho').textContent = null;
    }
}

function comprarProduto() {
    this.href += '&qtde=' + this.parentNode.querySelector('.qtde').value;
}

function comprarTudo() {
    var obj = JSON.parse(localStorage.getItem('carrinho'));
    
    for (var i in obj.produtos) {
        var carrinho = JSON.stringify(JSON.parse(localStorage.getItem('carrinho')).produtos[i]);
        var qtde = JSON.stringify({'quantidade': document.querySelectorAll('input[class="qtde"]')[i].value});
        Object.defineProperties(carrinho, {
            qtde: {
                value: document.querySelectorAll('input[class="qtde"]')[i].value, 
                writable: true
            }
        });
        
        window.localStorage.setItem('carrinho.produtos', JSON.stringify(carrinho));
    }
}

function deletarProduto() {
    var local = Number(this.parentNode.parentNode.id);
    var obj = JSON.parse(localStorage.getItem('carrinho')).produtos;
    
    for (var i in obj) {
        if (local === obj[i].produtoId) {
            obj.splice(obj.indexOf(obj[i]), 1);
        }
    }
    
    var carrinho = JSON.parse(localStorage.carrinho);
    carrinho.produtos = obj;
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
    
    montaCarrinho();
}

function limparCarrinho() {
    if (confirm('Tem certeza que deseja apagar todos os itens de seu carrinho?')) {
        localStorage.clear();
        montaCarrinho();
    }
}

init();