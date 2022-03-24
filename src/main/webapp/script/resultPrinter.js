function printGame(json){
    $.each(json, function(prop, value){
        $('#random-cover').empty().append('<form class="cover-form" action="DisplayInfo" method="POST" ' +
            'name="Display"> <input type="hidden" name="id" value=' + value + '> ' +
            '<input id="random-image" style="border-radius: 30px;" type="image" width="100%" ' +
            'src="copertine/' + value + '.png" alt="Submit"> </form>');
        if(prop == codice)
            return false;
    });
};