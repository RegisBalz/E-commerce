function init() {
    window.addEventListener('scroll', loadScrollTop);
    loadQtdeCarrinho();
    loadCategoria();
}

function loadCategoria() {
    var url = "http://localhost:8080/ecommerce-web/principal?action=load-categoria";
    var http = new XMLHttpRequest();
    
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var obj = JSON.parse(this.responseText);
            var local = location.href.replace(location.search, '');
            
            for (var i in obj) {
                aTag = document.createElement('a');
                aTag.textContent = obj[i].categoria;
                aTag.setAttribute('href', local + '?categoria=' + obj[i].categoria);
                document.querySelector('.nav-menu').appendChild(aTag);
            }
        }
    };
    
    http.open('GET', url);
    http.setRequestHeader('Content-Type', 'application/json');
    http.send();
}

function loadQtdeCarrinho() {
    var obj = JSON.parse(localStorage.getItem('carrinho'));
    
    if (obj !== null) {
        if (obj.hasOwnProperty('produtos')) {
            if (obj.produtos.length > 0) {
                document.querySelector('#qtde-carrinho').textContent = obj.produtos.length;
            }
        }
    }
}

function loadScrollTop() {
    var top = document.documentElement.scrollTop;
    
    if (top > 136) {
        document.querySelector('.menu').classList.add('f-nav');
    } else {
        document.querySelector('.menu').classList.remove('f-nav');
    }
}

init();