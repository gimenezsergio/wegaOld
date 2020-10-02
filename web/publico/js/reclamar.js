/* global request */

RECLAMAR = {};

RECLAMAR.primero = function(){
    var losInput = document.querySelectorAll("#loginForm input");
    for(var indice=0 ; indice < losInput.length; indice++){
        losInput[indice].nombre = losInput[indice].name;
        losInput[indice].valor = losInput[indice].value;    
    }
 //   document.querySelector("#panelOK").innerHTML = JSON.stringify(losInput);
    
    var xhr = new XMLHttpRequest();
    // GET y DELETE, los parametros por http URL
    // POST y PUT, los parametros por http request body
    xhr.open("POST", "../reclamo");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if( xhr.status === 200){
                // caso de exito
  //              document.querySelector("#contenedores").innerHTML = xhr.responseText;
                // request.getRequestDispatcher("principal.html").forward(request, response);
       
               }else{
                //caso de error
   //             document.querySelector("#contenedores").innerHTML = " un texto cualquieraa";
         //   window.location="principal.html"; done=1; 
            }
            
        }else{
            //caso de error
        }
        
    };
    xhr.send( JSON.stringify( losInput ) );
    
    
};

RECLAMAR.replicaCamposAgregarReclamosNoConforme = function(){
    
   var losInput = document.querySelectorAll("#Formulario input");
    for(var indice=0 ; indice < losInput.length; indice++){
        losInput[indice].nombre = losInput[indice].name;
        losInput[indice].valor = losInput[indice].value;    
    }
 //   document.querySelector("#panelOK").innerHTML = JSON.stringify(losInput);
    
    var xhr = new XMLHttpRequest();
    // GET y DELETE, los parametros por http URL
    // POST y PUT, los parametros por http request body
    xhr.open("POST", "/../reclamo");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if( xhr.status === 200){
                // caso de exito
 //               document.querySelector("#contenedor").innerHTML = xhr.responseText;
                
            }else{
                //caso de error
//                document.querySelector("#contenedor").innerHTML = " un texto cualquieraa";
            }
            
        }else{
            //caso de error
        }
        
    };
    xhr.send( JSON.stringify( losInput ) );
    
    
}; 
RECLAMAR.cargar = function(){
     
    var xhr = new XMLHttpRequest();
    // GET y DELETE, los parametros por http URL
    // POST y PUT, los parametros por http request body
    xhr.open("GET", "../reclamo");
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4){
            if( xhr.status === 200){
                // caso de exito
                 var reclamo = JSON.parse(xhr.responseText);
                var textoPlantilla =" `<input list='electo' name='electo'/> <datalist name='ver' id='electo' onchange='RECLAMAR.cargando()'> ${reclamo.map( reclamo =>  ` <option value='${reclamo.nro_reclamo}'>${reclamo.nro_reclamo}</option> ` ).join('') } </datalist> `; " ;
                document.querySelector('#inputSelectReclamo').innerHTML = eval( textoPlantilla );
            }else{
                //caso de error
//                document.querySelector("#contenedores").innerHTML += " un texto cualquieraa";
            }           
        }else{
            //caso de error
        }        
    };
     var reclamo = {};
    reclamo.id_reclamo = document.querySelector("#id_reclamo").value;
    reclamo.nro_reclamo = document.querySelector("#re_nro_reclamo").value;
   
    var reclamoStringJSON = JSON.stringify(reclamo);
    xhr.send( JSON.stringify(reclamoStringJSON  ) );
    

};
RECLAMAR.cargando = function(){
    document.querySelector('#reclamo').setAttribute('value', document.querySelector('#electo').value);
};