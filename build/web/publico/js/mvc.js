class Mvc {
    static storageInit() {
        if (localStorage) {
            Mvc.setOnclic();
            Mvc.getDatalist("verificacion", "verificacion", "#inputSelectVerificacion", " `<input list='ellector' name='ellector'/> <datalist name='ver' id='ellector'> ${result.map( result =>  ` <option value='${result.tipo}'>${result.tipo}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("resultado", "resultados", "#inputSelectResultado", " `<input list='elseletor' name='elseletor'/> <datalist name='res' id='elseletor'> ${result.map( result =>  ` <option value='${result.resultado}'>${result.resultado}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("origen", "origen", "#inputSelectOrigen", " `<input list='elsel' name='elsel'/> <datalist name='ori' id='elsel'> ${result.map( result =>  ` <option value='${result.origen}'>${result.origen}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("cliente", "clientes", "#inputSelectCliente", " `<input list='elsector' name='elsector'/> <datalist name='clie' id='elsector'> ${result.map( result =>  ` <option value='${result.nombre}'>${result.nombre}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("producto", "productos", "#inputSelectProducto", " `<input list='id_prod' name='id_prod' data-value=''/> <datalist name='prot' id='id_prod'> ${result.map( result =>  ` <option data-value='${result.producto_id}' dato='${result.codigo_producto}' id='${result.producto_id}'>${result.codigo_producto}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("diseno", "disenos", "#inputSelectDiseno", " `<input list='id_diseno2' name='id_diseno2' data-value=''/> <datalist name='prot' id='id_diseno2'> ${result.map( result =>  ` <option data-value='${result.dis_id}' dato='${result.dis_nombre}' id='${result.dis_id}'>${result.dis_nombre}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("Proceso", "procesos", "#inputSelectProceso", " `<input list='id_proceso2' name='id_proceso2' data-value=''/> <datalist name='prot' id='id_proceso2'> ${result.map( result =>  ` <option data-value='${result.proc_id}' dato='${result.proc_nombre}' id='${result.proc_id}'>${result.proc_nombre}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("FallaMaterial", "fallaMateriales", "#inputSelectFallaMat", " `<input list='id_falla_mate2' name='id_falla_mate2' data-value=''/> <datalist name='prot' id='id_falla_mate2'> ${result.map( result =>  ` <option data-value='${result.falla_mat_id}' dato='${result.falla_mat_nombre}' id='${result.falla_mat_id}'>${result.falla_mat_nombre}</option> ` ).join('') } </datalist> `; ");
            Mvc.getDatalist("Proveedor", "proveedores", "#inputSelectProveedor", " `<input list='id_proveedor2' name='id_proveedor2' data-value=''/> <datalist name='prot' id='id_proveedor2'> ${result.map( result =>  ` <option data-value='${result.prov_id}' dato='${result.prov_nombre}' id='${result.prov_id}'>${result.prov_nombre}</option> ` ).join('') } </datalist> `; ");
        }
    }

    static view(key) {
        let data = localStorage.getItem(key);
        let dataParse = JSON.parse(data);
        document.querySelector("#pnl_result").innerHTML = data;
        document.querySelector("#nombre_show").innerHTML = dataParse.name;
        document.querySelector("#edad_show").innerHTML = dataParse.age;
        document.querySelector("#telefono_show").innerHTML = dataParse.tel;
    }

    static getCookie(cname) {
        let name = cname + "=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                //return parseInt( c.substring(name.length, c.length));
                return c.substring(name.length, c.length);
            }
        }
        return "1";
    }

    static save(key) {

        let reclamo = {};

        Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        reclamo.encabezado = {};
        reclamo.encabezado.cliente = document.querySelector("#cliente_id").value;
        reclamo.encabezado.cliente_nombre = document.querySelector("input[name='elsector']").value;
        reclamo.encabezado.fecha_emision = document.querySelector("#fecha_emision").value;
        reclamo.encabezado.fecha_recepcion = document.querySelector("#fecha_recepcion").value;
        reclamo.encabezado.user = this.getCookie("userId");

        reclamo.productos = [];
        let lospanelesproductos = document.querySelectorAll('#productPnl article');
        for (let i = 0; i < lospanelesproductos.length; i++) {
            console.log("#producto_id" + "_" + i);
            if (i > 0) {
                Dataselect.itemSelected("verificacion", "#" + (lospanelesproductos[i].id) + " #inputSelectVerificacion input", "#" + (lospanelesproductos[i].id) + " #verif_id" + "_" + (i + 1), "id");
                Dataselect.itemSelected("productos", "#" + (lospanelesproductos[i].id) + " #inputSelectProducto input", "#" + (lospanelesproductos[i].id) + " #producto_id" + "_" + (i + 1), "id_producto");
                Dataselect.itemSelected("origen", "#" + (lospanelesproductos[i].id) + " #inputSelectOrigen input", "#" + (lospanelesproductos[i].id) + " #orgigen_id" + "_" + (i + 1), "id_origen");
                Dataselect.itemSelected("resultados", "#" + (lospanelesproductos[i].id) + " #inputSelectResultado input", "#" + (lospanelesproductos[i].id) + " #resultado" + "_" + (i + 1), "id");
                Dataselect.itemSelected("disenos", "#" + (lospanelesproductos[i].id) + " #inputSelectDiseno input", "#" + (lospanelesproductos[i].id) + " #id_diseno" + "_" + (i + 1), "dis_id");
                Dataselect.itemSelected("procesos", "#" + (lospanelesproductos[i].id) + " #inputSelectProceso input", "#" + (lospanelesproductos[i].id) + " #id_proceso" + "_" + (i + 1), "proc_id");
                Dataselect.itemSelected("fallaMateriales", "#" + (lospanelesproductos[i].id) + " #inputSelectFallaMat input", "#" + (lospanelesproductos[i].id) + " #id_falla_mate" + "_" + (i + 1), "falla_mat_id");
                Dataselect.itemSelected("proveedores", "#" + (lospanelesproductos[i].id) + " #inputSelectProveedor input", "#" + (lospanelesproductos[i].id) + " #id_proveedor" + "_" + (i + 1), "prov_id");
            } else {
                Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
                Dataselect.itemSelected("verificacion", "#inputSelectVerificacion input", "#verif_id", "id");
                Dataselect.itemSelected("origen", "#inputSelectOrigen input", "#orgigen_id", "id_origen");
                Dataselect.itemSelected("resultados", "#inputSelectResultado input", "#resultado", "id");
                Dataselect.itemSelected("disenos", "#inputSelectDiseno input", "#id_diseno", "dis_id");
                Dataselect.itemSelected("procesos", "#inputSelectProceso input", "#id_proceso", "proc_id");
                Dataselect.itemSelected("fallaMateriales", "#inputSelectFallaMat input", "#id_falla_mate", "falla_mat_id");
                Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
            }


            let losinputesproductos = lospanelesproductos[i].querySelectorAll('input, textarea');
            let observa = lospanelesproductos[i].querySelectorAll('textarea');
            let unproducto = {};
            for (let j = 0; j < losinputesproductos.length; j++) {
                unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
                // unproducto[observa[j].getAttribute('name')] = observa[j].value;
            }
            reclamo.productos.push(unproducto);
        }

        let reclamoStringJSON = JSON.stringify(reclamo);

        localStorage.setItem("json", JSON.stringify(reclamo));
        console.log("Guardar reclamo");

        //humane.log("Reclamo guardado");
        //console.log("POST humane");
        Mvc.reclamosList();

        //Validator.reclamoPut();

        Mvc.sendToServer("json");


    }

    static sendToServer(key) {
        SpinnerModal.open();
        let reclamo = JSON.stringify(localStorage.getItem(key));
        let reclamoParse = JSON.parse(reclamo);
        //let reclamoStr = JSON.stringify(reclamo);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../reclamo");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    SpinnerModal.close();
                    console.log("Respuesta server por reclamo: " + xhr.responseText);
                    let respServer = JSON.parse(xhr.responseText);
                    console.log("Respuesta server por reclamo: " + respServer);
                    console.log("Respuesta server tipo: " + respServer.tipo);

                    Notificaciones.ReclamoGuardar(respServer);
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send(reclamoParse);
    }

    static reclamosList() {
        let reclamosArray = [];
        //reclamos = JSON.parse(localStorage.getItem("json"));
        //
        reclamosArray.push(localStorage.getItem("json"));
        console.log("reclamos antes" + reclamosArray);
        if (localStorage.getItem("reclamos") === null) {
            console.log("No existe key");
        } else {
            console.log("existe key");


        }
        console.log("reclamos despues" + reclamosArray);
        localStorage.setItem("reclamos", reclamosArray);
        console.log(localStorage.getItem("reclamos"));
        console.log(reclamosArray.length);

    }

    static getDatalist(servlet, setItemLocalStorage, inputID, template) {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "../" + servlet);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito
                    let resultadosServer = JSON.parse(xhr.responseText);
                    localStorage.setItem(setItemLocalStorage, JSON.stringify(resultadosServer));
                    Mvc.getModelDatalist(setItemLocalStorage, template, inputID);
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
    }

    static addProduct() {
        let elem = document.querySelector("#grabar");
        elem.setAttribute("onclick", "Mvc.abm_producto();");
    }
    static abm_producto() {
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
        let producto = {};
        producto.id = document.querySelector("#producto_id").value;
        let productosJSON = JSON.stringify(localStorage.getItem("productos"));
        console.log("ID producto: " + producto.id);

// ARMA JSON, EMPROLIJAR
///////////////////////////////
        let key = "";
        let object = JSON.parse(localStorage.getItem("productos"));
        let results = [];
        let toSearch = producto.id;
        for (let i = 0; i < object.length; i++) {
            for (key in object[i]) {
                if (object[i][key].indexOf(toSearch) != -1) {
                    results.push(object[i]);
                }
            }
        }
        console.log(JSON.stringify(results[0]["codigo_producto"]).replace(/\"/g, ""));

        producto.nombre = JSON.stringify(results[0]["codigo_producto"]).replace(/\"/g, "");
        console.log(producto);
        producto = JSON.stringify(producto);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../producto");
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
        xhr.send(producto);
/////////////////////////////////
//////////////////////////////////


    }

    static getModelDatalist(getItemLocalStorage, template, inputID) {
        let result = JSON.parse(localStorage.getItem(getItemLocalStorage));
        let textoPlantilla = template;
        //let textoPlantilla = " `<input list='ellector' name='ellector'/> <datalist name='ver' id='ellector'> ${result.map( result =>  ` <option value='${result.tipo}'>${result.tipo}</option> ` ).join('') } </datalist> `; ";
        document.querySelector(inputID).innerHTML = eval(textoPlantilla);
        //console.log("Plantilla: " + getItemLocalStorage);

    }

    static saveDraft() {
        let reclamo = {};

        //Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        reclamo.encabezado = {};
        reclamo.encabezado.cliente = document.querySelector("#cliente_id").value;
        reclamo.encabezado.cliente_nombre = document.querySelector("input[name='elsector']").value;
        reclamo.encabezado.fecha_emision = document.querySelector("#fecha_emision").value;
        reclamo.encabezado.fecha_recepcion = document.querySelector("#fecha_recepcion").value;

        reclamo.productos = [];
        let lospanelesproductos = document.querySelectorAll('#productPnl article');
        for (let i = 0; i < lospanelesproductos.length; i++) {
            console.log("#producto_id" + "_" + i);
            if (i > 0) {

            } else {

            }


            let losinputesproductos = lospanelesproductos[i].querySelectorAll('input, textarea');
            let observa = lospanelesproductos[i].querySelectorAll('textarea');
            let unproducto = {};
            for (let j = 0; j < losinputesproductos.length; j++) {
                unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
                // unproducto[observa[j].getAttribute('name')] = observa[j].value;
            }
            reclamo.productos.push(unproducto);
        }

        let reclamoStringJSON = JSON.stringify(reclamo);

        localStorage.setItem("ReclamoDraft", JSON.stringify(reclamo));
        console.log("Guardar reclamo");
//        let reclamo = {};
//        reclamo.productos = [];
//        let lospanelesproductos = document.querySelectorAll('#productPnl article');
//        let losinputesproductos;
//        let observa;
//        let unproducto = {};
//        for (let i = 0; i < lospanelesproductos.length; i++) {
//            console.log("#producto_id" + "_" + i);
//            losinputesproductos = lospanelesproductos[i].querySelectorAll('input, textarea');
//            //observa = lospanelesproductos[i].querySelectorAll('textarea');
//            for (let j = 0; j < losinputesproductos.length; j++) {
//                unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
//                // unproducto[observa[j].getAttribute('name')] = observa[j].value;
//            }
//            reclamo.productos.push(unproducto);
//        }
//        let aux;
//        for (let i = 0; i < lospanelesproductos.length; i++) {
//            for (let k = 0; k < reclamo.productos.length; k++) {
//                //document.querySelector(reclamo.productos)
//                console.log(Object.keys(reclamo.productos[k]));
//                aux = Object.keys(reclamo.productos[k]);
//                console.log(aux);
//            }
//        }
//        Mvc.storageInit();
//        Mvc.cargarDatosBorrar(lospanelesproductos, losinputesproductos, reclamo);
//        Notificaciones.refrescarDatosOK();
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
                        let reclamoDraft = JSON.parse(localStorage.getItem("ReclamoDraft"));
                        console.log(reclamoDraft.productos.length);
                        for (let i = 1; i < reclamoDraft.productos.length; i++) {
                            Product.agregar();
                        }


                        document.querySelector("input[name='elsector']").value = reclamoDraft.encabezado.cliente_nombre;
                        document.querySelector("#fecha_emision").value = reclamoDraft.encabezado.fecha_emision;
                        document.querySelector("#fecha_recepcion").value = reclamoDraft.encabezado.fecha_recepcion;
                        for (let i = 0; i < reclamoDraft.productos.length; i++) {
                            console.log("#producto_id" + "_" + i);
                            if (i > 0) {
                                // Dataselect.itemSelected("verificacion", "#" + (lospanelesproductos[i].id) + " #inputSelectVerificacion input", "#" + (lospanelesproductos[i].id) + " #verif_id" + "_" + (i + 1), "id");
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectProducto input[name='id_prod']").value = reclamoDraft.productos[i].id_prod;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectOrigen input[name='elsel']").value = reclamoDraft.productos[i].elsel;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectProveedor input[name='id_proveedor2']").value = reclamoDraft.productos[i].id_proveedor2;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='cantidad_total']").value = reclamoDraft.productos[i].cantidad_total;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='lote']").value = reclamoDraft.productos[i].lote;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='Motivo']").value = reclamoDraft.productos[i].Motivo;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='fundados']").value = reclamoDraft.productos[i].fundados;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='infundados']").value = reclamoDraft.productos[i].infundados;
                                document.querySelector("#productItem_" + (i + 1) + " input[name='cant_tema_comercial']").value = reclamoDraft.productos[i].cant_tema_comercial;
                                document.querySelector("#productItem_" + (i + 1) + " #observacion").value = reclamoDraft.productos[i].observacion;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectVerificacion input[name='ellector']").value = reclamoDraft.productos[i].ellector;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectResultado input[name='elseletor']").value = reclamoDraft.productos[i].elseletor;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectDiseno input[name='id_diseno2']").value = reclamoDraft.productos[i].id_diseno2;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectProceso input[name='id_proceso2']").value = reclamoDraft.productos[i].id_proceso2;
                                document.querySelector("#productItem_" + (i + 1) + " #inputSelectFallaMat input[name='id_falla_mate2']").value = reclamoDraft.productos[i].id_falla_mate2;



                            } else {
                                document.querySelector("#productItem #inputSelectProveedor input[name='id_proveedor2']").value = reclamoDraft.productos[i].id_proveedor2;
                                document.querySelector("#productItem #inputSelectProducto input[name='id_prod']").value = reclamoDraft.productos[i].id_prod;
                                document.querySelector("#productItem #inputSelectOrigen input[name='elsel']").value = reclamoDraft.productos[i].elsel;
                                document.querySelector("#productItem input[name='cantidad_total']").value = reclamoDraft.productos[i].cantidad_total;
                                document.querySelector("#productItem input[name='lote']").value = reclamoDraft.productos[i].lote;
                                document.querySelector("#productItem input[name='Motivo']").value = reclamoDraft.productos[i].Motivo;
                                document.querySelector("#productItem input[name='fundados']").value = reclamoDraft.productos[i].fundados;
                                document.querySelector("#productItem input[name='infundados']").value = reclamoDraft.productos[i].infundados;
                                document.querySelector("#productItem input[name='cant_tema_comercial']").value = reclamoDraft.productos[i].cant_tema_comercial;
                                document.querySelector("#productItem #observacion").value = reclamoDraft.productos[i].observacion;
                                document.querySelector("#productItem #inputSelectVerificacion input[name='ellector']").value = reclamoDraft.productos[i].ellector;
                                document.querySelector("#productItem #inputSelectResultado input[name='elseletor']").value = reclamoDraft.productos[i].elseletor;
                                document.querySelector("#productItem #inputSelectDiseno input[name='id_diseno2']").value = reclamoDraft.productos[i].id_diseno2;
                                document.querySelector("#productItem #inputSelectProceso input[name='id_proceso2']").value = reclamoDraft.productos[i].id_proceso2;
                                document.querySelector("#productItem #inputSelectFallaMat input[name='id_falla_mate2']").value = reclamoDraft.productos[i].id_falla_mate2;



                            }
                        }
                        swal("Borrador de reclamo recuperado", {
                            icon: "success",
                        });
                    } else {
                        //swal("Your imaginary file is safe!");
                    }
                });

    }

    static cargarDatosBorrar(lospanelesproductos, losinputesproductos, reclamo) {

        let unproducto = {};
        let lospanelesproductos2 = document.querySelectorAll('#productPnl article');

        let aux;
        for (let i = 0; i < lospanelesproductos.length; i++) {
            for (let k = 0; k < reclamo.productos.length; k++) {
                //document.querySelector(reclamo.productos)
                console.log(Object.keys(reclamo.productos[k]));
                aux = Object.keys(reclamo.productos[k]);
                console.log(aux);

            }
        }


        for (let i = 0; i < lospanelesproductos2.length; i++) {
            console.log("#producto_id" + "_" + i);

            let losinputesproductos2 = lospanelesproductos2[i].querySelectorAll('input, textarea');
            //let observa = lospanelesproductos2[i].querySelectorAll('textarea');
            let unproducto2 = {};
            for (let j = 0; j < losinputesproductos.length; j++) {
                //unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;

                console.log("Inicio");
                console.log(unproducto[losinputesproductos[j].getAttribute('name')]);
                console.log(losinputesproductos[j]);
                console.log("Fin");
                console.log("aux : " + aux[j])
                if (aux[j] == "observacion") {

                } else {
                    //document.querySelector("input[name='"+aux[j]+"']").value = "NADA";
                    document.querySelector("input[name='" + aux[j] + "']").value = "NADA";
                    losinputesproductos[j].value = unproducto[losinputesproductos[j].getAttribute('name')];
                }

                // unproducto[observa[j].getAttribute('name')] = observa[j].value;
            }
            //reclamo.productos.push(unproducto2);

        }
        document.querySelector("input[name='id_diseno2']").value = "s";


    }

    static setOnclic() {
        let elem = document.querySelector("#grabar");
        elem.setAttribute("onclick", "Mvc.save('json');");

        let saveDraft = document.querySelector("#saveDraft");
        saveDraft.setAttribute("onclick", "Mvc.saveDraft()");

        let recoverDraft = document.querySelector("#recoverDraft");
        recoverDraft.setAttribute("onclick", "Mvc.recoverDraft()");
    }

}

//Mvc.storageInit();