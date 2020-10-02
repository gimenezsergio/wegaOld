class PrecargaProductos {
    static saveDraft() {
        let precarga = {};

        //Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        precarga.encabezado = {};
        precarga.encabezado.proveedor = document.querySelector("#id_proveedor").value;
        precarga.encabezado.proveedor_nombre = document.querySelector("input[name='id_proveedor2']").value;
        precarga.encabezado.fecha_manifiesto = document.querySelector("#fecha_manifiesto").value;
        precarga.encabezado.arribo = document.querySelector("#id_arribo").value;
        precarga.encabezado.arribo_nombre = document.querySelector("input[name='id_arribo2']").value;
        precarga.encabezado.codigo_manifesto = document.querySelector("#codigo_manifiesto").value;


        precarga.productos = [];
        let lospanelesproductos = document.querySelectorAll('#itemsContenedor article');
        for (let i = 0; i < lospanelesproductos.length; i++) {
            console.log("#producto_id" + "_" + i);
            let losinputesproductos = lospanelesproductos[i].querySelectorAll('input');
            // let observa = lospanelesproductos[i].querySelectorAll('textarea');
            let unproducto = {};
            for (let j = 0; j < losinputesproductos.length; j++) {
                unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
                // unproducto[observa[j].getAttribute('name')] = observa[j].value;
            }
            precarga.productos.push(unproducto);
        }

        let reclamoStringJSON = JSON.stringify(precarga);

        localStorage.setItem("PrecargaDraft", JSON.stringify(precarga));
        console.log("Guardar reclamo");

        Notificaciones.draftSaved();
    }

    static recoverDraft() {
        swal({
            title: "Recuperar borrador",
            text: "¿Está seguro de recuperar un borrador? Se perderán los datos actuales del formulario.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        document.querySelector("#recoverDraft").disabled = true;
                        let reclamoDraft = JSON.parse(localStorage.getItem("PrecargaDraft"));
                        console.log(reclamoDraft.productos.length);
                        for (let i = 1; i < reclamoDraft.productos.length; i++) {
                            PrecargaProductos.agregar();
                            Precarga.agregarItem();
                        }


                        document.querySelector("input[name='id_proveedor2']").value = reclamoDraft.encabezado.proveedor_nombre;
                        document.querySelector("#fecha_manifiesto").value = reclamoDraft.encabezado.fecha_manifiesto;
                        document.querySelector("input[name='id_arribo2']").value = reclamoDraft.encabezado.arribo_nombre;
                        document.querySelector("#codigo_manifiesto").value = reclamoDraft.encabezado.codigo_manifesto;
                        
                        for (let i = 0; i < reclamoDraft.productos.length; i++) {
                            console.log("#producto_id" + "_" + i);
                            if (i > 0) {
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectProducto input[name='id_prod']").value = reclamoDraft.productos[i].id_prod;                                
                                document.querySelector("#productItem_" + (i + 1) + " #cant1_" + (i + 1)).value = reclamoDraft.productos[i].cant1;
                                document.querySelector("#productItem_" + (i + 1) + " #muestras1_" + (i + 1)).value = reclamoDraft.productos[i].muestras1;
                                document.querySelector("#productItem_" + (i + 1) + " #obs1_" + (i + 1)).value = reclamoDraft.productos[i].obs;


                            } else {
                                document.querySelector("#productItem #inputSelectProducto input[name='id_prod']").value = reclamoDraft.productos[i].id_prod;
                                document.querySelector("#productItem #cant1").value = reclamoDraft.productos[i].cant1;
                                document.querySelector("#productItem #muestras1").value = reclamoDraft.productos[i].muestras1;
                                document.querySelector("#productItem #obs1").value = reclamoDraft.productos[i].obs;

                            }
                        }
                        Precarga.NUMERO_PRODUCTO_SEC = PrecargaProductos.NUMERO_PRODUCTO_SEC;
                        swal("Borrador de precarga recuperado", {
                            icon: "success",
                        });
                    } else {
                        //swal("Your imaginary file is safe!");
                    }
                });

    }
    
    static agregar() {
        let itm = document.querySelector("#productItem");
        let cln = itm.cloneNode(true);
        let producto = {};
        cln.id = "productItem" + "_" + PrecargaProductos.NUMERO_PRODUCTO_SEC;
        document.querySelector("#itemsContenedor").appendChild(cln);
        let losInputs = cln.querySelectorAll("input");
        for (let i = 0; i < losInputs.length; i++) {
            losInputs[i].value = "";
            losInputs[i].id = losInputs[i].id + "_" + PrecargaProductos.NUMERO_PRODUCTO_SEC;
            console.log(losInputs[i].id);
        }
        
        let observacion = cln.querySelectorAll("textarea");
        for (let j = 0; j < observacion.length; j++){
            observacion[j].value = "";
        }
        PrecargaProductos.NumOrderProduct( cln );
        PrecargaProductos.NUMERO_PRODUCTO_SEC++;

    }
    
    static NumOrderProduct( cln ){
        let numOrder = PrecargaProductos.ORDER_PRODUCT;
        let IdProductItem = "#" + cln.id;
        document.querySelector( IdProductItem + " span#productOrder" );
        document.querySelector( IdProductItem + " span#productOrder" ).id 
                = document.querySelector( IdProductItem + " span#productOrder" ).id + PrecargaProductos.NUMERO_PRODUCTO_SEC;        
        document.querySelector( "span#productOrder" + PrecargaProductos.NUMERO_PRODUCTO_SEC ).innerHTML 
                = PrecargaProductos.NUMERO_PRODUCTO_SEC;
    }
    
    static setOnclic() {        

        let saveDraft = document.querySelector("#saveDraft");
        saveDraft.setAttribute("onclick", "PrecargaProductos.saveDraft()");

        let recoverDraft = document.querySelector("#recoverDraft");
        recoverDraft.setAttribute("onclick", "PrecargaProductos.recoverDraft()");
    }
}
//Producto.hideResult();
PrecargaProductos.NUMERO_PRODUCTO_SEC = 2;
PrecargaProductos.ORDER_PRODUCT = 1;
PrecargaProductos.setOnclic();