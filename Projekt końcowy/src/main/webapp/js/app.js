$(function(){

    var buttons = $("button");
    var pushed = [];
    var trs = $("tr");

    var clicks = 0;
    var gameTime = 0;

    buttons.each(function(index, element) {
        pushed.push(true);
        $(element).css("background-color", "green");
    });

    buttons.each(function(index, element){

        var tr = trs.eq(index);
        var tds = tr.children();
        var len = tds.length;

        $(element).on("click", function(){
            change(index);
            for(var i = 0; i < len; i++){
                change(parseInt(tds.eq(i).text()))
            }
        });

        $(element).click();//klikanie każdego, inaczej kodowanie gry :)
        //musi się wydażyć przed sprawdzaniem warunku wygranej

        $(element).on("click", function(){
            incrementCounter();//zwiększenie licznika i wyświetlenie go na stronie
            if(checkWinCondition()){
                redirectGame();//creating form and sending it by post to /game
            }
        });

    });

    setInterval(function(){incrementGameTime();}, 1000);

    $("table").remove();

    if(checkWinCondition()){ //in case game was not well coded;
        buttons.eq(0).click();
        clicks = -1;
        incrementCounter();
        gameTime = 0;
    }

//Takie tam funkcje

    function change(index){
        if(pushed[index] === true){
            buttons.eq(index).css("background-color", "red");
            pushed[index] = false;
        }else{
            buttons.eq(index).css("background-color", "green");
            pushed[index] = true;
        }
    }

    function checkWinCondition(){
        for(var i = 0; i < pushed.length; i++){
            if(pushed[i] === false){
                return false;
            }
        }
        return true;
    }

    function incrementCounter(){
        var div = $("#counter");
        clicks++;
        div.text("Kliknięcia: " + clicks);
    }

    function incrementGameTime(){
        var div = $("#timer");
        gameTime++;

        var secs = gameTime % 60;
        var minutes = (gameTime - secs) / 60;

        if(secs < 10){
            secs = "0" + secs;
        }
        if(minutes < 10){
            minutes = "0" + minutes;
        }

        div.text("Czas gry: " + minutes + ":" + secs);
    }

    function redirectGame(){
        var form = $(
            "<form action='/game' method='post' style='display: none;'>" +
                "<input type='number' name='moves' value='" + clicks + "'/>" +
                "<input type='number' name='time' value='" + gameTime + "'/>" +
                "<input type='submit' id='goToGame'/>" +
            "</form>"
        );
        $("body").append(form);

        var toClick = $("#goToGame");
        toClick.click();
    }
});

