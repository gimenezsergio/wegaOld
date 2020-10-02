class Informes {
    
    static showButtons(){
        let buttons = `<h3>Informe Generico</h3>
            <button class="pure-button red" id="btnNew" onclick="Informes.news();">Nuevos</button>
            <button class="pure-button red" id="btnAll" onclick="Informes.all();">Todos</button>`;
        document.querySelector("#pnlInformeGenerico").innerHTML = buttons;
        Ingresos.nuevoHide();
        Ingresos.informeShow();
        Ingresos.ListadoHide();
        Ingresos.bienvenidaHide();
    }
    
    static news(){
        Informes.generico("news");
    }
    
    static all(){
        Informes.generico("all");
    }
    static generico(canti) {
        SpinnerModal.open();
        const traer = async() => {
            let respuesta = await fetch("../Carga?&cant="+canti, {method: 'GET'});
            let precargas = JSON.parse(await respuesta.text());
            localStorage.setItem("cargas", JSON.stringify(precargas));
            console.log(JSON.stringify(precargas));
            let templatePrecargas = await `
            <h3>Informe Generico</h3>
            <button class="pure-button red" id="btnNew" onclick="Informes.news();">Nuevos</button>
            <button class="pure-button red" id="btnAll" onclick="Informes.all();">Todos</button>
    <table class="pure-table pure-table-bordered center">
<thead>            
<tr>
            
<th>Nº orden</th><th>Manifiesto</th><th>Acción</th>
            
</tr>
            </thead>
    ${ precargas.map(precarga =>
                    ` 
              <tr class='persona_item'>
                  <td>${('000' + precarga.maestro.id).slice(-4)}</td>
                  <td>${precarga.maestro.codigoManifiesto}</td>
                  <td> <input class='pure-button-active pure-button' type='button' value='Ver' onclick='Informes.pedirListadoDetalleDiv(${precarga.maestro.id})' /> 
                       </td>
              </tr>
            ${precarga.detalles.map(detalle2 => 
            ` 
            
             
            ` ).join('')}
    `
            ).join('')
                    }
    </table>
`;
            //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
            document.querySelector("#pnlInformeGenerico").innerHTML = templatePrecargas;            
        SpinnerModal.close();
        };
        traer();
        
//                .catch(ex => {
//                    document.querySelector("#pnlListado").innerHTML = 'ERROR: ' + ex.message;
//                });
        
        Ingresos.nuevoHide();
        Ingresos.informeShow();
        Ingresos.ListadoHide();
        Ingresos.bienvenidaHide();
        
    }
    
    static evaluarRetenido2(idMaestro, idDetalle){
        
        const traer = async() => {
            
            
        let listadoPrecargas = JSON.parse(localStorage.getItem("cargas"));
        console.log(listadoPrecargas);

        let precargaSelect = listadoPrecargas.filter(precarga => {
            return precarga.maestro.id == idMaestro
        });

        let templatePrecargas = `
        
        <table class="pure-table pure-table-bordered center class="pure-form"">
            <thead>            
                <tr>
                <th>Codigo</th><th>Cant</th><th>Muestras</th><th colspan=2>Control de recepción</th><th>Cargado</th>
                </tr>
            </thead>
    ${ precargaSelect.map(precarga2 =>
    
                `<ul id='detalleEncabezado' class="pure-form">
                <li class='detalleEncabezado'>Nº orden: <span class='itemMaestro' id='itemMaestroId'>${precarga2.maestro.id}</span></li>
                <li class='detalleEncabezado'>Manifiesto: <span class='itemMaestro'>${precarga2.maestro.codigoManifiesto}</span></li>
                <li class='detalleEncabezado'>Fecha: <span class='itemMaestro'>${precarga2.maestro.fechaManifesto}</span></li>
                <li class='detalleEncabezado'>Proveedor: <span class='itemMaestro'>${precarga2.maestro.proveedor}</span></li>
                <li class='detalleEncabezado'>Recepción: <input type="date" name="fecha_recepcion" value="" id="fecha_recepcion"></li>
            
        </ul>
        
                <tr class='persona_item'>
                  ${precarga2.detalles.map(detalle2 =>
                            `
                            <h1>idDetalle: ${console.log("IdDetalle: " + idDetalle)}</h1>
                    
                    <td>${detalle2.producto}</td> 
                    <td>${detalle2.cant}</td>
                    <td>${detalle2.muestras}</td>
                    
        <td><input class='button-success pure-button ${detalle2.estadoPcking}' type='button' value='Packaging' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></td>
        <td><input class='button-success pure-button ${detalle2.estadoDiseno}' type='button' value='Producto' onclick='Precarga.routerPlanControl("${detalle2.producto}","${detalle2.id}","${detalle2.familiaId}", "${detalle2.subFamiliaId}", "${detalle2.familiaNombre}", "${detalle2.subfamiliaNombre}")' id='btnDiseno${detalle2.id}' /></td>
        <!--td><input class='pure-button' type='button' value='Detalles' onclick="Carga.consultarIndividual(${precarga2.maestro.id})" /></td--!>
        <td><input class='pure-button' type='button' value='Detalles' onclick="Carga.consultarIndividual(${precarga2.maestro.id}, ${detalle2.id}, ${detalle2.productoId})" /></td>
            
        <td><span id="evaluarId${detalle2.id}"> evaluar</span></td>
                </tr>
            
            `
                    ).join('')
                    }
            </table>  
    `
        ).join('')
                }
    
`;
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
    
    static pedirListadoDetalle(idMaestro) {
        let listadoPrecargas = JSON.parse(localStorage.getItem("cargas"));
        console.log(listadoPrecargas);

        let precargaSelect = listadoPrecargas.filter(precarga => {
            return precarga.maestro.id == idMaestro
        });

        let templatePrecargas = `
        <!--h3 id="titleInfGenerico">Control de ingreso</h3-->
        <table class="pure-table pure-table-bordered center class="pure-form"">
            <thead>            
                <tr>
                <th>Codigo</th><th>P</th><th>E</th><th>Lote</th><th>Cant</th><th>Mue</th><th>Observaciones</th>
                </tr>
            </thead>
    ${ precargaSelect.map(precarga2 =>
    
                `


 <ul id='detalleEncabezado' class="pure-form">
                <li class='detalleEncabezado' id='itemMaestroIdPdf'>Nº: <span class='itemMaestro' id='itemMaestroId'>${('000' + precarga2.maestro.id).slice(-4)}</span></li>
                <li class='detalleEncabezado'>Analista: <span class='itemMaestro' id='analistaPdf'>${precarga2.maestro.analista}</span></li>                
                <li class='detalleEncabezado'>Manifiesto: <span class='itemMaestro'>${precarga2.maestro.codigoManifiesto}</span></li>
                <li class='detalleEncabezado'>Fecha: <span class='itemMaestro'>${precarga2.maestro.fechaManifesto}</span></li>
                <li class='detalleEncabezado'>Proveedor: <span class='itemMaestro'>${precarga2.maestro.proveedor}</span></li>
        <li class='detalleEncabezado'>Arribo: <span class='itemMaestro'>${precarga2.maestro.arribo}</span></li>
        </ul>
        
                <tr class='persona_item'>
                  ${precarga2.detalles.map(detalle2 =>
                            `
                    <td><input class='pure-button' type='button' value='${detalle2.producto}' onclick="Carga.consultarIndividual(${precarga2.maestro.id}, ${detalle2.id}, ${detalle2.productoId})" /></td> 
                   
                    
        <!--td><input class='pure-button retenido${detalle2.packaging.hasOwnProperty("retener")}' type='button' value='' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></td-->        
<td><input class='pure-button ${detalle2.packaging.retener}' type='button' value='' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></td>                
<td><input class='pure-button retenido${detalle2.hasOwnProperty("retener")}' type='button' value=''  /></td>
        <!--td><input class='pure-button' type='button' value='Detalles' onclick="Carga.consultarIndividual(${precarga2.maestro.id})" /></td--!>
        
        <td>${detalle2.comentario}</td>
        <td>${detalle2.cant}</td>
        <td>${detalle2.muestras}</td>
        <td>${detalle2.Observaciones}</td>
                </tr>
            `
                    ).join('')
                    }
            </table>
        <button id="btnInfGenericoPdf" class="pure-button" onclick="Informes.pdf();">PDF <i class="fa fa-file-pdf-o" aria-hidden="true"></i></button>
        <div class="footer" id="firmas">
        <img src="../../img/firmas/firmaMariaBravo.png" width="200" id="firmaMariaBravo"/>
                                                <ul>
                                                    <li>María Bravo</li>
                                                    <li>Aprobó</li>
                                                </ul>
        </div>
        
        <div class="" id="notaPie">                    
                <div id="pec">PEC-05-F05 Revisión 01 Mayo 2019</div>
                <!--table class="center">
                <tr>
                <td>Realizó</td>
                <td>Revisó</td>
                <td>Aprobó</td>
                </tr>
                </table-->
                
            </div>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#cuerpoPackaging").innerHTML = templatePrecargas;

        //document.querySelector("#cuerpoPrecarga").outerHTML = "<td>" + precargaSelect[0].maestro.codigoManifiesto + "</td> <td>" + precargaSelect[0].maestro.fechaManifesto + "</td>";



        console.log(precargaSelect[0].maestro.codigoManifiesto);

        document.querySelector('#cargaModal').style.display = 'block';
    }
    
    static pedirListadoDetalleDiv(idMaestro) {
        let listadoPrecargas = JSON.parse(localStorage.getItem("cargas"));
        console.log(listadoPrecargas);

        let precargaSelect = listadoPrecargas.filter(precarga => {
            return precarga.maestro.id == idMaestro
        });

        let templatePrecargas = `
        

    ${ precargaSelect.map(precarga2 =>
    
                `
                
                <table>
            <thead>
                <tr>
                    <td>
                        <div class="header-space"></div>                       
                        <header></header>
                        
                    </td>
                </tr>
            </thead>
            <tbody>

 <ul id='detalleEncabezado' class="">
                <li class='detalleEncabezado' id='itemMaestroIdPdf'>Nº: <span class='itemMaestro' id='itemMaestroId'>${('000' + precarga2.maestro.id).slice(-4)}</span></li>
                <li class='detalleEncabezado'>Analista: <span class='itemMaestro' id='analistaPdf'>${precarga2.maestro.analista}</span></li>                
                <li class='detalleEncabezado'>Manifiesto: <span class='itemMaestro'>${precarga2.maestro.codigoManifiesto}</span></li>
                <li class='detalleEncabezado'>Fecha: <span class='itemMaestro'>${precarga2.maestro.fechaManifesto}</span></li>
                <li class='detalleEncabezado'>Proveedor: <span class='itemMaestro'>${precarga2.maestro.proveedor}</span></li>
        <li class='detalleEncabezado'>Arribo: <span class='itemMaestro'>${precarga2.maestro.arribo}</span></li>
        </ul>
        
        <tr>
<td>
       <div class='pdfItemProduc pdfItemProducHeader'>
                <div>Codigo</div><div>E</div><div>P</div><div>Lote</div><div>Cant</div><div>Mue</div><div>Observaciones</div>
</div> 
        
   </td>     
</tr>                
                  ${precarga2.detalles.map(detalle2 =>
                            `
            <tr>  <td>      
            <div class='persona_item pdfItemProduc'>
                    <div class=''><input class='pure-button' type='button' value='${detalle2.producto}' onclick="Carga.consultarIndividual(${precarga2.maestro.id}, ${detalle2.id}, ${detalle2.productoId})" /></div> 
                   
                    
        <!--td><input class='pure-button retenido${detalle2.packaging.hasOwnProperty("retener")}' type='button' value='' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></td-->        
<div><input class='pure-button ${detalle2.packaging.retener}' type='button' value='' onclick='Precarga.packagingCargar("${detalle2.producto}","${detalle2.id}")' id='btnPck${detalle2.id}'/></div>                
<div><input class='pure-button retenido${detalle2.hasOwnProperty("retener")}' type='button' value=''  /></div>
        <!--td><input class='pure-button' type='button' value='Detalles' onclick="Carga.consultarIndividual(${precarga2.maestro.id})" /></td--!>
        
        <div>${detalle2.comentario}</div>
        <div>${detalle2.cant}</div>
        <div>${detalle2.muestras}</div>
        <div>${detalle2.Observaciones}</div>
                </div>
        </td></tr>
        
        
        
            `
                    ).join('')
                    }
                    
                    </tbody>
            <tfoot>
                <tr>
                    <td>
                        <div class="footer-space"></div>
                        <footer></footer>
                    </td>
                </tr>
            </tfoot>
        </table>
                    
        <button id="btnInfGenericoPdf" class="pure-button" onclick="Informes.pdf();">PDF <i class="fa fa-file-pdf-o" aria-hidden="true"></i></button>
        <div class="" id="firmas">
        <img src="../../img/firmas/firmaMariaBravo.png" width="200" id="firmaMariaBravo"/>
                                                <ul>
                                                    <li>María Bravo</li>
                                                    <li>Aprobó</li>
                                                </ul>
        </div>
        
        <div class="" id="notaPie">                    
                <div id="pec">PEC-05-F05 Revisión 01 Mayo 2019</div>
                <!--table class="center">
                <tr>
                <td>Realizó</td>
                <td>Revisó</td>
                <td>Aprobó</td>
                </tr>
                </table-->
                
            </div>
        
        
        <div class="header">
        <img src="../../img/wega-logoOld.png" alt="Logo Wega" id="logo"/>
            <h3 id="titleInfGenerico">Control de ingreso</h3>
            
            <div id="printPdfDivisor">
                <div id='pec'>PEC-05-F05 Revisión 01 Mayo 2019</div>
            </div>
                
           
        </div>
        <div class="footer"></div>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#cuerpoPackaging").innerHTML = templatePrecargas;

        //document.querySelector("#cuerpoPrecarga").outerHTML = "<td>" + precargaSelect[0].maestro.codigoManifiesto + "</td> <td>" + precargaSelect[0].maestro.fechaManifesto + "</td>";



        console.log(precargaSelect[0].maestro.codigoManifiesto);

        document.querySelector('#cargaModal').style.display = 'block';
    }
    
    static pdf(){
        document.querySelector("#cuerpoPackagingAuxPrint").innerHTML = document.querySelector("#cuerpoPackaging").innerHTML;
        Ingresos.closeCarga();
        window.print();
        document.querySelector("#cuerpoPackagingAuxPrint").innerHTML = "";
    }
    
}

