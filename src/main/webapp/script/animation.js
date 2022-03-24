$(document).ready(function ()
{
    $(".cover").hover(
        function ()
        {
            $(".cover").animate({opacity: '0.5'}, "fast");
            $(this).animate({opacity: '1'});
        },
        function (){
            $(".cover").animate({opacity: '1'}, "fast");
        }
    );
});