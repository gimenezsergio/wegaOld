class informes {
    static setButtons(){
        let but = document.querySelector("#btnPrint");
        but.setAttribute("onclick","informes.imprimir()");
    }
    
    static imprimir(){
        console.log("imprimir");
        window.print("prueba.pdf");
    }
    static alta() {
        let txt = "";
        let myObj = JSON.parse(localStorage.getItem("json"));
        console.log("obj " + myObj);

        //  txt += "<table border='1'>"
        // txt += "<tr><td>" + myObj.encabezado.cliente + "</td></tr>";
        document.querySelector("#cliente").value = myObj.encabezado.cliente_nombre;
        document.querySelector("#fecha_emision").value = myObj.encabezado.fecha_emision;
        document.querySelector("#fecha_recepcion").value = myObj.encabezado.fecha_recepcion;

        console.log("objeto reclamo length: " + myObj.productos.length);
        if (myObj.productos.length >= 2) {
            let i = 0;
            let x;
            for (x in myObj) {
                //console.log("i primero" + i);
                //console.log("objeto " + myObj ) ;
                console.log("objeto produc" + myObj.productos[0].id_prod);
                let productNumber = i + 1;
                txt += "<article id='productItem' class='pure-form productItemContent product'>";
                txt += "<h3>Producto " + productNumber + " </h3>";
                txt += "<div class='flex-containerProducto pure-form'>";

                txt += "<div class='flex leftProduct'>";

                txt += "<label>Producto </label> <input readonly='' name='producto' id='producto' value='" + myObj.productos[i].id_prod + " '/> <span id='inputSelectProducto'></span>";
                txt += "<label>Cantidad </label><input readonly='' type='number' min='0' name='cantidad_total' placeholder='cant.total' value='" + myObj.productos[i].cantidad_total + "' size='3'>";
                txt += "<label>Motivo </label> <input readonly='' type='text' name='Motivo' value='" + myObj.productos[i].Motivo + "'>";
                txt += "</div>";
                txt += "<div class='flex rightProduct'>";

                txt += "<label>Origen </label> <input name='origen' readonly='' id='origenes' value='" + myObj.productos[i].elsel + "'/>";
                txt += "<label>Lote </label> <input type='text' name='lote' readonly='' value='" + myObj.productos[i].lote + "'>";
                txt += "</div>";
                txt += "</div>";
                txt += "<h3>Verificación</h3>";
                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftProduct'>";
                txt += "<label>Verificación </label> <input name='verificacion' readonly='' id='verificado' value='" + myObj.productos[i].verif_id + "'/>";
                txt += "</div>";
                txt += "<div class='flex rightProduct'>";
                txt += "<label>Resultado </label> <input name='resultado' id='resultado' value='" + myObj.productos[i].elseletor + "' readonly/>";
                txt += "</div>";
                txt += "</div>";
                txt += "<h3>Diagnóstico</h3>";
                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftC'>";
                txt += "<label>Diseño</label>";
                txt += "<input type='text' name='diseno' id='diseno' value='" + myObj.productos[i].diseno + "' readonly=''>";


                txt += "</div>";

                txt += "<div class='flex rightC'>";
                txt += "<label>Proceso</label>";
                txt += "<input type='text' name='proceso' id='proceso' value='" + myObj.productos[i].proceso + "' readonly=''>";
                txt += "<label>Observación </label>";
                txt += "<textarea name='observacion' rows='5' cols='40' readonly=''>" + myObj.productos[i].observacion + "</textarea>";
                txt += "</div>";
                txt += "<div class='flex tres'>";
                txt += "<label>Falla De Materiales</label>";
                txt += "<input type='text' name='falla_materiales' id='falla_materiales' readonly='' value='" + myObj.productos[i].falla_materiales + "'>";
                txt += "</div>";

                txt += "</div>";
                txt += "<h3>Dictamen</h3>";


                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftC'>";
                txt += "<label>Fundados </label>";
                txt += "<input type='number' name='fundados' id='fundados' value='" + myObj.productos[i].fundados + "' readonly=''>";
                txt += "</div>"; //"<div class='flex leftC'>";

                txt += "<div class='flex rightC'><label>Infudados </label><input type='number' name='infundados' id='infundados' value='" + myObj.productos[i].infundados + "' readonly=''></div>";
                txt += "<div class='flex tres'><label>Tema Comercial </label><input type='number' name='cant_tema_comercial' id='cant_tema_comercial'value='" + myObj.productos[i].cant_tema_comercial + "' readonly=''></div>";
                txt += "</div>"; //"<div class='flex-containerProducto pure-form'>";
                txt += "</div>";

                txt += "</article>";



                console.log("i " + i);
                i++;
            }
            //   txt += "</table>"
            document.getElementById("demo").innerHTML = txt;
        } else {
            let i = 0;
            let x;
            
             
                let productNumber = i + 1;
                txt += "<article id='productItem' class='pure-form productItemContent product'>";
                txt += "<h3>Producto " + productNumber + " </h3>";
                txt += "<div class='flex-containerProducto pure-form'>";

                txt += "<div class='flex leftProduct'>";

                txt += "<label>Producto </label> <input readonly='' name='producto' id='producto' value='" + myObj.productos[0].id_prod + " '/> <span id='inputSelectProducto'></span>";
                txt += "<label>Cantidad </label><input readonly='' type='number' min='0' name='cantidad_total' placeholder='cant.total' value='" + myObj.productos[0].cantidad_total + "' size='3'>";
                txt += "<label>Motivo </label> <input readonly='' type='text' name='Motivo' value='" + myObj.productos[0].Motivo + "'>";
                txt += "</div>";
                txt += "<div class='flex rightProduct'>";

                txt += "<label>Origen </label> <input name='origen' readonly='' id='origenes' value='" + myObj.productos[0].elsel + "'/>";
                txt += "<label>Lote </label> <input type='text' name='lote' readonly='' value='" + myObj.productos[0].lote + "'>";
                txt += "</div>";
                txt += "</div>";
                txt += "<h3>Verificación</h3>";
                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftProduct'>";
                txt += "<label>Verificación </label> <input name='verificacion' readonly='' id='verificado' value='" + myObj.productos[0].verif_id + "'/>";
                txt += "</div>";
                txt += "<div class='flex rightProduct'>";
                txt += "<label>Resultado </label> <input name='resultado' id='resultado' value='" + myObj.productos[0].elseletor + "' readonly/>";
                txt += "</div>";
                txt += "</div>";
                txt += "<h3>Diagnóstico</h3>";
                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftC'>";
                txt += "<label>Diseño</label>";
                txt += "<input type='text' name='diseno' id='diseno' value='" + myObj.productos[0].diseno + "' readonly=''>";


                txt += "</div>";

                txt += "<div class='flex rightC'>";
                txt += "<label>Proceso</label>";
                txt += "<input type='text' name='proceso' id='proceso' value='" + myObj.productos[0].proceso + "' readonly=''>";
                txt += "<label>Observación </label>";
                txt += "<textarea name='observacion' rows='5' cols='40' readonly=''>" + myObj.productos[0].observacion + "</textarea>";
                txt += "</div>";
                txt += "<div class='flex tres'>";
                txt += "<label>Falla De Materiales</label>";
                txt += "<input type='text' name='falla_materiales' id='falla_materiales' readonly='' value='" + myObj.productos[0].falla_materiales + "'>";
                txt += "</div>";

                txt += "</div>";
                txt += "<h3>Dictamen</h3>";


                txt += "<div class='flex-containerProducto pure-form'>";
                txt += "<div class='flex leftC'>";
                txt += "<label>Fundados </label>";
                txt += "<input type='number' name='fundados' id='fundados' value='" + myObj.productos[0].fundados + "' readonly=''>";
                txt += "</div>"; //"<div class='flex leftC'>";

                txt += "<div class='flex rightC'><label>Infudados </label><input type='number' name='infundados' id='infundados' value='" + myObj.productos[0].infundados + "' readonly=''></div>";
                txt += "<div class='flex tres'><label>Tema Comercial </label><input type='number' name='cant_tema_comercial' id='cant_tema_comercial'value='" + myObj.productos[0].cant_tema_comercial + "' readonly=''></div>";
                txt += "</div>"; //"<div class='flex-containerProducto pure-form'>";
                txt += "</div>";

                txt += "</article>";



                console.log("i " + i);
                i++;
            
            //   txt += "</table>"
            document.getElementById("demo").innerHTML = txt;
        }
    }

}

informes.alta();
informes.setButtons();