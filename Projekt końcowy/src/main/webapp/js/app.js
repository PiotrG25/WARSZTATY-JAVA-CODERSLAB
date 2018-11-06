$(function(){

    var buttons = $("button");
    var pushed = [];
    var trs = $("tr");

    var clicks = 0;
    var gameTime = 0;
    var points = 100;//początkowa wartość dekrementowana po każdym kliknięciu

    buttons.each(function(index, element) {
        pushed.push(false);
        $(element).css("background-color", "red");
    });

    console.log(pushed.length);

    buttons.each(function(index, element){

        var tr = trs.eq(index);
        var tds = tr.children();
        var len = tds.length;

        $(element).on("click", function(){
            change(index);
            for(var i = 0; i < len; i++){
                change(parseInt(tds.eq(i).text()))
            }
            console.log("Kliknięto: " + index);
        });

        $(element).click();//klikanie każdego, inaczej kodowanie gry :)
        //musi się wydażyć przed sprawdzaniem warunku wygranej

        $(element).on("click", function(){
            incrementCounter();//zwiększenie licznika i wyświetlenie go na stronie
            decrementPoints();//zmniejszenie ilości punktów
            if(checkWinCondition()){
                alert("Wygrałeś!!!\nZdobyłeś: " + points + " punktów");//todo warynek wygranej
            }
        });

    });

    setInterval(function(){incrementGameTime();}, 1000);

    $("table").remove();

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

    function decrementPoints(){
        points--;
        if(points === 0){
            alert("przegrałeś!\n:(")
        }
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
});

