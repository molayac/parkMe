/**
 * Created by Marlon Olaya on 20/03/2017.
 */
restMod.constant('urls', {
    BASE: 'http://localhost:8080/',
    USR_SERVICE_API : 'http://localhost:8080/usuarios/',
    REP_SERVICE_API : 'http://localhost:8080/reports/'
});

restMod.factory('restSrv', ['$http', 'urls', function ($http, urls) {

    var restSrv = {};

    restSrv.getUsuarios = function () {
        return $http.get(urls.USR_SERVICE_API);
    };

    restSrv.getUsuariosReport = function (){
        return $http.get(urls.REP_SERVICE_API);
    };

    restSrv.getUsuarioReport = function (id, search){
        var config = {
            params: {
                "ingreso": search
            }
        };
        return $http.get(urls.REP_SERVICE_API+id, config);
    };

    restSrv.getUsr= function(search){
        var config = {
            params: {
                "word": search
            }
        };
        return $http.get(urls.USR_SERVICE_API + "searchByCedula", config);
    }

    restSrv.getUsuariosReportMes = function (search){
        var config = {
            params: {
                "ingreso": search
            }
        };
        return $http.get(urls.REP_SERVICE_API, config);
    };

    restSrv.getSearchTypes = function(){
      return [{value: "cedula", text: "CEDULA"}, {value: "placa", text: "PLACA"}];
    };

    restSrv.getSearchMonths = function(){
        return [{id: "0", text: "Enero"}, {id: "1", text: "Febrero"},
            {id: "2", text: "Marzo"}, {id: "3", text: "Abril"},
            {id: "4", text: "Mayo"}, {id: "5", text: "Junio"},
            {id: "6", text: "Julio"}, {id: "7", text: "Agosto"},
            {id: "8", text: "Septiembre"}, {id: "9", text: "Octubre"},
            {id: "10", text: "Noviembre"}, {id: "11", text: "Diciembre"}
        ];
    };

    restSrv.getTipos = function () {
        return $http.get(urls.USR_SERVICE_API + 'tipos');
    };

    restSrv.getRoles = function () {
        return $http.get(urls.USR_SERVICE_API + 'roles');
    };

    return restSrv;

}]);

crudMod.constant('urls', {
    BASE: 'http://localhost:8080/',
    USR_SERVICE_API: 'http://localhost:8080/usuarios/',
    REP_SERVICE_API : 'http://localhost:8080/reports/'

});

crudMod.factory('crudSrv',
    ['$http', '$q', 'urls',
        function ($http, $q, urls) {

            var factory = {
                getUsr:getUsr,
                createUsr: createUsr,
                updateUsr: updateUsr,
                removeUsr: removeUsr,
                search: search

            };
            return factory;

            function getUsr(search){
                var config = {
                    params: {
                        "word": search
                    }
                };
                return $http.get(urls.USR_SERVICE_API + "searchByCedula", config);
            }

            function search(searchWord, searchType) {
                var config = {
                    params: {
                        "word": searchWord,
                        "type": searchType
                    }
                };
                return $http.get(urls.USR_SERVICE_API + "search", config);
            }

            function createUsr(usr) {
                console.log('Creating Ave');
                return $http.post(urls.USR_SERVICE_API, usr);
            }

            function updateUsr(usr, id) {
                console.log('Actualizar datos del ave con id: ' + id);
                return $http.put(urls.USR_SERVICE_API + id, usr);
            }

            function removeUsr(id) {
                console.log('Eliminando Ave con id: ' + id);
                return $http.delete(urls.USR_SERVICE_API + id);
            }

        }
    ]);

regMod.constant('urls', {
    BASE: 'http://localhost:8080/',
    REG_SERVICE_API: 'http://localhost:8080/registros/'

});

regMod.factory('regSrv',
    ['$http', '$q', 'urls',
        function ($http, $q, urls) {

            var factory = {
                getReg:getReg,
                createReg: createReg,
                updateReg: updateReg,
                search: search

            };
            return factory;

            function getReg(id){
                return $http.get(urls.REG_SERVICE_API + id);
            }

            function search(searchWord, searchType) {
                var config = {
                    params: {
                        "word": searchWord,
                        "type": searchType
                    }
                };
                return $http.get(urls.REG_SERVICE_API + "search", config);
            }

            function createReg(reg) {
                console.log('Creating Registro IN');
                return $http.post(urls.REG_SERVICE_API, reg);
            }

            function updateReg(reg, id) {
                console.log('Actualizar datos OUT del registro con id: ' + id);
                return $http.put(urls.REG_SERVICE_API + id, reg);
            }

        }
    ]);

app.service('commonService', function ($http) {
    var info;

    return {
        getInfo: getInfo,
        setInfo: setInfo
    };

    function getInfo() {
        return info;
    }
    function setInfo(value) {
        info = value;
    }
});



