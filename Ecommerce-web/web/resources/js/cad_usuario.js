var URL = "http://localhost:8080/ecommerce-web/cadastro";

            function cadastraUsuario(oForm) {
                var formData = {};
                formData["nome_completo"] = Number(oForm.nome_completo.value);
                formData["email"] = Number(oForm.email.value);
                formData["fone"] = Number(oForm.fone.value);
                formData["usuario"] = Number(oForm.usuario.value);
                formData["senha"] = Number(oForm.senha.value);
                formData["nome_estado"] = Number(oForm.nome_estado.value);
                formData["cidade"] = Number(oForm.cidade.value);
                formData["bairro"] = Number(oForm.bairro.value);
                formData["rua"] = Number(oForm.rua.value);
                formData["cep"] = Number(oForm.cep.value);
                formData["cpf"] = Number(oForm.cpf.value);

                var http = new XMLHttpRequest();

                http.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        parseJson(this.responseText);
                    }
                    if (this.readyState === 4 && this.status !== 200) {
                        document.getElementById("result").innerHTML = "Erro: " + this.statusText;
                    }
                    if (this.readyState === 3) {
                        document.getElementById("result").innerHTML = "Aguarde...";
                    }
                };

                http.open("POST", URL);
                http.setRequestHeader("Content-type", "application/json");
                http.send(JSON.stringify(formData));
            }

            function parseJson(jsonData) {
                var obj = JSON.parse(jsonData);
                document.getElementById("result").innerHTML = "" + obj.msg;
            }