var myChart;
class Informes {

    static setButton() {
        let btnBUscar = document.querySelector("#buscar");
        btnBUscar.setAttribute("onclick", "Informes.buscar()");
    }
    static buscar(desde, hasta) {
        console.log("metodo buscar");
        let xhr = new XMLHttpRequest();
        xhr.open("PUT", "../../informeGerencial");
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
                        Informes.drawTable();
                        //Informes.parserChartJson(xhr.responseText);
                        Informes.dataTable(xhr.responseText);
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
    
    static pdf() {
        window.print();
    }
    

    static obtener() {
        let xhr = new XMLHttpRequest();
        xhr.open("GET", "../../informeGerencial");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log("obtener(): " + xhr.responseText);
                    // Informes.templateReclamo(xhr.responseText);
                    Informes.parserChartJson(xhr.responseText);
                    Informes.chartGerencial(xhr.responseText)
                    Informes.dataTable(xhr.responseText);
                    // caso de exito
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send();

    }
    static drawTable() {
        document.querySelector("#datatables").innerHTML = " <div class='grid-row' layout='row top-stretch'> <div class='grid-cell'> <datatable id='data-table-main' :source='deliveries.rows' :striped='deliveries.striped' :line-numbers='deliveries.lineNumbers'>  <datatable-column v-for='column in deliveries.columns' :id='column.id' :label='column.label' :width='column.width' :sortable='column.sortable' :groupable='column.groupable' :aggregators='column.aggregators' :formatter='column.formatter'> </datatable-column>          </datatable> </div></div>";
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
                aggregators: []
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
                id: "infundados",
                label: "Infundados",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "tema_comercial",
                label: "Tema Comercial",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: [aggregators.total]
            },
            {
                id: "origen",
                label: "Origen",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: []
            },
            {
                id: "diagnostico",
                label: "Diagnostico",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: []
            },
            {
                id: "lote",
                label: "Lote",
                width: null,
                sortable: true,
                groupable: true,
                aggregators: []
            }];
        for (let i in jsonObj) {
            rowsItem.id = i;
            rowsItem.Fundados = parseInt(jsonObj[i].cant_fundados);
            rowsItem.Producto = jsonObj[i].codigo_producto;
            rowsItem.infundados = jsonObj[i].cant_infundados;
            rowsItem.tema_comercial = jsonObj[i].cant_tema_comercial;
            rowsItem.origen = jsonObj[i].origen;
            rowsItem.diagnostico = jsonObj[i].diagnostico;
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

    static chartGerencial(Jsondata) {
        let ctx = document.getElementById("myChart").getContext('2d');
        if (myChart) {
            myChart.destroy();
        }
        myChart = new Chart(ctx, {
            type: 'pie',
            data: Jsondata,
            options: {
                responsive: true
            }
        });
    }
    static parserChartJson(aConvertir) {
        let labels = [];
        let data = [];
        let colors = [];
        let chart = {};
        chart.options = {};
        chart.data = {};
        let aConvertirJson = JSON.parse(aConvertir);

        for (let i in aConvertirJson) {
            for (let j in aConvertirJson[i]) {

            }
            console.log("nombre: " + aConvertirJson[i].codigo_producto);
            data.push(aConvertirJson[i].cant_fundados);
            labels.push(aConvertirJson[i].codigo_producto);
            colors.push(Informes.colorRamdom());
        }
        chart.labels = labels;
        chart.datasets = [{label: 'Productos fundados', data: data,
                backgroundColor: colors, borderWidth: 1}];

        console.log(JSON.stringify(chart));
        Informes.chartGerencial(chart);

    }
 
    static colorRamdom() {
        let letters = '0123456789ABCDEF';
        let color = '#';
        for (let i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }
}
//Informes.obtener();
//Informes.chartGerencial();
Informes.setButton();