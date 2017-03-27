/**
 * Created by Marlon Olaya on 19/03/2017.
 */
app.controller("repCtrl",
    function ($scope, $rootScope, restSrv, crudSrv, regSrv, $timeout) {
    $scope.monthSpanish = [
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        ];

    $scope.listMonthsType = restSrv.getSearchMonths();
    $scope.selectedMonth = $scope.listMonthsType[moment().month()];

    $scope.cambiarMes = function(mes, word){
        var user = {};
        var date = moment().month(mes).format("YYYY-MM");
        console.log("CAMBIO DE MES: "+date);
        if(word === undefined || word === null) {
           restSrv.getUsuariosReportMes(date).then(function (response) {
                $scope.reportViews = response.data;
                $scope.actualMonth = $scope.monthSpanish[mes];
            });
        }else{
            restSrv.getUsr(word).then(function(response){
                user = response.data;
                console.log("USER Obtained: "+user[0].id);
                restSrv.getUsuarioReport(user[0].id, date).then(function(response){
                    $scope.reportViews = [ response.data ];
                    $scope.actualMonth = $scope.monthSpanish[mes];
                    console.log( JSON.stringify($scope.resultViews) );
                });
            });

        }
    }

    $scope.actualMonth= getActualMes();

    function getActualMes(){
        var today = moment().tz("America/Bogota").format("YYYY-MM-DD");
        return $scope.monthSpanish[moment(today).month()];
    }

    $scope.getUser = function(id, mes){
        restSrv.getUsuarioReport(id, mes).then(function (response) {
            $scope.reportView = response.data;
        });
    }

    restSrv.getUsuariosReport().then(function (response) {
        $scope.reportViews = response.data;
        console.log("LOAD DATA: "+ JSON.stringify(response.data));
    });


});
app.controller('usrCtrl',
    function ($scope, $rootScope, restSrv, crudSrv, regSrv, commonService, $timeout) {
        /**
         *  Init Values
         */

        angular.element(document).ready(function () {
            $("#imgToUpload").on("change", uploadStorageFile);
        });

        restSrv.getTipos().then(function (response) {
            $scope.tipos = response.data;
        });

        restSrv.getRoles().then(function (response) {
            $scope.roles = response.data;
        });

        $scope.regActualDate =[];
        $scope.listSearchType = restSrv.getSearchTypes();
        $scope.selectedType = $scope.listSearchType[0];
        $scope.resultSearch = [];
        $scope.successMessage = '';
        $scope.listVehiculos = [];
        $scope.errorUpload = '';
        $scope.successUpload = '';
        $scope.fillAllValues = '';
        $scope.searchNull = false;


        /* END Init Values */

        /**
         * Funciones Modal
         */

        $scope.resetForm = function () {
            $scope.modalTitle = "Registra un nuevo usuario!!!";
            resetForm();
        }

        $scope.vehiculosRelease = function () {
            $scope.listVehiculos = [];
            $scope.validator = false;
        }

        $scope.vehiculosPush = function () {
            if(!validatorPush()){
                return false;
            }
            var row = {
                placa: $scope.placa,
                ccModelo: $scope.ccModelo,
                tiemposNPuertas: $scope.tiemposNPuertas,
                imagen: $scope.imagen,
                tipo: $scope.tipoModal
            };
            $scope.listVehiculos.push(row);
            console.log("Push Vehiculos " + JSON.stringify($scope.listVehiculos));
            resetPushValues();
        }

        $scope.previewLoadImage = function (data, user) {
            if (data.tipo.nombre != "BICICLETA") {
                $scope.showModalPw = true;
                if (data.tipo.nombre != "MOTO") {
                    $scope.modalPwLblCcModelo = "Modelo";
                    $scope.modalPwLblTiemposNPuertas = "# Puertas";
                } else {
                    $scope.modalPwLblCcModelo = "Cilindraje";
                    $scope.modalPwLblTiemposNPuertas = "# Tiempos";
                }
            }
            $scope.modalPwData = {
                user: user,
                data: data
            };
/*            $scope.modalPwPlaca = data.placa;
            $scope.modalPwCcModelo = data.ccModelo;
            $scope.modalPwTiemposNPuertas = data.tiemposNPuertas;
            $scope.modalPwImage = data.imagen;
            $scope.modalPwTitle = "Imagen de " + data.tipo.nombre;
*/
        }

        $scope.loadData = function (x) {
            $scope.validator = true;
            $scope.usrId = x.id;
            $scope.rolModal = x.rol;
            $scope.nombres = x.nombres;
            $scope.cedula = x.cedula;
            $scope.apellidos = x.apellidos;
            $scope.tipoModal = x.vehiculos[0].tipo;
            $scope.listVehiculos = x.vehiculos;
            $scope.modalTitle = "Editar el usuario " + x.nombres + "...";
            $("#customModal").modal("toggle");
        }

        $scope.search = function () {
            var searchWord = $scope.searchWord;
            var tipo = $scope.selectedType.value;
            console.log("Search type: "+ tipo);
            crudSrv.search(searchWord, tipo).then(function (response) {
                $scope.resultSearch = response.data;
                $scope.searchNull = false;
                if($scope.resultSearch.length == 0){
                    $scope.searchNull = true;
                }

            });
        };

        /**
         * Funciones CRUD Usuario
         */
        $scope.submit = function () {
            if (!$scope.validator)
                return false;

            var usr = {
                nombres: $scope.nombres,
                apellidos: $scope.apellidos,
                cedula: $scope.cedula,
                vehiculos: $scope.listVehiculos,
                rol: {id: $scope.rolModal.id}
            };
            console.log("Submit usr id: " + $scope.usrId + " data: " + JSON.stringify(usr));
            if ($scope.usrId == 0) {
                crudSrv.createUsr(usr).then(function (response) {
                    console.log("Something");
                    resetForm();
                    $scope.successMessage = "Usuario registrado correctamente! -> " + JSON.stringify(response.data);
                    /*Reload Table*/
                    restSrv.getUsuarios().then(function (response) {
                        $scope.resultSearch = response.data;
                    });
                });
            } else {
                crudSrv.updateUsr(usr, $scope.usrId).then(function (response) {
                    resetForm();
                    $scope.successMessage = "Usuario actualizada correctamente! -> " + JSON.stringify(response.data);
                    /*Reload Table*/
                    restSrv.getUsuarios().then(function (response) {
                        $scope.resultSearch = response.data;
                    });
                });
            }

            $("#customModal").modal("toggle");
            $scope.validator = false;
            $('#succes-alert').show();
            $timeout(function () {
                $("#success-alert").alert("close");
            }, 5000);
        }

        $scope.removeUsr = function (id) {
            crudSrv.removeUsr(id).then(function (response) {
                $scope.successMessage = "Se eliminó exitosamente el usuario con id: " + id;
                restSrv.getUsuarios().then(function (response) {
                    $scope.resultSearch = response.data;
                });
            });
            $('#succes-alert').show();
            $timeout(function () {
                $scope.successMessage = "";
                $("#success-alert").alert("close");
            }, 5000);
        }

        /**
         * Funcion para cargar imagen en servidor
         */
        function uploadStorageFile() {
            var data = new FormData();
            data.append('file', $("#imgToUpload")[0].files[0]);
            $.ajax({
                url: "/file/upload",
                type: "POST",
                data: data,
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                cache: false,
                success: function (response) {
                    // Handle upload success
                    console.log("File succesfully uploaded: " + response);
                    $scope.imagen = response;
                },
                error: function () {
                    // Handle upload error
                    console.log(
                        "File not uploaded (perhaps it's too much big)");
                    $scope.imagen = undefined;
                }
            });
        }

        /**
         * Funciones generales
         */
        $scope.getLblCcModelo = function (x) {
            if (x && x.nombre === "MOTO") {
                return "Cilindraje";
            }
            return "Modelo";
        }

        $scope.getLblTiemposNPuertas = function (x) {
            if (x && x.nombre === "MOTO") {
                return "Tiempos";
            }
            return "# Puertas";
        }

        function validatorPush(){
            if ($scope.tipoModal && $scope.tipoModal.nombre != "BICICLETA") {
                if ($scope.tiemposNPuertas === null || $scope.tiemposNPuertas === undefined
                    || $scope.placa === null || $scope.placa === undefined
                    || $scope.ccModelo === null || $scope.ccModelo === undefined) {
                    $scope.fillAllValues = "Todos los campos con * son obligatorios!";
                    $scope.validator = false;
                    return false;
                }

            }
            return true;
        }

        function resetPushValues(){
            $scope.placa = "";
            $scope.ccModelo = "";
            $scope.tiemposNPuertas = "";
            $scope.imagen = "";
            $scope.validator = true;
            $("#imgToUpload").val("");

        }

        function resetForm() {
            $scope.imagen = "";
            $scope.nombres = "";
            $scope.apellidos = "";
            $scope.tipoModal = "";
            $scope.rolModal = "";
            $scope.usrId = "0";
            $scope.cedula = "";
            $("#imgToUpload").val("");
            $scope.listVehiculos = [];
        }

        $scope.dateTimeConversor = function(x){
            console.log("DATE: x "+x+" -> "+moment(x).tz("America/Bogota").format('YYYY-MM-DD HH:mm') );
            return moment(x).tz("America/Bogota").format('YYYY-MM-DD HH:mm');

        }

        /**
         * Funciones Registros Crud
         */

        $scope.regIngreso = function(user, celda){
            var reg = {
                usuario : { id : user.id },
                celda : celda
            }
            regSrv.createReg(reg).then(function(response){
               $scope.successMessage = "Registro de Ingreso realizado con satisfacción: " + JSON.stringify(response.data);
                /*Reload Table
                restSrv.getUsuarios().then(function (response) {
                    $scope.resultSearch = response.data;
                });
                 */
                $scope.resultSearch = "";
            });
            $("#registerModal").modal("toggle");
            $('#succes-alert').show();
            $timeout(function () {
                $scope.successMessage = "";
                $("#success-alert").alert("close");
            }, 5000);
        }

        $scope.regSalida = function(rg, id){
            var reg = {
                usuario : { id : id },
                ingreso : rg.ingreso,
                celda : rg.celda,
                fecha : rg.fecha
            }
            console.log("registro: "+rg.id);
            regSrv.updateReg(reg, rg.id).then(function(response){
                $scope.successMessage = "Registro de Salida realizado con satisfacción: " + JSON.stringify(response.data);
                /*Reload Table
                restSrv.getUsuarios().then(function (response) {
                    $scope.resultSearch = response.data;
                });
                 */

            });
            $("#registerModal").modal("hide");
            $scope.resultSearch = "";
            $('#succes-alert').show();
            $timeout(function () {
                $scope.successMessage = "";
                $("#success-alert").alert("close");
            }, 5000);
        }

        $scope.regSubmit = function(reg, user){
            console.log("REGISTRANDO: "+JSON.stringify(user));
            if(user.registro != null && user.registro != undefined){
                if(user.registro.id != null && user.registro.id != undefined){
                    $scope.regSalida(reg, user.id);
                }
                else{
                    $scope.regIngreso(user,$scope.modalRgData.regCelda);
                }
            }
            $('#succes-alert').show();
            $timeout(function () {
                $scope.successMessage = "";
                $("#success-alert").alert("close");
            }, 5000);

        }

        $scope.searchRegistro = function(){
            var searchWord = $scope.searchWord;
                var tipo = $scope.selectedType.value;
                console.log("Search type: "+ tipo);
            regSrv.search(searchWord, tipo).then(function (response) {
                $scope.regResultSearch = response.data;
            });
        };

        $scope.registerModal = function (user) {
            var existeRegistro = false;
            var registro ={};
            var disableSalida = true;
            if(user.registro != undefined && user.registro != null && user.registro.length >0){
                console.log("Validating Today");
                registro = user.registro[user.registro.length - 1];
                existeRegistro = $scope.regTodayValidator(registro);
            }
            var disabled = false;
            var title = "ingreso";
            var regActualDate = '';
            var regCelda = '';
            var regId = '0';
            if(existeRegistro){
                console.log("Hay registros");
                disabled = true;
                title = "salida";
                regActualDate = $scope.regActualDate.actual;
                regCelda = $scope.regActualDate.celda;
                regId = $scope.regActualDate.id;
                disableSalida = false;
                if(registro.salida != null && registro.salida != undefined){
                    disableSalida = true;
                }
            }
            var modalRgData ={
                title: title,
                disabled : disabled,
                regActualDate : regActualDate,
                regCelda : regCelda,
                regId : regId,
                user: user,
                reg : registro,
                disableSalida : disableSalida
            }
            console.log("OPEN REG MODAL: "+JSON.stringify(modalRgData));
            $scope.modalRgData = modalRgData;
            $("#registerModal").modal("toggle");
        }

        $scope.regTodayValidator = function(rg) {
            var reg = moment(rg.ingreso).tz('America/Bogota').format('YYYY-MM-DD');
            if (moment().diff(reg, "days") == 0) {
                $scope.regActualDate = {
                        actual : moment(rg.ingreso).tz('America/Bogota').format('YYYY-MM-DD HH:mm:ss'),
                        id: rg.id,
                        celda : rg.celda
                    };
                return true;
            }
            return false;
        }

    });


