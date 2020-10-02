class Validator {
    static dictamen() {
        let reclamo = JSON.parse(localStorage.getItem("json"));
        console.log("array de: " + reclamo.productos.length);
        for (let i = 0; i <= reclamo.productos.length - 1; i++) {
            console.log("i =" + i);
            console.log("cantidad total " + reclamo.productos[i].cantidad_total);
            let totalDictamen
                    = Number(reclamo.productos[i].cant_tema_comercial)
                    + Number(reclamo.productos[i].infundados)
                    + Number(reclamo.productos[i].infundados);
            console.log("total dictamen " + totalDictamen);

        }
    }

    static reclamoPut() {
        //input vacios
        //numeros positivos
        //dictamen
        let inputList = [];

        let fecha_emision = document.querySelector("#fecha_emision").value;
        if (!fecha_emision || 0 === fecha_emision.length) {
            inputList.push("Fecha Emision");
        }

        let id_cliente = document.querySelector("#cliente_id").value;
        if (!id_cliente || 0 === id_cliente.length) {
            inputList.push("Cliente");
        }
        
        let fecha_recepcion = document.querySelector("#fecha_recepcion").value;
        if (!fecha_recepcion || 0 === fecha_recepcion.length) {
            inputList.push("Fecha de recepcion");
        }
        
        if (inputList.length < 1) {
            Mvc.sendToServer("json");
        } else {
            Notificaciones.comboNUll(inputList[0])
        }
        

    }

}

