"use strict";
class InformeClienteParticular {
    static alta() {
        let informeTecnico = {};
        let listaFotos = [];
        let misImagenes = document.querySelectorAll("#preview img");

        if (misImagenes === undefined || misImagenes.length == 0) {

        } else {
            console.log("cantidad de fotos: " + misImagenes.length);
            for (let i = 0; i < misImagenes.length; i++) {
                console.log("foto: " + misImagenes[i].src);
                listaFotos.push(misImagenes[i].src);
            }

        }


        Dataselect.itemSelected("clientes", "#inputSelectCliente input", "#cliente_id", "id_cliente");
        Dataselect.itemSelected("productos", "#inputSelectProducto input", "#producto_id", "id_producto");
        informeTecnico.cliente = document.querySelector("#cliente_id").value;
        informeTecnico.motivo = document.querySelector("#motivo").value;
        informeTecnico.fecha = document.querySelector("#fecha").value;
        informeTecnico.producto = document.querySelector("#producto_id").value;
        informeTecnico.codigo = document.querySelector("#codigo").value;
        informeTecnico.cantidad = document.querySelector("#cantidad").value;
        informeTecnico.lote = document.querySelector("#lote").value;

        informeTecnico.visual = document.querySelector("#visual").value;
        informeTecnico.observaciones = document.querySelector("#observaciones").value;
        informeTecnico.analisis_ensayos = document.querySelector("#analisis_ensayos").value;
        informeTecnico.inst_medios_control = document.querySelector("#inst_medios_control").value;
        informeTecnico.conclusiones = document.querySelector("#conclusiones").value;
        informeTecnico.fotos = listaFotos;



        console.log(informeTecnico);
        let informeTecnicoString = JSON.stringify(informeTecnico);
        console.log(informeTecnicoString);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../../InformeClienteParticular");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito
                    let respuesta = JSON.parse(xhr.responseText);
                    Notificaciones.informeTecnicoGuardar(respuesta);
                } else {
                    //caso de error
                }
            } else {
                //caso de error
            }
        };
        xhr.send(informeTecnicoString);

    }

    static getUltimos() {
        let xhr = new XMLHttpRequest();
        xhr.open("GET", "../../InformeClienteParticular");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    // caso de exito

                    let respuesta = JSON.parse(xhr.responseText);
                    if (respuesta.tipo == "warning") {
                        Notificaciones.ReclamoBusqueda(respuesta);
                    } else {
                        InformeClienteParticular.listInformesDraw(respuesta);
                        localStorage.setItem("InformeParticular", xhr.responseText);
                        //ReclamoBusqueda.listReclamosDraw(respuesta);
                        //  localStorage.setItem("BusquedaRelcamos", JSON.stringify(respuesta));
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

    static listInformesDraw(respServer) {


        let listadoReclamos = respServer;
        //console.log(respServer[0].encabezado.id_reclamo);
        console.log(respServer);
        //ReclamoBusqueda.obtenerEncabezado(listadoReclamos, reclamoID);
        const template = `
                    <h2>Listado de informes Técnicos</h2>
        <div id='itemInfTec'>
                    <table class="pure-table pure-table-bordered center">
                    <thead>
                    <th>ID</th>
                    <th>Cliente</th>
                    <th>Fecha</th>
                    <th>Producto</th>
                    <th>Acción</th>
                    </thead>
                    ${ listadoReclamos.map(informe =>
                `
                    <tr class='persona_item'>
                    <td>${informe.codigo}</td>
                    <td>${informe.cliente}</td>
                    <td>${informe.fecha}</td>
                    <td>${informe.producto}</td>
        <td><button class='button-success pure-button' value='${informe.codigo}' onclick='InformeClienteParticular.drawInfPart(${informe.codigo});'>Ver </button>
                    </tr>
                    `
        ).join('')
                }
                    </table>
        </div>
                    `;
        //let templateReclamo = document.querySelector("#templateListReclamos").innerHTML;
        document.querySelector("#panelResultados").innerHTML = template;
        //document.querySelector("#panelResultados").innerHTML = eval (templateReclamo);
    }

    static drawInfPart(idInforme) {
        document.querySelector("#templateInfParticualar").classList.remove("ocultar");
        document.querySelector("#panelResultadosWrapper").classList.add("ocultar");
        console.log("ID informe: " + idInforme);
        let informes = JSON.parse(localStorage.getItem("InformeParticular"));
        function isInforme(informes) {
            return informes.codigo === "" + idInforme + "";
        }
        var informeFiltrado = informes.filter(isInforme);
        console.log("id encontrado:" + JSON.stringify(informeFiltrado));
        document.querySelector("#clie_id").innerHTML = informeFiltrado[0].cliente;
        document.querySelector("#producto").innerHTML = informeFiltrado[0].producto;
        document.querySelector("#motivo").innerHTML = informeFiltrado[0].motivo;
        document.querySelector("#idInforme").innerHTML = informeFiltrado[0].codigo;
        document.querySelector("#fecha").innerHTML = moment(informeFiltrado[0].fecha).format('DD/MM/YYYY');
        document.querySelector("#cantidad").innerHTML = informeFiltrado[0].cantidad;
        document.querySelector("#lote").innerHTML = informeFiltrado[0].lote;
        document.querySelector("#visual").innerHTML = informeFiltrado[0].visual;
        document.querySelector("#observaciones").innerHTML = informeFiltrado[0].observaciones;
        document.querySelector("#analisis_ensayos").innerHTML = informeFiltrado[0].analisis_ensayos;
        document.querySelector("#inst_medios_control").innerHTML = informeFiltrado[0].inst_medios_control;
        document.querySelector("#conclusiones").innerHTML = informeFiltrado[0].conclusiones;

        document.querySelector("#foto1").src = informeFiltrado[0].fotos[0];
        let fotos = informeFiltrado[0].fotos;
        console.log("fotos cantidad: " + fotos.length);

        const template = `
                    ${ fotos.map(foto =>
                `
                    <img src='${foto}' height='300px'>  
                    `
        ).join('')
                }
                    `;
        document.querySelector("#pnlFotos").innerHTML = template;
    }

    static mostrarListado() {

        document.querySelector("#templateInfParticualar").classList.add("ocultar");
        document.querySelector("#panelResultadosWrapper").classList.remove("ocultar");
    }

    static setButton() {
        let elem = document.querySelector("#grabar");
        elem.setAttribute("onclick", "InformeClienteParticular.alta();");


    }

    static showListado() {
        document.querySelector("#verListadoInformes").setAttribute("onclick", "InformeClienteParticular.mostrarListado();");
    }

    static pdf() {
        window.print();
    }

}

class Fotos {
    static previewFiles() {
        var preview = document.querySelector('#preview');
        var files = document.querySelector('input[type=file]').files;
        let listaFotos = [];
        function readAndPreview(file) {
            if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                var reader = new FileReader();
                reader.addEventListener("load", function () {
                    var image = new Image();
                    image.height = 300;
                    image.title = file.name;
                    //localStorage.setItem("fotos", this.result);
                    // console.log("mi foto: " + this.result);
                    image.src = this.result;
                    preview.appendChild(image);
                }, false);
                reader.readAsDataURL(file);
            }
        }

        if (files) {
            [].forEach.call(files, readAndPreview);
        }
    }

    static leerImagenes() {
        let misImagenes = document.querySelectorAll("#preview img");
        console.log("cantidad de fotos: " + misImagenes.length);
        // console.log("imagenees: " + misImagenes);
        for (let i = 0; i < misImagenes.length; i++) {
            console.log("foto: " + misImagenes[i].src);
        }
    }
}
document.querySelector("#templateInfParticualar").classList.add("ocultar");
InformeClienteParticular.showListado();

