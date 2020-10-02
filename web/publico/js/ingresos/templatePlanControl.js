class TemplatePlanesControl {
    static aireFrenos(){
        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_aire_frenos", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_aire_frenos", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_aire_frenos", medidas: []},
            {nombre: "Rosca", ItemPlanId: "6", id: "4", id_name: "rosca", plan: "_aire_frenos", medidas: []},
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
        //        
//                                  templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`; 
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaAireFreno()");
    }
    static filtroCumbustibleDiselCartucho() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_diesel_cartucho", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_diesel_cartucho", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_diesel_cartucho", medidas: []},
            {nombre: "Díametro externo de tapa superior", ItemPlanId: "83", id: "4", id_name: "diametro_ext_tapa_sup", plan: "_diesel_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro interno de tapa superior", ItemPlanId: "84", id: "5", id_name: "diametro_int_tapa_sup", plan: "_diesel_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro externo de tapa inferior", ItemPlanId: "85", id: "6", id_name: "diametro_ext_tapa_inf", plan: "_diesel_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Díametro interno de tapa inferior", ItemPlanId: "86", id: "7", id_name: "diametro_int_tapa_inf", plan: "_diesel_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Sello 1 - diámetro interior y sección", ItemPlanId: "68", id: "8", id_name: "sello1", plan: "_diesel_cartucho",  medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Sello 2 - diámetro interior y sección", ItemPlanId: "69", id: "8", id_name: "sello2", plan: "_diesel_cartucho",  medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Sello 3 - diámetro interior y sección", ItemPlanId: "71", id: "6", id_name: "sello3", plan: "_diesel_cartucho",  medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Sello 4 - diámetro interior y sección", ItemPlanId: "73", id: "7", id_name: "sello4", plan: "_diesel_cartucho",  medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},                                    
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "8", id_name: "elemento_filtrante", plan: "_diesel_cartucho", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
        //        
//                                  templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`; 
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroCumbustibleDiselCartucho()");
    }
    
    static filtroCumbustibleDiselSelladaMetalico() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Díametro de carcasa", ItemPlanId: "52", id: "4", id_name: "diametroCarcasa", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Alto total del filtro / con junta", ItemPlanId: "100", id: "5", id_name: "altoTotal", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Rosca brida", ItemPlanId: "101", id: "6", id_name: "rosca_brida", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Diámetro interior de la junta", ItemPlanId: "102", id: "7", id_name: "diametroInterior", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Alto de la junta", ItemPlanId: "103", id: "8", id_name: "altoJunta", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Dureza", ItemPlanId: "104", id: "9", id_name: "dureza", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Rosca purgador / sensor", ItemPlanId: "105", id: "10", id_name: "roscaPurgador", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Rosca vaso racor", ItemPlanId: "106", id: "11", id_name: "roscaVaso", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Vaso decantador", ItemPlanId: "107", id: "12", id_name: "vasoDecantador", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Pico entrada", ItemPlanId: "108", id: "13", id_name: "picoEntrada", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico salida ", ItemPlanId: "109", id: "14", id_name: "picoSalida", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico retorno a filtro", ItemPlanId: "92", id: "15", id_name: "picoRetornoFiltro", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Pico retorno a tanque", ItemPlanId: "93", id: "16", id_name: "picoRetornoTanque", plan: "_DieselSelladaMetalico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Control de temperatura", ItemPlanId: "99", id: "17", id_name: "temperatura", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Calefactor", ItemPlanId: "98", id: "18", id_name: "calefactor", plan: "_DieselSelladaMetalico", medidas: []},
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "19", id_name: "filtrante", plan: "_DieselSelladaMetalico", medidas: []}
            
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs" value=""/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
//        
//                                  templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`; 
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroCumbustibleDiselSelladaMetalico()");
    }

    
    static filtroCumbustibleDiselSelladaPlastico() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Díametro de carcasa", ItemPlanId: "52", id: "4", id_name: "diametroCarcasa", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Alto total del filtro", ItemPlanId: "96", id: "5", id_name: "altoTotal", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Pico entrada / rosca", ItemPlanId: "90", id: "6", id_name: "picoEntrada", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico salida / rosca", ItemPlanId: "91", id: "7", id_name: "picoSalida", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico retorno a filtro", ItemPlanId: "92", id: "8", id_name: "picoRetornoFiltro", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Pico retorno a tanque", ItemPlanId: "93", id: "9", id_name: "picoRetornoTanque", plan: "_DieselSelladaPlastico", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Soporte", ItemPlanId: "97", id: "10", id_name: "sooprte", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Control de temperatura", ItemPlanId: "99", id: "11", id_name: "controlTemp", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Calefactor", ItemPlanId: "98", id: "12", id_name: "calefactor", plan: "_DieselSelladaPlastico", medidas: []},
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "13", id_name: "elemFilrante", plan: "_DieselSelladaPlastico", medidas: []}
            
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
//                          templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`; 
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaDieselSelladaPlastico()");
    }
    
    static filtroCumbustibleNaftaCartucho() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_nafta_cartucho", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_nafta_cartucho", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_nafta_cartucho", medidas: []},
            {nombre: "Díametro externo de tapa superior", ItemPlanId: "83", id: "4", id_name: "diametro_ext_tapa_sup", plan: "_nafta_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro interno de tapa superior", ItemPlanId: "84", id: "5", id_name: "diametro_int_tapa_superior", plan: "_nafta_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro externo de tapa inferior", ItemPlanId: "85", id: "6", id_name: "diametro_ext_tapa_inf", plan: "_nafta_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Díametro interno de tapa inferior", ItemPlanId: "86", id: "7", id_name: "diametro_int_tapa_inf", plan: "_nafta_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Altura total del filtro", ItemPlanId: "67", id: "8", id_name: "altura_total", plan: "_nafta_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
        
//                  templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;        
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaNaftaCartucho()");
    }
    
    static filtroCumbustibleTank() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_nafta_tank", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_nafta_tank", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_nafta_tank", medidas: []},
            {nombre: "Díametro 1", ItemPlanId: "110", id: "4", id_name: "diametro1", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro 2", ItemPlanId: "111", id: "5", id_name: "diametro2", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Altura total del filtro", ItemPlanId: "67", id: "6", id_name: "altura_total", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Díametro conector interno 1", ItemPlanId: "112", id: "7", id_name: "diametro_conector1", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Díametro conector interno 2", ItemPlanId: "113", id: "8", id_name: "diametro_conector2", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro conector interno 3", ItemPlanId: "114", id: "8", id_name: "diametro_conector3", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro alojamiento bomba eléctrica", ItemPlanId: "115", id: "9", id_name: "diametro_alojamiento", plan: "_nafta_tank", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},                        
            {nombre: "Soporte para fijación", ItemPlanId: "95", id: "10", id_name: "soporte_fijacion", plan: "_nafta_tank", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
//        templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;        
                         
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaCumbustibleTank()");
    }
    
    static filtroCumbustibleNaftaUSellada() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_nafta_u_sellada", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_nafta_u_sellada", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_nafta_u_sellada", medidas: []},
            {nombre: "Díametro de carcasa", ItemPlanId: "52", id: "4", id_name: "diametro_carcasa", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Largo total del filtro", ItemPlanId: "89", id: "5", id_name: "largo_tot_fieltro", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Pico entrada / rosca", ItemPlanId: "90", id: "6", id_name: "pico_entrada", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico salida / rosca", ItemPlanId: "91", id: "7", id_name: "pico_salida", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Pico retorno a filtro", ItemPlanId: "92", id: "8", id_name: "pico_ret_fieltro", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Pico retorno a tanque", ItemPlanId: "93", id: "9", id_name: "pico_ret_tanque", plan: "_nafta_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},                        
            {nombre: "Válvula canister", ItemPlanId: "94", id: "10", id_name: "valvula_canister", plan: "_nafta_u_sellada", medidas: []},
            {nombre: "Soporte para fijación", ItemPlanId: "95", id: "11", id_name: "soporte_fijacion", plan: "_nafta_u_sellada", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
        
        
//                          templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let Medida_${filtro.id_name} = {};</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>Medida_${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.Medida_${filtro.id_name} = Medida_${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;        
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaCumbustibleNaftaUSellada()");
    }
    
    static filtroAceiteCartucho() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_filtro_aceite_cartucho", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_filtro_aceite_cartucho", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_filtro_aceite_cartucho", medidas: []},
            {nombre: "Díametro tapa superior", ItemPlanId: "63", id: "4", id_name: "dia_tapa_sup", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro interno tapa superior", ItemPlanId: "64", id: "5", id_name: "dia_int_tapa_sup", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro tapa inferior", ItemPlanId: "65", id: "6", id_name: "dia_tapa_inf", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Díametro interno tapa inferior", ItemPlanId: "66", id: "7", id_name: "dia_int_tapa_inf", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Altura total del filtro", ItemPlanId: "67", id: "8", id_name: "altura_tot_filtro", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            
            {nombre: "Sello 1 - diámetro interior y sección", ItemPlanId: "68", id: "9", id_name: "sello1", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Sello 2 - diámetro interior y sección", ItemPlanId: "69", id: "10", id_name: "sello2", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Sello 3 - diámetro interior y sección", ItemPlanId: "71", id: "11", id_name: "sello3", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Sello 4 - diámetro interior y sección", ItemPlanId: "73", id: "12", id_name: "sello4", plan: "_filtro_aceite_cartucho", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Arandelas", ItemPlanId: "74", id: "13", id_name: "arandelas", plan: "_filtro_aceite_cartucho", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
        
                        </div>
    `
        ).join('')
                }
    
`;
                        
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroAceiteCartucho()");
    }
    
    static filtroAceiteUSellada() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Díametro de carcasa", ItemPlanId: "52", id: "4", id_name: "diametro_carcasa", plan: "_filtro_aceite_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Alto total con Junta", ItemPlanId: "53", id: "5", id_name: "alto_total_junta", plan: "_filtro_aceite_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Díametro de la junta sello", ItemPlanId: "54", id: "6", id_name: "diametro_junta_sello", plan: "_filtro_aceite_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Altura de la junta sello", ItemPlanId: "55", id: "7", id_name: "altura_junta_sello", plan: "_filtro_aceite_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Dureza de la junta sello", ItemPlanId: "56", id: "8", id_name: "dureza_junta_sello", plan: "_filtro_aceite_u_sellada", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Rosca", ItemPlanId: "6", id: "9", id_name: "rosca", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Brida", ItemPlanId: "58", id: "10", id_name: "brida", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Flapper", ItemPlanId: "59", id: "11", id_name: "flapper", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Válvula de descarga", ItemPlanId: "60", id: "12", id_name: "valvula_descarga", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Válvula de seguridad", ItemPlanId: "61", id: "13", id_name: "valvula_seguridad", plan: "_filtro_aceite_u_sellada", medidas: []},
            {nombre: "Tuerca de fijación/extracción", ItemPlanId: "62", id: "14", id_name: "tuerca_fijacion", plan: "_filtro_aceite_u_sellada", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroAceiteUSellada()");
    }
    
    static filtroAirePesados() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_filtro_aire_pesados", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_filtro_aire_pesados", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_filtro_aire_pesados", medidas: []},
            {nombre: "Diámetro exterior tapa superior", ItemPlanId: "41", id: "4", id_name: "diametro_ext_tapa_sup", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro/agujero tapa superior", ItemPlanId: "49", id: "5", id_name: "diametro_agujero_tapa_superior", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro exterior base inferior", ItemPlanId: "43", id: "6", id_name: "diametro_ext_base_inf", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Diámetro/agujero base inferior", ItemPlanId: "50", id: "7", id_name: "diametro_agujero_base_inf", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Altura total", ItemPlanId: "45", id: "8", id_name: "altura_total", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro exterior junta sello", ItemPlanId: "46", id: "9", id_name: "diametro_ext_junta_sello", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro interior junta sello", ItemPlanId: "47", id: "10", id_name: "diametro_int_junta_sello", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Altura junta sello", ItemPlanId: "48", id: "11", id_name: "altura_junta_sello", plan: "_filtro_aire_pesados", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
        
//                 let templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let ${filtro.id_name} = {};</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.${filtro.id_name} = ${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;
        
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.filtrosAirePesadosAlta()");
    }
    static filtroAireRedondo() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_filtro_aire_redondos", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_filtro_aire_redondos", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_filtro_aire_redondos", medidas: []},
            {nombre: "Diámetro exterior tapa superior", ItemPlanId: "41", id: "4", id_name: "diametro_ext_tapa_sup", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro interior tapa superior", ItemPlanId: "42", id: "5", id_name: "diametro_int_tapa_sup", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro exterior base inferior", ItemPlanId: "43", id: "6", id_name: "diametro_ext_base_inf", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},            
            {nombre: "Diámetro interior base inferior", ItemPlanId: "44", id: "7", id_name: "diametro_int_base_inf", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Altura total", ItemPlanId: "45", id: "8", id_name: "altura_total", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro exterior junta sello", ItemPlanId: "46", id: "9", id_name: "diametro_ext_junta_sello", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Diámetro interior junta sello", ItemPlanId: "47", id: "10", id_name: "diametro_int_junta_sello", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Altura junta sello", ItemPlanId: "48", id: "11", id_name: "altura_junta_sello", plan: "_filtro_aire_redondos", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "12", id_name: "elem_filtrante", plan: "_filtro_aire_redondos", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        
//          templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let ${filtro.id_name} = {};</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.${filtro.id_name} = ${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.filtroAireRedondo()");
    }
    
    static filtroAireHabitaculo() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_filtro_aire_habit", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_filtro_aire_habit", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_filtro_aire_habit", medidas: []},
            {nombre: "Largo", ItemPlanId: "34", id: "4", id_name: "largo", plan: "_filtro_aire_habit", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Alto", ItemPlanId: "35", id: "5", id_name: "alto", plan: "_filtro_aire_habit", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Ancho", ItemPlanId: "36", id: "6", id_name: "Ancho", plan: "_filtro_aire_habit", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "6", id_name: "elem_filtrante", plan: "_filtro_aire_habit", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroAireHabitaculo()");
    }
    
    static filtroAirePanel() {

        let filtros = [
            {nombre: "Identificación", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_FAP", medidas: []},
            {nombre: "Aspecto visual", ItemPlanId: "33", id: "2", id_name: "Aspecto_visual", plan: "_FAP", medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "3", id_name: "lote", plan: "_FAP", medidas: []},
            {nombre: "Largo 1", ItemPlanId: "75", id: "4", id_name: "largo1", plan: "_FAP",medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Largo 2", ItemPlanId: "76", id: "5", id_name: "largo2", plan: "_FAP", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Ancho 1", ItemPlanId: "77", id: "6", id_name: "ancho1", plan: "_FAP", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Ancho 2", ItemPlanId: "78", id: "7", id_name: "ancho2", plan: "_FAP", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Alto", ItemPlanId: "35", id: "8", id_name: "alto", plan: "_FAP", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Linea de hotmel", ItemPlanId: "79", id: "9", id_name: "hotmel", plan: "_FAP", medidas: []},
            {nombre: "Manto metálico / plástico", ItemPlanId: "80", id: "10", id_name: "manto", plan: "_FAP", medidas: []},
            {nombre: "Prefiltro", ItemPlanId: "81", id: "11", id_name: "prefiltro", plan: "_FAP", medidas: []},
            {nombre: "Manija", ItemPlanId: "82", id: "12", id_name: "Manija", plan: "_FAP", medidas: []},
            {nombre: "Elemento filtrante", ItemPlanId: "40", id: "13", id_name: "filtrante", plan: "_FAP", medidas: []}
            ];


        let templatePrecargas = `
    
    ${ filtros.map(filtro =>
                `
                        <div class="container">
                            <span id="planId_${filtro.id_name}${filtro.plan}" data-planitemid=${filtro.ItemPlanId}>${filtro.nombre}:</span>
                            
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}1" value="1"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}2" value="2"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${filtro.id_name}${filtro.plan}" id="${filtro.id_name}${filtro.plan}3" value="3"/>
                                <label class="button-label" for="${filtro.id_name}${filtro.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                
        
                                <input type="text" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_obs"/>
                                ${filtro.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${filtro.id_name}${filtro.plan}_${medida.nombre}" min="1" data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;


    



//    let      templatePrecargas = `
//    
//    ${ filtros.map(filtro =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${filtro.id_name}${filtro.plan} = Packaging.getCheckedRadio("${filtro.id_name}${filtro.plan}");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_id = document.querySelector("#planId_${filtro.id_name}${filtro.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${filtro.id_name}${filtro.plan}_obs = document.querySelector("#${filtro.id_name}${filtro.plan}_obs").value;</p>
//        
//                                ${filtro.medidas.map( medida => 
//                                    `
//                                    <p>let ${filtro.id_name} = {};</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre} = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").value;</p>
//                                    <p>${filtro.id_name}.${filtro.id_name}${filtro.plan}_${medida.nombre}_id = document.querySelector("#${filtro.id_name}${filtro.plan}_${medida.nombre}").getAttribute("data-${filtro.id_name}${filtro.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.${filtro.id_name} = ${filtro.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;



        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaFiltroFAP()");
    }
    
    static lamparas() {
        let lamparas = [
            {nombre: "Identificacion", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_lamparas"},
            {nombre: "Lote", ItemPlanId: "5", id: "2", id_name: "lote", plan: "_lamparas"},
            {nombre: "Diseño", ItemPlanId: "29", id: "3", id_name: "diseno", plan: "_lamparas"},
            {nombre: "Zocalo", ItemPlanId: "30", id: "4", id_name: "zocalo", plan: "_lamparas"},
            {nombre: "Tensión", ItemPlanId: "31", id: "5", id_name: "tension", plan: "_lamparas"},
            {nombre: "Potencia", ItemPlanId: "32", id: "6", id_name: "potencia", plan: "_lamparas"},
            {nombre: "CHAS", ItemPlanId: "22", id: "7", id_name: "chas", plan: "_lamparas"}];


        let templatePrecargas = `
    
    ${ lamparas.map(lampara =>
                `
                        <div class="container">
                            <span id="planId_${lampara.id_name}${lampara.plan}" data-planitemid=${lampara.ItemPlanId}>${lampara.nombre}:</span>
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${lampara.id_name}${lampara.plan}" id="${lampara.id_name}${lampara.plan}1" value="1"/>
                                <label class="button-label" for="${lampara.id_name}${lampara.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${lampara.id_name}${lampara.plan}" id="${lampara.id_name}${lampara.plan}2" value="2"/>
                                <label class="button-label" for="${lampara.id_name}${lampara.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${lampara.id_name}${lampara.plan}" id="${lampara.id_name}${lampara.plan}3" value="3"/>
                                <label class="button-label" for="${lampara.id_name}${lampara.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                <input type="text" class="obsIndividuales" id="${lampara.id_name}${lampara.plan}_obs"/>
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaLamparas()");

    }
    static bujiasEncendido() {

        let bujias = [
            {nombre: "Identificacion", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_bujia_encendido",medidas: []},
            {nombre: "Lote", ItemPlanId: "5", id: "2", id_name: "lote", plan: "_bujia_encendido",medidas: []},
            {nombre: "Rosca", ItemPlanId: "6", id: "3", id_name: "rosca", plan: "_bujia_encendido",medidas: []},
            {nombre: "Largo de rosca", ItemPlanId: "23", id: "4", id_name: "largo_rosca", plan: "_bujia_encendido",medidas: []},
            {nombre: "Hexagono", ItemPlanId: "13", id: "5", id_name: "hexagono", plan: "_bujia_encendido",medidas: []},
            {nombre: "Asiento", ItemPlanId: "24", id: "6", id_name: "asiento", plan: "_bujia_encendido",medidas: []},
            {nombre: "Resistor", ItemPlanId: "25", id: "7", id_name: "resistor", plan: "_bujia_encendido", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Rango termico", ItemPlanId: "26", id: "8", id_name: "rango_termico", plan: "_bujia_encendido",medidas: []},
            {nombre: "Posicion del electrodo", ItemPlanId: "27", id: "9", id_name: "posicion_electrodo", plan: "_bujia_encendido",medidas: []},
            {nombre: "Cantidad de electrodos", ItemPlanId: "28", id: "10", id_name: "cantidad_electrodos", plan: "_bujia_encendido",medidas: []}];


        let templatePrecargas = `
    
    ${ bujias.map(bujia =>
                `
                        <div class="container">
                            <span id="planId_${bujia.id_name}${bujia.plan}" data-planitemid=${bujia.ItemPlanId}>${bujia.nombre}:</span>
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}1" value="1"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}2" value="2"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}3" value="3"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                <input type="text" class="obsIndividuales" id="${bujia.id_name}${bujia.plan}_obs"/>
                                ${bujia.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${bujia.id_name}${bujia.plan}_${medida.nombre}" min="1" data-${bujia.id_name}${bujia.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }
    
`;


//        let      templatePrecargas = `
//    
//    ${ bujias.map(bujia =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${bujia.id_name}${bujia.plan} = Packaging.getCheckedRadio("${bujia.id_name}${bujia.plan}");</p>
//        <p>datosDiseno.${bujia.id_name}${bujia.plan}_id = document.querySelector("#planId_${bujia.id_name}${bujia.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${bujia.id_name}${bujia.plan}_obs = document.querySelector("#${bujia.id_name}${bujia.plan}_obs").value;</p>
//        
//                                ${bujia.medidas.map( medida => 
//                                    `
//                                    <p>let ${bujia.id_name} = {};</p>
//                                    <p>${bujia.id_name}.${bujia.id_name}${bujia.plan}_${medida.nombre} = document.querySelector("#${bujia.id_name}${bujia.plan}_${medida.nombre}").value;</p>
//                                    <p>${bujia.id_name}.${bujia.id_name}${bujia.plan}_${medida.nombre}_id = document.querySelector("#${bujia.id_name}${bujia.plan}_${medida.nombre}").getAttribute("data-${bujia.id_name}${bujia.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.${bujia.id_name} = ${bujia.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaBujiaEncendido()");
    }

    static bujiasPrecalentamiento() {

        let bujias = [
            {nombre: "Identificacion", ItemPlanId: "4", id: "1", id_name: "identificacion", plan: "_bujia_preca", medidas: []},
            {nombre: "Lote", id: "2", ItemPlanId: "5", id_name: "lote", plan: "_bujia_preca", medidas: []},
            {nombre: "Rosca", ItemPlanId: "6", id: "3", id_name: "rosca", plan: "_bujia_preca", medidas: []},
            {nombre: "Hexagono", ItemPlanId: "13", id: "4", id_name: "hexagono", plan: "_bujia_preca", medidas: []},
            {nombre: "Longuitud L1", ItemPlanId: "14", id: "5", id_name: "longuitudL1", plan: "_bujia_preca", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Longuitud L2", ItemPlanId: "15", id: "7", id_name: "longuitudL2", plan: "_bujia_preca", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Longuitud L3", ItemPlanId: "16", id: "8", id_name: "longuitudL3", plan: "_bujia_preca", medidas: [{nombre :"Nom", valor:"", id : 3}, {nombre :"Min", valor:"", id : 2}, {nombre :"Max", valor:"", id : 1}]},
            {nombre: "Perno de conexión", ItemPlanId: "17", id: "9", id_name: "perno_conexion", plan: "_bujia_preca", medidas: []}];


        let templatePrecargas = `
    
    ${ bujias.map(bujia =>
                `
                        <div class="container">
                            <span id="planId_${bujia.id_name}${bujia.plan}" data-planitemid=${bujia.ItemPlanId}>${bujia.nombre}:</span>
                            <div class="button-wrap">
                                <input class="hidden radio-label yes-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}1" value="1"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}1">
                                    <h1>OK</h1>
                                </label>
                                <input class="hidden radio-label no-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}2" value="2"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}2">
                                    <h1>No OK</h1>
                                </label>
                                <input class="hidden radio-label maybe-button" type="radio" name="${bujia.id_name}${bujia.plan}" id="${bujia.id_name}${bujia.plan}3" value="3"/>
                                <label class="button-label" for="${bujia.id_name}${bujia.plan}3">
                                    <h1>N/A</h1>
                                </label>
                                <input type="text" class="obsIndividuales" id="${bujia.id_name}${bujia.plan}_obs"/>
                                ${bujia.medidas.map( medida => 
                                    `
                                    ${medida.nombre} <input type="number" class="obsIndividuales" id="${bujia.id_name}${bujia.plan}_${medida.nombre}" min="1" data-${bujia.id_name}${bujia.plan}_${medida.nombre}_id = ${medida.id}  style="width: 70px"/>
                                    `
                                 ).join('')}
                            </div>
                        </div>
    `
        ).join('')
                }             
    
`;
        
//            let      templatePrecargas = `
//    
//    ${ bujias.map(bujia =>
//                `
//                        <div class="container">
//                            
//                                <p>
//            datosDiseno.${bujia.id_name}${bujia.plan} = Packaging.getCheckedRadio("${bujia.id_name}${bujia.plan}");</p>
//        <p>datosDiseno.${bujia.id_name}${bujia.plan}_id = document.querySelector("#planId_${bujia.id_name}${bujia.plan}").getAttribute("data-planitemid");</p>
//        <p>datosDiseno.${bujia.id_name}${bujia.plan}_obs = document.querySelector("#${bujia.id_name}${bujia.plan}_obs").value;</p>
//        
//                                ${bujia.medidas.map( medida => 
//                                    `
//                                    <p>let ${bujia.id_name} = {};</p>
//                                    <p>${bujia.id_name}.${bujia.id_name}${bujia.plan}_${medida.nombre} = document.querySelector("#${bujia.id_name}${bujia.plan}_${medida.nombre}").value;</p>
//                                    <p>${bujia.id_name}.${bujia.id_name}${bujia.plan}_${medida.nombre}_id = document.querySelector("#${bujia.id_name}${bujia.plan}_${medida.nombre}").getAttribute("data-${bujia.id_name}${bujia.plan}_${medida.nombre}_id");</p>
//                                    <p>datosDiseno.${bujia.id_name} = ${bujia.id_name};</p>
//                                    `
//                                 ).join('')}
//                            </div>
//        
//                        </div>
//    `
//        ).join('')
//                }
//    
//`;
        //    document.querySelector('#panelResultados').innerHTML = await eval(templatePersonas);
        document.querySelector("#planDisenoContent").innerHTML = templatePrecargas;
        document.querySelector("#grabarPlanDiseno").setAttribute("onclick", "PlanControlDiseno.altaBujiaPreca()");
    }
}
