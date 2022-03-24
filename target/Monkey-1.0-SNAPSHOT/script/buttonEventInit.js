$(document).ready(function(){
    $("#random-button").click(function(){
        $.get('RandomSoftware', function(resp){
            printGame(resp);
        })
            .fail(function(){
                alert("Richiesta fallita");
            });
    });
});