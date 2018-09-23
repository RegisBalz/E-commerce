function init() {
    loadProdutoInfo();
    document.querySelector('a[class="comprar"]').addEventListener('click', comprarProduto);
    document.querySelector('button[class="carrinho"]').addEventListener('click', enviarCarrinho);
    if (localStorage.length > 0) {
        document.querySelector('#qtde-carrinho').textContent = JSON.parse(localStorage.carrinho).produtos.length;
    }
}

function loadProdutoInfo() {
    var URL = "http://localhost:8080/ecommerce-web/produtoInfo" + location.search;
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            document.querySelector('.produtoNome').textContent = obj.produto;
            document.querySelector('.produtoImagem').src = obj.foto;
            document.querySelector('#produtoId').textContent = 'ID: ' + obj.produtoId;
            document.querySelector('#produtoId').classList.add(obj.produtoId);
            document.querySelector('#produto').textContent = 'NOME: ' + obj.produto;
            document.querySelector('#categoria').textContent = 'CATEGORIA: ' + obj.categoria.categoria;
            if (obj.desconto !== 0) {
                var desconto = (obj.valor * obj.desconto) / 100;
                var desconto = obj.valor - desconto;
                document.querySelector('#valor').textContent = 'PREÇO: R$ ' + desconto.toFixed(2);
                document.querySelector('#desconto').textContent = 'DESCONTO: ' + obj.desconto + '%';
                document.querySelector('.compra-price .cp1').textContent = 'R$ ' + obj.valor;
                document.querySelector('.compra-price .cp2').textContent = ' (' + obj.desconto + '% de desconto)';
                document.querySelector('.compra-valor').textContent = 'R$ ' + desconto.toFixed(2);
            } else {
                document.querySelector('#valor').textContent = 'PREÇO: R$ ' + obj.valor;
                descricao = document.querySelector('#desconto');
                document.querySelector('.maisInfo').removeChild(descricao);
                document.querySelector('.compra-valor').textContent = 'R$ ' + obj.valor;
            }
            document.querySelector('#estoque').textContent = 'RESTANTES: ' + obj.estoque;
            document.querySelector('#descricao').textContent = 'DESCRIÇÃO: ' + obj.descricao;
            
            loadZoomImage();
        }
    };
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function comprarProduto() {
    this.href += '?qtde=1&id=' + document.querySelector('#produtoId').classList.value;
}

function enviarCarrinho() {
    var local = Number(document.querySelector('li[id="produtoId"]').className);
    var URL = 'http://localhost:8080/ecommerce-web/produtoInfo?id=' + local;
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            var carrinho;
            
            if (localStorage.carrinho) {
                carrinho = JSON.parse(localStorage.carrinho);
            } else {
                carrinho = {'produtos': [{'produtoId': 0}]};
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
    
    http.open('GET', URL);
    http.setRequestHeader('Content-type', 'application/json');
    http.send();
}

function addCarrinho() {
    var adicionar = true;
    var id = location.search.split('=');
    for (var i in localStorage) {
        if (localStorage[i] === id[1]) {
            adicionar = false;
            alert('Este produto já foi adicionado ao carrinho!');
        }
    }
    
    if (adicionar === true) {
        localStorage.setItem('id' + id[1], id[1]);
        alert('Adicionado ao carrinho!');
    }
    
    document.querySelector('#qtde-carrinho').textContent = localStorage.length;
}

function loadZoomImage() {
    var containerImagem = document.querySelector('div[class="containerImagem"]');
    var produtoImagem = document.querySelector('img[class="produtoImagem"]');
    
    produtoImagem.addEventListener('mousemove', updatePosition);
    produtoImagem.addEventListener('mouseout', outImage);
    
    var posX = containerImagem.offsetLeft;
    var posY = containerImagem.offsetTop;
    var containerX = containerImagem.clientWidth;
    var containerY = containerImagem.clientHeight;
    var imagemX = produtoImagem.clientWidth;
    var imagemY = produtoImagem.clientHeight;
    
    if (imagemX > containerX || imagemY > containerY) {
        var contentX = imagemX - containerX;
        var contentY = imagemY - containerY;
    }
    
    function updatePosition(e) {
        var positionX = (e.pageX - posX) / containerX * 100;
        var positionY = (e.pageY - posY) / containerY * 100;
        var leftPosition = 0 - (contentX / 100 * positionX);
        var topPosition = 0 - (contentY / 100 * positionY);
        produtoImagem.style.width = 'auto';
        produtoImagem.style.left = leftPosition + 'px';
        produtoImagem.style.top = topPosition + 'px';
    }
    
    function outImage() {
        produtoImagem.style.width = '100%';
        produtoImagem.style.left = '0';
        produtoImagem.style.top = '0';
    }
    
    outImage();
    produtoImagem.style.visibility = 'visible';
}

init();