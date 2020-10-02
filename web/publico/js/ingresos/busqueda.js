class Busqueda {

    static main() {
        document.querySelector("#consutarBusquedaIngresos").setAttribute("onclick", "Busqueda.proveedor();");
    }
    static proveedor() {
        SpinnerModal.open();
        const traer = async () => {
            Dataselect.itemSelected("proveedores", "#inputSelectProveedor input", "#id_proveedor", "prov_id");
            let busProv = {};
            busProv.id_proveedor = document.querySelector("#id_proveedor").value;
            busProv.fecha_desde = document.querySelector("#fecha_desde").value;
            busProv.fecha_hasta = document.querySelector("#fecha_hasta").value;


            let registroStringJSON = JSON.stringify(busProv);
            if (busProv.fecha_desde === "" || busProv.fecha_hasta === "") {
                console.log("fechas vacias");
                swal({
                    closeOnClickOutside: false,
                    closeOnEsc: false,
                    title: "Las fechas desde y hasta deben estar completas.",
                    icon: "warning"
                });
            } else {
                console.log("hacer GET");
                let respuesta = await fetch("../../BusquedaIngresos?&busqueda=" + registroStringJSON,
                    { method: 'GET' });

                //let datotexto = JSON.parse(await respuesta.text());
                
                let no_oks_desordenados = JSON.parse(await respuesta.text());
                let no_oks = no_oks_desordenados.sort((a, b) => (a.productoNombre > b.productoNombre) ? 1 : -1);
                //console.log( " AGRUPADO " + JSON.stringify(this.unirProductos(no_oks)));
                //localStorage.setItem("cargas", JSON.stringify(medidas));
                console.log(JSON.stringify(no_oks));
                if (no_oks === undefined || no_oks.length == 0) {
                    swal({
                        closeOnClickOutside: false,
                        closeOnEsc: false,
                        title: "Sin resultados.",
                        icon: "warning"
                    });
                } else {
                    let templateNo_Ok = await `
                    
                    <table class="pure-table pure-table-bordered centerBusqIng">
                    <thead>            
                        <tr>            
                        <th>Producto</th><th>Cantidad</th><th>Diseño</th><th>Packaging</th>
                        </tr>
                    </thead>
                    ${ no_oks.map(no_ok =>
                        ` 
                    <tr class='persona_item'>
                        <td>${no_ok.productoNombre}</td>
                        <td>${no_ok.cantidad}</td>                                             
                        <td class='retenido_${no_ok.estadoDiseno}'>${no_ok.estadoDiseno}</td>
                        <td class='retenido_${no_ok.estadoPakaging}'>${no_ok.estadoPakaging}</td>                                                                                        
                    </tr>            
    `
                    ).join('')
                        }
    </table>
`;

                    document.querySelector('#itemsContenedor').innerHTML = templateNo_Ok;
                }

            }



            SpinnerModal.close();
        };
        traer();
        //.catch(ex => {
        //    document.querySelector("#panelMensajes").innerHTML = 'ERROR: ' + ex.message;
        // });
    }
    static unirProductos(inputArray) {
        //var inputArray = [
        //    {productoId:"13081",productoNombre:"JFA-0887",cantidad:600,estadoPakaging:"ok",estadoDiseno:"1",estadoDisenoYPkg:"","noOk":1},
        //            ], 
        let outputArray = [];

        inputArray.forEach(function (e) {
            //console.log(e.subject + " - " + e.status);
            if (!this[e.productoNombre]) {


                this[e.productoNombre] = { productoNombre: e.productoNombre, marks: 0, noOfStudents: 0, status: e.status }
               // console.log(this[e.subject]);
               // console.log(e.subject);
                outputArray.push(this[e.productoNombre]);
            }
            this[e.productoNombre].cantidad += Number(e.cantidad);
            
            if (e.status === "nook") {
                this[e.productoNombre].status = "nooK"
                //console.log("El producto " + e.subject + " tiene estado : " + e.status);
            } else {
                //console.log(e.subject + " no paso por el fi");
            }
        }, {});

        //console.log(outputArray);
        return outputArray;
    }
}

Busqueda.main();