class Product {
    static agregar() {
        let itm = document.querySelector("#productItem");
        let cln = itm.cloneNode(true);
        let producto = {};
        cln.id = "productItem" + "_" + Product.NUMERO_PRODUCTO_SEC;
        document.querySelector("#productPnl").appendChild(cln);
        let losInputs = cln.querySelectorAll("input");
        for (let i = 0; i < losInputs.length; i++) {
            losInputs[i].value = "";
            losInputs[i].id = losInputs[i].id + "_" + Product.NUMERO_PRODUCTO_SEC;
            console.log(losInputs[i].id);
        }
        
        let observacion = cln.querySelectorAll("textarea");
        for (let j = 0; j < observacion.length; j++){
            observacion[j].value = "";
        }
        Product.NumOrderProduct( cln );
        Product.NUMERO_PRODUCTO_SEC++;

    }
    
    static NumOrderProduct( cln ){
        let numOrder = Product.ORDER_PRODUCT;
        let IdProductItem = "#" + cln.id;
        document.querySelector( IdProductItem + " span#productOrder" );
        document.querySelector( IdProductItem + " span#productOrder" ).id 
                = document.querySelector( IdProductItem + " span#productOrder" ).id + Product.NUMERO_PRODUCTO_SEC;        
        document.querySelector( "span#productOrder" + Product.NUMERO_PRODUCTO_SEC ).innerHTML 
                = Product.NUMERO_PRODUCTO_SEC;
    }

    static setOnclick() {
        let elemm = document.querySelector("#producAddBtn");
        elemm.setAttribute("onclick", "Product.agregar()");
        let grabarbtn = document.querySelector("#grabar");
        grabarbtn.setAttribute("onclick", "Product.grabar()");
    }

    static borrar() {
        //Traer id de div seleccionado para borrar
        //Borrar id
    }

    static grabar() {
        let cliente = {};
        cliente.productos = [];
        let lospanelesproductos = document.querySelectorAll('#productPnl article');
        for (let i = 0; i < lospanelesproductos.length; i++) {
            let losinputesproductos = lospanelesproductos[i].querySelectorAll('input');
            let unproducto = {};
            for (let j = 0; j < losinputesproductos.length; j++) {
                unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
            }
            cliente.productos.push(unproducto);
        }
        let clienteStringJSON = JSON.stringify(cliente);
        console.log("Cliente: " + clienteStringJSON);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../reclamo");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }

        };
        xhr.send(clienteStringJSON);
    }
}

Product.NUMERO_PRODUCTO_SEC = 2;
Product.ORDER_PRODUCT = 1;
Product.setOnclick();
let product_order = document.querySelector("#productOrder").innerHTML;
