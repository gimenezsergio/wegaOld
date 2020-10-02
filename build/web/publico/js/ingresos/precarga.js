class Precarga {
    static init() {
        document.querySelector("#grabarPrecarga").setAttribute("onclick", "Precarga.alta();");
        document.querySelector("#agregarItem").setAttribute("onclick", "Precarga.agregarItem();");
        document.querySelector("#listadoBtn").setAttribute("onclick", "Precarga.pedirListado()");
    }
    static alta() {
        const traer = async() => {
            Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
            Dataselect.itemSelected("arribo", "#inputSelectArribo input", "#id_arribo", "arriboId");
            let registro = {};
            registro.encabezado = {};
            registro.encabezado.id_proveedor = document.querySelector("#id_proveedor").value;
            registro.encabezado.codigo_manifiesto = document.querySelector("#codigo_manifiesto").value;
            registro.encabezado.fecha_manifiesto = document.querySelector("#fecha_manifiesto").value;
            //registro.encabezado.fecha_recepcion = document.querySelector("#fecha_recepcion").value;
            registro.encabezado.arribo = document.querySelector("#id_arribo").value;
            registro.encabezado.obs = document.querySelector("#obs").value;


            registro.productos = [];
            let lospanelesproductos = document.querySelectorAll('#itemsContenedor article');
            for (let i = 0; i < lospanelesproductos.length; i++) {
                console.log("#producto_id" + "_" + i);
                if (i > 0) {
                    Dataselect.itemSelected("productos", "#" + (lospanelesproductos[i].id) + " #inputSelectProducto input", "#" + (lospanelesproductos[i].id) + " #producto_id" + "_" + (i + 1), "id_producto");
                    //Dataselect.itemSelected("productos", "#" + (lospanelesproductos[i].id) + " #inputSelectProducto input", "#" + (lospanelesproductos[i].id) + " #producto_id" + "_" + (i + 1), "id_producto");

                } else {
                    Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
                }

                let losinputesproductos = lospanelesproductos[i].querySelectorAll('input');
                let unproducto = {};
                for (let j = 0; j < losinputesproductos.length; j++) {
                    unproducto[losinputesproductos[j].getAttribute('name')] = losinputesproductos[j].value;
                    // unproducto[observa[j].getAttribute('name')] = observa[j].value;
                }
                registro.productos.push(unproducto);
            }
            console.log("precarga:  " + JSON.stringify(registro));

            let registroStringJSON = JSON.stringify(registro);
            let respuesta = await fetch("../Precarga",
                    {method: 'POST', body: registroStringJSON});

            let datotexto = JSON.parse(await respuesta.text());

            Notificaciones.PrecargaGuardar(datotexto);
            //document.querySelector('#panelResultados').innerHTML = datotexto;
        };
        traer()
        //.catch(ex => {
        //    document.querySelector("#panelMensajes").innerHTML = 'ERROR: ' + ex.message;
        // });
    }

    static agregarItem() {
        swal({
            title: "Nuevo item",
            text: "¿Agregar un nuevo item?",
            icon: "info",
            buttons: true,
            dangerMode: true,
        })
                .then((willDelete) => {
                    if (willDelete) {
                        let itm = document.querySelector("#productItem");
                        let cln = itm.cloneNode(true);
                        let producto = {};
                        cln.id = "itemsManifiesto" + Precarga.NUMERO_PRODUCTO_SEC;
                        document.querySelector("#itemsContenedor").appendChild(cln);
                        let etiquetas = document.querySelectorAll("#itemsManifiesto" + Precarga.NUMERO_PRODUCTO_SEC + " .etiqueta");
                        for (let i = 0; i < etiquetas.length; i++) {
                            etiquetas[i].remove();
                        }

                        let losInputs = cln.querySelectorAll("input");
                        for (let i = 0; i < losInputs.length; i++) {
                            losInputs[i].value = "";
                            losInputs[i].id = losInputs[i].id + "_" + Precarga.NUMERO_PRODUCTO_SEC;
                            console.log(losInputs[i].id);
                        }

                        PrecargaProductos.NumOrderProduct(cln);
                        //product.NumOrderProduct( cln );
                        Precarga.NUMERO_PRODUCTO_SEC++;
                    } else {
                        console.log("No se agrega item");
                    }
                });




    }

    static pedirListado() {
        const traer = async() => {
            let respuesta = await fetch("../Precarga", {method: 'GET'});
            let precargas = JSON.parse(await respuesta.text());
            localStorage.setItem("precargas", JSON.stringify(precargas));
            console.log(JSON.stringify(precargas));
            let templatePrecargas = await `
            <h3>Carga</h3>
    <table class="pure-table pure-table-bordered center">
<thead>            
<tr>
            
<th>Nº orden</th><th>Manifiesto</th><th>Acción</th>
            
</tr>
            </thead>
    ${ precargas.map(precarga =>
                    `
              <tr class='persona_item'>
                  <td>${precarga.maestro.id}</td>
                  <td>${precarga.maestro.codigoManifiesto}</td>
                  <td> <input class='pure-button-active pure-button' type='button' value='Ver' onclick='Precarga.pedirListadoDetalle(${precarga.maestro.id})' /> 
                       </td>
              </tr>
    `
            ).join('')
                    }
    </table>
`;
            //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
            document.querySelector("#pnlListado").innerHTML = templatePrecargas;
        };
        traer()
//                .catch(ex => {
//                    document.querySelector("#pnlListado").innerHTML = 'ERROR: ' + ex.message;
//                });
        Ingresos.nuevoHide();
        Ingresos.informeHide();
        Ingresos.ListadoShow();
        Ingresos.bienvenidaHide();
    }

    static pedirListadoDetalle(idMaestro) {
        let listadoPrecargas = JSON.parse(localStorage.getItem("precargas"));
        console.log(listadoPrecargas);

        let precargaSelect = listadoPrecargas.filter(precarga => {
            return precarga.maestro.id == idMaestro
        });

        let productosIdDetalle = [];
        let templatePrecargas = `
        <h3>Detalle del plan de control</h3>
        <table class="pure-table pure-table-bordered center pure-form">
            <thead>            
                <tr>
                <th>Codigo</th><th>Cant</th><th>Muestras</th><th colspan=2>Control de recepción</th><th>Cargado</th> <th>Observación</th>
                </tr>
            </thead>
    ${ precargaSelect.map(precarga2 =>
                `<ul id='detalleEncabezado' class="pure-form">
                <li class='detalleEncabezado'>Nº orden: <span class='itemMaestro' id='itemMaestroId'>${precarga2.maestro.id}</span></li>
                <li class='detalleEncabezado'>Manifiesto: <span class='itemMaestro'>${precarga2.maestro.codigoManifiesto}</span></li>
                <li class='detalleEncabezado'>Fecha: <span class='itemMaestro'>${precarga2.maestro.fechaManifesto}</span></li>
                <li class='detalleEncabezado'>Proveedor: <span class='itemMaestro'>${precarga2.maestro.proveedor}</span></li>
                <li class='detalleEncabezado'>Recepción: <input type="date" name="fecha_recepcion" value="" id="fecha_recepcion"></li>
                <li class='detalleEncabezado'>Analista: <input type="text" name="analistaCarga" value="" id="analistaCarga"></li>
        </ul>
        
                <tr class='persona_item'>
                  ${precarga2.detalles.map(detalle2 =>
                            `                            
                    <td class="idParaDetelleId">${detalle2.producto}</td> 
                    <td>${detalle2.cant}</td>
                    <td>${detalle2.muestras}</td>
                    
        <td><input class='button-success pure-button ${detalle2.estadoPcking}' type='button' value='Packaging' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></td>
        <td><input class='button-success pure-button ${detalle2.estadoDiseno}' type='button' value='Producto' onclick='Precarga.routerPlanControl("${detalle2.producto}","${detalle2.id}","${detalle2.familiaId}", "${detalle2.subFamiliaId}", "${detalle2.familiaNombre}", "${detalle2.subfamiliaNombre}")' id='btnDiseno${detalle2.id}' /></td>
        <!--td><input class='pure-button' type='button' value='Ver' onclick="Carga.consultarIndividual(${precarga2.maestro.id})" /></td--!>
        <td><input class='pure-button' type='button' value='Ver' onclick="Carga.consultarIndividual(${precarga2.maestro.id}, ${detalle2.id}, ${detalle2.productoId})" /></td>
        <td class="observaciones pure-form"><input id="item_${detalle2.producto.replace("/", "-")}_obs" type=text/> <span style='display:none;' class="idDetalleObs" id="item_${detalle2.producto.replace("/", "-")}_detalleId">${detalle2.id}</span> </td>        
                </tr>
            `
                    ).join('')
                    }
            </table>  
        <button type="button" class="pure-button" id="closeCargaStatus" onclick='Carga.updateStatus();' >Cerrar carga</button>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#cuerpoPackaging").innerHTML = templatePrecargas;

        //document.querySelector("#cuerpoPrecarga").outerHTML = "<td>" + precargaSelect[0].maestro.codigoManifiesto + "</td> <td>" + precargaSelect[0].maestro.fechaManifesto + "</td>";


        console.log("productosIdDetalle: " + productosIdDetalle);
        console.log(precargaSelect[0].maestro.codigoManifiesto);

        document.querySelector('#cargaModal').style.display = 'block';
    }

    static packagingCargar(producto, idDetalle) {
        document.querySelector('#packagingModal').style.display = 'block';
        document.querySelector("#packagingProducto").innerHTML = producto;
        document.querySelector("#idDetalle").innerHTML = idDetalle;
    }

    static routerPlanControl(producto, id, familiaId, subfamiliaId, familiaNombre, subfamiliaNombre) {
        console.log("familia: " + familiaId + " subfamilia: " + subfamiliaId);
        //decidir el plan de control segun la familia o subfamilia
        if (subfamiliaId === "28") {
            PlanControlDiseno.bujiasEncendidoCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "29") {
            PlanControlDiseno.bujiasPrecalentamientoCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (familiaId === "9") {
            PlanControlDiseno.lamparasCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "1") {
            PlanControlDiseno.filtrosAirePanelCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "4") {
            PlanControlDiseno.filtrosAireHabitaculoCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "2") {
            PlanControlDiseno.filtrosAireRedondosCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "3") {
            PlanControlDiseno.filtrosAirePesadosCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "11" || subfamiliaId === "10") {
            PlanControlDiseno.filtrosAceiteCartucho(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "48") {
            PlanControlDiseno.filtrosCumbustibleNaftaUSellada(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "42") {
            PlanControlDiseno.filtrosCumbustibleNaftaTank(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "43") {
            PlanControlDiseno.filtroCumbustibleNaftaCartucho(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "44") {
            PlanControlDiseno.filtroCumbustibleDiselSelladaPlastico(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "8") {
            PlanControlDiseno.filtrosAceiteUSelladaCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "45") {
            PlanControlDiseno.filtroCumbustibleDiselSelladaMetalico(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "46") {
            PlanControlDiseno.filtroCumbustibleDiselCartucho(producto, id, familiaNombre, subfamiliaNombre);
        } else if (subfamiliaId === "5") {
            PlanControlDiseno.aireFrenosCargar(producto, id, familiaNombre, subfamiliaNombre);
        } else {
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: "info",
                title: "Este producto no tiene plan de control.",
                buttons: {
                    //cancel: true,
                    confirm: "Ok"

                },
            });
        }
    }

}

class Carga {

    static updateStatus(listaIds) {
        console.log("updateStatus");
        const traer = async() => {
            console.log("lista ids: " + listaIds);
            let carga = {};
            carga.comentarios = [];

            carga.itemMaestroId = document.querySelector("#itemMaestroId").innerText;
            carga.fecha_recepcion = document.querySelector("#fecha_recepcion").value;
            carga.analista = document.querySelector("#analistaCarga").value;
            let idParaDetelleId = document.querySelectorAll(".idParaDetelleId");

            idParaDetelleId.forEach(function (userItem) {
                let comentario = {};
                console.log(userItem.innerText);
               // item_${detalle2.producto.replace("/", "-")}_detalleId
               let idAux = userItem.innerText;
                comentario.idDetalle = document.querySelector("#item_" + idAux.replace("/", "-") + "_detalleId").innerText;
                comentario.obs = document.querySelector("#item_" + idAux.replace("/", "-") + "_obs").value;
                carga.comentarios.push(comentario);
            });
            console.log(carga);
            let registroStringJSON = JSON.stringify(carga);
            let respuesta = await fetch("../Carga",
                    {method: 'POST', body: registroStringJSON,
                        /*credentials: 'include'  */
                        credentials: 'same-origin'
                    });
            //let datotexto = JSON.parse(await respuesta.text());
            let datotexto = await respuesta.text();
            console.log(respuesta);
            console.log(datotexto);
            Notificaciones.closeCarga(JSON.parse(datotexto));

            //document.querySelector('#panelMsgOK').innerHTML = datotexto;
        };
        traer()
//                .catch(ex => {
//                    console.log(ex.message);
//                });

    }

    static consultarIndividual(maestroId, detalleId, pproducto) {
        const traer = async() => {
            let respuesta = await fetch("../PlanControlDiseno?&maestroId=" + maestroId + "&detalleId=" + detalleId + "&productoId=" + pproducto, {
                method: 'GET',
            });
            let cargas = JSON.parse(await respuesta.text());
            console.log(JSON.stringify(cargas));
            let templateCargas = await `
                        
    ${ cargas.map(carga =>
                    `
                    <h3>Resultado del plan de control</h3>
            <table class="pure-table pure-table-bordered center">
            <thead>            
                <tr>
                <th>Codigo</th><th>Manifesto</th><th>Fecha</th><th>Proveedor</th>
                </tr>
            </thead>
            
                  <td>${carga.maestro.id}</td>  <td>${carga.maestro.codigoManifiesto}</td> <td>${carga.maestro.fechaManifesto}</td> <td>${carga.maestro.proveedor}</td>
            </table>
                
                ${carga.detalles.map(detalle => ` 
                <table class="pure-table pure-table-bordered center">
                <thead>            
                <tr>
                <th>Packacging</th><th>Valor</th><th>Coment</th>
                </tr>
                </thead>
                <h3>Código: ${detalle.producto} </h3> 
            
                <button id="detalleProductoMedidasBtn" class="pure-button" onclick="Carga.consultaMedidas(${detalle.id})">Medidas</button>
                <tr><td>Barra colectiva</td><td><span class="valorplan${detalle.packaging.colBarra}"> ${detalle.packaging.colBarraNombre}</span></td> <td> </td></tr>
                <tr><td>Barra individual</td><td><span class="valorplan${detalle.packaging.indBarra}"> ${detalle.packaging.indBarraNombre}</span></td> <td> </td></tr>
                <tr><td>Identificacion</td><td><span class="valorplan${detalle.packaging.indIdent}"> ${detalle.packaging.indIdentNombre}</span></td> <td> </td></tr>
                <tr><td>Equivalencias</td><td><span class="valorplan${detalle.packaging.indEquiv}"> ${detalle.packaging.indEquivNombre}</span></td> <td> </td></tr>
                <tr><td>Aplicaciones</td><td><span class="valorplan${detalle.packaging.indAplicaciones}"> ${detalle.packaging.indAplicacionesNombre}</span></td> <td> </td></tr>
                <tr><td>CHAS</td><td><span class="valorplan${detalle.packaging.chas}"> ${detalle.packaging.chasNombre}</span></td> <td> </td></tr>
                </table>
                
                            
                            <table class="pure-table pure-table-bordered center">
                            <thead>            
                            <tr>
                            <th>Diseño</th><th>Valor</th> <th>Coment</th>
                            </tr>
                            </thead>
                            ${detalle.listaPlanGeneral.map(planGeneral => `
                            <tr><td> ${planGeneral.tipoPlan}</td><td> <span class="valorplan${planGeneral.ValorId}"> ${planGeneral.valor} </span></td> <td>${planGeneral.comentarios}</td> </tr>
                 `

                            ).join('')}
                </table>
 `
                        ).join('')}

    `
            ).join('')
                    }

`;
            document.querySelector("#controlarPlanContent").innerHTML = templateCargas;
            document.querySelector('#controlarPlanModal').style.display = 'block';
        };
        traer()
//                .catch(ex => {
//                    document.querySelector("#pnlListado").innerHTML = 'ERROR: ' + ex.message;
//                });
        Ingresos.nuevoHide();
        Ingresos.ListadoHide();
        Ingresos.informeShow();
        Ingresos.bienvenidaHide();

    }

    static consultaMedidas(idMedida) {


        const traer = async() => {
            let respuesta = await fetch("../Medidas?&id=" + idMedida, {method: 'GET'});
            let medidas = JSON.parse(await respuesta.text());
            //localStorage.setItem("cargas", JSON.stringify(medidas));
            console.log(JSON.stringify(medidas));
            let templatePrecargas = await `
            <h3>Medidas</h3>
    <table class="pure-table pure-table-bordered center">
<thead>            
<tr>
            
<th>Item</th><th>Tipo Medición</th><th>Medida</th>
            
</tr>
            </thead>
    ${ medidas.map(medida =>
                    ` 
              <tr class='persona_item'>
                  <td>${medida.plan_nombre}</td>
                  <td>${medida.medicion_nombre}</td>                  
                  <td>${medida.medida}</td>
              </tr>
            
    `
            ).join('')
                    }
    </table>
`;
            //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
            document.querySelector("#MedidasContent").innerHTML = templatePrecargas;
            document.querySelector('#MedidasModal').style.display = 'block';
        };
        traer()
//                .catch(ex => {
//                    document.querySelector("#pnlListado").innerHTML = 'ERROR: ' + ex.message;
//                });


    }

}





class Packaging {
    static init() {
        document.querySelector("#grabarPackaging").setAttribute("onclick", "Packaging.alta()");
    }
    static alta() {
        console.log("alta packaging");


        const traer = async() => {
            let datosPkg = {};
            datosPkg.colBarra = Packaging.getCheckedRadio("col_cod_barra");
            datosPkg.indBarra = Packaging.getCheckedRadio("ind_cod_barra");
            datosPkg.indIdent = Packaging.getCheckedRadio("ind_ident");
            datosPkg.indEquiv = Packaging.getCheckedRadio("ind_equiv");
            datosPkg.aplica = Packaging.getCheckedRadio("ind_aplica");
            datosPkg.ind_chas = Packaging.getCheckedRadio("ind_chas");
            datosPkg.idDetalle = document.querySelector("#idDetalle").innerText;
            datosPkg.idMaestro = document.querySelector("#itemMaestroId").innerText;
            datosPkg.pkg_col_cod_barra_obs = document.querySelector("#pkg_col_cod_barra_obs").value;
            datosPkg.pkg_ind_cod_barra_obs = document.querySelector("#pkg_ind_cod_barra_obs").value;
            datosPkg.pkg_ind_ident_obs = document.querySelector("#pkg_ind_ident_obs").value;
            datosPkg.pkg_ind_equiv_obs = document.querySelector("#pkg_ind_equiv_obs").value;
            datosPkg.pkg_ind_aplica_obs = document.querySelector("#pkg_ind_aplica_obs").value;
            datosPkg.pkg_ind_chas_obs = document.querySelector("#pkg_ind_chas_obs").value;

            let registroStringJSON = JSON.stringify(datosPkg);
            let respuesta = await fetch("../Packaging",
                    {method: 'POST', body: registroStringJSON,
                        /*credentials: 'include'  */
                        credentials: 'same-origin'
                    });
            let datotexto = JSON.parse(await respuesta.text());
            //let datotexto = await respuesta.text();
            console.log(respuesta);
            console.log(datotexto);
            Notificaciones.packagingAndPlanDiseno(datotexto);
            if (datotexto.tipo == "success") {
                document.querySelector("#btnPck" + datosPkg.idDetalle).classList.remove("pcking_sin_realizar");
                document.querySelector("#btnPck" + datosPkg.idDetalle).classList.add("pcking_completo");
            }

            console.log("Luego de notificaciones");
            //document.querySelector('#panelMsgOK').innerHTML = datotexto;
        };
        traer()
                .catch(ex => {
                    console.log("Expetion: " + ex);
                });

        console.log();

        // console.log(JSON.stringify(datosPkg));
    }

    static getCheckedRadio(groupRadio) {
        let radioButtons = document.getElementsByName(groupRadio);
        let selected = "";
        for (let x = 0; x < radioButtons.length; x++) {
            if (radioButtons[x].checked) {
                //alert("You checked id" + radioButtons[x].id);
                //alert("Value is " + radioButtons[x].value);
                selected = radioButtons[x].value;
            }
        }
        return selected;
    }

    static clearCheckRadio(clase) {
        let ele = document.getElementsByName(clase);
        for (var i = 0; i < ele.length; i++)
            ele[i].checked = false;
    }

    static agregar() {
        console.log("[..] Registro agregar()");
        const traer = async() => {
            let registro = {};
            registro.nombre = document.querySelector("#reg_nombre").value;
            registro.clave = document.querySelector("#reg_clave").value;
            let registroStringJSON = JSON.stringify(registro);
            let respuesta = await fetch("RegistroServer",
                    {method: 'POST', body: registroStringJSON,
                        /*credentials: 'include'  */
                        credentials: 'same-origin'
                    });
            //let datotexto = JSON.parse(await respuesta.text());
            let datotexto = await respuesta.text();
            document.querySelector('#panelMsgOK').innerHTML = datotexto;
        };
        traer()
                .catch(ex => {
                    document.querySelector("#panelMsgERROR").innerHTML = 'ERROR: ' + ex.message;
                });
        console.log("[OK] Registro agregar()");
    }

    static inputDisabled() {
        let losInputs = document.querySelectorAll(".obsIndividuales");
        console.log(losInputs);
        for (let x = 0; x < losInputs.length; x++) {
            console.log(losInputs[x].setAttribute("disabled", "disabled"));
        }
    }

}

Packaging.init();

Precarga.NUMERO_PRODUCTO_SEC = 2;
Precarga.init();