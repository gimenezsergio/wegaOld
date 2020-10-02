class miMenu {
    static principalBtn() {
        let configurationBtn = document.querySelector("#configurationBtn");
        configurationBtn.setAttribute("onclick", "miMenu.showConfiguration();");

        let principalBtn = document.querySelector("#principalBtn1");
        principalBtn.setAttribute("onclick", "miMenu.hideConfiguration();");

//        let ingresolBtn = document.querySelector("#ingresoBtn");
//        ingresolBtn.setAttribute("onclick", "miMenu.showIngresos();");

        let laboratorioBtn = document.querySelector("#laboratorioBtn");
        laboratorioBtn.setAttribute("onclick", "miMenu.showLaboratorio();");



        let reclamoBtn = document.querySelector("#reclamosBtn");
        reclamoBtn.setAttribute("onclick", "miMenu.showReclamos();");

        let principalBtn2 = document.querySelector("#principalBtn2");
        principalBtn2.setAttribute("onclick", "miMenu.hideReclamos();");

        let principalBtn3 = document.querySelector("#principalBtn3");
        principalBtn3.setAttribute("onclick", "miMenu.hideIngresos();");

        let principalBtn4 = document.querySelector("#principalBtn4");
        principalBtn4.setAttribute("onclick", "miMenu.hideLaboratorio();");
    }

    static toSubMenu() {
        let miUrlReferrer = document.referrer;
        if (miUrlReferrer.includes("clienteGenerico.html") || miUrlReferrer.includes("reclamos.html") || miUrlReferrer.includes("busquedaReclamos.html") || miUrlReferrer.includes("informeClienteParticular.html") || miUrlReferrer.includes("informeGerencial.html")) {
            miMenu.showReclamos();
            console.log("Dentro de submenu");
        } else {
            console.log("FUERA de submenu");
        }

        if (miUrlReferrer.includes("cliente.html")
                || miUrlReferrer.includes("proveedores.html")
                || miUrlReferrer.includes("productos.html")
                || miUrlReferrer.includes("familias.html")
                || miUrlReferrer.includes("productosFamilias.html")
                || miUrlReferrer.includes("diseno.html")
                || miUrlReferrer.includes("proceso.html")
                || miUrlReferrer.includes("falla_material.html")
                ) {
            miMenu.showConfiguration();
            console.log("Dentro de submenu");
        } else {
            console.log("FUERA de submenu");
        }
    }

    static hidePrincipal() {
        document.querySelector("#principalPnl").className += ' hide';
    }
    static showPrincipal() {
        document.querySelector("#principalPnl").classList.remove("hide");
    }
    static showConfiguration() {
        miMenu.hidePrincipal();
        document.querySelector("#confPnl").classList.remove("hide");
    }
    static hideConfiguration() {
        miMenu.showPrincipal();
        document.querySelector("#confPnl").className += ' hide';
    }
    static hideIngresos() {
        document.querySelector("#ingresosPnl").className += ' hide';
        miMenu.showPrincipal();
    }
    static showIngresos() {
        console.log("showIngresos");
        miMenu.hidePrincipal();
        document.querySelector("#ingresosPnl").classList.remove("hide");
    }
    static showLaboratorio() {
        miMenu.hidePrincipal();
        document.querySelector("#labPnl").classList.remove("hide");
    }
    static hideLaboratorio() {
        document.querySelector("#labPnl").className += ' hide';
        miMenu.showPrincipal();
    }
    static hideReclamos() {
        document.querySelector("#reclamoPnl").className += ' hide';
        miMenu.showPrincipal();
    }
    static showReclamos() {
        miMenu.hidePrincipal();
        document.querySelector("#reclamoPnl").classList.remove("hide");
    }

}

miMenu.principalBtn();