var black = false;

$(document).ready(function(){
    $("#change-background").click(function(){
        if(black) {
            $("body").css({"background-color" : "rgb(238,237,234)"});
            $(".software-info").css({"color" : "black"});
            $("#random-title").css({"color" : "black"});
            $("#change-background").css({"color" : "black"});
            black = false
        }
        else {
            $("body").css({"background-color" : "#1c1c1c"});
            $(".software-info").css({"color" : "white"});
            $("#random-title").css({"color" : "white"});
            $("#change-background").css({"color" : "white"});
            black = true;
        }
    });
});