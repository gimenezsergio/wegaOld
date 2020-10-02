/* global input */

extras = {};
extras.limpiarInputs = function (elSelectorDeInputs) {
    var listadoInputs = document.querySelectorAll(elSelectorDeInputs);
    for (var i = 0; i < listadoInputs.length; i++) {
        listadoInputs[i].value = "";
    }
};
extras.cargar_fecha = function () {


    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();


    if (mm < 10) {
        mm = '0' + mm;
    }


    if (dd < 10) {
        dd = '0' + dd;
    }

    today = yyyy + '-' + mm + '-' + dd;
    document.querySelector("#fecha_actual").value = today;
};

class Dataselect {
    //localStorageGetItem: Nombre del JSON en localStorage
    //toSearchSelectorDOM: ID en el DOM para seleccionar item a buscar
    //inputDestination: ID en el DOM para donde va el codigo(id)
    //JsonKeyLocalStorage: Nombre del key en el localStorage
    //key
    static itemSelected(localStorageGetItem, toSearchSelectorDOM, inputDestination, JsonKeyLocalStorage) {
        console.log("inputDestination: " + inputDestination);
        console.log("toSearchSelectorDOM: " + toSearchSelectorDOM);

        let key = "";
        let object = JSON.parse(localStorage.getItem(localStorageGetItem));
        let results = [];
        let toSearch = document.querySelector(toSearchSelectorDOM).value;

        if (Dataselect.toSearchNUll(toSearch) === null) {
            console.log("Null 1" + toSearch);
            Notificaciones.comboNUll(localStorageGetItem);
            document.querySelector(inputDestination).value = "";
        } else {
            console.log("Null 2" + toSearch);
            let flag = false;


            //Verificar todos los datalist
            if (localStorageGetItem == "clientes") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].nombre == toSearch) {
                        console.log("El ID de " + object[i].nombre + " es: " + object[i].id_cliente);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id_cliente;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("cliente", toSearch);
                }
            } else if (localStorageGetItem == "productos") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].codigo_producto == toSearch) {
                        console.log("El ID de " + object[i].codigo_producto + " es: " + object[i].id_producto);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id_producto;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("producto", toSearch);
                }
            } else if (localStorageGetItem == "disenos") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].dis_nombre == toSearch) {
                        console.log("El ID de " + object[i].dis_nombre + " es: " + object[i].dis_id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].dis_id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("diseño", toSearch);
                }
            } else if (localStorageGetItem == "fallaMateriales") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].falla_mat_nombre == toSearch) {
                        console.log("El ID de " + object[i].falla_mat_nombre + " es: " + object[i].falla_mat_id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].falla_mat_id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("falla de materiales", toSearch);
                }
            } else if (localStorageGetItem == "origen") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].origen == toSearch) {
                        console.log("El ID de " + object[i].origen + " es: " + object[i].id_origen);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id_origen;
                    }

                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("origen", toSearch);
                }
            } else if (localStorageGetItem == "procesos") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].proc_nombre == toSearch) {
                        console.log("El ID de " + object[i].proc_nombre + " es: " + object[i].proc_id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].proc_id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("origen", toSearch);
                }
            } else if (localStorageGetItem == "proveedores") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].prov_nombre == toSearch) {
                        console.log("El ID de " + object[i].prov_nombre + " es: " + object[i].prov_id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].prov_id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("proveedores", toSearch);
                }
            } else if (localStorageGetItem == "resultados") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].resultado == toSearch) {
                        console.log("El ID de " + object[i].resultado + " es: " + object[i].id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("resultado", toSearch);
                }
            } else if (localStorageGetItem == "verificacion") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].tipo == toSearch) {
                        console.log("El ID de " + object[i].tipo + " es: " + object[i].id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("verificación", toSearch);
                }
            } else if (localStorageGetItem == "familia") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].nombre == toSearch) {
                        console.log("El ID de " + object[i].nombre + " es: " + object[i].id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("familia", toSearch);
                }
            } else if (localStorageGetItem == "subfamilia") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].nombre == toSearch) {
                        console.log("El ID de " + object[i].nombre + " es: " + object[i].id);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].id;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("subfamilia", toSearch);
                }
            } else if (localStorageGetItem == "arribo") {
                for (let i = 0; i < object.length; i++) {
                    if (object[i].nombre == toSearch) {
                        console.log("El ID de " + object[i].nombre + " es: " + object[i].arriboId);
                        flag = true;
                        document.querySelector(inputDestination).value = object[i].arriboId;
                    }
                }
                if (!flag) {
                    Notificaciones.registroComboInexistente("arribo", toSearch);
                }

            } else {
                console.log("El combo " + localStorageGetItem + " no esta actualizando el ID.");
            }
        }


    }

    static toSearchNUll(search) {
        if (!search || 0 === search.length) {
            console.log("Dato de combo esta vacio: " + search);
            return null;
        } else {
            console.log("Dato de combo es: " + search);
        }
    }

}

class SpinnerModal {
    static open() {
        document.getElementById('myModal').style.display = 'block';
    }

    static close() {
        document.getElementById('myModal').style.display = 'none';
    }
}