$(document).ready(function() {
    $('#title').focus();
    $('#loading').hide();
});

function showMe() {
    $('#loading').show();
};

$(function() {
    $( '#accordion').accordion({
        collapsible: true
    });
});