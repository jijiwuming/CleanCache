var exec = require('cordova/exec');

exports.CleanCache = function(arg0, success, error) {
    exec(success, error, "CleanCache", "CleanCache", [arg0]);
};
