var restMod = angular.module("restMod", []);
var crudMod = angular.module("crudMod", []);
var regMod = angular.module("regMod", []);

var app = angular.module('usrApp',['restMod', 'crudMod', 'regMod']).run(function($rootScope) {
    $rootScope.usrId = 0;
});

app.constant('urls', {
    BASE: 'http://localhost:8080/',
    USR_SERVICE_API : 'http://localhost:8080/usuarios/',
    FILE_SERVICE_API : 'http://localhost:8080/file/',
    REG_SERVICE_API : 'http://localhost:8080/registros/',
    REP_SERVICE_API : 'http://localhost:8080/reports/'
});


