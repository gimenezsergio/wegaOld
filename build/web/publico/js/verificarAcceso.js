class MiAcceso {

    static exitSystemBtn() {
        document.querySelector("#exit").setAttribute("onclick", "MiAcceso.exitSystem()");
    }

    static exitSystem() {
        document.forms["exit"].submit();
    }

    static pedir(user, pass) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "../AccessLogin");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    if (xhr.responseText.indexOf("<!-- mjsError de error -->") > -1) {
                        humane.log(xhr.responseText, {addnCls: 'humane-flatty-error'});
                        // humane.log(xhr.responseText);
                        //humane.log("xhr.responseText");

                    } else {

                        humane.log("Ingreso: " + xhr.responseText, {addnCls: 'humane-flatty-success'});
                        document.querySelector('#contenedorPrincipal').innerHTML = xhr.responseText;
                    }
                    console.log(xhr.responseText);


                } else {
                    //

                }

            }


        };
        let parametrosEnviar = {};
        parametrosEnviar.user = user;
        parametrosEnviar.pass = pass;
        xhr.send(JSON.stringify(parametrosEnviar));
    }

    static selectInput() {
        let user = document.querySelector("#inputUser").value;
        let pass = document.querySelector("#inputPassword").value;
        MiAcceso.pedir(user, pass);
    }

    static init() {
        document.querySelector("#btnVerificar").setAttribute("onclick", "MiAcceso.selectInput()");

    }

}



