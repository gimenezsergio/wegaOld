class Notificaciones {

    static PrecargaGuardar(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "Ok",
                                value: "Ok",
                            }
                        }

            }).then((value) => {

                if (value === "Ok") {
                    window.location.href = 'precarga.html';
                }
                if (value === null) {
                    console.log("null");
                }
            });

        }

        console.log(mybuttons);

    }

    static informeGerenError(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "warning",
            title: title,
            buttons: {
                //cancel: true,
                confirm: "Confirm"

            },
        })



    }

    static draftSaved() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: "Borrador guardado",
            buttons: {
                //cancel: true,
                confirm: "Ok"
            },
        })
    }

    static refrescarDatosOK() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: "Datos de los casilleros actualizados",
            buttons: {
                //cancel: true,
                confirm: "Ok"

            },
        })
    }

    static ReclamoBusqueda(respServer) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: respServer.mensaje,
            icon: respServer.tipo,
            html: true
        });
    }

    static ReclamoAprobar(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al aprobar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al aprobar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "Últimos",
                                value: "ultimos",
                            },
                            nuevoReclamo: {
                                text: "Menu",
                                value: "Menu",
                            },
                            buscar: {
                                text: "Buscar",
                                value: "buscar",
                            },

                        }

            }).then((value) => {

                if (value === "Menu") {
                    window.location.href = 'principal.html';
                }
                if (value === null) {
                    console.log("null");
                }
                if (value === "ultimos") {
                    window.location.href = 'clienteGenerico.html';
                }
                if (value === "buscar") {
                    window.location.href = 'busquedaReclamos.html';
                }
            });

        }

        console.log(mybuttons);

    }

    static closeCarga(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "OK",
                                value: "Ok",
                            }
                        }

            }).then((value) => {
                if (value === null) {
                    console.log("null");
                }
                if (value === "Ok") {
                    //window.location.href = 'clienteGenerico.html';
                    Precarga.pedirListado();
                    Ingresos.closeCarga();
                }
            });

        }

        console.log(mybuttons);

    }

    static packagingAndPlanDiseno(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "OK",
                                value: "Ok",
                            }
                        }

            }).then((value) => {
                if (value === null) {
                    console.log("null");
                }
                if (value === "Ok") {
                    //window.location.href = 'clienteGenerico.html';
                    Ingresos.closePackaging();
                    Ingresos.closeDiseno();
                }
            });

        }

        console.log(mybuttons);

    }

    static ReclamoGuardar(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "Informe",
                                value: "Informe",
                            },
                            nuevoReclamo: {
                                text: "Nuevo Reclamo",
                                value: "nuevoReclamo",
                            },
                        }

            }).then((value) => {

                if (value === "nuevoReclamo") {
                    window.location.href = 'reclamos.html';
                }
                if (value === null) {
                    console.log("null");
                }
                if (value === "Informe") {
                    window.location.href = 'clienteGenerico.html';
                }
            });

        }

        console.log(mybuttons);

    }

    static abmProductoFamilia(respServer) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: respServer.mensaje,
            icon: respServer.tipo,
            html: true,

        })
                ;
    }

    static abmProductoOk2(respServer) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: respServer.mensaje,
            icon: respServer.tipo,
            html: true,

        })
                ;
    }

    static informeTecnicoGuardar(respServer) {
        let myIcon;
        let mybuttons = {};
        let roll = {};
        let nuevoReclamo = {};

        if (respServer.tipo == "warning") {
            console.log("ERROR al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                title: respServer.mensaje,
                icon: respServer.tipo,
            });


        } else {
            console.log("OK al guardar");
            swal({
                closeOnClickOutside: false,
                closeOnEsc: false,
                icon: respServer.tipo,
                //title: "Reclamo guardado",
                title: respServer.mensaje,
                buttons:
                        {
                            //cancel: true,
                            //confirm: "Confirm",
                            roll: {
                                text: "Listado",
                                value: "Listado",
                            },
                            nuevoReclamo: {
                                text: "Nuevo Informe",
                                value: "nuevoInforme",
                            },
                        }

            }).then((value) => {

                if (value === "nuevoInforme") {
                    window.location.href = 'informeClienteParticular.html';
                }
                if (value === null) {
                    console.log("null");
                }
                if (value === "Listado") {
                    window.location.href = 'InformeClienteParticularUltimos.html';
                }
            });

        }

        console.log(mybuttons);

    }
    //FAMILIAS
    static abmFamiliaNewNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "El nuevo nombre no puede estar vacio",
            icon: "warning",
        });
    }

    static abmFamiliaOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Familias",
                    value: "Familias",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Familias") {
                window.location.href = 'familias.html';
            }
        });
        ;
    }

    //FALLA DE MATERAILES
    static abmFallaMatNewNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "El nuevo nombre no puede estar vacio",
            icon: "warning",
        });
    }
    static abmFallaMatSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre de la Falla de Materiales",
            icon: "warning",
        });
    }

    static abmFallaMatOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Falla de Material",
                    value: "FallaMat",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "FallaMat") {
                window.location.href = 'falla_material.html';
            }
        });
        ;
    }

    //PROCESO

    static abmProcesoSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre del proceso",
            icon: "warning",
        });
    }

    static abmProcesoOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Proceso",
                    value: "Proceso",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Proceso") {
                window.location.href = 'proceso.html';
            }
        });
        ;
    }

    //DISENO

    static abmDisenoNewNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "El nuevo nombre no puede estar vacio",
            icon: "warning",
        });
    }

    static abmDisenoSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre del diseño",
            icon: "warning",
        });
    }

    static abmDisenoOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Diseno",
                    value: "Diseno",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Diseno") {
                window.location.href = 'diseno.html';
            }
        });
        ;
    }

    //PROVEEDORES

    static abmProveedorOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Proveedores",
                    value: "Proveedores",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Proveedores") {
                window.location.href = 'proveedores.html';
            }
        });
        ;
    }

    static abmProveedorSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre del proveedor",
            icon: "warning",
        });
    }

    static abmProveedorNewNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "El nuevo nombre no puede estar vacio",
            icon: "warning",
        });
    }

    //ABM PRODUCTOS
    static abmProductoNewNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "El nuevo nombre no puede estar vacio",
            icon: "warning",
        });
    }

    static abmProductoSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre del producto",
            icon: "warning",
        });
    }

    static abmProductoNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Ingrese el nombre del producto",
            icon: "warning",
        });
    }

    static abmProductoOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Productos",
                    value: "Productos",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Productos") {
                window.location.href = 'productos.html';
            }
        });
        ;

    }

    static abmProductoError(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "warning",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Productos",
                    value: "Productos",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Productos") {
                window.location.href = 'productos.html';
            }
        });
        ;

    }

    static abmProductoFamiliaOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Productos",
                    value: "Productos",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Productos") {
                window.location.href = 'productosFamilias.html';
            }
        });
        ;

    }

    // AMB CLIENTE

    static abmClienteOK(title) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            icon: "success",
            title: title,
            buttons: {
                //cancel: true,
                //confirm: "Confirm",
                menu: {
                    text: "Menu",
                    value: "Menu",
                },
                nuevoReclamo: {
                    text: "Clientes",
                    value: "Clientes",
                },
            },
        }).then((value) => {

            if (value === "Menu") {
                window.location.href = '../principal.html';
            }

            if (value === "Clientes") {
                window.location.href = 'cliente.html';
            }
        });
        ;

    }

    static abmClienteNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Ingrese el nombre del cliente",
            icon: "warning",
        });
    }

    static abmClienteSelectNull() {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "Seleccione el nombre del cliente",
            icon: "warning",
        });
    }

    //GENERAL

    static comboNUll(inputID) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: inputID + " no puede estar vacio",
            icon: "warning",
        });

    }

    /// Aviso de que el dato ingresado a mano en el datalist no existe en el listado.
    static registroComboInexistente(nombreCombo, inputText) {
        swal({
            closeOnClickOutside: false,
            closeOnEsc: false,
            title: "'" + inputText + "'" + " no existe en: " + nombreCombo,
            icon: "warning",
        });
    }

}