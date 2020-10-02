class ProvedoresBorrar {
        static buscarProveedorBorrar(desde, hasta, proveedor) {
        console.log("metodo buscar");
        let xhr = new XMLHttpRequest();
        xhr.open("PUT", "../../InformeBusquedaProvisorioProveedores");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log("OK BUSCAR(): " + xhr.responseText);
                    let respuesta = JSON.parse(xhr.responseText);
                    console.log("respueta: " + respuesta);
                    console.log("respueta: " + respuesta.titulo);
                    if (respuesta.titulo == "Error") {
                        Notificaciones.informeGerenError(respuesta.mensaje);
                    } else {
//                        Informes.drawTable();
//                        Informes.parserChartJson(xhr.responseText);
//                        Informes.dataTable(xhr.responseText);
    ProvedoresBorrar.dataTable(xhr.responseText);
                    }
                } else {
                }
            } else {
            }
        };
        let busqueda = {};
        busqueda.desde = document.querySelector("#fecha_desde").value;
        busqueda.hasta = document.querySelector("#fecha_hasta").value;
        let busquedaStrJson = JSON.stringify(busqueda);
        xhr.send(busquedaStrJson);
    }
    
    static dataTable(json) {
        var aggregators = vuetiful.aggregators;
        var formatters = vuetiful.formatters;
        var currencies = vuetiful.maps.currencies;
        let jsonObj = JSON.parse(json);
        let deliveries = {};
        let rows = [];
        let rowsItem = {};
        let columns = [];
        columns = [
            {
                id: "Producto",
                label: "Producto",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "Fundados",
                label: "Fundados",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "id_diseno",
                label: "Diseño",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "id_proceso",
                label: "Proceso",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "id_falla_mat",
                label: "Falla mat",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "id_provee",
                label: "Prov",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            }];
        for (let i in jsonObj) {
            rowsItem.id = i;
            rowsItem.Fundados = parseInt(jsonObj[i].cant_fundados);
            rowsItem.Producto = jsonObj[i].codigo_producto;
            rowsItem.id_diseno = jsonObj[i].id_diseno;
            rowsItem.id_proceso = jsonObj[i].id_proceso;
            rowsItem.id_falla_mat = jsonObj[i].id_falla_mat;
            rowsItem.id_provee = jsonObj[i].id_provee;
            rowsItem.lote = jsonObj[i].lote;
            rows.push(rowsItem);
            rowsItem = {};
        }
        ;
        deliveries = {
            striped: true,
            editable: true,
            lineNumbers: false,
            filter: null,
            currency: "USD",
            dateFormat: "D MMMM YYYY",
            columns: columns,
            rows: rows,
            selected: []
        };

        new Vue({
            el: "#datatables",
            data: function () {
                return {
                    deliveries: deliveries,
                    currencies: currencies,
                    aggregators: aggregators,
                    dateFormats: [
                        "DD/MM/YYYY",
                        "DD MMM YYYY",
                        "D MMMM YYYY",
                        "D/MM/YYYY h:mm a"
                    ],
                    formatters: [
                        {id: "C", name: "Currency"},
                        {id: "DT", name: "Date and Time"}
                    ]
                };
            }
        });
    }
    
    static setButton(){
        document.querySelector("#buscarProveedorProvisorio").setAttribute("onclick","ProvedoresBorrar.buscarProveedorBorrar();");
    }
    
}

ProvedoresBorrar.setButton();