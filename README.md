# DM107 - Desenvolvimento de Web Services com Segurança sob plataforma Java e PHP

Trabalho entregue como forma de avaliação da Disciplina DM107 do curso de especialização em [Desenvolvimento de Aplicações para Dispositivos Móveis e Cloud Computing](http://www.inatel.br/pos/desenvolvimento-de-aplicacoes-para-dispositivos-moveis-e-cloud-computing-srs) no [Inatel](http://inatel.br/home/).

## Requerimentos

Para a execucação dos projetos Java e PHP se faz necessário a instalação dos seguintes programas:

* Eclipse JavaEE
* Gradle
* Apache Tomcat
* Mysql
* PHP
* Apache
* Composer

## Autenticação

Todos os recursos criados pelos projetos Java e PHP são protegidos por authenticação *basic auth*. Os usuários disponiveis são:

| Usuário | Senha   | Base64 Encoded       |
| :-----: | :-----: | -------------------- |
| admin   | admin   | YWRtaW46YWRtaW4=     |
| daniela | daniela | ZGFuaWVsYTpkYW5pZWxh |
| rafael  | rafael  | cmFmYWVsOnJhZmFlbA== |

## Recursos

### Cadastrar entrega

* URL:

```text
http://localhost:8080/TrabalhoLogisticaApi/rest/apiLogistica/entrega/
```

* Method:

```text
POST
```

* Header

```text
Authorization: Basic YWRtaW46YWRtaW4==
Content-type: application/json
```

* Body:

```json
{
  "numero_pedido": <num_pedido>,
  "id_cliente": <id_cliente>,
  "nome_recebedor": <nome_recebedor>,
  "cpf_recebedor": <cpf_recebedor>,
  "data_hora_entrega": <data_entrega>
}
```

> **Note:** Os campos obrigatórios são `numero_pedido`, `id_cliente`.

* Responses

  * Success

    **Code:** 200 OK

  * Error

    **Code:** 401 UNAUTHORIZED

* CURL Exemplo

```bash
curl -X POST \
  http://localhost:8080/TrabalhoLogisticaApi/rest/apiLogistica/entrega/ \
  -H 'authorization: Basic YWRtaW46YWRtaW4=' \
  -H 'content-type: application/json' \
  -d '{ "numero_pedido": 50, "id_cliente": 5 }'
```

---
### Listar entrega pelo número do pedido

* URL:

```text
http://localhost:8080/TrabalhoLogisticaApi/rest/apiLogistica/entrega/{numero_pedido}
```

* Method:

```text
GET
```

* Header

```text
Authorization: Basic YWRtaW46YWRtaW4==
```

* Responses

  * Success

    **Code:** 200 OK

    **Response Content:**
    ```json
    {
        "id":6,"numero_pedido":13,
        "id_cliente":1,
        "nome_recebedor":"231",
        "cpf_recebedor":"fdsafasdf",
        "data_hora_entrega":"2017-11-13 11:00:00"
    }
    ```

  * Error

    **Code:** 401 UNAUTHORIZED

* CURL Exemplo

```bash
curl -X GET \
  http://localhost:8080/TrabalhoLogisticaApi/rest/apiLogistica/entrega/6 \
  -H 'authorization: Basic YWRtaW46YWRtaW4='
```

---

### Atualizar entrega pelo id

* URL:

```text
http://localhost/trabalho-dm107/php/src/public/api/entregas/{id_entrega}
```

* Method:

```text
PUT
```

* Header

```text
Authorization: Basic YWRtaW46YWRtaW4==
Content-type: application/json
```

* Body:

```json
{
  "numero_pedido": <num_pedido>,
  "id_cliente": <id_cliente>,
  "nome_recebedor": <nome_recebedor>,
  "cpf_recebedor": <cpf_recebedor>,
  "data_hora_entrega": <data_entrega>
}
```

> **Note:** Os campos obrigatórios são `nome_recebedor`, `cpf_recebedor` e `data_hora_entrega`.

* Responses

  * Success

    **Code:** 200 OK

    **Response Content:**
    ```json
    {
        "status":true,
        "message":"Entrega 6 atualizada com sucesso"
    }
    ```

  * Error

    **Code:** 401 UNAUTHORIZED

* CURL Exemplo

```bash
curl -X PUT \
  http://localhost/trabalho-dm107/php/src/public/api/entregas/6 \
  -H 'authorization: Basic YWRtaW46YWRtaW4=' \
  -H 'content-type: application/json' \
  -d '{
        "nome_recebedor":"231",
        "cpf_recebedor":"fdsafasdf",
        "data_hora_entrega":"2017-11-13 11:00:00"
      }'
```

---

### Remover entrega pelo id

* URL:

```text
http://localhost/trabalho-dm107/php/src/public/api/entregas/{id_entrega}
```

* Method:

```text
DELETE
```

* Header

```text
Authorization: Basic YWRtaW46YWRtaW4==
```

* Responses

  * Success

    **Code:** 200 OK

    **Response Content:**
    ```json
    {
        "status":true,
        "message":"Entrega 6 removida com sucesso"
    }
    ```

  * Error

    **Code:** 401 UNAUTHORIZED

* CURL Exemplo

```bash
curl -X DELETE \
  http://localhost/trabalho-dm107/php/src/public/api/entregas/6 \
  -H 'authorization: Basic YWRtaW46YWRtaW4=' \
```
