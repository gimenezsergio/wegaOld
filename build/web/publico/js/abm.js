class AbmConfiguraciones {

    static buttonCliente() {
        let btnPut = document.querySelector("#actualizar");
        btnPut.setAttribute("onclick", "AbmConfiguraciones.abmClientePut();");
        let btnBorrar = document.querySelector("#borrar");
        btnBorrar.setAttribute("onclick", "AbmConfiguraciones.abmClienteDelete();");
        let btnInsert = document.querySelector("#agregar");
        btnInsert.setAttribute("onclick", "AbmConfiguraciones.abmClienteInsert();");
    }

    static productoAbmBtn() {
        let PutBtn = document.querySelector("#actualizar");
        PutBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoPut();");
        let BorrarBtn = document.querySelector("#borrar");
        BorrarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoDelete();");
        let InsertBtn = document.querySelector("#agregar");
        InsertBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductInsert();");
    }
    
    static buttonFamilia() {
        let insertarBtn = document.querySelector("#insertar");
        insertarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoByFamilyInsertar();");
        let borrarBtn = document.querySelector("#borrar");
        borrarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoByFamilyBorrar();");
        let actualizarBtn = document.querySelector("#actualizar");
        actualizarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoByFamilyActualizar();");
    }

    static productoByFamilyAbmBtn() {
        let consultarBtn = document.querySelector("#consultar");
        consultarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoByFamilyConsult();");
        let asignarBtn = document.querySelector("#asignar");
        asignarBtn.setAttribute("onclick", "AbmConfiguraciones.abmProductoByFamilyAsignar();");
    }

    static proveedorBtn() {
        let PutBtn = document.querySelector("#actualizar");
        PutBtn.setAttribute("onclick", "AbmConfiguraciones.proveedorPut();");
        let BorrarBtn = document.querySelector("#borrar");
        BorrarBtn.setAttribute("onclick", "AbmConfiguraciones.proveedorDelete();");
        let InsertBtn = document.querySelector("#agregar");
        InsertBtn.setAttribute("onclick", "AbmConfiguraciones.abmProveedorInsert();");
    }

    static buttonDiseno() {
        let PutBtn = document.querySelector("#actualizar");
        PutBtn.setAttribute("onclick", "AbmConfiguraciones.disenoPut();");
        let BorrarBtn = document.querySelector("#borrar");
        BorrarBtn.setAttribute("onclick", "AbmConfiguraciones.disenoDelete();");
        let InsertBtn = document.querySelector("#agregar");
        InsertBtn.setAttribute("onclick", "AbmConfiguraciones.abmDisenoInsert();");
    }

    static buttonProceso() {
        let PutBtn = document.querySelector("#actualizar");
        PutBtn.setAttribute("onclick", "AbmConfiguraciones.procesoPut();");
        let BorrarBtn = document.querySelector("#borrar");
        BorrarBtn.setAttribute("onclick", "AbmConfiguraciones.procesoDelete();");
        let InsertBtn = document.querySelector("#agregar");
        InsertBtn.setAttribute("onclick", "AbmConfiguraciones.abmProcesoInsert();");
    }

    static buttonFallaMateriales() {
        let PutBtn = document.querySelector("#actualizar");
        PutBtn.setAttribute("onclick", "AbmConfiguraciones.fallaMatPut();");
        let BorrarBtn = document.querySelector("#borrar");
        BorrarBtn.setAttribute("onclick", "AbmConfiguraciones.fallaMatDelete();");
        let InsertBtn = document.querySelector("#agregar");
        InsertBtn.setAttribute("onclick", "AbmConfiguraciones.abmFallaMatInsert();");
    }
    
    
    //SUBFAMILIAS
    static cargarSubfamilia() {
        console.log("subfamilia");
        Dataselect.itemSelected("familia", "#inputSelectFamilia input", "#familia_id", "id");
        let templateSubFamilia = " `<input list='subfamilia_id2' name='subfamilia_id2' data-value=''/> <datalist name='prot2' id='subfamilia_id2'> ${result.map( result =>  ` <option data-value='${result.id}' dato='${result.nombre}' id='${result.id}'>${result.nombre}</option> ` ).join('') } </datalist> `; ";
        let familiaId = document.querySelector("#familia_id").value;
        Mvc.getDatalist("subFamilia?&q=" + familiaId, "subfamilia", "#inputSelectSubFamilia", templateSubFamilia);
        //Dataselect.itemSelected("subfamilia", "#inputSelectSubFamilia input", "#subfamilia_id", "id");
    }
    

    //FAMILIAS
    static abmProductoByFamilyInsertar(){
        console.log("insertar");
        let productoFamilia = {};
        productoFamilia.nombre = document.querySelector("#newNameFamiliaInsert").value;
        
        if (productoFamilia.nombre === "") {
            Notificaciones.abmFamiliaNewNull();
        } else {
            productoFamilia = JSON.stringify(productoFamilia);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../Familia");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmFamiliaOK(xhr.responseText);
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(productoFamilia);
            console.log("Objeto enviado: " + productoFamilia);
            console.log("INSERT {OK}");
        }
    }
    
    static abmProductoByFamilyBorrar(){
        console.log("borrar");
        Dataselect.itemSelected("familia", "#inputSelectFamilia input", "#familia_id", "id");
        let familia = {};
        familia.id = document.querySelector("#familia_id").value;
        if (familia.id === "") {
            Notificaciones.abmFamiliaNewNull();
        } else {
            familia = JSON.stringify(familia);
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../Familia");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmFamiliaOK("Familia borrada");
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(familia);
            console.log("Objeto enviado: " + familia);
            console.log("BORRAR {OK}");
        }
        
    }
    
    static abmProductoByFamilyActualizar(){
        console.log("actualizar");
        Dataselect.itemSelected("familia", "#inputSelectFamilia input", "#familia_id", "id");
        let familia = {};
        familia.nombre = document.querySelector("#newNameFamilia").value;
        familia.id = document.querySelector("#familia_id").value;
        if (familia.id === "") {
            Notificaciones.abmFamiliaNewNull();
        } else {
            familia = JSON.stringify(familia);
            var xhr = new XMLHttpRequest();
            xhr.open("PUT", "../Familia");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmFamiliaOK("Familia actualizada");
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(familia);
            console.log("Objeto enviado: " + familia);
            console.log("PUT {OK}");
        }
    }

    static abmProductoByFamilyConsult() {


        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");

        let producto = {};
        producto.nombre = document.querySelector("input[name='id_prod']").value;
        producto.id_producto = document.querySelector("#producto_id").value;
        console.log("ID " + producto.id_producto + " Nombre " + producto.nombre);
        console.log(producto);
        if (producto.nombre === "") {
            Notificaciones.abmProductoSelectNull();
        } else {
            //producto = JSON.stringify(producto);
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "../producto?&q=" + producto.id_producto);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        // caso de exito
                        let resultadosServer = JSON.parse(xhr.responseText);
                        console.log("repuesta " + xhr.responseText);
                        //NOTIFICACION
                        //Notificaciones.abmProductoOK(xhr.responseText);
                        document.querySelector("#FamiliaActual").value = resultadosServer[0].familia;
                        document.querySelector("#subFamiliaActual").value = resultadosServer[0].subFamilia;
                        

                    } else {
                        //caso de error
                    }
                } else {
                    //caso de error
                }
            };
            xhr.send();
        }
    }
    
    static abmProductoByFamilyAsignar() {
        console.log("asignar");
        Dataselect.itemSelected("familia", "#inputSelectFamilia input", "#familia_id", "id");
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
        Dataselect.itemSelected("subfamilia", "#inputSelectSubFamilia input", "#subfamilia_id", "id");
        let familia = {};
        familia.familia = document.querySelector("#familia_id").value;
        familia.id_producto = document.querySelector("#producto_id").value;
        familia.subFamilia = document.querySelector("#subfamilia_id").value;
        if (familia.id === "") {
            //Notificaciones.abmFallaMatNewNull();
        } else {
            familia = JSON.stringify(familia);
            var xhr = new XMLHttpRequest();
            xhr.open("PUT", "../producto");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    //Notificaciones.abmProductoFamiliaOK("Familia en producto actualizada");
                    Notificaciones.abmProductoFamilia(JSON.parse(xhr.responseText));
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(familia);
            console.log("Objeto enviado: " + familia);
            console.log("INSERT {OK}");
        }
    }

    //FALLA DE MATERIALES

    static abmFallaMatInsert() {
        console.log("insert falla material");
        let fallaMat = {};
        fallaMat.falla_mat_nombre = document.querySelector("#newNameFallaMtInsert").value;
        if (fallaMat.falla_mat_nombre === "") {
            Notificaciones.abmFallaMatNewNull();
        } else {
            fallaMat = JSON.stringify(fallaMat);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../FallaMaterial");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmFallaMatOK(xhr.responseText);
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(fallaMat);
            console.log("Objeto enviado: " + fallaMat);
            console.log("INSERT {OK}");
        }
    }

    static fallaMatDelete() {
        Dataselect.itemSelected("fallaMateriales", "#inputSelectFallaMat input", "#id_falla_mate", "falla_mat_id");
        let fallaMat = {};
        fallaMat.falla_mat_nombre = document.querySelector("input[name='id_falla_mate2']").value;
        fallaMat.falla_mat_id = document.querySelector("#id_falla_mate").value;
        console.log(fallaMat);
        if (fallaMat.falla_mat_nombre === "") {
            Notificaciones.abmFallaMatSelectNull()
        } else {
            fallaMat = JSON.stringify(fallaMat);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../FallaMaterial?&q=" + fallaMat);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmFallaMatOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }
    }
    static fallaMatPut() {
        Dataselect.itemSelected("fallaMateriales", "#inputSelectFallaMat input", "#id_falla_mate", "falla_mat_id");
        let fallaMat = {};
        fallaMat.nombreActual = document.querySelector("input[name='id_falla_mate2']").value;
        fallaMat.falla_mat_nombre = document.querySelector("#newNameFallaM").value;
        fallaMat.falla_mat_id = document.querySelector("#id_falla_mate").value;
        if (fallaMat.falla_mat_nombre === "" || fallaMat.nombreActual === "") {
            if (fallaMat.falla_mat_nombre === "") {
                Notificaciones.abmProveedorNewNull();
            }

            if (fallaMat.nombreActual === "") {
                Notificaciones.abmFallaMatSelectNull();
            }

        } else {
            fallaMat = JSON.stringify(fallaMat);
            console.log(fallaMat);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../FallaMaterial");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmFallaMatOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(fallaMat);
        }
    }

    //PROCESO

    static abmProcesoInsert() {
        console.log("insert Proveedor");
        let proceso = {};
        proceso.proc_nombre = document.querySelector("#newNameProcesoInsert").value;
        if (proceso.proc_nombre === "") {
            Notificaciones.abmDisenoNewNull();
        } else {
            proceso = JSON.stringify(proceso);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../Proceso");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmProcesoOK(xhr.responseText);
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(proceso);
            console.log("Objeto enviado: " + proceso);
            console.log("INSERT {OK}");
        }
    }

    static procesoDelete() {
        Dataselect.itemSelected("procesos", "#inputSelectProceso input", "#id_proceso", "proc_id");
        let proceso = {};
        proceso.proc_nombre = document.querySelector("input[name='id_proceso2']").value;
        proceso.proc_id = document.querySelector("#id_proceso").value;
        console.log(proceso);
        if (proceso.proc_nombre === "") {
            Notificaciones.abmProcesoSelectNull()
        } else {
            proceso = JSON.stringify(proceso);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../Proceso?&q=" + proceso);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmProcesoOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }
    }

    static procesoPut() {
        Dataselect.itemSelected("procesos", "#inputSelectProceso input", "#id_proceso", "proc_id");
        let proceso = {};
        proceso.nombreActual = document.querySelector("input[name='id_proceso2']").value;
        proceso.proc_nombre = document.querySelector("#newNameProceso").value;
        proceso.proc_id = document.querySelector("#id_proceso").value;
        if (proceso.proc_nombre === "" || proceso.nombreActual === "") {
            if (proceso.proc_nombre === "") {
                Notificaciones.abmProveedorNewNull();
            }

            if (proceso.nombreActual === "") {
                Notificaciones.abmProcesoSelectNull();
            }

        } else {
            proceso = JSON.stringify(proceso);
            console.log(proceso);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../Proceso");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmProcesoOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(proceso);
        }
    }

    //DISENO

    static abmDisenoInsert() {
        console.log("insert Proveedor");
        let diseno = {};
        diseno.dis_nombre = document.querySelector("#newNameClientInsert").value;
        if (diseno.dis_nombre === "") {
            Notificaciones.abmDisenoNewNull();
        } else {
            diseno = JSON.stringify(diseno);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../diseno");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmDisenoOK(xhr.responseText);
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(diseno);
            console.log("Objeto enviado: " + diseno);
            console.log("INSERT {OK}");
        }
    }

    static disenoDelete() {
        Dataselect.itemSelected("disenos", "#inputSelectDiseno input", "#id_diseno", "dis_id");
        let diseno = {};
        diseno.dis_nombre = document.querySelector("input[name='id_diseno2']").value;
        diseno.dis_id = document.querySelector("#id_diseno").value;

        if (diseno.dis_nombre === "") {
            Notificaciones.abmDisenoSelectNull()
        } else {
            diseno = JSON.stringify(diseno);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../diseno?&q=" + diseno);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmDisenoOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }
    }

    static disenoPut() {
        Dataselect.itemSelected("disenos", "#inputSelectDiseno input", "#id_diseno", "dis_id");
        let diseno = {};
        diseno.nombreActual = document.querySelector("input[name='id_diseno2']").value;
        diseno.dis_nombre = document.querySelector("#newNameDiseno").value;
        diseno.dis_id = document.querySelector("#id_diseno").value;
        if (diseno.dis_nombre === "" || diseno.nombreActual === "") {
            if (diseno.dis_nombre === "") {
                Notificaciones.abmProveedorNewNull();
            }

            if (diseno.nombreActual === "") {
                Notificaciones.abmDisenoSelectNull();
            }

        } else {
            diseno = JSON.stringify(diseno);
            console.log(diseno);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../diseno");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmDisenoOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(diseno);
        }
    }

    //PROVEEDORES

    static abmProveedorInsert() {
        console.log("insert Proveedor");
        let provee = {};
        provee.prov_nombre = document.querySelector("#newNameProveetInsert").value;
        if (provee.prov_nombre === "") {
            Notificaciones.abmDisenoNewNull();
        } else {
            provee = JSON.stringify(provee);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../Proveedor");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmProveedorOK(xhr.responseText);
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(provee);
            console.log("Objeto enviado: " + provee);
            console.log("INSERT {OK}");
        }
    }

    static proveedorDelete() {
        Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
        let provee = {};
        provee.prov_nombre = document.querySelector("input[name='id_proveedor2']").value;
        provee.prov_id = document.querySelector("#id_proveedor").value;

        if (provee.prov_nombre === "") {
            Notificaciones.abmProveedorSelectNull()
        } else {
            provee = JSON.stringify(provee);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../Proveedor?&q=" + provee);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmProveedorOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }

    }

    static proveedorPut() {
        Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
        let provee = {};
        provee.nombreActual = document.querySelector("input[name='id_proveedor2']").value;
        provee.prov_nombre = document.querySelector("#newNameProv").value;
        provee.prov_id = document.querySelector("#id_proveedor").value;
        console.log("ID " + provee.prov_id + " Nombre " + provee.nombre);
        console.log(provee);
        console.log("provee nuevo es: " + provee.prov_nombre);
        if (provee.prov_nombre === "" || provee.nombreActual === "") {
            if (provee.prov_nombre === "") {
                Notificaciones.abmProveedorNewNull();
            }

            if (provee.nombreActual === "") {
                Notificaciones.abmProductoSelectNull();
            }

        } else {
            provee = JSON.stringify(provee);
            console.log(provee);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../Proveedor");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmProveedorOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(provee);
        }
    }

    //PRODUCTOS

    static abmProductoDelete() {
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");

        let producto = {};
        producto.nombre = document.querySelector("input[name='id_prod']").value;
        producto.id_producto = document.querySelector("#producto_id").value;
        console.log("ID " + producto.id_producto + " Nombre " + producto.nombre);
        console.log(producto);
        if (producto.nombre === "") {
            Notificaciones.abmProductoSelectNull();
        } else {
            producto = JSON.stringify(producto);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../producto?&q=" + producto);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmProductoOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }

    }

    static abmProductoPut() {
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
        let producto = {};
        producto.nombreActual = document.querySelector("input[name='id_prod']").value;
        producto.codigo_producto = document.querySelector("#newNameProductoPut").value;
        producto.id_producto = document.querySelector("#producto_id").value;
        console.log("ID " + producto.id_cliente + " Nombre " + producto.nombre);
        console.log(producto);
        console.log("producto nuevo es: " + producto.codigo_producto);
        if (producto.codigo_producto === "" || producto.nombreActual === "") {
            if (producto.codigo_producto === "") {
                Notificaciones.abmProductoNewNull();
            }

            if (producto.nombreActual === "") {
                Notificaciones.abmProductoSelectNull();
            }

        } else {
            producto = JSON.stringify(producto);
            console.log(producto);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../producto");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmProductoOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(producto);
        }
    }

    static abmProductInsert() {
        Dataselect.itemSelected("familia", "#inputSelectFamilia input", "#familia_id", "id");
        
        Dataselect.itemSelected("subfamilia", "#inputSelectSubFamilia input", "#subfamilia_id", "id");
        
        
        
        console.log("insert Producto");
        let producto = {};
        producto.codigo_producto = document.querySelector("#newNameProducto").value;
        producto.familia = document.querySelector("#familia_id").value;
        producto.subFamilia = document.querySelector("#subfamilia_id").value;
        console.log(" Nombre " + producto.codigo_producto);
        console.log(producto);
        if (producto.codigo_producto === "") {
            Notificaciones.abmProductoNull();
        } else {
            console.log(" Nombre completo" + producto.codigo_producto);
            producto = JSON.stringify(producto);

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../producto");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    Notificaciones.abmProductoOk2(JSON.parse(xhr.responseText))
                    console.log("repuesta " + xhr.responseText);
                }
            };
            xhr.send(producto);
            console.log("Objeto enviado: " + producto);
            console.log("INSERT {OK}");
        }
    }

    //CLIENTES

    static abmClientePut() {
        Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        let cliente = {};
        cliente.nombreActual = document.querySelector("input[name='elsector']").value;
        cliente.nombre = document.querySelector("#newNameClient").value;
        cliente.id_cliente = document.querySelector("#cliente_id").value;
        console.log("ID " + cliente.id_cliente + " Nombre " + cliente.nombre);
        console.log(cliente);
        if (cliente.nombre === "" || cliente.nombreActual === "") {
            if (cliente.nombre === "") {
                Notificaciones.abmClienteNull();
            }

            if (cliente.nombreActual === "") {
                Notificaciones.abmClienteSelectNull();
            }

        } else {
            cliente = JSON.stringify(cliente);
            console.log(cliente);
            let xhr = new XMLHttpRequest();
            xhr.open("PUT", "../cliente");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        Notificaciones.abmClienteOK(xhr.responseText);
                    } else {
                    }
                } else {
                }
            };
            xhr.send(cliente);
        }
    }

    static abmClienteInsert() {
        console.log("insert ");
        Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        let cliente = {};
        cliente.nombre = document.querySelector("input[name='elsector']").value;
        cliente.nombre = document.querySelector("#newNameClientInsert").value;
        console.log(" Nombre " + cliente.nombre);
        console.log(cliente);
        if (cliente.nombre === "") {
            Notificaciones.abmClienteNull();
        } else {
            console.log(" Nombre completo" + cliente.nombre);
            cliente = JSON.stringify(cliente);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "../cliente");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmClienteOK(xhr.responseText);
                }
            };
            xhr.send(cliente);
            console.log("INSERT {OK}");
        }
    }

    static abmClienteDelete() {
        Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        let cliente = {};
        cliente.nombre = document.querySelector("input[name='elsector']").value;
        cliente.id_cliente = document.querySelector("#cliente_id").value;
        console.log("ID " + cliente.id_cliente + " Nombre " + cliente.nombre);
        console.log(cliente);
        if (cliente.nombre === "") {
            Notificaciones.abmClienteNull();
        } else {
            cliente = JSON.stringify(cliente);

            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "../cliente?&q=" + cliente);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    console.log("repuesta " + xhr.responseText);
                    Notificaciones.abmClienteOK(xhr.responseText);
                }
            };
            xhr.send(  );
            console.log("borrar");
        }
    }
}