class Ingresos {
    static nuevo(){
        Ingresos.ListadoHide();
        Ingresos.informeHide();
        Ingresos.nuevoShow();
        Ingresos.bienvenidaHide();
    }

    static informeShow(){
        document.querySelector("#pnlInformeGenerico").classList.add('pnlShow');
        document.querySelector("#pnlInformeGenerico").classList.remove('pnlHide');
    }
    
    static informeHide(){
        document.querySelector("#pnlInformeGenerico").classList.add('pnlHide');
        document.querySelector("#pnlInformeGenerico").classList.remove('pnlShow');
    }
    
    static bienvenidaHide(){
        document.querySelector("#pnlBienvenida").classList.add('pnlHide');
        document.querySelector("#pnlBienvenida").classList.remove('pnlBienvenidaCss');
        
    }
    
    static nuevoShow(){
        document.querySelector("#pnlNuevo").classList.add('pnlShow');
        document.querySelector("#pnlNuevo").classList.remove('pnlHide');
    }
    
    
    static nuevoHide(){
        document.querySelector("#pnlNuevo").classList.add('pnlHide');
        document.querySelector("#pnlNuevo").classList.remove('pnlShow');
    }
    
    static ListadoShow(){
        document.querySelector("#pnlListado").classList.add('pnlShow');
        document.querySelector("#pnlListado").classList.remove('pnlHide');
    }
    
    static ListadoHide(){
        document.querySelector("#pnlListado").classList.add('pnlHide');
        document.querySelector("#pnlListado").classList.remove('pnlShow');
    }
    
    static setBtn(){
        document.querySelector("#nuevoBtn").setAttribute("onclick","Ingresos.nuevo();");
    }
    
    static closeCarga(){
        document.querySelector('#cargaModal').style.display = 'none';
        console.log("close carga");
        
    }
    
    static closePackaging() {
        document.querySelector('#packagingModal').style.display = 'none';
        let radiosPackaging = ["col_cod_barra", "ind_cod_barra", "ind_ident", "ind_equiv", "ind_aplica","ind_chas"];
        for (let x = 0; x <= radiosPackaging.length; x++) {
            Packaging.clearCheckRadio(radiosPackaging[x]);
        }
        document.querySelector("#pkg_col_cod_barra_obs").value = "";
        document.querySelector("#pkg_ind_cod_barra_obs").value = "";
        document.querySelector("#pkg_ind_ident_obs").value = "";
        document.querySelector("#pkg_ind_equiv_obs").value = "";
        document.querySelector("#pkg_ind_aplica_obs").value = "";

        console.log("a limpiar radio");
        
    }
    
    static closeDiseno() {
        document.querySelector('#DisenoModal').style.display = 'none';
    }
    
    static closeMedidas() {
        document.querySelector('#MedidasModal').style.display = 'none';
    }
    
    static closeControlarPlan() {
        document.querySelector('#controlarPlanModal').style.display = 'none';
    }
    
    
    static setOpt(){
//        document.querySelector("#precargaOpt").setAttribute("onclick","Ingresos.precargaOpt();");
//        document.querySelector("#packingOpt").setAttribute("onclick","Ingresos.packingOpt();");
//        document.querySelector("#dimenOpt").setAttribute("onclick","Ingresos.dimenOpt();");
//        document.querySelector("#finOpt").setAttribute("onclick","Ingresos.finOpt();");
    }
    
    static rmAllClassActive(){
        document.querySelector("#precargaOpt").classList.remove('is-active');
        document.querySelector("#packingOpt").classList.remove('is-active');
        document.querySelector("#dimenOpt").classList.remove('is-active');
        document.querySelector("#finOpt").classList.remove('is-active');
    }
    
    static precargaOpt(){
        Ingresos.rmAllClassActive();
        document.querySelector("#precargaOpt").classList.add('is-active');
        //document.querySelector("#cargaCuerpo").innerHTML = "<div id='nuevo'> <article> <label>Recepción</label> <input type='date' name='fecha_emision' value='' id='fecha_emision'> </article> <article> <label>Manifiesto </label> <input type='date' name='fecha_emision' value='' id='fecha_emision'> </article> <article> <label>Codigo</label> <input name='id_falla_mate' id='id_falla_mate'/> </article>   <article> <label>Prove</label> <input name='id_falla_mate' id='id_falla_mate'/> </article>  <article> <label>Muestras </label> <input name='id_falla_mate' id='id_falla_mate'/> </article> </div>";
    }
    static packingOpt(){
        Ingresos.rmAllClassActive();
        document.querySelector("#packingOpt").classList.add('is-active');
      document.querySelector("#cuerpoPackaging").innerHTML = "<div id='nuevo'> <article> <label><input class='checkboxPking' type='checkbox' name='vehicle1' value='Bike'> Dimensional</label>  </article> <article> <label><input class='checkboxPking' type='checkbox' name='vehicle1' value='Bike'> Arte Packaging</label> </article> <article> <label><input class='checkboxPking' type='checkbox' name='vehicle1' value='Bike'> Codigo de barra</label> </article> <article> <label><input class='checkboxPking' type='checkbox' name='vehicle1' value='Bike'> Aspecto visual</label>  </article> <article> <label><input class='checkboxPking' type='checkbox' name='vehicle1' value='Bike'> Colores </label> </article> </div>";
    }
    static dimenOpt(){
        Ingresos.rmAllClassActive();
        document.querySelector("#dimenOpt").classList.add('is-active');
        //document.querySelector("#cargaCuerpo").innerHTML = "<div id='nuevo'> <article> <label>Largo</label> <input type='text' name='fecha_emision' value='' id='fecha_emision'> </article> <article> <label>Ancho </label> <input type='text' name='fecha_emision' value='' id='fecha_emision'> </article> <article> <label>Altoo</label> <input name='id_falla_mate' id='id_falla_mate'/> </article> <article> <label>Cantidad</label> <input name='id_falla_mate' id='id_falla_mate'/> </article> <article> <label>Dureza de PUR</label> <input name='id_falla_mate' id='id_falla_mate'/> </article> </div>";
    }
    static finOpt(){
        Ingresos.rmAllClassActive();
        document.querySelector("#finOpt").classList.add('is-active');
        //document.querySelector("#cargaCuerpo").innerHTML = "Fin";
    }
    
    static init(){
//         document.querySelector("#nuevo").classList.add('hide');
//          document.querySelector("#modificar").classList.add('hide');
          document.querySelector("#cargaBtn").setAttribute("onclick","Ingresos.closeCarga();");
          document.querySelector("#packagingClose").setAttribute("onclick","Ingresos.closePackaging();");
          document.querySelector("#planDisenoClose").setAttribute("onclick","Ingresos.closeDiseno();");
          document.querySelector("#controlarPlanClose").setAttribute("onclick","Ingresos.closeControlarPlan();");
          document.querySelector("#informeBtn").setAttribute("onclick","Informes.showButtons();");
          document.querySelector("#MedidasClose").setAttribute("onclick","Ingresos.closeMedidas();");  
        
          
          Ingresos.nuevoHide();
          Ingresos.setOpt();
    }
}

Ingresos.setBtn();
Ingresos.init();