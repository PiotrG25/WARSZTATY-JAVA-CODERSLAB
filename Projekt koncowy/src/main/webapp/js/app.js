$(function(){

    var buttons = $("#game .buttonInGame");
    var enemyButtons = $("#enemyGame .buttonInGame");

    var pushed = [];
    var enemyPushed = [];

    var trs = $("tr");
    var giveUpButton = $("#giveUp");

    var clicks = 0;
    var gameTime = 0;

    var pseudoClick = [];

    buttons.each(function(index, element){
        pushed.push(true);
        $(element).css("background-color", "green");
        enemyPushed.push(true);
        enemyButtons.eq(index).css("background-color", "green");
    });

    buttons.each(function(index, element){

        var tr = trs.eq(index);
        var tds = tr.children();
        var len = tds.length;

        pseudoClick.push(function(){
            changeEnemyButton(index);
            for(var i = 0; i < len; i++){
                changeEnemyButton(parseInt(tds.eq(i).text()))
            }
        });
        $(element).on("click", function(){
            changeButton(index);
            for(var i = 0; i < len; i++){
                changeButton(parseInt(tds.eq(i).text()))
            }
        });

        $(element).click();//klikanie każdego, inaczej kodowanie gry :)
        pseudoClick[index]();
        //musi się wydażyć przed sprawdzaniem warunku wygranej

        if(checkWinCondition()){
            buttons.eq(0).click();
            pseudoClick[0]();
        }

        $(element).on("click", function(){
            incrementCounter();//zwiększenie licznika i wyświetlenie go na stronie
            if(checkWinCondition()){
                redirectGame();//creating form and sending it by post to /game
            }
            monkeyClick();
        });
    });

    setInterval(function(){incrementGameTime();}, 1000);

    $("table").remove();

    giveUpButton.click(function(){
        giveUp();
    });

//Takie tam funkcje

    function monkeyClick(){
        var rand = parseInt(Math.random() * enemyButtons.length);
        console.log(rand);
        pseudoClick[rand]();
        if(checkMonkeyWinCondition()){
            monkeyRedirect();
        }
    }

    function changeButton(index){
        if(pushed[index] === true){
            buttons.eq(index).css("background-color", "red");
            pushed[index] = false;
        }else{
            buttons.eq(index).css("background-color", "green");
            pushed[index] = true;
        }
    }
    function changeEnemyButton(index){
        if(enemyPushed[index] === true){
            enemyButtons.eq(index).css("background-color", "red");
            enemyPushed[index] = false;
        }else{
            enemyButtons.eq(index).css("background-color", "green");
            enemyPushed[index] = true;
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
    function checkMonkeyWinCondition(){
        for(var i = 0; i < enemyPushed.length; i++){
            if(enemyPushed[i] === false){
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
    function monkeyRedirect(){
        var form = $(
            "<form action='/main'>" +
                "<input type='submit' id='goToMain'/>" +
            "</form>"
        );
        $("body").append(form);

        var toClick = $("#goToMain");
        toClick.click();
    }

    function giveUp(){
        monkeyRedirect();
    }
});

