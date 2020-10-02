class ReclamoBusqueda {
    static busquedaPorCliente() {
        if (document.querySelector("#optSelected").value == "clientes") {

            Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
            let xhr = new XMLHttpRequest();
            let busquedaJson = {};
            busquedaJson.id = document.querySelector("#cliente_id").value;
            busquedaJson.desde = document.querySelector("#desde").value;
            busquedaJson.hasta = document.querySelector("#hasta").value;
            busquedaJson.nombre = document.querySelector("input[name='elsector']").value;
            busquedaJson.tipo = document.querySelector("#optSelected").value;
            let busquedaJsonStr = JSON.stringify(busquedaJson);
            xhr.open("GET", "../reclamo?&id=" + busquedaJson.id + "&desde=" + busquedaJson.desde + "&hasta=" + busquedaJson.hasta + "&nombre=" + busquedaJson.nombre + "&tipo=" + busquedaJson.tipo);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        // caso de exito

                        let respuesta = JSON.parse(xhr.responseText);
                        if (respuesta.tipo == "warning") {
                            Notificaciones.ReclamoBusqueda(respuesta);
                        } else {
                            document.querySelector("#panelRsDictamen").innerHTML = "";
                            ReclamoBusqueda.datosObjeto(respuesta);
                            //ReclamoBusqueda.listReclamosDraw(respuesta);
                            localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                        }

                    } else {
                        //caso de error
                    }
                } else {
                    //caso de error
                }
            };
            xhr.send();
        } else if (document.querySelector("#optSelected").value == "proveedor") {
            console.log("Provedor selecionado");
            ReclamoBusqueda.proveedorInformes();
            ReclamoBusqueda.proveedorFundados();

        } else if (document.querySelector("#optSelected").value == "producto") {
            console.log("Producto selecionado");
            ReclamoBusqueda.productoInformes();
            ReclamoBusqueda.productoFundados();

        }

    }
    
    static productoFundados() {
        
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");

        let xhr = new XMLHttpRequest();
        let busquedaJson = {};
        busquedaJson.id = document.querySelector("#producto_id").value;
        busquedaJson.desde = document.querySelector("#desde").value;
        busquedaJson.hasta = document.querySelector("#hasta").value;
        busquedaJson.nombre = document.querySelector("input[name='id_prod']").value;
        busquedaJson.tipo = document.querySelector("#optSelected").value;
        let busquedaJsonStr = JSON.stringify(busquedaJson);
        xhr.open("GET", "../ReclamosFundados?&id=" + busquedaJson.id + "&desde=" + busquedaJson.desde + "&hasta=" + busquedaJson.hasta + "&nombre=" + busquedaJson.nombre + "&tipo=" + busquedaJson.tipo);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        //ReclamoBusqueda.datosObjeto(respuesta);
                        ReclamoBusqueda.drawFundados(respuesta);
                       // localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                    }

                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
        
    }
    
    static proveedorFundados() {
        
        Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");

        let xhr = new XMLHttpRequest();
        let busquedaJson = {};
        busquedaJson.id = document.querySelector("#id_proveedor").value;
        busquedaJson.desde = document.querySelector("#desde").value;
        busquedaJson.hasta = document.querySelector("#hasta").value;
        busquedaJson.nombre = document.querySelector("input[name='id_proveedor2']").value;
        busquedaJson.tipo = document.querySelector("#optSelected").value;
        let busquedaJsonStr = JSON.stringify(busquedaJson);
        xhr.open("GET", "../ReclamosFundados?&id=" + busquedaJson.id + "&desde=" + busquedaJson.desde + "&hasta=" + busquedaJson.hasta + "&nombre=" + busquedaJson.nombre + "&tipo=" + busquedaJson.tipo);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        //ReclamoBusqueda.datosObjeto(respuesta);
                        ReclamoBusqueda.drawFundados(respuesta);
                       // localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                    }

                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
        
    }
    
    static drawFundados(respServer) {
        let listadoFundados = respServer;
        const template = `
                    <h2>Informe de fundados</h2>
                    <table class="pure-table pure-table-bordered center">
                    <thead><th>Producto</th><th>Fundados</th><th>Proveedor</th></thead>
                    ${ listadoFundados.map(fundado =>
                    `
                    <tr class='persona_item'>
                    <td>${fundado.productoNmbre}</td>
                    <td>${fundado.fundados}</td>
                    <td>${fundado.proveedorNombre}</td>
                    </tr>
                    `
            ).join('')
                    }
                    </table>
                    `;
            //let templateReclamo = document.querySelector("#templateListReclamos").innerHTML;
            document.querySelector("#panelRsDictamen").innerHTML = template;
    }

    static proveedorInformes() {
        Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
        let xhr = new XMLHttpRequest();
        let busquedaJson = {};
        busquedaJson.id = document.querySelector("#id_proveedor").value;
        busquedaJson.desde = document.querySelector("#desde").value;
        busquedaJson.hasta = document.querySelector("#hasta").value;
        busquedaJson.nombre = document.querySelector("input[name='id_proveedor2']").value;
        busquedaJson.tipo = document.querySelector("#optSelected").value;
        let busquedaJsonStr = JSON.stringify(busquedaJson);
        xhr.open("GET", "../reclamo?&id=" + busquedaJson.id + "&desde=" + busquedaJson.desde + "&hasta=" + busquedaJson.hasta + "&nombre=" + busquedaJson.nombre + "&tipo=" + busquedaJson.tipo);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        ReclamoBusqueda.datosObjeto(respuesta);
                        //ReclamoBusqueda.listReclamosDraw(respuesta);
                        localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                    }

                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
    }

    static productoInformes() {
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");

        let xhr = new XMLHttpRequest();
        let busquedaJson = {};
        busquedaJson.id = document.querySelector("#producto_id").value;
        busquedaJson.desde = document.querySelector("#desde").value;
        busquedaJson.hasta = document.querySelector("#hasta").value;
        busquedaJson.nombre = document.querySelector("input[name='id_prod']").value;
        busquedaJson.tipo = document.querySelector("#optSelected").value;
        let busquedaJsonStr = JSON.stringify(busquedaJson);
        xhr.open("GET", "../reclamo?&id=" + busquedaJson.id + "&desde=" + busquedaJson.desde + "&hasta=" + busquedaJson.hasta + "&nombre=" + busquedaJson.nombre + "&tipo=" + busquedaJson.tipo);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        ReclamoBusqueda.datosObjeto(respuesta);
                        //ReclamoBusqueda.listReclamosDraw(respuesta);
                        localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                    }

                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
    }

    static checkAprobado(respuesta) {
        //console.log("APROBADO: " + respuesta.)

    }

    static busquedaUltimos() {
        ReclamoBusqueda.setButtonUltimos();
        let xhr = new XMLHttpRequest();
        xhr.open("GET", "../ReclamoUltimos");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        ReclamoBusqueda.datosObjeto(respuesta);
                        //ReclamoBusqueda.listReclamosDraw(respuesta);
                        localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
                    }

                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();
    }

    static datosObjeto(respuesta) {
        ReclamoBusqueda.listReclamosDraw(respuesta);

    }

    static encabezado(pId_reclamo) {
        let listadoReclamos = JSON.parse(localStorage.getItem("BusquedaRelcamos"));
        let resultado = listadoReclamos.filter(val => {
            return val.encabezado.id_reclamo = 2
        });
        console.log("ENCABEZADO : " + JSON.stringify(resultado));
        console.log("ENCABEZADO : " + JSON.stringify(resultado.encabezado));
    }

    static aprobarReclamo(idReclamo) {
        let xhr = new XMLHttpRequest();
        xhr.open("PUT", "../ReclamoUltimos");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log("Respuesta server por reclamo: " + xhr.responseText);
                    let respServer = JSON.parse(xhr.responseText);
                    // console.log("Respuesta server por reclamo: " + respServer);
                    //console.log("Respuesta server tipo: " + respServer.tipo);

                    Notificaciones.ReclamoAprobar(respServer);
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send(idReclamo);
    }

    static setButtonUltimos() {
        let btnVerReclamProduc = document.querySelector("#verReclaOProdudctos");
        btnVerReclamProduc.setAttribute("onclick", "ReclamoBusqueda.btnVerReclamProduc();");
        ReclamoBusqueda.btnVerDetalleHide();
        ReclamoBusqueda.reclamosDetalleHide();
    }
    static setButonPorCliente() {
        let elem = document.querySelector("#buscar");
        elem.setAttribute("onclick", "ReclamoBusqueda.busquedaPorCliente()");
        let btnVerReclamProduc = document.querySelector("#verReclaOProdudctos");
        btnVerReclamProduc.setAttribute("onclick", "ReclamoBusqueda.btnVerReclamProduc();");
        ReclamoBusqueda.btnVerDetalleHide();
        ReclamoBusqueda.reclamosDetalleHide();
    }
    static listReclamosDraw(respServer) {

        ReclamoBusqueda.reclamosDetalleHide();
        ReclamoBusqueda.reclamosListShow();
        let listadoReclamos = respServer;
        //console.log(respServer[0].encabezado.id_reclamo);
        console.log(respServer);
        //ReclamoBusqueda.obtenerEncabezado(listadoReclamos, reclamoID);
        console.log("Usuario actual: " + respServer[0].encabezado);
        console.log("Usuario actual: " + respServer[0].encabezado.logedUser);
        if (respServer[0].encabezado.logedUser === 5) {
            console.log("Usuario es Jorge");
            const template = `
                    <h2>Listado de reclamos</h2>
                    <table class="pure-table pure-table-bordered center">
                    <thead><th>Cliente</th><th>Reclamo</th><th>Fecha Recepción</th><th>Ver</th><th>Estado</th></thead>
                    ${ listadoReclamos.map(reclamo =>
                    `

                    <tr class='persona_item'>
                    <td>${reclamo.encabezado.cli_nombre}</td>
                    <td>${reclamo.encabezado.id_reclamo}</td>
                    <td>${moment(reclamo.encabezado.fecha_recepcion).format('DD/MM/YYYY')}</td>




                    <td> <button class='button-success pure-button' value='${reclamo.encabezado.id_reclamo}' onclick='ReclamoBusqueda.listReclamoDetalleDraw(${reclamo.encabezado.id_reclamo}, ${reclamo.encabezado.aprobadoBoolean}, "${reclamo.encabezado.userFirma}","${reclamo.encabezado.userName}" ); ReclamoBusqueda.encabezado(${reclamo.encabezado.id_reclamo});'>Ver </button>  </td>
${reclamo.encabezado.aprobadoBoolean ? '<td><spam id="aprobado">Aprobado</spam></td>' : `<td><button class='button-success pure-button' value='${reclamo.encabezado.id_reclamo}' onclick='ReclamoBusqueda.aprobarReclamo(${reclamo.encabezado.id_reclamo});'>Aprobar </button></td>`}

                    </tr>
                    `
            ).join('')
                    }
                    </table>
                    `;
            //let templateReclamo = document.querySelector("#templateListReclamos").innerHTML;
            document.querySelector("#panelResultados").innerHTML = template;

        } else {
            console.log("Usuario NO es Jorge");
            const template2 = `
                    <h2>Listado de reclamos</h2>
                    <table class="pure-table pure-table-bordered center">
                    <thead><th>Cliente</th><th>Reclamo</th><th>Fecha Recepción</th><th>Ver</th><th>Estado</th></thead>
                    ${ listadoReclamos.map(reclamo =>
                    `

                    <tr class='persona_item'>
                    <td>${reclamo.encabezado.cli_nombre}</td>
                    <td>${reclamo.encabezado.id_reclamo}</td>
                    <td>${moment(reclamo.encabezado.fecha_recepcion).format('DD/MM/YYYY')}</td>




                    <td> <button class='button-success pure-button' value='${reclamo.encabezado.id_reclamo}' onclick='ReclamoBusqueda.listReclamoDetalleDraw(${reclamo.encabezado.id_reclamo}, ${reclamo.encabezado.aprobadoBoolean}); ReclamoBusqueda.encabezado(${reclamo.encabezado.id_reclamo});'>Ver </button>  </td>
${reclamo.encabezado.aprobadoBoolean ? '<td><spam id="aprobado">Aprobado</spam></td>' : `<td><spam id="pendiente">Pendiente<spam></td>`}

                    </tr>
                    `
            ).join('')
                    }
                    </table>
                    `;
            //let templateReclamo = document.querySelector("#templateListReclamos").innerHTML;
            document.querySelector("#panelResultados").innerHTML = template2;
        }

        //document.querySelector("#panelResultados").innerHTML = eval (templateReclamo);
    }

    static listReclamoDetalleDraw(reclamoID, aprobado, firma, userOwn) {
        console.log("aprobado : " + aprobado);
        if (aprobado) {
            document.querySelector("#FirmaJorgeBellocchio").classList.remove("ocultarFirma");
            document.querySelector("#FirmaJorgeBellocchio").classList.add("mostrarFirma");
        } else {
            document.querySelector("#FirmaJorgeBellocchio").classList.remove("mostrarFirma");
            document.querySelector("#FirmaJorgeBellocchio").classList.add("ocultarFirma");
        }
        //ReclamoBusqueda.encabezado();
        ReclamoBusqueda.reclamosListHide();
        ReclamoBusqueda.reclamosDetalleShow();
        ReclamoBusqueda.btnVerDetalleShow();
        let listadoReclamos = JSON.parse(localStorage.getItem("BusquedaRelcamos"));
        ReclamoBusqueda.obtenerEncabezado(listadoReclamos, reclamoID);
        listadoReclamos = ReclamoBusqueda.obtenerArrayProductos(listadoReclamos, reclamoID);
        let detalleCount = 1;
        console.log("<img src='../img/firmas/" + firma +"'");
        document.querySelector("#firmaAnalista").innerHTML = "<img src='../img/firmas/" + firma + "' width='200' id='firmaMariaBravo'/><ul><li>" + userOwn + "</li><li>Analista</li></ul>";
        console.log(listadoReclamos);
//        ${console.log(detalleCount)}
//        ${detalleCount = detalleCount + 1}
//        ${listadoReclamos.length}
//${listadoReclamos.map((currElement, index) => {  console.log(index)   })}
        let template = `


                    <button id='btnPdf' class='button-success pure-button' onclick='ReclamoBusqueda.pdf();'>PDF <i class='fa fa-file-pdf-o' aria-hidden='true'></i></button>
                    ${ listadoReclamos.map(reclamo =>
                `

                      <div id="WraperTmplDetalle">
            <div class="flex-containerCliente pure-form">
                <div class="flex leftC">

                    <ul>
                        <li><span class="nombreItemDetalle">Producto:</span> ${reclamo.nombre_producto}</li>
                        <li><span class="nombreItemDetalle"> Origen:</span> ${reclamo.nombre_origen}</li>
                        <li><span class="nombreItemDetalle"> Proveedor:</span> ${reclamo.nombre_provee}</li>
                    </ul>
                </div>
                <div class="flex rightC">
                    <ul>
                        <li><span class="nombreItemDetalle"> Cantidad total:</span> ${reclamo.cantidad_total}</li>
                        <li><span class="nombreItemDetalle"> Lote:</span> ${reclamo.lote}</li>
                        <li><span class="nombreItemDetalle"> Motivo:</span> ${reclamo.motivo}</li>
                    </ul>
                </div>

                <div class="flex tres">

                    <ul>
                        <li><span class="nombreItemDetalle"> Verificación:</span> ${reclamo.nombre_verificacion}</li>
                        <li><span class="nombreItemDetalle"> Resultado:</span> ${reclamo.nombre_resultado}</li>

                    </ul>
                </div>
            </div>

            <div class="flex-containerCliente pure-form">
                <div class="flex leftC">


                        <span class="itemDiagnostico"> <span class="nombreItemDetalle"> Diseño:</span> ${reclamo.nombre_diseno} </span>

                </div>
                <div class="flex rightC">

                        <span class="itemDiagnostico"> <span class="nombreItemDetalle"> Proceso:</span> ${reclamo.nombre_proceso} </span>


                </div>

                <div class="flex tres">

                        <span class="itemDiagnostico">  <span class="nombreItemDetalle"> Falla de materiales:</span> ${reclamo.nombre_falla_mat} </span>

                </div>
            </div>


 ${reclamo.observacion.length > 0 ? `<div class="flex-containerCliente pure-form"> <span class="nombreItemDetalle itemDiagnostico"> Observación:</span> ${reclamo.observacion}</div>` : ''}







            <div class="flex-containerCliente pure-form dictamen">
                <div class="flex leftC">

                    <ul>

                        <li><span class="nombreItemDetalle"> Fundados:</span> ${reclamo.cant_fundados}</li>
                    </ul>
                </div>
                <div class="flex rightC">
                    <ul>


        ${reclamo.cant_infundados > 0 ? '<li class="alertaDictamen"><span class="nombreItemDetalle"> Infundados:</span> ' : `<li><span class="nombreItemDetalle"> Infundados:</span>`} ${reclamo.cant_infundados} </li>
                    </ul>
                </div>

                <div class="flex tres">
                    <ul>

        ${reclamo.cant_tema_comercial > 0 ? '<li class="alertaDictamen"><span class="nombreItemDetalle"> Tema comercial:</span> ' : `<li><span class="nombreItemDetalle"> Tema comercial:</span>`} ${reclamo.cant_tema_comercial} </li>
                    </ul>
                </div>


            </div>
</div>


                    `
        ).join('')
                }

                    `;

        //console.log(listadoReclamos[0].encabezado.id_reclamo);
        //let templateReclamo = document.querySelector("#templateReclamoDetalle").innerHTML;
        // document.querySelector("#pnlReclamoDetalle").innerHTML = eval (templateReclamo);
        document.querySelector("#pnlReclamoDetalle").innerHTML = template;
    }

    static obtenerArrayProductos(listadoProdcutos, reclamoID) {
        for (let i = 0; i <= (listadoProdcutos.length - 1); i++) {
            console.log("i: " + i);
            console.log("detalleListado " + i + " : " + JSON.stringify(listadoProdcutos[i].listadoDetalle));
            //let reclamoID = 103;
            if (listadoProdcutos[i].encabezado.id_reclamo == reclamoID) {
                console.log("El detalle del reclamo " + reclamoID + " es " + JSON.stringify(listadoProdcutos[i].listadoDetalle));
                return listadoProdcutos[i].listadoDetalle;
            } else {
                console.log(listadoProdcutos[i].encabezado.id_reclamo + " no es " + reclamoID);
            }
        }

    }

    static obtenerEncabezado(listadoProdcutos, reclamoID) {
        for (let i = 0; i <= (listadoProdcutos.length - 1); i++) {
            //console.log("i: " + i);
            console.log("detalleListado para ENCABEZADO: " + i + " : " + JSON.stringify(listadoProdcutos[i].listadoDetalle));
            //let reclamoID = 103;
            if (listadoProdcutos[i].encabezado.id_reclamo == reclamoID) {
                // console.log("El detalle del reclamo " + reclamoID + " es " + JSON.stringify(listadoProdcutos[i].listadoDetalle));
                document.querySelector("#clienteEncabezado").innerHTML = listadoProdcutos[i].encabezado.cli_nombre;

                document.querySelector("#fecha_emision").innerHTML = moment(listadoProdcutos[i].encabezado.fecha_emision).format('DD/MM/YYYY');

                document.querySelector("#fecha_recepcion").innerHTML = moment(listadoProdcutos[i].encabezado.fecha_recepcion).format('DD/MM/YYYY');
                document.querySelector("#reclamo_num").innerHTML = listadoProdcutos[i].encabezado.id_reclamo;
                console.log("CLIENTE de encabezado: " + listadoProdcutos[i].encabezado.cli_nombre);
                //return listadoProdcutos[i].listadoDetalle;
            } else {
                //console.log(listadoProdcutos[i].encabezado.id_reclamo + " no es " + reclamoID);
            }
        }
    }
    static pdf() {
        window.print();
    }

    static btnVerReclamProduc() {
        ReclamoBusqueda.reclamosDetalleHide();
        ReclamoBusqueda.reclamosListShow();
        ReclamoBusqueda.btnVerDetalleHide();

    }
    static reclamosListHide() {
        document.querySelector("#panelResultadosWrapper").classList.add("ocultar");
    }
    static reclamosListShow() {
        document.querySelector("#panelResultadosWrapper").classList.remove("ocultar");
    }

    static reclamosDetalleHide() {
        document.querySelector("#pnlReclamoDetalleWrapper").classList.add("ocultar");
    }

    static reclamosDetalleShow() {
        document.querySelector("#pnlReclamoDetalleWrapper").classList.remove("ocultar");
    }

    static btnVerDetalleHide() {
        document.querySelector("#verReclaOProdudctos").classList.add("ocultar");

    }

    static btnVerDetalleShow() {
        document.querySelector("#verReclaOProdudctos").classList.remove("ocultar");

    }

}

class OptSeacrh {
    static init() {
        OptSeacrh.setButtons();
        OptSeacrh.clientes();
    }

    static selected(param) {
        document.querySelector("#optSelected").value = param;
    }
    static producto() {
        console.log("productos");
        document.querySelector("#pnlProducto").classList.remove("ocultar");
        document.querySelector("#pnlProducto").classList.add("mostrar");

        document.querySelector("#pnlProveedor").classList.remove("mostrar");
        document.querySelector("#pnlProveedor").classList.add("ocultar");

        document.querySelector("#pnlCliente").classList.remove("mostrar");
        document.querySelector("#pnlCliente").classList.add("ocultar");
        OptSeacrh.selected("producto");
    }

    static clientes() {
        console.log("Cliente");

        document.querySelector("#pnlCliente").classList.remove("ocultar");
        document.querySelector("#pnlCliente").classList.add("mostrar");

        document.querySelector("#pnlProducto").classList.remove("mostrar");
        document.querySelector("#pnlProducto").classList.add("ocultar");

        document.querySelector("#pnlProveedor").classList.remove("mostrar");
        document.querySelector("#pnlProveedor").classList.add("ocultar");
        OptSeacrh.selected("clientes");

    }

    static proveedores() {
        console.log("Proveedores");
        document.querySelector("#pnlProveedor").classList.remove("ocultar");
        document.querySelector("#pnlProveedor").classList.add("mostrar");

        document.querySelector("#pnlCliente").classList.remove("mostrar");
        document.querySelector("#pnlCliente").classList.add("ocultar");

        document.querySelector("#pnlProducto").classList.remove("mostrar");
        document.querySelector("#pnlProducto").classList.add("ocultar");
        OptSeacrh.selected("proveedor");

    }

    static setButtons() {
        document.querySelector("#one").setAttribute("onclick", "OptSeacrh.clientes()");
        document.querySelector("#two").setAttribute("onclick", "OptSeacrh.proveedores()");
        document.querySelector("#three").setAttribute("onclick", "OptSeacrh.producto()");
    }

}
